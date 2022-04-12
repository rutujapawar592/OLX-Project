package com.olx.advertise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.advertise.entity.AdvertiseEntity;

public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer> {

}
