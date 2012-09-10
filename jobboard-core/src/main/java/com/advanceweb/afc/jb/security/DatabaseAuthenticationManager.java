package com.advanceweb.afc.jb.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.user.dao.UserDao;
import org.apache.log4j.Logger;

import com.advanceweb.afc.jb.common.util.OpenAMEUtility;

/**
 * A custom authentication manager that allows access if the user details exist
 * in the database otherwise, throw a {@link BadCredentialsException}
 */
public class DatabaseAuthenticationManager implements AuthenticationManager {
	
	private static final Logger LOGGER = Logger
			.getLogger("DatabaseAuthenticationManager.class");


	@Autowired
	private UserDao userDAO;

	@Value("${userNotExist}")
	private String userNotExist;

	@Value("${wrongPassword}")
	private String wrongPassword;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationManager#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		UserDTO user = null;
		// This is used to check if user authenticated with Open AM.
				boolean isAuthenticated = OpenAMEUtility.getOpenAMAuthentication(auth.getName(),auth.getCredentials().toString());
				LOGGER.info("OpenAM authentication - "+isAuthenticated);
				//OpenAM Code Ends

		
		try {
			user = userDAO.getUser(auth.getName());
			if (user == null) {
				throw new BadCredentialsException(userNotExist);
			}
		} catch (Exception e) {
			throw new BadCredentialsException(userNotExist);
		}
		if (!(user.getPassword().equals(auth.getCredentials()))) {
			throw new BadCredentialsException(wrongPassword);
		}
		return new UsernamePasswordAuthenticationToken(auth.getName(),
				auth.getCredentials(), getAuthorities(user.getUserId()));
	}

	/**
	 * @param userId
	 * @return
	 */
	public Collection<SimpleGrantedAuthority> getAuthorities(int userId) {
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();

		List<UserRoleDTO> roleList = userDAO.getUserRole(userId);
		for (UserRoleDTO userRole : roleList) {
			if (userRole.getRoleName().equals(MMJBCommonConstants.MERION_ADMIN)) {
				authList.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_MERION_ADMIN));
			}
			if (userRole.getRoleName().equals(MMJBCommonConstants.JOBSEEKER)) {
				authList.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_JOB_SEEKER));
			}
			if (userRole.getRoleName().equals(
					MMJBCommonConstants.FACILITY_ADMIN)) {
				authList.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_FACILITY_ADMIN));
			}
			if (userRole.getRoleName()
					.equals(MMJBCommonConstants.FACILITY_USER)) {
				authList.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_FACILITY_USER));
			}
			if (userRole.getRoleName().equals(MMJBCommonConstants.FACILITY)) {
				authList.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_FACILITY));
			}
			if (userRole.getRoleName().equals(
					MMJBCommonConstants.FACILITY_GROUP)) {
				authList.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_FACILITY_GROUP));
			}
			if (userRole.getRoleName().equals(
					MMJBCommonConstants.FACILITY_SYSTEM)) {
				authList.add(new SimpleGrantedAuthority(
						MMJBCommonConstants.ROLE_FACILITY_SYSTEM));
			}
		}
		return authList;
	}

}
