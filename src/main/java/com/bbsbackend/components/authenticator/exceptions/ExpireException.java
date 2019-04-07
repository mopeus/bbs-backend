package com.bbsbackend.components.authenticator.exceptions;

public class ExpireException extends AuthenticatorException{
    public ExpireException(String errorMessage) {
        super(errorMessage);
    }
}
