package org.ibm.examples.repository;

import java.util.List;
import java.util.Optional;

import org.ibm.examples.model.Stock;

public interface StockRepository {

	public List<Stock> getStocks();

	public int getNumberOfStocks();

	public int createStock(Stock stock);

	public Optional<Stock> getStockById(int id);

	public Optional<Stock> deleteStockById(int id);
}
