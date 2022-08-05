package com.to.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//exception for Authorization
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EtAuthException extends RuntimeException {
	
	//constructor
	public EtAuthException(String message) {
		super(message);
	}

}
