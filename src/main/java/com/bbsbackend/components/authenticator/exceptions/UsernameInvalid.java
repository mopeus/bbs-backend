package com.bbsbackend.components.authenticator.exceptions;

public class UsernameInvalid extends AuthenticatorException{
    public UsernameInvalid(String errorMessage) {
        super(errorMessage);
    }
}
