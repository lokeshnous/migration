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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * A  authentication manager that allows access if the user details exist
 * in the database otherwise, throw a {@link BadCredentialsException}
 */
public class DatabaseAuthenticationManager implements AuthenticationManager {
	
	private static final Logger LOGGER = Logger
			.getLogger(DatabaseAuthenticationManager.class);


	@Autowired
	private UserDao userDAO;

	@Value("${userNotExist}")
	private String userNotExist;

	@Value("${wrongPassword}")
	private String wrongPassword;

	/**
	 * This method is used to authenticate the use with DB 
	 * @param Authentication object, which contains the user id and password provided by the user 
	 * @return Authentication
	 */
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		UserDTO user = null;
		// This is used to check if user authenticated with Open AM.
//				boolean isAuthenticated = OpenAMEUtility.getOpenAMAuthentication(auth.getName(),auth.getCredentials().toString());
//				LOGGER.info("OpenAM authentication - "+isAuthenticated);
				//OpenAM Code Ends

		
		try {
			user = userDAO.getUser(auth.getName());
			if (user == null) {
				LOGGER.debug("User not found with the given email id:"+auth.getName());
				throw new BadCredentialsException(userNotExist);
			}
		} catch (Exception e) {
			LOGGER.debug("Error while fetching the data with the given email id:"+auth.getName());
			throw new BadCredentialsException(userNotExist);
		}
		if (!(user.getPassword().equals(auth.getCredentials()))) {
			LOGGER.debug("User password is not matching with the given password");
			throw new BadCredentialsException(wrongPassword);
		}
		return new UsernamePasswordAuthenticationToken(auth.getName(),
				auth.getCredentials(), getAuthorities(user.getUserId()));
	}

	/**
	 * This method is used to get the roles of the corresponding user
	 * @param int userId
	 * @return Collection<SimpleGrantedAuthority>
	 */
	public Collection<SimpleGrantedAuthority> getAuthorities(int userId) {
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();

		List<UserRoleDTO> roleList = userDAO.getUserRole(userId);
		for (UserRoleDTO userRole : roleList) {
			
			isMerionAdmin(userRole.getRoleName(),authList);
			
		    isJobSeeker(userRole.getRoleName(),authList);
			
		    isFacilityAdmin(userRole.getRoleName(),authList);
			
		    isFacilityUser(userRole.getRoleName(),authList);
			
		    isFacility(userRole.getRoleName(),authList);
			
		    isFacilityGroupe(userRole.getRoleName(),authList);
			
		    isFacilitySystem(userRole.getRoleName(),authList);
			
		    isFullAccess(userRole.getRoleName(),authList);
			
		    isPostEdit(userRole.getRoleName(),authList);
		}
		return authList;
	}
	
	/**
	 * This method is used to add the role of Merion Admin 
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isMerionAdmin(String roleName,List<SimpleGrantedAuthority> authList){
		if (roleName.equals(MMJBCommonConstants.MERION_ADMIN)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_MERION_ADMIN));
		}
	}
	
	/**
	 * This method is used to add the role of Job Seeker 
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isJobSeeker(String roleName,List<SimpleGrantedAuthority> authList){
		if (roleName.equals(MMJBCommonConstants.JOBSEEKER)) {
			authList.add(new SimpleGrantedAuthority(
					MMJBCommonConstants.ROLE_JOB_SEEKER));
		}
	}
	
	/**
	 * This method is used to add the role of Facility Admin 
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isFacilityAdmin(String roleName,List<SimpleGrantedAuthority> authList){
	if (roleName.equals(
			MMJBCommonConstants.FACILITY_ADMIN)) {
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_ADMIN));
	}
	}
	
	/**
	 * This method is used to add the role of Facility User 
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isFacilityUser(String roleName,List<SimpleGrantedAuthority> authList){
	if (roleName
			.equals(MMJBCommonConstants.FACILITY_USER)) {
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_USER));
	}
	}
	/**
	 * This method is used to add the role of Facility
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isFacility(String roleName,List<SimpleGrantedAuthority> authList){
	if (roleName.equals(MMJBCommonConstants.FACILITY)) {
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY));
	}
	}
	
	/**
	 * This method is used to add the role of Facility Group
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isFacilityGroupe(String roleName,List<SimpleGrantedAuthority> authList){
	if (roleName.equals(
			MMJBCommonConstants.FACILITY_GROUP)) {
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_GROUP));
	}	
}
	/**
	 * This method is used to add the role of Facility System
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isFacilitySystem(String roleName,List<SimpleGrantedAuthority> authList){
	if (roleName.equals(
			MMJBCommonConstants.FACILITY_SYSTEM)) {
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_SYSTEM));
	}
	}
	/**
	 * This method is used to add the role of Full Access
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isFullAccess(String roleName,List<SimpleGrantedAuthority> authList){
	if (roleName.equals(
			MMJBCommonConstants.FACILITY_FULL_ACCESS)) {
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_FULL_ACCESS));
	}
	}
	/**
	 * This method is used to add the role of Post Edit
	 * @param String roleName
	 * @param List<SimpleGrantedAuthority> authList
	 */
	private void isPostEdit(String roleName,List<SimpleGrantedAuthority> authList){
	if (roleName.equals(
			MMJBCommonConstants.FACILITY_POST_EDIT)) {
		authList.add(new SimpleGrantedAuthority(
				MMJBCommonConstants.ROLE_FACILITY_POST_EDIT));
	}
	}
}
