package com.mobiquityinc.packer.exception;

public class APIException extends Exception {

	private static final long serialVersionUID = 8737342459129046092L;

	public APIException(String message, Exception e) {
		super(message, e);
	}

	public APIException(String message) {
		super(message);
	}
}
