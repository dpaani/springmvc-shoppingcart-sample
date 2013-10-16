package com.codetosalvation.shoppingcart.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TaxRate {

	private double rate;

    public TaxRate(double rate) {
        this.rate = rate;
    }

    public double getSalesTaxRate() {
        return rate;
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
