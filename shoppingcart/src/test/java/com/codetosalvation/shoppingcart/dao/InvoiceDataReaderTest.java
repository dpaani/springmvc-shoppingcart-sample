package com.codetosalvation.shoppingcart.dao;

import com.codetosalvation.shoppingcart.exception.ResourceNotFoundException;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class InvoiceDataReaderTest {
	Logger logger = LoggerFactory.getLogger(InvoiceDataReaderTest.class);
	InvoiceDataReader reader = new InvoiceDataReader();

	@Test
	public void testRead() {
		logger.debug("Testing InvoiceDataReader read operation");
		logger.debug("Reading input bucket #1");
		Resource resource = new ClassPathResource("data/input1");
		List<String> lines = reader.read(resource);
		Assert.assertEquals(3, lines.size());
	}

	@Test(expected=ResourceNotFoundException.class)
	public void testReadForInvalidBucketName() {
		logger.debug("Testing InvoiceDataReader read operation");
		logger.debug("Reading input bucket #1");
		Resource resource = new ClassPathResource("data/input4");
		reader.read(resource);
	}

}
