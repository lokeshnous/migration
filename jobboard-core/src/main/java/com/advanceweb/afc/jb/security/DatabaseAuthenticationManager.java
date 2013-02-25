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


	@Value("${wrongPassword}")
	private String wrongPassword;

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
		// This is used to check if user authenticated with Open AM.
		// boolean isAuthenticated =
		// OpenAMEUtility.getOpenAMAuthentication(auth.getName(),auth.getCredentials().toString());
		// LOGGER.info("OpenAM authentication - "+isAuthenticated);
		// OpenAM Code Ends

		try {
			if(!(auth instanceof PreAuthenticatedAuthenticationToken)){
//			validUser=authenticationDelegate.validateUser(auth.getName().toString(), auth.getCredentials().toString());
				//validUser=true;
//			validUser=authenticationDelegate.validateUser("harsha@gmail.com", "harsha123");
//			validUser=authenticationDelegate.validateUser("fdnyrk@sbcglobal.net", "yo040204");
			}
			UserDTO userDTO=userService.getAdvancePassUser(auth.getPrincipal().toString());
			if(userDTO==null){
				throw new BadCredentialsException(userNotExist);
			}
			if(userDTO!=null && !userDTO.getPassword().equals( auth.getCredentials())){
				throw new BadCredentialsException(userNotExist);
			}
			user = userDAO.getUser(auth.getName());
//			if(validUser || auth instanceof PreAuthenticatedAuthenticationToken){
//			user = userDAO.getUser(auth.getName());
//			}
//			if (!(validUser || auth instanceof PreAuthenticatedAuthenticationToken)) {
//				LOGGER.debug("User not found with the given email id:"
//						+ auth.getName());
//				throw new BadCredentialsException(userNotExist);
//			}
		} catch (Exception e) {
			LOGGER.debug("Error while fetching the data with the given email id:"
					+ auth.getName());
			throw new BadCredentialsException(userNotExist);
		}
//		if (!(user.getPassword().equals(auth.getCredentials()))) {
//			LOGGER.debug("User password is not matching with the given password");
//			throw new BadCredentialsException(wrongPassword);
//		}
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
//		SecurityContextHolder.getContext().setAuthentication(token);
		return new UsernamePasswordAuthenticationToken(auth.getName(),
				auth.getCredentials(), userRoles);
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
			e.printStackTrace();
		}
		
	//	return new User(userDTO.getEmailId(), userDTO.getPassword(), userRoles);
		return new User(advPassUser.getEmailId(), advPassUser.getPassword(), userRoles);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.AuthenticationUserDetailsService#loadUserDetails(org.springframework.security.core.Authentication)
	 */
	@Override
	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		LOGGER.debug("loadUserDetails===>");
		/*if(!token.isAuthenticated()){
			throw new BadCredentialsException(userNotExist);
		}*/
		 SecurityContextHolder.getContext().setAuthentication(token);
		return loadUserByUsername(token.getPrincipal().toString());
	}
	/*protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
		 try {
			loginManager.onAuthenticationSuccess(request, response, authResult);
		} catch (Exception e) {
			System.out.println("exception in onAuthenticationSuccess");
			e.printStackTrace();
		}
	    }*/
	/*protected  void additionalAuthenticationChecks(UserDetails userDetails,
	        UsernamePasswordAuthenticationToken authentication)
	        throws AuthenticationException{
		System.out.println("in additional");
		if (!(userDetails.getPassword().equals(authentication.getCredentials()))) {
			LOGGER.debug("User password is not matching with the given password");
			throw new BadCredentialsException(wrongPassword);
		}
	}*/

}
