package com.codetosalvation.shoppingcart.service.impl;

import com.codetosalvation.shoppingcart.model.TaxRate;
import com.codetosalvation.shoppingcart.service.TaxRateService;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service class helps to get Tax Rates for a product
 * @author ddakshna
 *
 */
@Service
public class TaxRateServiceImpl implements TaxRateService, InitializingBean {

	Logger logger = LoggerFactory.getLogger(TaxRateServiceImpl.class);

	@Value("${regular_tax_rate}")
	double regularTaxRate;

	@Value("${exempt_tax_rate}")
	double exemptTaxRate;

	@Value("${imported_tax_rate}")
	double importedTaxRate;

	@Value("${exempt_items_list}")
	String[] exemptItemsList;

	public static final String IMPORTED = "imported";
	public TaxRate REGULAR_TAX_RATE;
	public TaxRate EXEMPT_TAX_RATE;
	public TaxRate IMPORTED_TAX_RATE;

	@Override
	public void afterPropertiesSet() throws Exception {
		REGULAR_TAX_RATE = new TaxRate(regularTaxRate);
		EXEMPT_TAX_RATE = new TaxRate(exemptTaxRate);
		IMPORTED_TAX_RATE = new TaxRate(importedTaxRate);
	}

	/**
	 * It would be more appropriate to get tax rate based on category. since
	 * category is not available from input requirement, this method uses
	 * description and finds applicable tax rates
	 *
	 * @param description
	 * @return
	 */
	@Override
	public Set<TaxRate> getTaxRates(String description) {
		final Set<TaxRate> taxRates = new HashSet<TaxRate>();
		taxRates.add(REGULAR_TAX_RATE);

		if (isExeptProduct(description)) {
			taxRates.remove(REGULAR_TAX_RATE);
			taxRates.add(EXEMPT_TAX_RATE);
		}

		if (isImportedProduct(description)) {
			taxRates.add(IMPORTED_TAX_RATE);
		}
		return taxRates;
	}

	private boolean isImportedProduct(String description) {
		return StringUtils.contains(description, TaxRateServiceImpl.IMPORTED);
	}

	private boolean isExeptProduct(String description) {

		for (final String exemptToken : exemptItemsList) {
			if (StringUtils.contains(description.toLowerCase(), exemptToken)) {
				return true;
			}
		}
		return false;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}


	public void setRegularTaxRate(double regularTaxRate) {
		this.regularTaxRate = regularTaxRate;
	}


	public void setExemptTaxRate(double exemptTaxRate) {
		this.exemptTaxRate = exemptTaxRate;
	}


	public void setImportedTaxRate(double importedTaxRate) {
		this.importedTaxRate = importedTaxRate;
	}


	public void setExemptItemsList(String[] exemptItemsList) {
		this.exemptItemsList = exemptItemsList;
	}

}
