package com.advanceweb.afc.jb.login.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.user.UserService;

/**
 * Controller for handling the service provider user login flow.
 */
@Controller
@RequestMapping("/signin")
public class SocialLoginController {

	private static final Logger LOGGER = Logger
			.getLogger(SocialLoginController.class);
	private static final String PROVIDER_ID="/{providerId}";
	@Value("${loginErrMsg}")
	private String loginErrMsg;
	@Autowired
	private LoginManager loginSuccessManager;
	@Autowired
	protected AuthenticationManager customAuthenticationManager;
	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final SignInAdapter signInAdapter;
	@Value("${application.signInUrl}")
	private String signInUrl;
	@Value("${application.signUpUrl}")
	private String signUpUrl;
	@Value("${application.postUrl}")
	private String postSignInUrl ;
	private final ConnectSupport webSupport = new ConnectSupport();
	@Autowired
	private UserService userService;

	/**
	 * Creates a new provider sign-in controller.
	 * 
	 * @param connectionFactoryLocator
	 *            the locator of {@link ConnectionFactory connection factories}
	 *            used to support service provider login.
	 * @param signInAdapter
	 *            handles user sign-in
	 */
	@Inject
	public SocialLoginController(
			ConnectionFactoryLocator connectionFactoryLocator,
			SignInAdapter signInAdapter) {
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.signInAdapter = signInAdapter;
		this.webSupport.setUseAuthenticateUrl(true);
	}

	/**
	 * Sets the URL of the application's sign in page.
	 * 
	 * @param signInUrl
	 *            the signIn URL
	 */
	public void setSignInUrl(String signInUrl) {
		this.signInUrl = signInUrl;
	}

	/**
	 * Sets the URL to redirect the user to if no local user account can be
	 * mapped when signing in using a service provider.
	 * 
	 * @param signUpUrl
	 *            the signUp URL
	 */
	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	/**
	 * Sets the default URL to redirect the user to after signing in using a
	 * service provider.
	 * 
	 * @param postSignInUrl
	 *            the postSignIn URL
	 */
	public void setPostSignInUrl(String postSignInUrl) {
		this.postSignInUrl = postSignInUrl;
	}

	/**
	 * Configures the base secure URL for the application. If specified, will be
	 * used to generate OAuth callback URLs. If not specified, OAuth callback
	 * URLs are generated from web request info.
	 * 
	 * @param applicationUrl
	 *            the application URL value
	 */
	public void setApplicationUrl(String applicationUrl) {
		webSupport.setApplicationUrl(applicationUrl);
	}

