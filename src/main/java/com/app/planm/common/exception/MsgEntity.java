package com.app.planm.common.exception;

public class MsgEntity {

	private int code;
    private String message;
    private Object result;

    public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
    public MsgEntity(int code, String message) {
    	this.code 	 = code;
    	this.message = message;
    	this.result  = null;    	
    }
    public MsgEntity(int code, String message, Object result) {
    	this.code    = code;
    	this.message = message;
    	this.result	 = result;    	
    }    	
}
