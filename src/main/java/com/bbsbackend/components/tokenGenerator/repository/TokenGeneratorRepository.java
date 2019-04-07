package com.bbsbackend.components.tokenGenerator.repository;

import java.time.Instant;

public interface TokenGeneratorRepository {
	void saveToken(String token,Instant expireTime);
	Instant getExpireTime(String token);
	void delete(String token);
	String getRepositoryName();
	

}
