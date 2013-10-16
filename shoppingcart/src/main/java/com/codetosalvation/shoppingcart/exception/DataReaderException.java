package com.codetosalvation.shoppingcart.exception;

import org.springframework.core.NestedRuntimeException;


public class DataReaderException extends  NestedRuntimeException  {

	private static final long serialVersionUID = -3286105882312845461L;

	public DataReaderException(String msg) {
		super(msg);
	}

	public DataReaderException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
