package org.appdirect.challenge.service.exception;

public class UnauthorizedUserException extends RuntimeException{

	private static final long serialVersionUID = -5945700951740925366L;

	public UnauthorizedUserException(final String message){
		super(message);
	}
	
	public UnauthorizedUserException(final Throwable cause){
		super(cause);
	}
	
	public UnauthorizedUserException(final String message, final Throwable cause){
		super(message, cause);
	}
}
