/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * A authentication manager that allows access if the user details exist in the
 * database otherwise, throw a {@link BadCredentialsException}
 */
public class AdminAuthenticationManager implements AuthenticationManager{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AdminAuthenticationManager.class);
	
	/** The user dao. */
	@Autowired
	UserDao userDAO;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication auth)throws AuthenticationException {
		UserDTO user=null;		
		
		try {
			
			user = userDAO.getAdvancePassUser(auth.getName());
			if(user==null){
				LOGGER.debug("user not present in Advance Pass");
				throw new BadCredentialsException("User does not exists!");
			}
		 
		if(!(user.getPassword().equals(auth.getCredentials()))){
			LOGGER.debug("Invalid Password");
			throw new BadCredentialsException("Wrong password!");
		}
		UserDTO adminInfo=userDAO.getAdminInfo(auth.getName());
		if(adminInfo!=null){
			boolean validUser=userDAO.isAdmin(adminInfo.getUserId());
			if(!validUser){
				LOGGER.debug("user is not present in Admin role");
				throw new BadCredentialsException("user is not present in Admin role");
			}
			
		}else{
			LOGGER.debug("user not present in JB2 Database");
			throw new BadCredentialsException("user not present in JB2 Database");
		}
		
			return new UsernamePasswordAuthenticationToken(
					auth.getName(), 
					auth.getCredentials(), 
					getAuthorities());
		}
		catch (BadCredentialsException e) {
				throw new BadCredentialsException("Exception while authenticating Admin");
			}
		catch (Exception e) {
			  LOGGER.debug("Exception while authenticating Admin");
				throw new BadCredentialsException("Exception while authenticating Admin");
			}
	}
	
	 /**
 	 * Gets the authorities.
 	 *
 	 * @return the authorities
 	 */
 	public Collection<SimpleGrantedAuthority> getAuthorities() {
			List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
			authList.add(new SimpleGrantedAuthority("ROLE_MERION_ADMIN"));
			return authList;
	  }


}
