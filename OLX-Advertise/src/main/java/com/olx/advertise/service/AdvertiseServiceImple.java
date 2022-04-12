package com.olx.advertise.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.olx.advertise.dto.Advertise;
import com.olx.advertise.entity.AdvertiseEntity;
import com.olx.advertise.repository.AdvertiseRepo;


@Service
public class AdvertiseServiceImple implements AdvertiseService {

	@Autowired
	EntityManager entityManager;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AdvertiseRepo advertiseRepo;

	@Override
	public Advertise postAdvertise(Advertise Advertise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertise updateAdvertise(Advertise Advertise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertise> getAllAdvertiseByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertise> getAdvertiseByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertise> filterAdvertise(String searchText, Integer categoryId, String postedBy, String dateCondition,
			LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortedBy, int startIndex, int records) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> criteriaQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> root = criteriaQuery.from(AdvertiseEntity.class);

		Predicate predicateTitle = criteriaBuilder.and();
		Predicate predicateDescription = criteriaBuilder.and();
		Predicate predicateSearchText = criteriaBuilder.and();
		Predicate predicateCategory = criteriaBuilder.and();
		Predicate predicateDateConditionEquals = criteriaBuilder.and();
		Predicate predicateDateConditionGreateThan = criteriaBuilder.and();
		Predicate predicateDateConditionLessThan = criteriaBuilder.and();
		Predicate predicateDateConditionBetweenFromDate = criteriaBuilder.and();
		Predicate predicatePostedBy = criteriaBuilder.and();
		Predicate predicateDateCondition = criteriaBuilder.and();
		Predicate predicateOrderBy = criteriaBuilder.and();
		Predicate predicateFinal = criteriaBuilder.and();

		if (searchText != null && !"".equalsIgnoreCase(searchText)) {
			predicateTitle = criteriaBuilder.like(root.get("title"), "%" + searchText + "%");
			predicateDescription = criteriaBuilder.like(root.get("description"), "%" + searchText + "%");
			predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
		}

		if (postedBy != null && !"".equalsIgnoreCase(postedBy)) {
			predicatePostedBy = criteriaBuilder.equal(root.get("username"), postedBy);
		}
		
		if(dateCondition!=null && dateCondition.contains("equal")) {
			predicateDateConditionEquals = criteriaBuilder.equal(root.get("createdDate"), onDate);
		}
		
		if(dateCondition!=null && dateCondition.contains("greatethan")) {
			predicateDateConditionGreateThan = criteriaBuilder.greaterThan(root.get("createdDate"), fromDate);
		}
		
		if(dateCondition!=null && dateCondition.contains("lessthan")) {
			predicateDateConditionLessThan = criteriaBuilder.greaterThan(root.get("createdDate"), onDate);
		}
		
		if(dateCondition!=null && dateCondition.contains("between")) {
			predicateDateConditionBetweenFromDate=criteriaBuilder.between(root.get("createdDate"), fromDate, toDate);
		}
		
		
		
		predicateDateCondition = criteriaBuilder.and(predicateDateConditionEquals,predicateDateConditionGreateThan,
				predicateDateConditionLessThan,predicateDateConditionBetweenFromDate);
		
		
		
		
		if(categoryId != null )
		{
			predicateCategory = criteriaBuilder.equal(root.get("category"), categoryId);
		}

		predicateFinal = criteriaBuilder.and(predicateSearchText, predicateCategory, predicateDateCondition,
				predicatePostedBy);
		criteriaQuery.where(predicateFinal);
		if(sortedBy != null && !sortedBy.equalsIgnoreCase(""))
		{
			if(sortedBy == "title")
			{
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(root.get("title")));
			}
			else
			{
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(root.get("price")));
			}
			
			
		}
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		
		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(records);
		List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
		return convertEntityListIntoDTOList(advertiseEntityList);
	}

	private List<Advertise> convertEntityListIntoDTOList(List<AdvertiseEntity> advertiseEntityList) {
		// return new StockEntity(stock.getId(), stock.getName(), stock.getMarket(),
		// stock.getPrice());
		List<Advertise> advertisesList = new ArrayList<>();
		for(AdvertiseEntity advertiseEntity : advertiseEntityList)
		{
			TypeMap<AdvertiseEntity, Advertise> typeMap = modelMapper.typeMap(AdvertiseEntity.class, Advertise.class);
			Advertise advertise = modelMapper.map(advertiseEntity, Advertise.class);
			advertisesList.add(advertise);
		}
		
		return advertisesList;
	}

//	private StockEntity convertDTOIEntity(Stock stock) {
//
//		TypeMap<Stock, StockEntity> typeMap = modelMapper.typeMap(Stock.class, StockEntity.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(Stock::getMarket, StockEntity::setmarketName);
//		});
//		StockEntity stockEntity = modelMapper.map(stock, StockEntity.class);
//
//		return stockEntity;
//	}

	@Override
	public Advertise SearchAdvertiseByText(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advertise returnAdvertise(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
