package com.app.planm.common.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 4624867800115411491L;

	public CustomException(String msg) {
        super(msg);
    }
}
