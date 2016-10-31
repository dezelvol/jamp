package com.jamp.io.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Incapsulating of exceptions so user can't see actual implementation details
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exResponse(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
