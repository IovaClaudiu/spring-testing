package org.ibm.examples.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@NotBlank
	public String name;

	@NotBlank
	public String symbol;

	public Double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String companyName) {
		this.name = companyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String companySymbol) {
		this.symbol = companySymbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
