package com.nyilvt.nyilvt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAddressCountException extends RuntimeException {
	public InvalidAddressCountException(String message) {
		super(message);
	}
}