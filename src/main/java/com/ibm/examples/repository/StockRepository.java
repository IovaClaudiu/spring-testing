package com.ibm.examples.repository;

import java.util.List;
import java.util.Optional;

import com.ibm.examples.model.Stock;

public interface StockRepository {

	public List<Stock> getStocks();

	public Integer createStock(Stock stock);

	public Optional<Stock> getStockById(int id);

	public Optional<Stock> deleteStockById(int id);
}
