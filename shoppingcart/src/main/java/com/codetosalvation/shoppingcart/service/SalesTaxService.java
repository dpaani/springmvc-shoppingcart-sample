package com.codetosalvation.shoppingcart.service;

import com.codetosalvation.shoppingcart.model.ShoppingCart;
import com.codetosalvation.shoppingcart.model.ShoppingCartLineItem;


public interface SalesTaxService {
	 public double calculateSaleTax(ShoppingCartLineItem item) ;
	 public double calculateTotalTaxes(ShoppingCart shoppingCart);
	 public double calculateTotalCost(ShoppingCart shoppingCart);
}
