package com.pinguela.retroworld.service;

public class ServiceException extends Exception{
	/*
	 * Exception raiz de servicios.
	 * */
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
