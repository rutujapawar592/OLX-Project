package com.olx.advertise.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
import com.olx.advertise.exception.InvalidAuthTokenException;
import com.olx.advertise.exception.InvalidCategoryIdException;
import com.olx.advertise.exception.InvalidStatusIdException;
import com.olx.advertise.repository.AdvertiseRepo;

@Service
public class AdvertiseServiceImple implements AdvertiseService {

	@Autowired
	EntityManager entityManager;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AdvertiseRepo advertiseRepo;

	@Autowired
	LoginServiceDelegate loginServiceDelegate;

	@Autowired
	MasterDataDelegate masterDataDelegate;

	@Override
	public Advertise createNewAdvertise(Advertise advertise, String authToken) {
		// TODO Auto-generated method stub
		String cateName = masterDataDelegate.getCategoryDescription(advertise.getCategory(), authToken);
		String statusName = masterDataDelegate.getStatusName(advertise.getStatusId(), authToken);
		if (cateName != null && statusName != null) {
			advertise.setCategoryName(cateName);
			advertise.setStatusName(statusName);
			AdvertiseEntity advertiseEntity = advertiseRepo.save(convertDTOIntoEntity(advertise));
			return convertEntityIntoDTO(advertiseEntity);
		} else {
			throw new InvalidAuthTokenException(authToken);
		}
	}

	@Override
	public Advertise updateAdvertise(Advertise advertise, int id, String token) {
		// TODO Auto-generated method stub
		if (loginServiceDelegate.isTokenValid(token)) {
			Optional<AdvertiseEntity> advertiseEntityOptional = advertiseRepo.findById(id);
			if (advertiseEntityOptional.isPresent()) {
				AdvertiseEntity advertiseEntity = advertiseEntityOptional.get();
				Advertise updatedAdvertise = convertEntityIntoDTO(advertiseEntity);
				updatedAdvertise.setId(id);
				updatedAdvertise.setTitle(advertise.getTitle());
				updatedAdvertise.setCategory(advertise.getCategory());
				updatedAdvertise.setCategoryName(advertise.getCategoryName());
				updatedAdvertise.setCreatedDate(advertise.getCreatedDate());
				updatedAdvertise.setDescription(advertise.getDescription());
				updatedAdvertise.setModifiedDate(advertise.getModifiedDate());
				updatedAdvertise.setPrice(advertise.getPrice());
				updatedAdvertise.setStatusId(advertise.getStatusId());
				updatedAdvertise.setStatusName(advertise.getStatusName());
				return updatedAdvertise;
			} else
				throw new InvalidCategoryIdException();

		} else {
			throw new InvalidAuthTokenException();
		}
	}

	@Override
	public List<Advertise> getAllAdvertises(String authToken) {
		// TODO Auto-generated method stub
		if (loginServiceDelegate.isTokenValid(authToken)) {
			List<AdvertiseEntity> advertiseEntities = advertiseRepo.findAll();
			List<Advertise> advertiseDTO = new ArrayList<Advertise>();
			Iterator<AdvertiseEntity> itrAdvertiseEntities = advertiseEntities.iterator();
			while (itrAdvertiseEntities.hasNext()) {
				Advertise advertise = convertEntityIntoDTO(itrAdvertiseEntities.next());
				advertiseDTO.add(advertise);
			}

			return advertiseDTO;
		} else
			throw new InvalidAuthTokenException();
	}

	@Override
	public Advertise getAdvertiseById(int id, String authToken) {
		// TODO Auto-generated method stub
		if (loginServiceDelegate.isTokenValid(authToken)) {
			Optional<AdvertiseEntity> advertiseEntitiesOp = advertiseRepo.findById(id);
			Advertise advertiseDTO = null;
			if (advertiseEntitiesOp.isPresent()) {
				Advertise advertise = convertEntityIntoDTO(advertiseEntitiesOp.get());
				return advertise;
			}
			

		}
		throw new InvalidAuthTokenException();
	}

