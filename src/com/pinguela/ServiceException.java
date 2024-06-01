package com.pinguela;

public class ServiceException extends PinguelaException{
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
