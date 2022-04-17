package com.olx.login.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.olx.login.dto.User;
import com.olx.login.entity.UserDocument;
import com.olx.login.repository.LoginRepoMongoImpl;
import com.olx.login.security.JwtUtil;
import com.olx.login.security.UserDetailsServiceImple;

public class LoginServiceMongoImpl implements LoginService {

	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	LoginRepoMongoImpl loginRepoMongoImpl;
	
	@Autowired
	JwtUtil jwtUtil;
	
	
	@Autowired
	UserDetailsServiceImple userDetailsServiceImple;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public String authenticate(User user) {
		// TODO Auto-generated method stub
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					user.getUserName(),user.getPassword()));
			
		}
		catch (AuthenticationException e) {
			return e.toString();
			// TODO: handle exception
		}
		
		return jwtUtil.generateToken(user.getUserName());
	}

	@Override
	public Boolean logout(String authToken) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		UserDocument userDocument = ConvertDTOIntoDocument(user);
		User userResponse= ConvertDocumentIntoDTO(loginRepoMongoImpl.save(userDocument));
		return userResponse;
	}

	@Override
	public User getUser(String userToken) {
		// TODO Auto-generated method stub
		String Username = jwtUtil.extractUsername(userToken);
		List<UserDocument> userDocumentLists = loginRepoMongoImpl.findByUserName(Username);
		User user = ConvertDocumentIntoDTO(userDocumentLists.get(0));
		return user;
	}

	@Override
	public Boolean tokenValidatation(String userTokenWithBearer) {
		// TODO Auto-generated method stub
		
		String userToken = userTokenWithBearer.substring(7);
		UserDetails userDetails =   userDetailsServiceImple.loadUserByUsername(jwtUtil.extractUsername(userToken));
		return jwtUtil.validateToken(userToken, userDetails);
	}
	
	public User ConvertDocumentIntoDTO(UserDocument userDocument) {
		
		User user = modelMapper.map(userDocument, User.class);
		return user;
	}
	
	public UserDocument ConvertDTOIntoDocument(User user) {
		UserDocument userDocument = modelMapper.map(user, UserDocument.class);
		return userDocument;
	}
	

}
