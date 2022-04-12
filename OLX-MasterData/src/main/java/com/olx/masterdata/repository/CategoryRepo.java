package com.olx.masterdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.masterdata.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer>{

}
