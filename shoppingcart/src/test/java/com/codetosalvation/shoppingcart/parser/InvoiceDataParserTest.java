package com.codetosalvation.shoppingcart.parser;

import com.codetosalvation.shoppingcart.dao.InvoiceDataReader;
import com.codetosalvation.shoppingcart.model.InvoiceFileLineItem;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class InvoiceDataParserTest {
	InvoiceDataReader reader = new InvoiceDataReader();

	@Test
	public void testParse() {
		Resource resource = new ClassPathResource("data/input1");
		List<String> lines = reader.read(resource);
		InvoiceDataParser parser = new InvoiceDataParser(lines);
		List<InvoiceFileLineItem> lineItems = parser.parseData();
		Assert.assertEquals("Both should equal",lines.size(), lineItems.size());
	}

	@Test
	public void testParseAndMakeSureAllDataValid() {
		Resource resource = new ClassPathResource("data/input1");
		List<String> lines = reader.read(resource);
		InvoiceDataParser parser = new InvoiceDataParser(lines);
		List<InvoiceFileLineItem> lineItems = parser.parseData();
		for(InvoiceFileLineItem invoiceFileLineItem : lineItems) {
			Assert.assertNotNull(invoiceFileLineItem.getProductDescription());
			Assert.assertTrue("Qty should be not be zero", invoiceFileLineItem.getQuantity() !=0);
			Assert.assertTrue("Amount should be not be zero", invoiceFileLineItem.getAmount() !=0);
		}
	}

	@Test
	public void testParseForInvalidLineItem() {
		Resource resource = new ClassPathResource("data/input1");
		List<String> lines = reader.read(resource);
		lines.set(0, "invalid");
		InvoiceDataParser parser = new InvoiceDataParser(lines);
		List<InvoiceFileLineItem> lineItems = parser.parseData();
		InvoiceFileLineItem invoiceFileLineItem = lineItems.get(0);
		Assert.assertNotNull(invoiceFileLineItem.getProductDescription());
		Assert.assertFalse("Qty should be zero", invoiceFileLineItem.getQuantity() !=0);
		Assert.assertFalse("Amount should be zero", invoiceFileLineItem.getAmount() !=0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testParseForInvalidLineItemWithNullValue() {
		Resource resource = new ClassPathResource("data/input1");
		List<String> lines = reader.read(resource);
		lines.set(1, null);
		InvoiceDataParser parser = new InvoiceDataParser(lines);
		parser.parseData();
	}
}
