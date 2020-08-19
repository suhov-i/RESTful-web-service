package com.isukhov.webservices.mobileappws.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = -6503447269872465392L;

    public UserServiceException(String message) {
        super(message);
    }
}
