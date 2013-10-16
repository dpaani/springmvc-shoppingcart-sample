package com.codetosalvation.shoppingcart.web.controller;

import com.codetosalvation.shoppingcart.model.ShoppingCartLineItem;
import com.codetosalvation.shoppingcart.service.ShoppingCartService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class exposes services for shopping cart
 *
 * @author ddakshna
 *
 */
@Controller
@RequestMapping("/")
public class ShoppingCartController {

	Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	ShoppingCartService shoppingCartService;

	/**
	 * Loads existing test data from class path file base on bucket id
	 *
	 * @param bucket
	 *            1, 2 or 3
	 */
	@RequestMapping("loadcart/{bucket}")
	public @ResponseBody
	void loadShoppingCart(@PathVariable("bucket") String bucket) {
		logger.debug("Loading data from bucket:  #" + bucket);
		shoppingCartService.loadCartFromFile(bucket);
		logger.info("Bucket loaded successfully");
	}

	/**
	 * Loads shopping cart line items from request body
	 *
	 * @param inputLineItem
	 *            string data
	 */
	@RequestMapping(value = "addcart", method = RequestMethod.POST)
	public @ResponseBody
	void addCart(@RequestBody String inputLineItem) {
		shoppingCartService.addShoppingCartLineItem(inputLineItem);
	}

	/**
	 * Returns line items from current shopping cart
	 *
	 * @return list of ShoppingCartLineItem
	 */
	@RequestMapping("viewcart")
	public @ResponseBody
	List<ShoppingCartLineItem> viewShoppingCart() {
		logger.debug("Retrieving cart");
		return shoppingCartService.getShoppingCart().getLineItems();
	}

	/**
	 * Returns shopping cart input data class path file
	 *
	 * @param bucket
	 *            possible values are 1, 2 or 3
	 * @return List of strings
	 */
	@RequestMapping("viewbucketinput")
	public @ResponseBody
	List<String> viewBucketInput(@RequestParam("bucket") int bucket) {
		logger.debug("Getting bucket input");
		return shoppingCartService.getBucketInput(bucket);
	}

}
