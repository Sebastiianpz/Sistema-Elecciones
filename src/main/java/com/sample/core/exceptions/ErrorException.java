package com.sample.core.exceptions;

public class ErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorException(String mensaje){
		super(mensaje);
	}

	public ErrorException(String mensaje ,Throwable  tr ){
		super(mensaje, tr);
	}
}
