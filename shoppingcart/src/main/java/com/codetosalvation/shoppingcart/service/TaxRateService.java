package com.codetosalvation.shoppingcart.service;

import com.codetosalvation.shoppingcart.model.TaxRate;

import java.util.Set;


public interface TaxRateService {
	public Set<TaxRate> getTaxRates(String description);
}
