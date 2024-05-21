package com.pinguela.retroworld.dao;

public class DataException extends Exception{
	/*
	 * Exception raiz de datos.
	 * */
	
	public DataException() {
		super();
	}
	
	public DataException(String message) {
		super(message);
	}
	
	public DataException(Throwable cause) {
		super(cause);
	}
	
	public DataException(String message, Throwable cause) {
		super(message, cause);
	}
}
