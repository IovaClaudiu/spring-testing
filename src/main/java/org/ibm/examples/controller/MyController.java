package org.ibm.examples.controller;

import java.util.List;

import javax.validation.Valid;

import org.ibm.examples.exception.MyException;
import org.ibm.examples.model.Stock;
import org.ibm.examples.repository.JdbcStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
@Transactional
public class MyController {

	@Autowired
	JdbcStockRepository repo;

	// echivalent cu @PostMapping
	@RequestMapping(method = RequestMethod.POST, value = "/stocks")
	public int createStock(@Valid @RequestBody Stock stock) {
		return repo.createStock(stock);
	}

	// echivalent cu @GetMapping
	@RequestMapping(method = RequestMethod.GET, value = "/stocks")
	public List<Stock> getStocks() {
		return repo.getStocks();
	}

	// echivalent cu @DeleteMapping
	@RequestMapping(method = RequestMethod.DELETE, value = "/stocks/{id}")
	@Modifying
	public Stock deleteStock(@PathVariable(name = "id") Integer id) {
		return repo.deleteStockById(id).orElseThrow(() -> new MyException(id.toString()));
	}

	// echivalent cu @GetMapping
	@RequestMapping(method = RequestMethod.GET, value = "/stocks/{id}")
	public Stock getStockBySymbol(@PathVariable(name = "id") Integer id) {
		return repo.getStockById(id).orElseThrow(() -> new MyException(id.toString()));
	}
}
