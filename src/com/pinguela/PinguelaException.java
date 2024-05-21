package com.pinguela;

public class PinguelaException extends Exception{
	/*
	 * Exception raiz de nuestra compañía, IES A PINGUELA.
	 * */
	
	public PinguelaException() {
		super();
	}
	
	public PinguelaException(String message) {
		super(message);
	}
	
	public PinguelaException(Throwable cause) {
		super(cause);
	}
	
	public PinguelaException(String message, Throwable cause) {
		super(message, cause);
	}
}
