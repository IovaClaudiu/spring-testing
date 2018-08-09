package com.ibm.examples.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.ibm.examples.MyTransactional;
import com.ibm.examples.model.Stock;

@Service
public class StockService {

	@PersistenceContext
	EntityManager manager;

	@MyTransactional
	public List<Stock> getStocks() {
		return manager.createQuery("SELECT s from Stock s", Stock.class).getResultList();
	}

	@MyTransactional
	public Optional<Stock> getStockById(int id) {
		String jpaTxt = "select s from Stock s WHERE s.id = :id";
		TypedQuery<Stock> createQuery = manager.createQuery(jpaTxt, Stock.class);
		createQuery.setParameter("id", id);
		try {
			return Optional.of(createQuery.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@MyTransactional
	@Modifying
	public Integer createStock(Stock stock) {
		Query createQuery = manager
				.createNativeQuery("INSERT INTO stock (company_name, symbol, price) VALUES (?, ?, ?)", Stock.class);
		createQuery.setParameter(1, stock.getName());
		createQuery.setParameter(2, stock.getSymbol());
		createQuery.setParameter(3, stock.getPrice());
		return createQuery.executeUpdate();
	}

	@MyTransactional
	@Modifying
	public Optional<Stock> deleteStock(int id) {
		Optional<Stock> stockById = getStockById(id);
		Query createQuery = manager.createQuery("delete from Stock s WHERE s.id = :id");
		createQuery.setParameter("id", id);
		if (createQuery.executeUpdate() > 0) {
			return stockById;
		} else {
			return Optional.empty();
		}
	}

}
