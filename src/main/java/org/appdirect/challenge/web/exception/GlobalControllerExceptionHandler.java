package org.appdirect.challenge.web.exception;

import javax.servlet.http.HttpServletRequest;

import org.appdirect.challenge.service.exception.JsonProcessingException;
import org.appdirect.challenge.service.exception.UnauthorizedUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedUserException.class)
	@ResponseBody
	public ResponseInfo handleUnauthorizedUserException(final HttpServletRequest req, final Exception ex){
		String message = ex.getMessage();
		LOGGER.error(message, ex);
		return new ResponseInfo(false,HttpStatus.UNAUTHORIZED.name(), message);
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public ResponseInfo handleDataIntegrityViolationException(final HttpServletRequest req, final Exception ex){
		String message = ex.getMessage();
		LOGGER.error(message, ex);
		return new ResponseInfo(false,HttpStatus.CONFLICT.name(), message);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataRetrievalFailureException.class)
	@ResponseBody
	public ResponseInfo handleDataRetrievalFailureException(final HttpServletRequest req, final Exception ex){
		String message = ex.getMessage();
		LOGGER.error(message, ex);
		return new ResponseInfo(false,HttpStatus.NOT_FOUND.name(), message);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JsonProcessingException.class)
	@ResponseBody
	public ResponseInfo handleJsonProcessingException(final HttpServletRequest req, final Exception ex){
		String message = ex.getMessage();
		LOGGER.error(message, ex);
		return new ResponseInfo(false,HttpStatus.BAD_REQUEST.name(), message);
	}
}
