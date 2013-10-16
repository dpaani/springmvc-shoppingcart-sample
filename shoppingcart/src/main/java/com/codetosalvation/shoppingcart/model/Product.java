package com.codetosalvation.shoppingcart.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Product implements Comparable<Product>, Serializable {
	private static final long serialVersionUID = 7474633245159556743L;

	private long id;
	private String description;
	private double price;
	private boolean imported;

	public Product() {

	}
	public Product(long id, String description, int price, boolean imported) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.imported = imported;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int compareTo(Product p) {
		return ((Long) id).compareTo((Long) p.id);
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

}
