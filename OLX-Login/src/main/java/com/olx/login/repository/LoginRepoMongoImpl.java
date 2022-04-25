package com.olx.login.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.login.entity.UserDocument;


public interface LoginRepoMongoImpl extends  MongoRepository<UserDocument, Integer>{

	public List<UserDocument> findByUserName(String username);
}
