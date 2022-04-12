package com.olx.login.service;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.login.dto.User;
import com.olx.login.entity.UserEntity;
import com.olx.login.repository.LoginRepo;


@Service
public class LoginServiceImpl implements LoginService{
	
	Map<String, User> users = new HashMap<>();
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	LoginRepo loginRepo;
	@Override
	public String authenticate(User user) {
		// TODO Auto-generated method stub
		Iterator<Entry<String, User>> itrUser = users.entrySet().iterator();
		while(itrUser.hasNext()) {
			Entry<String, User> entryset = itrUser.next();
			if(user.getUserName() == entryset.getValue().getUserName())
			{
				return entryset.getKey();
			}
		}
		return null;
	}

	@Override
	public Boolean logout(String authToken) {
		// TODO Auto-generated method stub
		Iterator<Entry<String, User>> itrUser = users.entrySet().iterator();
		while(itrUser.hasNext()) {
			Entry<String, User> entryset = itrUser.next();
			if(authToken == entryset.getKey())
			{
			
				return true;
			}
		}
		return false;
	}

	//3
	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		UserEntity userEntity = convertDTOIEntity(user);
		userEntity = loginRepo.save(userEntity);
		return convertEntityIDTO(userEntity);
	}

	//4
	@Override
	public User getUser(String userToken) {
		// TODO Auto-generated method stub
		
		return new User(1,"Suraj", "Singh", "Sandhu123", "1234567", "password", "97787564564");
	}

	@Override
	public Boolean tokenValidatation(String userToken) {
		// TODO Auto-generated method stub
		Boolean checkToken = logout(userToken);
		if(checkToken == true)
			return true;
		return false;
	}
	
	private User convertEntityIDTO(UserEntity userEntity) {
		//return new UserEntity(User.getId(), User.getName(), User.getMarket(), User.getPrice());
//		TypeMap<UserEntity, User> typeMap = modelMapper.typeMap(UserEntity.class, User.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(UserEntity::getmarketName, User::setMarket);
//		});
		User user = modelMapper.map(userEntity, User.class);
		return user;
	}
	private UserEntity convertDTOIEntity(User user) {
		
//		TypeMap<User, UserEntity> typeMap = modelMapper.typeMap(User.class, UserEntity.class);
//		typeMap.addMappings(mapper -> {
//			mapper.map(User::getMa, UserEntity::setmarketName);
//		});
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		
		return userEntity;
	}

}
