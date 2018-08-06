package org.ibm.examples.controller;

import java.util.List;

import javax.validation.Valid;

import org.ibm.examples.exception.MyException;
import org.ibm.examples.model.Stock;
import org.ibm.examples.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	StockRepository repo;

	// echivalent cu @PostMapping
	@RequestMapping(method = RequestMethod.POST, value = "/stocks")
	public Stock createStock(@Valid @RequestBody Stock stock) {
		return repo.save(stock);
	}

	// echivalent cu @GetMapping
	@RequestMapping(method = RequestMethod.GET, value = "/stocks")
	public List<Stock> getStocks() {
		return repo.findAll();
	}

	// echivalent cu @DeleteMapping
	@RequestMapping(method = RequestMethod.DELETE, value = "/stocks/{id}")
	public Stock deleteStock(@PathVariable(name = "id") Integer id) {
		Stock stock = repo.findById(id).orElseThrow(() -> new MyException("" + id));
		repo.delete(stock);
		return stock;
	}

	// echivalent cu @PutMapping
	@RequestMapping(method = RequestMethod.PUT, value = "/stocks/{id}")
	public Stock updateStock(@PathVariable(name = "id") Integer id, @Valid @RequestBody Stock newStock) {
		Stock stock = repo.findById(id).orElseThrow(() -> new MyException("" + id));

		stock.setName(newStock.getName());
		stock.setSymbol(newStock.getSymbol());
		stock.setPrice(newStock.getPrice());

		return repo.save(stock);
	}

	// echivalent cu @GetMapping
	@RequestMapping(method = RequestMethod.GET, value = "/stocks/{symbol}")
	public Stock getStockBySymbol(@PathVariable(name = "symbol") String symbol) {
		return repo.findBySymbol(symbol).orElseThrow(() -> new MyException(symbol));
	}
}
