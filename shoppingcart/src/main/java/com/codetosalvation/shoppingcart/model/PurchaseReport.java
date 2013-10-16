package com.codetosalvation.shoppingcart.model;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PurchaseReport {
	private List<ShoppingCartLineItem> lineItems;
	BigDecimal salesTax = null;
	BigDecimal total = null;

	public List<ShoppingCartLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<ShoppingCartLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
