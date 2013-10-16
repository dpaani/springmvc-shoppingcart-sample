package com.codetosalvation.shoppingcart.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FileLineItem implements Serializable {

	private static final long serialVersionUID = 5104980509918667304L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
