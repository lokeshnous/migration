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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * A custom authentication manager that allows access if the user details
 * exist in the database otherwise, throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	UserDao userDAO;
	@Value("${userNotExist}")
	private String userNotExist;
	@Value("${wrongPassword}")
	private String wrongPassword;
		
	public Authentication authenticate(Authentication auth)throws AuthenticationException {
		MerUser user=null;		
		
		try {
			
			user = userDAO.getUser(auth.getName());
			if(user==null){
				throw new BadCredentialsException(userNotExist);
			}
		} catch (Exception e) {
			throw new BadCredentialsException(userNotExist);
		}
		if(!(user.getPassword().equals(auth.getCredentials()))){
			throw new BadCredentialsException(wrongPassword);
		}
			return new UsernamePasswordAuthenticationToken(
					auth.getName(), 
					auth.getCredentials(), 
					getAuthorities(user.getUserId()));
	}
	
	 public Collection<GrantedAuthority> getAuthorities(int userId) {
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			
			List<AdmUserRole> roleList=userDAO.getUserRole(userId);
			List userRole=new ArrayList();
			for(AdmUserRole role:roleList){
				userRole.add(role.getAdmRole().getName());
			}
			   
			if ( userRole.contains(MMJBCommonConstants.MERION_ADMIN)) {
				authList.add(new GrantedAuthorityImpl(MMJBCommonConstants.ROLE_MERION_ADMIN));
			}
			 
			 if ( userRole.contains(MMJBCommonConstants.JOBSEEKER)) {
					authList.add(new GrantedAuthorityImpl(MMJBCommonConstants.ROLE_JOB_SEEKER));
				}
			 
			 if ( userRole.contains(MMJBCommonConstants.FACILITY_ADMIN)) {
					authList.add(new GrantedAuthorityImpl(MMJBCommonConstants.ROLE_FACILITY_ADMIN));
				}

			 if ( userRole.contains(MMJBCommonConstants.FACILITY_USER)) {
					authList.add(new GrantedAuthorityImpl(MMJBCommonConstants.ROLE_FACILITY_USER));
				}
			return authList;
	  }

}
