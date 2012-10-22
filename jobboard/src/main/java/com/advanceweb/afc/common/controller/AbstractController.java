package com.advanceweb.afc.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.common.client.ClientContext;

public abstract class AbstractController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private ProfileRegistration profileRegistration;

	@Value("${client.application}")
	private String clientApplication;

	/**
	 * Get the client context details
	 * 
	 * @param request
	 * @param session
	 * @param string 
	 * @return
	 */
	protected ClientContext getClientContextDetails(HttpServletRequest request,
			HttpSession session, String pageName) {
		ClientContext clientContext = new ClientContext();
		clientContext.setProperty(ClientContext.CLIENT_APPLICATION,
				clientApplication);
		clientContext.setProperty(ClientContext.CLIENT_USER_AGENT,
				request.getHeader(MMJBCommonConstants.USER_AGENT));
		clientContext.setProperty(ClientContext.CLIENT_SESSIONID,
				session.getId());
		clientContext.setProperty(ClientContext.CLIENT_IP,
				request.getLocalAddr());
		clientContext.setProperty(ClientContext.CLIENT_PAGE, pageName);
		clientContext.setProperty(ClientContext.CLIENT_REFERRER,
				request.getHeader(MMJBCommonConstants.REFERER));
		clientContext.setProperty(ClientContext.CLIENT_HOSTNAME,
				request.getHeader(MMJBCommonConstants.HOST));
		clientContext.setProperty(ClientContext.CLIENT_LOCATION, null);
		clientContext.setProperty(ClientContext.CLIENT_REQUEST_URL, request
				.getRequestURL().toString());

		clientContext.setProperty(ClientContext.USER_CURRENT_SEARCH, null);
		clientContext.setProperty(ClientContext.USER_PREVIOUS_SEARCH, null);
		// Check for login User details
		int userId = 0;
		String userLocation = null;
		String userProfession = null;
		String userRole = null;
		String userGender = null;
		if (session.getAttribute(MMJBCommonConstants.USER_ID) != null) {
			userId = (Integer) session.getAttribute(MMJBCommonConstants.USER_ID);
			List<UserRoleDTO> userRoleDTOs = loginService.getUserRole(userId);
			userRole = userRoleDTOs.get(0).getRoleName();
			// TODO: Currently jobseeker is considering
			JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileRegistration
					.viewProfile(userId);
			for (ProfileAttribDTO profileAttribDTO : jsRegistrationDTO.getAttribList()) {

				if (profileAttribDTO.getStrLabelValue() != null
						&& profileAttribDTO.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.MYPROFESSION)) {
					if (!isInteger(profileAttribDTO.getStrLabelValue())) {
						userProfession = profileAttribDTO.getStrLabelValue();
					} else {
						for (DropDownDTO dropDown : profileAttribDTO.getDropdown()) {
							if (MMJBCommonConstants.PROFESSION_OTHERS
									.equals(dropDown.getOptionName())) {
								userProfession = dropDown.getOptionName();
							}
						}
					}
				}
				if (profileAttribDTO.getStrLabelValue() != null
						&& profileAttribDTO.getStrLabelName().equalsIgnoreCase(
								MMJBCommonConstants.CAP_CITY)
						&& !isInteger(profileAttribDTO.getStrLabelValue())) {
					userLocation = profileAttribDTO.getStrLabelValue();
				}
			}

			// TODO:gender not storing
			userGender = null;
		}
		clientContext.setProperty(ClientContext.USER_ID, String.valueOf(userId));
		clientContext.setProperty(ClientContext.USER_LOCATION, userLocation);
		clientContext
				.setProperty(ClientContext.USER_PROFESSION, userProfession);
		clientContext.setProperty(ClientContext.USER_GENDER, userGender);
		clientContext.setProperty(ClientContext.USER_ROLE, userRole);
		return clientContext;
	}
	
	/**
	 * Check for integer to get the other profession value
	 * 
	 * @param input
	 * @return
	 */
	private boolean isInteger(String input) {
		boolean status = false;
		try {
			Integer.parseInt(input);
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

}
