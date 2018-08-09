package com.ibm.examples.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.examples.model.Stock;
import com.ibm.examples.services.StockService;

@Repository
public class JdbcStockRepository implements StockRepository {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	StockService service;

	@Override
	public List<Stock> getStocks() {
		return service.getStocks();
	}

	@Override
	public Integer createStock(Stock stock) {
		return service.createStock(stock);
	}

	@Override
	public Optional<Stock> getStockById(int id) {
		return service.getStockById(id);
	}

	@Override
	public Optional<Stock> deleteStockById(int id) {
		return service.deleteStock(id);
	}
}