	/**
	 * Process a sign-in form submission by commencing the process of
	 * establishing a connection to the provider on behalf of the user. For
	 * OAuth1, fetches a new request token from the provider, temporarily stores
	 * it in the session, then redirects the user to the provider's site for
	 * authentication authorization. For OAuth2, redirects the user to the
	 * provider's site for authentication authorization.
	 */
	@RequestMapping(value = PROVIDER_ID, method = RequestMethod.POST)
	public RedirectView signIn(@PathVariable String providerId,
			NativeWebRequest request) {
		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);
		request.setAttribute(MMJBCommonConstants.PAGE_VALUE, pageValue,
				RequestAttributes.SCOPE_SESSION);
		ConnectionFactory<?> connectionFactory = connectionFactoryLocator
				.getConnectionFactory(providerId);
		try {
			return new RedirectView(webSupport.buildOAuthUrl(connectionFactory,
					request));
		} catch (Exception e) {
			LOGGER.info("Exception while process a sign-in form submission ("
					+ e.getMessage() + "). Redirecting to " + signInUrl);
			return redirect(URIBuilder.fromUri(signInUrl)
					.queryParam("error", "provider").build().toString());
		}
	}

	/**
	 * Process the authentication callback from an OAuth 1 service provider.
	 * Called after the member authorizes the authentication, generally done
	 * once by having he or she click "Allow" in their web browser at the
	 * provider's site. Handles the provider sign-in callback by first
	 * determining if a local user account is associated with the connected
	 * provider account. If so, signs the local user in by delegating to
	 * SocialLoginAdapter If not, redirects the user to a signup page to create
	 * a new account.
	 */
	@RequestMapping(value = PROVIDER_ID, method = RequestMethod.GET, params = "oauth_token")
	public RedirectView oauth1Callback(@PathVariable String providerId,
			NativeWebRequest request) {
		try {
			OAuth1ConnectionFactory<?> connectionFactory = (OAuth1ConnectionFactory<?>) connectionFactoryLocator
					.getConnectionFactory(providerId);
			Connection<?> connection = webSupport.completeConnection(
					connectionFactory, request);
			return handleSignIn(connection, request);
		} catch (Exception e) {
			LOGGER.info("Exception while handling OAuth1 callback ("
					+ e.getMessage() + "). Redirecting to " + signInUrl);
			return redirect(URIBuilder.fromUri(signInUrl)
					.queryParam("error", "provider").build().toString());
		}
	}

	/**
	 * Process the authentication callback from an OAuth 2 service provider.
	 * Called after the user authorizes the authentication. Handles the service
	 * provider sign-in callback by first determining if a local user account is
	 * associated with the connected service provider account. If so, signs the
	 * local user in by delegating to SocialLoginAdapter. If not, redirects the
	 * user to a signup page to create a new account or link the existing
	 * account.
	 */
	@RequestMapping(value = PROVIDER_ID, method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(@PathVariable String providerId,
			@RequestParam("code") String code, NativeWebRequest request) {
		try {
			OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator
					.getConnectionFactory(providerId);
			Connection<?> connection = webSupport.completeConnection(
					connectionFactory, request);
			return handleSignIn(connection, request);
		} catch (Exception e) {
			LOGGER.info("Exception while handling OAuth2 callback ("
					+ e.getMessage() + "). Redirecting to " + signInUrl);
			return redirect(URIBuilder.fromUri(signInUrl)
					.queryParam("error", "provider").build().toString());
		}
	}

	/**
	 * Process the authentication callback when neither the oauth_token or code
	 * parameter is given, likely indicating that the user denied authorization
	 * with the service provider. Redirects to application's sign in URL, as set
	 * in the signInUrl property.
	 */
	@RequestMapping(value = PROVIDER_ID, method = RequestMethod.GET)
	public RedirectView canceledAuthorizationCallback() {
		return redirect(signInUrl);
	}

	// internal helper method,used to redirect user to sign up page or dash
	// board depending on user's social media profile is linked to DB
	private RedirectView handleSignIn(Connection<?> connection,
			NativeWebRequest request) {
		String pageValue = (String) request
				.getAttribute(MMJBCommonConstants.PAGE_VALUE,
						RequestAttributes.SCOPE_SESSION);
		UserDTO userDTO = null;
		String profileId = null;
		if (connection.getApi() instanceof Facebook) {
			Facebook facebook = (Facebook) connection.getApi();
			FacebookProfile profile = facebook.userOperations()
					.getUserProfile();
			profileId = profile.getId();
		}
		if (connection.getApi() instanceof LinkedIn) {
			LinkedIn linkedin = (LinkedIn) connection.getApi();
			profileId = linkedin.profileOperations().getUserProfile().getId();
		}
		try {
			userDTO = userService.getUserBySocialProfileId(profileId);
		} catch (JobBoardException e) {
			LOGGER.info("Error occurred while fetching the user based on the social profile id"+e.getMessage());
		}
		if (userDTO == null) {
			SocialConnectionManager signInAttempt = new SocialConnectionManager(
					connection, connectionFactoryLocator);
			request.setAttribute(SocialConnectionManager.SESSION_ATTRIBUTE,
					signInAttempt, RequestAttributes.SCOPE_SESSION);
			return redirect(signUpUrl + pageValue);
		} else {
			String originalUrl = signInAdapter.signIn(userDTO.getEmailId(),
					connection, request);
			return originalUrl != null ? redirect(originalUrl + pageValue)
					: redirect(postSignInUrl);
		}
	}

	// internal helper method
	private RedirectView redirect(String url) {
		return new RedirectView(url, true);
	}

	/**
	 * Process the callback when a user is not registered in the application
	 * with his social media profile.
	 */
	@RequestMapping(value = "/socialSignUp")
	public ModelAndView userSignUp(
			WebRequest request,
			@RequestParam(value = "pageValue", required = false) String pageValue) {
		Connection<?> connection = SocialLoginUtils.getConnection(request);
		return getUserPage(connection, pageValue);
	}

	/**
	 * Process the callback when a user is registered in the application.
	 */
	@RequestMapping(value = "/socialSignIn")
	public void userSignIn(
			@RequestParam(value = "pageValue", required = false) String pageValue,
			HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		try {
			request.setAttribute("socialSignUp", "socialSignUp");
			loginSuccessManager.onAuthenticationSuccess(request, response,
					authentication);
		} catch (Exception e) {
			LOGGER.info("Exception while processing the callback when a user is registered in the application ("
					+ e.getMessage() + ")");
		}
	}

	// internal method,used to redirect to the common social media login page
	private ModelAndView getUserPage(Connection<?> connection, String pageValue) {
		ModelAndView model = new ModelAndView();
		String profileId = null;
		String serviceProviderId = null;
		SocialLoginForm loginform = new SocialLoginForm();
		if (connection.getApi() instanceof Facebook) {
			Facebook facebook = (Facebook) connection.getApi();
			FacebookProfile profile = facebook.userOperations()
					.getUserProfile();
			profileId = profile.getId();
			serviceProviderId = MMJBCommonConstants.FACEBOOK;
		}
		if (connection.getApi() instanceof LinkedIn) {
			LinkedIn linkedin = (LinkedIn) connection.getApi();
			profileId = linkedin.profileOperations().getUserProfile().getId();
			serviceProviderId = MMJBCommonConstants.LINKEDIN;
		}
		loginform.setPageValue(pageValue);
		loginform.setProfileId(profileId);
		loginform.setServiceProviderId(serviceProviderId);
		model.addObject("socialLoginForm", loginform);
		model.setViewName("commonSocialLogin");
		return model;
	}

	/**
	 * Process the callback when a user is registered in the application and
	 * first time login with his social media account(e.g. Facebook,LinkedIn).
	 * Redirect to the user's dash board and update his social media account
	 * profileId in the DB
	 */
	@RequestMapping(value = "/verifyUserAccount")
	public ModelAndView verifyUserAccount(
			@ModelAttribute("socialLoginForm") SocialLoginForm socialLoginForm,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		int profileAttrId=0;
		model.addObject("socialLoginForm", socialLoginForm);
		model.setViewName("commonSocialLogin");
		//UserDTO user = userService.getUser(socialLoginForm.getEmailId());
		UserDTO user = userService.getAdvancePassUser(socialLoginForm.getEmailId());
		if (user == null) {
			return addErrorMessage(model,socialLoginForm);
		} else if (!(socialLoginForm.getPassword().equals(user.getPassword()))) {
			return addErrorMessage(model,socialLoginForm);
		}
		String pageValue = request.getParameter(MMJBCommonConstants.PAGE_VALUE);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getEmailId(), user.getPassword(), null);
		Authentication authenticatedUser = customAuthenticationManager
				.authenticate(token);
		
		if(checkUserRoute(pageValue,authenticatedUser)){
			return addErrorMessage(model,socialLoginForm);
		}
		
		if (authenticatedUser != null) {
			SecurityContextHolder.getContext().setAuthentication(
					authenticatedUser);
			if(socialLoginForm.getServiceProviderId().equals(MMJBCommonConstants.FACEBOOK)){
				profileAttrId=Integer.parseInt(MMJBCommonConstants.FACEBOOK_PROFILE_ATTR_ID);
				}
				if(socialLoginForm.getServiceProviderId().equals(MMJBCommonConstants.LINKEDIN)){
					profileAttrId=Integer.parseInt(MMJBCommonConstants.LINKEDIN_PROFILE_ATTR_ID);
				}
			try {
				UserDTO userDTO = userService.getUser(socialLoginForm.getEmailId());
				userService.updateSocialProfileId(userDTO.getUserId(),
						socialLoginForm.getProfileId(),
						profileAttrId);
				loginSuccessManager.onAuthenticationSuccess(request, response,
						authenticatedUser);
			}catch (JobBoardException e) {
				LOGGER.info("Exception while updating the social profile id)"+e.getMessage());
			}
			catch (Exception e) {
				LOGGER.info("Exception while processing the callback when a user is registered in the application and first time login with his social media account("
						+ e.getMessage() + ")");
			}
		}
		return null;
	}
	
/**
* Internal method used to add the validation message
*/	
private ModelAndView addErrorMessage(ModelAndView model,SocialLoginForm socialLoginForm){
	model.addObject("errorMessage", loginErrMsg);
	socialLoginForm.setError(true);
	return model;
}

/**
 * Internal method used to check the route from where the user started the login process
 * @param String pageValue, from where the user is started the login process
 * @param Authentication authenticatedUser, contains all the authentication details 
 * @return boolean value, return true if user role is not matching with the page value from where user started the login process,otherwise false.
 */
private boolean checkUserRoute(String pageValue,Authentication authenticatedUser){
	boolean result=false;
	if(pageValue.equals(MMJBCommonConstants.JOB_SEEKER)&&!loginSuccessManager.isJobSeeker(authenticatedUser, pageValue,"jobseekerRegistration")){
		result=true;
	}
	else if(pageValue.equals(MMJBCommonConstants.EMPLOYER)&&!loginSuccessManager.isFacility(authenticatedUser, pageValue,"employerRegistration")){
		result=true;
	}
	else if(pageValue.equals(MMJBCommonConstants.AGENCY)&& !loginSuccessManager.isFacilitySystem(authenticatedUser, pageValue,"agencyRegistration")){
		result=true;
	}
	return result;
}
}