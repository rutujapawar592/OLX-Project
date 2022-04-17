package com.olx.login.security;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.login.entity.UserEntity;
import com.olx.login.repository.LoginRepo;


@Service
public class UserDetailsServiceImple implements UserDetailsService {

	@Autowired
	LoginRepo loginRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		List<UserEntity> userEntitiesList = loginRepo.findByUserName(username);
		
		if(userEntitiesList == null || userEntitiesList.size() == 0)
		{
			throw new UsernameNotFoundException(username);
		}
		
		UserEntity userEntity = userEntitiesList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
 		UserDetails userDetails = new User(username, this.passwordEncoder.encode(userEntity.getPassword()),authorities);
		return userDetails;
	}

}
