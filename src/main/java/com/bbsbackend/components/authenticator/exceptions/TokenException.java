package com.bbsbackend.components.authenticator.exceptions;

public class TokenException extends AuthenticatorException{
	public TokenException(String errorMessage) {
		super(errorMessage);
	}
}
