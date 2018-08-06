package org.ibm.examples.repository;

import java.util.Optional;

import org.ibm.examples.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

	public Optional<Stock> findBySymbol(String symbol);
}
