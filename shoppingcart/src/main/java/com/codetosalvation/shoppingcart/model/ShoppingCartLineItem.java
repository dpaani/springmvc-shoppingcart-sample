package com.codetosalvation.shoppingcart.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ShoppingCartLineItem  implements Serializable{
	private static final long serialVersionUID = -5630606706066283715L;
	private Product product;
	private int quantity;
	private double amount;
	//@JsonIgnore
	private double totalCost;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

    public double calculateTotalPrice() {
        return this.getQuantity() * this.getProduct().getPrice();
    }

	public double calculateTaxRate() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this);
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
