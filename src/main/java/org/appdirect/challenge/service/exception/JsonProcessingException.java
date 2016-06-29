package org.appdirect.challenge.service.exception;

public class JsonProcessingException extends RuntimeException{

	private static final long serialVersionUID = 1075688838309737193L;

	public JsonProcessingException(final String message){
		super(message);
	}
	
	public JsonProcessingException(final Throwable cause){
		super(cause);
	}
	
	public JsonProcessingException(final String message, final Throwable cause){
		super(message, cause);
	}
}
