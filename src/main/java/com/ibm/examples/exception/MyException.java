package com.ibm.examples.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyException(String property) {
		super("The element with property: --> " + property + " <-- cannot be found!");
	}
}