	@Override
	public Boolean deleteAdvertiseById(int id, String authtoken) {
		// TODO Auto-generated method stub
		if (loginServiceDelegate.isTokenValid(authtoken)) {
			advertiseRepo.deleteById(id);
			;
			return true;
		} else
			throw new InvalidAuthTokenException();
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

		if (dateCondition != null && dateCondition.contains("equal")) {
			predicateDateConditionEquals = criteriaBuilder.equal(root.get("createdDate"), onDate);
		}

		if (dateCondition != null && dateCondition.contains("greatethan")) {
			predicateDateConditionGreateThan = criteriaBuilder.greaterThan(root.get("createdDate"), fromDate);
		}

		if (dateCondition != null && dateCondition.contains("lessthan")) {
			predicateDateConditionLessThan = criteriaBuilder.greaterThan(root.get("createdDate"), onDate);
		}

		if (dateCondition != null && dateCondition.contains("between")) {
			predicateDateConditionBetweenFromDate = criteriaBuilder.between(root.get("createdDate"), fromDate, toDate);
		}

		predicateDateCondition = criteriaBuilder.and(predicateDateConditionEquals, predicateDateConditionGreateThan,
				predicateDateConditionLessThan, predicateDateConditionBetweenFromDate);

		if (categoryId != null) {
			predicateCategory = criteriaBuilder.equal(root.get("category"), categoryId);
		}

		predicateFinal = criteriaBuilder.and(predicateSearchText, predicateCategory, predicateDateCondition,
				predicatePostedBy);
		criteriaQuery.where(predicateFinal);
		if (sortedBy != null && !sortedBy.equalsIgnoreCase("")) {
			if (sortedBy == "title") {
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(root.get("title")));
			} else {
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(root.get("price")));
			}

		}
		TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(criteriaQuery);

		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(records);
		List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
		return convertEntityListIntoDTOList(advertiseEntityList);
	}

	@Override
	public List<Advertise> SearchAdvertiseByText(String searchText) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdvertiseEntity> advertiseQuery = criteriaBuilder.createQuery(AdvertiseEntity.class);
		Root<AdvertiseEntity> advertiseRoot = advertiseQuery.from(AdvertiseEntity.class);

		Predicate predicateTitle = criteriaBuilder.and();
		Predicate predicateDescription = criteriaBuilder.and();
		Predicate predicateSearchText = criteriaBuilder.and();
		if (searchText != null && !searchText.equalsIgnoreCase("")) {
			predicateTitle = criteriaBuilder.like(advertiseRoot.get("title"), "%" + searchText + "%");
			predicateDescription = criteriaBuilder.like(advertiseRoot.get("description"), "%" + searchText + "%");
			predicateSearchText = criteriaBuilder.or(predicateTitle, predicateDescription);
			advertiseQuery.where(predicateSearchText);

			TypedQuery<AdvertiseEntity> typedQuery = entityManager.createQuery(advertiseQuery);

			List<AdvertiseEntity> advertiseEntityList = typedQuery.getResultList();
			return convertEntityListIntoDTOList(advertiseEntityList);

		} else
			throw new InvalidStatusIdException();
	}

	@Override
	public List<Advertise> getAdvertiseAllById(int id, String authToken) {
		// TODO Auto-generated method stub
		if (loginServiceDelegate.isTokenValid(authToken)) {
			List<AdvertiseEntity> advertiseEntities = advertiseRepo.findAllById(id);
			List<Advertise> advertiseDTO = new ArrayList<Advertise>();
			Iterator<AdvertiseEntity> itrAdvertiseEntities = advertiseEntities.iterator();
			while (itrAdvertiseEntities.hasNext()) {
				Advertise advertise = convertEntityIntoDTO(itrAdvertiseEntities.next());
				advertiseDTO.add(advertise);
			}

			return advertiseDTO;
		} else
			throw new InvalidAuthTokenException();
	}

	private Advertise convertEntityIntoDTO(AdvertiseEntity advertiseEntity) {
		// return new UserEntity(User.getId(), User.getName(), User.getMarket(),
		// User.getPrice());
//		TypeMap<UserEntity, User> typeMap = modelMapper.typeMap(UserEntity.class, User.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(UserEntity::getmarketName, User::setMarket);
//		});
		Advertise advertise = modelMapper.map(advertiseEntity, Advertise.class);
		return advertise;
	}

	private AdvertiseEntity convertDTOIntoEntity(Advertise advertise) {

//		TypeMap<User, UserEntity> typeMap = modelMapper.typeMap(User.class, UserEntity.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(User::getMa, UserEntity::setmarketName);
//		});
		AdvertiseEntity advertiseEntity = modelMapper.map(advertise, AdvertiseEntity.class);

		return advertiseEntity;
	}

	private List<Advertise> convertEntityListIntoDTOList(List<AdvertiseEntity> advertiseEntityList) {
		// return new StockEntity(stock.getId(), stock.getName(), stock.getMarket(),
		// stock.getPrice());
		List<Advertise> advertisesList = new ArrayList<>();
		for (AdvertiseEntity advertiseEntity : advertiseEntityList) {
			TypeMap<AdvertiseEntity, Advertise> typeMap = modelMapper.typeMap(AdvertiseEntity.class, Advertise.class);
			Advertise advertise = modelMapper.map(advertiseEntity, Advertise.class);
			advertisesList.add(advertise);
		}

		return advertisesList;
	}

}
