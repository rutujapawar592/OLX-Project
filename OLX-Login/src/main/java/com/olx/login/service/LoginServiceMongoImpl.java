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
import com.olx.login.repository.BlacklistedTokensDocumentRepo;

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
	BlacklistedTokensDocumentRepo blacklistedTokensDocumentRepo;
	
	@Autowired
	JwtUtil jwtUtil;
	
	
	@Autowired
	UserDetailsServiceImple userDetailsServiceImple;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public String authenticate(User user) {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					user.getUserName(),user.getPassword()));
			
		}
		catch (AuthenticationException e) {
			return e.toString();
		}
		
		return jwtUtil.generateToken(user.getUserName());
	}

	@Override
	public Boolean logout(String authToken) {
		
	BlackListedTokenDocumnents blackListedTokenDocumnents = blacklistedTokensDocumentRepo.findByToken(authToken);
		if (blackListedTokenDocumnents != null) {
			throw new InvalidAuthTokenException();
		}

		blackListedTokenDocumnents = new BlackListedTokenDocumnents(authToken, LocalDate.now());
		blacklistedTokensDocumentRepo.save(blackListedTokenDocumnents);
		return true;	
	
	}

	@Override
	public User register(User user) {
		UserDocument userDocument = ConvertDTOIntoDocument(user);
		User userResponse= ConvertDocumentIntoDTO(loginRepoMongoImpl.save(userDocument));
		return userResponse;
	}

	@Override
	public User getUser(String userToken) {
		String Username = jwtUtil.extractUsername(userToken);
		List<UserDocument> userDocumentLists = loginRepoMongoImpl.findByUserName(Username);
		User user = ConvertDocumentIntoDTO(userDocumentLists.get(0));
		return user;
	}

	@Override
	public Boolean tokenValidatation(String userTokenWithBearer) {
		
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
