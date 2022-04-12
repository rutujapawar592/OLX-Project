package com.olx.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.login.entity.UserEntity;

public interface LoginRepo extends JpaRepository<UserEntity, Integer>{

}


//R 
//CRUD
//PagingAndSorting
//JpaRepo