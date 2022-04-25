package com.olx.login.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.olx.login.dto.User;
import com.olx.login.entity.BlackListedTokenDocumnents;
import com.olx.login.entity.UserEntity;
import com.olx.login.exception.InvalidAuthTokenException;
import com.olx.login.exception.InvalidCredentialsException;
import com.olx.login.repository.BlacklistedTokensDocumentRepo;
import com.olx.login.repository.LoginRepo;
import com.olx.login.security.JwtUtil;


@Service
@Primary
public class LoginServiceImpl implements LoginService {

	Map<String, User> users = new HashMap<>();
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	LoginRepo loginRepo;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired(required = true)
	AuthenticationManager authenticationManager;

	@Autowired
	BlacklistedTokensDocumentRepo blacklistedTokensDocumentRepo;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public String authenticate(User user) {
		// TODO Auto-generated method stub
		try {
			this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		} catch (AuthenticationException e) {
			throw new InvalidCredentialsException(e.toString());
		}
		return jwtUtil.generateToken(user.getUserName());
	}

	@Override
	public Boolean logout(String authToken) {
		// TODO Auto-generated method stub
		BlackListedTokenDocumnents blackListedTokenDocumnents = blacklistedTokensDocumentRepo.findByToken(authToken);
		if (blackListedTokenDocumnents != null) {
			throw new InvalidAuthTokenException();
		}

		blackListedTokenDocumnents = new BlackListedTokenDocumnents(authToken, LocalDate.now());
		blacklistedTokensDocumentRepo.save(blackListedTokenDocumnents);
		return true;

	}

	// 3
	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		UserEntity userEntity = convertDTOIEntity(user);
		userEntity = loginRepo.save(userEntity);
		return convertEntityIDTO(userEntity);
	}

	// 4
	@Override
	public User getUser(String userToken) {
		// TODO Auto-generated method stub
		String username = jwtUtil.extractUsername(userToken);
		List<UserEntity> userEntitiesList = loginRepo.findByUserName(username);
		UserEntity userEntity = userEntitiesList.get(0);
		return convertEntityIDTO(userEntity);
	}

	@Override
	public Boolean tokenValidatation(String authToken) {
		authToken = authToken.substring(7);
		BlackListedTokenDocumnents blacklistedToken = blacklistedTokensDocumentRepo.findByToken(authToken);
		if (blacklistedToken != null) {
			throw new InvalidAuthTokenException();
		}
		String username = jwtUtil.extractUsername(authToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return jwtUtil.validateToken(authToken, userDetails);
	}

	private User convertEntityIDTO(UserEntity userEntity) {
		
		User user = modelMapper.map(userEntity, User.class);
		return user;
	}

	private UserEntity convertDTOIEntity(User user) {

		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		return userEntity;
	}

}
