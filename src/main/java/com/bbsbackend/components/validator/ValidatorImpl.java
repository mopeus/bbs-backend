package com.bbsbackend.components.validator;

import java.util.function.Function;

public class ValidatorImpl<T>implements Validator<T> {
	private final String componentName;
	private final Function<T, Boolean>pred;
	
	public ValidatorImpl(String componentName,Function<T, Boolean>pred) {
		this.componentName=componentName;
		this.pred=pred;
		
	}
	public String getName() {
		return this.componentName;
	}
	
	public Boolean validate(T value) {
		return pred.apply(value);
	}
}
