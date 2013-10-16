package com.codetosalvation.shoppingcart.service;

import com.codetosalvation.shoppingcart.model.TaxRate;
import com.codetosalvation.shoppingcart.service.impl.TaxRateServiceImpl;
import com.codetosalvation.shoppingcart.util.NumberUtils;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TaxRateServiceTest {

	TaxRateServiceImpl impl = new TaxRateServiceImpl();
	@Before
	public void setup() throws Exception{
		impl.setRegularTaxRate(.10d);
		impl.setImportedTaxRate(.05d);
		impl.setExemptTaxRate(0.0d);
		impl.afterPropertiesSet();
		impl.setExemptItemsList(new String[]{"chocolate","pills","book"});
	}

	@Test
	public void testGetTaxRatesForExemptItem() {
		Set<TaxRate> rates = impl.getTaxRates("book");
		Assert.assertEquals("Applicable tax rate should be 1", 1, rates.size());
		Assert.assertEquals(0.0, rates.toArray(new TaxRate[]{})[0].getSalesTaxRate(),0.0);
	}

	@Test
	public void testGetTaxRatesForRegularItem() {
		Set<TaxRate> rates = impl.getTaxRates("CD");
		Assert.assertEquals("Applicable tax rate should be 1", 1, rates.size());
		Assert.assertEquals(0.1, rates.toArray(new TaxRate[]{})[0].getSalesTaxRate(),0.0);
	}

	@Test
	public void testGetTaxRatesForExemptImportedItem() {
		Set<TaxRate> rates = impl.getTaxRates("imported book");
		Assert.assertEquals("Applicable tax rate should be 2", 2, rates.size());
		double totalTaxRate = 0.0;
		for(TaxRate taxRate : rates) {
			totalTaxRate+=taxRate.getSalesTaxRate();
		}
		Assert.assertEquals(0.05, totalTaxRate,0.0);
	}

	@Test
	public void testGetTaxRatesForNonExemptImportedItem() {
		Set<TaxRate> rates = impl.getTaxRates("imported CD");
		Assert.assertEquals("Applicable tax rate should be 2", 2, rates.size());
		double totalTaxRate = 0.0;
		for(TaxRate taxRate : rates) {
			totalTaxRate+=taxRate.getSalesTaxRate();
		}
		Assert.assertEquals(0.15, NumberUtils.round(totalTaxRate),0.0);
	}
}
