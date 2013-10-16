package com.codetosalvation.shoppingcart.exception;

import org.springframework.core.NestedRuntimeException;


public class SalesTaxApplicationException  extends NestedRuntimeException {

	private static final long serialVersionUID = -2913930036565918442L;

	public SalesTaxApplicationException(String msg) {
		super(msg);
	}

	public SalesTaxApplicationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
