package com.advanceweb.afc.jb.login.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.user.UserService;
/**
* SocialLoginAdapter that bridges between a SocialLoginController and a jobboard-specific user login service.
* Invoked at the end of a service provider sign-in attempt to sign-in the local user account associated with the service provider user account.
*/
public class SocialLoginAdapter implements SignInAdapter {

	@Autowired
	protected AuthenticationManager customAuthenticationManager;
	
	@Autowired
	private UserService userService;
/**
* Complete a service provider login attempt by signing in the local user account with the specified id.
* @param userId the local user id
* @param connection the connection
* @param request a reference to the current web request; is a "native" web request instance providing access to the native
* request and response objects, such as a HttpServletRequest and HttpServletResponse, if needed
* @return the URL that ProviderSignInController should redirect to after sign in. May be null, indicating that SocialLoginController
* should redirect to its postSignInUrl.
*/
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
		String result=null;
		UserDTO user = userService.getUser(localUserId);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmailId(), user.getPassword(), null);
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);
		if(authenticatedUser!=null){
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		result="/signin/socialSignIn.html?pageValue=";
		}
        return result;
    }
}
