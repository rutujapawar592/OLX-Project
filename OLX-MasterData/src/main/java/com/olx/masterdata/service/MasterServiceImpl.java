package com.olx.masterdata.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.masterdata.dto.Category;
import com.olx.masterdata.dto.Status;
import com.olx.masterdata.entity.CategoryEntity;
import com.olx.masterdata.entity.StatusEntity;
import com.olx.masterdata.repository.CategoryRepo;
import com.olx.masterdata.repository.StatusRepo;



@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	StatusRepo statusRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		List<CategoryEntity> categoryEntities = categoryRepo.findAll();
		List<Category> categoryDTO = new ArrayList<Category>();
		Iterator<CategoryEntity> itrStockEntities = categoryEntities.iterator();
		while(itrStockEntities.hasNext())
		{
			Category category = convertEntityIDTO(itrStockEntities.next());
			categoryDTO.add(category);
		}
		
		
		return categoryDTO;
	}

	@Override
	public List<Status> getAllStatus() {
		// TODO Auto-generated method stub
		List<StatusEntity> statusEntities = statusRepo.findAll();
		List<Status> statusDTO = new ArrayList<Status>();
		Iterator<StatusEntity> itrStockEntities = statusEntities.iterator();
		while(itrStockEntities.hasNext())
		{
			Status stock = convertEntityIDTO(itrStockEntities.next());
			statusDTO.add(stock);
		}
		
		
		return statusDTO;
	}
	
	
	private Status convertEntityIDTO(StatusEntity statusEntity) {
		//return new StatusEntity(Status.getId(), Status.getName(), Status.getMarket(), Status.getPrice());
//		TypeMap<StatusEntity, Status> typeMap = modelMapper.typeMap(StatusEntity.class, Status.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(StatusEntity::getma, Status::setMarket);
//		});
		Status status = modelMapper.map(statusEntity, Status.class);
		return status;
	}
	private StatusEntity convertDTOIEntity(Status status) {
		
//		TypeMap<Status, StatusEntity> typeMap = modelMapper.typeMap(Status.class, StatusEntity.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(Status::getMarket, StatusEntity::setmarketName);
//		});
		StatusEntity statusEntity = modelMapper.map(status, StatusEntity.class);
		
		return statusEntity;
	}
	
	private Category convertEntityIDTO(CategoryEntity categoryEntity) {
		//return new StatusEntity(Status.getId(), Status.getName(), Status.getMarket(), Status.getPrice());
//		TypeMap<StatusEntity, Status> typeMap = modelMapper.typeMap(StatusEntity.class, Status.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(StatusEntity::getma, Status::setMarket);
//		});
		Category category = modelMapper.map(categoryEntity, Category.class);
		return category;
	}
	private CategoryEntity convertDTOIEntity(Category category) {
		
//		TypeMap<Status, StatusEntity> typeMap = modelMapper.typeMap(Status.class, StatusEntity.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(Status::getMarket, StatusEntity::setmarketName);
//		});
		CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
		
		return categoryEntity;
	}
}
