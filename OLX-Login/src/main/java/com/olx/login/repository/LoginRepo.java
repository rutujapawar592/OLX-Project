package com.olx.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.login.entity.UserEntity;

public interface LoginRepo extends JpaRepository<UserEntity, Integer> {
	
	public List<UserEntity> findByUserName(String username);
}


//R 
//CRUD
//PagingAndSorting
//JpaRepo