package com.bbsbackend.components.tokenGenerator;

import java.time.Instant;

import com.bbsbackend.components.tokenGenerator.repository.TokenGeneratorRepository;

public interface TokenGenerator {
	String genToken(String key,Instant expireTime);
	boolean isExpired(String token);
	void expire(String token);
	String getName();
	static TokenGenerator defau1t(String componentName,TokenGeneratorRepository repository) {
		return new TokenGeneratorImpl(componentName,repository);
	}
	
}
