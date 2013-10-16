package com.codetosalvation.shoppingcart.service;

import com.codetosalvation.shoppingcart.model.ShoppingCart;

import java.util.List;


public interface ShoppingCartService {

	public ShoppingCart loadCartFromFile(String inputFile);
	public ShoppingCart getShoppingCart();
	public ShoppingCart addShoppingCartLineItem(String inputLineItem);
	public List<String> getBucketInput(int bucket);

}
