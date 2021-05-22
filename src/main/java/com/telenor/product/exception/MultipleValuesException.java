package com.telenor.product.exception;

public class MultipleValuesException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultipleValuesException(String exception) {
		super(exception);
	}
}
