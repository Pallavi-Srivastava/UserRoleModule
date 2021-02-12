package com.hm.productclaim.exception;

public class UserException extends Exception {

    public UserException(String message, ExceptionType exceptionType) {
        super(message);
        this.type=exceptionType;
    }

    public enum ExceptionType {
    	RECORD_NOT_AVAILABLE,ALREADY_IN_LIST,ROLE_NOT_AVAILABLE
    }
    public ExceptionType type;
}
