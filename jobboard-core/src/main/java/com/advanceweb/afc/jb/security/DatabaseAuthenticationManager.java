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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.user.UserService;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * A authentication manager that allows access if the user details exist in the
 * database otherwise, throw a {@link BadCredentialsException}
 */
public class DatabaseAuthenticationManager extends DaoAuthenticationProvider implements AuthenticationManager,
		UserDetailsService ,AuthenticationUserDetailsService{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(DatabaseAuthenticationManager.class);

	/** The user dao. */
	@Autowired
	private UserDao userDAO;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The authentication delegate. */
	@Autowired
	private DatabaseAuthenticationDelegate authenticationDelegate;

	/** The user not exist. */
	@Value("${userNotExist}")
	private String userNotExist;


	/**
	 * This method is used to authenticate the use with DB
	 * 
	 * @param Authentication
	 *            object, which contains the user id and password provided by
	 *            the user
	 * @return Authentication
	 */
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		UserDTO user = null;
		boolean validUser=false;

		try {
			if(!(auth instanceof PreAuthenticatedAuthenticationToken)){
			validUser=authenticationDelegate.validateUser(auth.getName().toString(), auth.getCredentials().toString());
			}
			if(validUser || auth instanceof PreAuthenticatedAuthenticationToken){
			user = userDAO.getUser(auth.getName());
			}
			if(validUser && user==null){
				List<SimpleGrantedAuthority> AdvPassUserRoleList = new ArrayList<SimpleGrantedAuthority>();
				AdvPassUserRoleList.add(new SimpleGrantedAuthority(MMJBCommonConstants.ROLE_ADVANCE_PASS_USER));
				return new UsernamePasswordAuthenticationToken(auth.getName(),
						auth.getCredentials(), AdvPassUserRoleList);
			}
			if (!(validUser || auth instanceof PreAuthenticatedAuthenticationToken)) {
				LOGGER.debug("User not found with the given email id:"
						+ auth.getName());
				throw new BadCredentialsException(userNotExist);
			}
		
			if(validUser && user==null){
				LOGGER.info("user with email : "
						+ auth.getName()+" is authenticated through Advance Pass but user is not available in JB2 DB");
				throw new BadCredentialsException(userNotExist);
			}
		Collection<SimpleGrantedAuthority> userRoles=null;
		userRoles = getAuthorities(user.getUserId());
		if(userRoles.isEmpty()){
			LOGGER.debug("No Roles associated with the user :"
					+ auth.getName());
			throw new BadCredentialsException(userNotExist);
		}
		if(user.isAdmin()){
			userRoles.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_MERION_ADMIN));
		}
		return new UsernamePasswordAuthenticationToken(auth.getName(),
				auth.getCredentials(), userRoles);
		} catch (Exception e) {
			LOGGER.error("Error while fetching the data with the given email id:"
					+ auth.getName());
			throw new BadCredentialsException(userNotExist);
		}
	}
			

	/**
	 * This method is used to get the roles of the corresponding user
	 * 
	 * @param int userId
	 * @return Collection<SimpleGrantedAuthority>
	 */
	public Collection<SimpleGrantedAuthority> getAuthorities(int userId) {
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
		
		List<UserRoleDTO> roleList = userDAO.getUserRole(userId);
		for (UserRoleDTO userRole : roleList) {

			isMerionAdmin(userRole.getRoleName(), authList);

			isJobSeeker(userRole.getRoleName(), authList);

			isFacilityAdmin(userRole.getRoleName(), authList);

			isFacilityUser(userRole.getRoleName(), authList);

			isFacility(userRole.getRoleName(), authList);

			isFacilityGroupe(userRole.getRoleName(), authList);

			isFacilitySystem(userRole.getRoleName(), authList);

			isFullAccess(userRole.getRoleName(), authList);

			isPostEdit(userRole.getRoleName(), authList);
		}
		return authList;
	}

	/**
	 * This method is used to add the role of Merion Admin
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isMerionAdmin(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.MERION_ADMIN)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_MERION_ADMIN));
		}
	}

	/**
	 * This method is used to add the role of Job Seeker
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isJobSeeker(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.JOBSEEKER)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_JOB_SEEKER));
		}
	}

	/**
	 * This method is used to add the role of Facility Admin
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isFacilityAdmin(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.FACILITY_ADMIN)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_FACILITY_ADMIN));
		}
	}

	/**
	 * This method is used to add the role of Facility User
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isFacilityUser(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.FACILITY_USER)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_FACILITY_USER));
		}
	}

	/**
	 * This method is used to add the role of Facility
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isFacility(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.FACILITY)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_FACILITY));
		}
	}

	/**
	 * This method is used to add the role of Facility Group
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isFacilityGroupe(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.FACILITY_GROUP)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_FACILITY_GROUP));
		}
	}

	/**
	 * This method is used to add the role of Facility System
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isFacilitySystem(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.FACILITY_SYSTEM)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_FACILITY_SYSTEM));
		}
	}

	/**
	 * This method is used to add the role of Full Access
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isFullAccess(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.FACILITY_FULL_ACCESS)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_FACILITY_FULL_ACCESS));
		}
	}

	/**
	 * This method is used to add the role of Post Edit
	 * 
	 * @param String
	 *            roleName
	 * @param List
	 *            <SimpleGrantedAuthority> authList
	 */
	private void isPostEdit(String roleName,
			List<SimpleGrantedAuthority> authList) {
		if (roleName.equals(MMJBCommonConstants.FACILITY_POST_EDIT)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_FACILITY_POST_EDIT));
		}
	}

	/**
	 * This method is used by
	 * {@link org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices}
	 * and
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
	 * class to Locates the user based on the emailId.
	 * 
	 * @param String
	 *            emailId, the emailId identifying the user whose data is
	 *            required.
	 * @return UserDetailsobject, a fully populated user record.
	 * @throws UsernameNotFoundException
	 *             if the user could not be found.
	 */
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		UserDTO userDTO = null;
		userDTO = userDAO.getUser(email);
		if (userDTO == null) {
			throw new UsernameNotFoundException(
					"Could not find user with emailId '" + email + "'");
		}
		Collection<SimpleGrantedAuthority> userRoles=null;
		UserDTO advPassUser=null;
		try {
			 userRoles = getAuthorities(userDTO
					.getUserId());
			
			if(userDTO.isAdmin()){
				userRoles.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_MERION_ADMIN));
			}
			advPassUser=userService.getAdvancePassUser(email);
		} catch (Exception e) {
			LOGGER.debug("error in loadUserByUsername :"+e.getMessage());
		}
		
		return new User(advPassUser.getEmailId(), advPassUser.getPassword(), userRoles);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.AuthenticationUserDetailsService#loadUserDetails(org.springframework.security.core.Authentication)
	 */
	@Override
	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		LOGGER.debug(" in loadUserDetails method===>");
		 SecurityContextHolder.getContext().setAuthentication(token);
		return loadUserByUsername(token.getPrincipal().toString());
	}
	
}
