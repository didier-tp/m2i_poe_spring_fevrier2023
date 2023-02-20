package com.m2i.tp.appliSpring.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND) //404
//pas d'annotation @ResponseStatus mais @ControllerAdvice
public class NotFoundException extends RuntimeException {

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
