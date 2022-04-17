package com.olx.login.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.login.entity.BlackListedTokenDocumnents;

public interface BlacklistedTokensDocumentRepo extends MongoRepository<BlackListedTokenDocumnents, Integer>{
	
	public BlackListedTokenDocumnents findByToken(String tokenName);
}
