package com.bbsbackend.components.authenticator.exceptions;

public class PasswordInvalid extends AuthenticatorException{
    public PasswordInvalid(String errorMessage) {
        super(errorMessage);
    }
}
