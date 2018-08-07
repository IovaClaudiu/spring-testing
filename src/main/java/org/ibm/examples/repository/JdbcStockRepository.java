package org.ibm.examples.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.ibm.examples.model.Stock;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcStockRepository implements StockRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Stock> getStocks() {
		return manager.createQuery("select s from Stock s", Stock.class).getResultList();
	}

	@Override
	public int getNumberOfStocks() {
		String jpaTxt = "select count(a.stock_id) from Stock a";
		return ((Long) manager.createQuery(jpaTxt).getSingleResult()).intValue();
	}

	@Override
	public int createStock(Stock stock) {
		Query createQuery = manager
				.createNativeQuery("INSERT INTO stock (company_name, symbol, price) VALUES (?, ?, ?)", Stock.class);
		createQuery.setParameter(1, stock.getName());
		createQuery.setParameter(2, stock.getSymbol());
		createQuery.setParameter(3, stock.getPrice());
		return createQuery.executeUpdate();
	}

	@Override
	public Optional<Stock> getStockById(int id) {
		String jpaTxt = "select s from Stock s WHERE s.id = :id";
		TypedQuery<Stock> createQuery = manager.createQuery(jpaTxt, Stock.class);
		createQuery.setParameter("id", id);
		return Optional.of(createQuery.getSingleResult());
	}

	@Override
	public Optional<Stock> deleteStockById(int id) {
		Optional<Stock> stockById = getStockById(id);
		String jpaText = "delete from Stock s WHERE s.id = :id";
		Query createQuery = manager.createQuery(jpaText);
		createQuery.setParameter("id", id);
		createQuery.executeUpdate();
		return stockById;
	}
}
