package com.cg.ecdm.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(ECMException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(
			ECMException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}



}
