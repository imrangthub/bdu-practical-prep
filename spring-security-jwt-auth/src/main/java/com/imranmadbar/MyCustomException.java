package com.imranmadbar;

public class MyCustomException extends RuntimeException {
	
	public MyCustomException(String errorMessage) {
		super(errorMessage);
	}
}
