package com.codetosalvation.shoppingcart.service.impl;

import com.codetosalvation.shoppingcart.dao.DataReader;
import com.codetosalvation.shoppingcart.exception.SalesTaxApplicationException;
import com.codetosalvation.shoppingcart.model.InvoiceFileLineItem;
import com.codetosalvation.shoppingcart.model.ShoppingCart;
import com.codetosalvation.shoppingcart.model.ShoppingCartLineItem;
import com.codetosalvation.shoppingcart.parser.InvoiceDataParser;
import com.codetosalvation.shoppingcart.service.ShoppingCartService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
/**
 * Service class exposes methods to perform Shopping cart activites
 * @author ddakshna
 *
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

	@Autowired
	@Qualifier("invoiceDataReader")
	DataReader dataReader;

	@Autowired
	ShoppingCart shoppingCart;

	private static final String DATA_FOLDER_PREFIX = "data/input";

	/**
	 * Reads raw data from input bucket and loads to shopping cart object
	 */
	@Override
	public ShoppingCart loadCartFromFile(String inputFile) throws SalesTaxApplicationException {
		Resource resource = new ClassPathResource(DATA_FOLDER_PREFIX+inputFile);
		logger.debug("Loading data from "+resource.getFilename());

		shoppingCart.clear();
		addLineItemsToShoppingCart(resource);

		return shoppingCart;
	}

	/**
	 * Adds line item to shopping cart
	 */
	@Override
	public ShoppingCart addShoppingCartLineItem(String inputLineItem) {
		Resource resource = new ByteArrayResource(inputLineItem.getBytes());
		addLineItemsToShoppingCart(resource);
		return shoppingCart;
	}

	/**
	 * Converts class path or byte array resource to InvoiceFileLineItem objects
	 * @param resource
	 * @return list of InvoiceFileLineItem
	 */
	private List<InvoiceFileLineItem> toInvoiceLineItems(Resource resource) {
		List<String> invoiceRecordsAsString = dataReader.read(resource);
		return new InvoiceDataParser(invoiceRecordsAsString).parseData();
	}

	/**
	 * Converts class path or byte array resource to InvoiceFileLineItem objects
	 * @param resource
	 */
	private void addLineItemsToShoppingCart(Resource resource) {
		List<InvoiceFileLineItem> invoiceLineItems = toInvoiceLineItems(resource);
		logger.debug("Loading "+invoiceLineItems.size()+" items to shopping cart");
		for(InvoiceFileLineItem invoiceFileLineItem : invoiceLineItems) {
			addLineItemToShoppingCart(invoiceFileLineItem);
		}
	}

	/**
	 * Converts invoiceFileLineItem to ShoppingCartLineItem and adds to shopping cart
	 * @param invoiceFileLineItem
	 */
	private void addLineItemToShoppingCart(InvoiceFileLineItem invoiceFileLineItem) {
		ShoppingCartLineItem shoppingCartLineItem = invoiceFileLineItem.toShoppingCartLineItem();
		logger.debug("Adding shopping cart line item: "+shoppingCartLineItem);
		shoppingCart.addLineItem(shoppingCartLineItem);
	}

	/**
	 * Returns current shopping cart
	 */
	@Override
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * Returns input bucket name data from static file
	 */
	public List<String> getBucketInput(int bucket) {
		return dataReader.read(new ClassPathResource(DATA_FOLDER_PREFIX+bucket));
	}
}
