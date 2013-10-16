package com.codetosalvation.shoppingcart.web.handler;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = { com.codetosalvation.shoppingcart.exception.ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleResourceNotFoundException(com.codetosalvation.shoppingcart.exception.ResourceNotFoundException ex,
			WebRequest request) {
		logger.error(ex.getMessage(), ex);
		com.codetosalvation.shoppingcart.model.ErrorResponse errorResponse = new com.codetosalvation.shoppingcart.model.ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorReferenceId(UUID.randomUUID().toString());
		errorResponse.setType(HttpStatus.NOT_FOUND.name());
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = { Exception.class})
	protected ResponseEntity<Object> handleResourceNotFoundException(Exception ex,
			WebRequest request) {
		logger.error(ex.getMessage(), ex);
		com.codetosalvation.shoppingcart.model.ErrorResponse errorResponse = new com.codetosalvation.shoppingcart.model.ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorReferenceId(UUID.randomUUID().toString());
		errorResponse.setType(HttpStatus.INTERNAL_SERVER_ERROR.name());
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
