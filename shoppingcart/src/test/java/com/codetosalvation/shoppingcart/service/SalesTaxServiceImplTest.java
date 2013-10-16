package com.codetosalvation.shoppingcart.service;

import static org.junit.Assert.fail;

import com.codetosalvation.shoppingcart.dao.InvoiceDataReader;
import com.codetosalvation.shoppingcart.model.InvoiceFileLineItem;
import com.codetosalvation.shoppingcart.model.ShoppingCart;
import com.codetosalvation.shoppingcart.model.ShoppingCartLineItem;
import com.codetosalvation.shoppingcart.parser.InvoiceDataParser;
import com.codetosalvation.shoppingcart.service.impl.SalesTaxServiceImpl;
import com.codetosalvation.shoppingcart.service.impl.TaxRateServiceImpl;
import com.codetosalvation.shoppingcart.util.NumberUtils;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class SalesTaxServiceImplTest {
	Logger logger = LoggerFactory.getLogger(SalesTaxServiceImplTest.class);
	SalesTaxServiceImpl impl = new SalesTaxServiceImpl();
	TaxRateServiceImpl taxRateServiceImpl = new TaxRateServiceImpl();
	InvoiceDataReader reader = new InvoiceDataReader();

	ShoppingCart shoppingCart = new ShoppingCart();
	@Before
	public void setup() throws Exception {

		taxRateServiceImpl.setRegularTaxRate(.10d);
		taxRateServiceImpl.setImportedTaxRate(.05d);
		taxRateServiceImpl.setExemptTaxRate(0.0d);
		taxRateServiceImpl.afterPropertiesSet();
		taxRateServiceImpl.setExemptItemsList(new String[]{"chocolate","pills","book"});

		impl.setTaxRateService(taxRateServiceImpl);

		Resource resource = new ClassPathResource("data/input1");
		List<String> lines = reader.read(resource);
		InvoiceDataParser parser = new InvoiceDataParser(lines);
		List<InvoiceFileLineItem> lineItems = parser.parseData();

		for(InvoiceFileLineItem invoiceFileLineItem : lineItems) {
			shoppingCart.addLineItem(invoiceFileLineItem.toShoppingCartLineItem());
		}

	}

	@Test
	public void testCalculateTotalTaxes() {
		double totalTaxes = impl.calculateTotalTaxes(shoppingCart);
		logger.info("Total Taxes for bucket #1: "+totalTaxes);
		Assert.assertEquals(1.5, totalTaxes,0.0);
	}

	@Test
	public void testCalculateTotalCost() {
		double totalCost = impl.calculateTotalCost(shoppingCart);
		logger.info("Total Cost for bucket #1: "+NumberUtils.round(totalCost));
		Assert.assertEquals(29.83, NumberUtils.round(totalCost),0.0);
	}

	@Test
	public void testCalculateCostOfLineItems() {
		impl.calculateTotalCost(shoppingCart);
		List<ShoppingCartLineItem> lineItems = shoppingCart.getLineItems();

		logger.info("Total Cost for line item #1: "+NumberUtils.round(lineItems.get(0).getTotalCost()));
		logger.info("Total Cost for line item #2: "+NumberUtils.round(lineItems.get(1).getTotalCost()));
		logger.info("Total Cost for line item #3: "+NumberUtils.round(lineItems.get(2).getTotalCost()));
		Assert.assertEquals(12.49, NumberUtils.round(lineItems.get(0).getTotalCost()),0.0);
		Assert.assertEquals(16.48, NumberUtils.round(lineItems.get(1).getTotalCost()),0.0);
		Assert.assertEquals(0.83, NumberUtils.round(lineItems.get(2).getTotalCost()),0.0);
	}
}
