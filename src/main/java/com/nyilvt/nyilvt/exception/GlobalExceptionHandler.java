package com.nyilvt.nyilvt.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
		Map<String, String> error = new HashMap<>();

		error.put("hiba", ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidAddressCountException.class)
	public ResponseEntity<Map<String, String>> handleInvalidAddressCountException(InvalidAddressCountException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("hiba", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, String> error = new HashMap<>();

		error.put("hiba", ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
