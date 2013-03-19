package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.UserService;

public class LogoutManager extends SimpleUrlLogoutSuccessHandler {

	/** The job seeker job detail service. */
	@Autowired
	private JobSeekerJobDetailService jobSeekerJobDetailService;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The email service. */
	@Autowired
	private MMEmailService emailService;
	
	/** The advance web address. */
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	
	/** The dothtml extention. */
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;
	
	/** The navigation path. */
	@Value("${navigationPath}")
	private String navigationPath;
	
	/** The jobseeker page extention. */
	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;
	
	/** The email configuration. */
	@Autowired
	@Resource(name = "emailConfiguration")
	private Properties emailConfiguration;
	
	private @Value("${advancepass.crossdomainlogout.url}")
	String advancepassCrossdomainLogoutUrl;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(LogoutManager.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String cookieName = MMJBCommonConstants.SPRING_SECURITY_REMEMBER_ME_COOKIE;
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request
				.getContextPath() : "/");
		response.addCookie(cookie);
		response.addCookie(deleteAuthenticationCookie());
		response.addCookie(deleteSecureAuthenticationCookie());
		response.addCookie(deleteUserCookie()); 
		HttpSession session = request.getSession(false);
		if (null == authentication) {
			if (session != null) {
				session.invalidate();
			}
//			response.sendRedirect(request.getContextPath()
//					+ "/healthcare/index.html");
			response.sendRedirect(advancepassCrossdomainLogoutUrl);
		} else {

			if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_JOB_SEEKER))) {
				if(null !=session
						.getAttribute(MMJBCommonConstants.LOGIN_DATE_TIME)){
				Date loginDate = (Date) session
						.getAttribute(MMJBCommonConstants.LOGIN_DATE_TIME);
				Date logoutDate = MMUtils.getCurrentDateAndTime();
				SimpleDateFormat formatter = new SimpleDateFormat(
						MMJBCommonConstants.SQL_DATE_PATTERN, Locale.US);

				String loginDateStr = formatter.format(loginDate);
				String logoutDateStr = formatter.format(logoutDate);

				List<AppliedJobDTO> appliedJobDTOList;
				try {
					appliedJobDTOList = jobSeekerJobDetailService
							.getAppliedJobsByCriteria((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID),
									loginDateStr, logoutDateStr);
					if (null != appliedJobDTOList
							&& !appliedJobDTOList.isEmpty()) {
						sendTotalAppliedJobEmail(request, appliedJobDTOList);

					}
				} catch (JobBoardServiceException e) {
					LOGGER.error(e);
				}
				}
				if (session != null) {
					session.invalidate();
				}
				/*response.sendRedirect(request.getContextPath()
						+ "/commonLogin/login.html?page=jobSeeker");*/
//				response.sendRedirect(request.getContextPath()
//						+ "/healthcare/index.html");
				response.sendRedirect(advancepassCrossdomainLogoutUrl);
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY))
					|| authentication.getAuthorities().contains(
							new SimpleGrantedAuthority(
									MMJBCommonConstants.ROLE_FACILITY_GROUP))) {
				if (session != null) {
					session.invalidate();
				}
				/*response.sendRedirect(request.getContextPath()
						+ "/commonLogin/login.html?page=employer");*/
//				response.sendRedirect(request.getContextPath()
//						+ "/healthcare/index.html");
				response.sendRedirect(advancepassCrossdomainLogoutUrl);
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY_SYSTEM))) {
				if (session != null) {
					session.invalidate();
				}
				/*response.sendRedirect(request.getContextPath()
						+ "/commonLogin/login.html?page=agency");*/
//				response.sendRedirect(request.getContextPath()
//						+ "/healthcare/index.html");
				response.sendRedirect(advancepassCrossdomainLogoutUrl);
			}

		}

	}

	/**
	 * Pulls in information from the ‘Jobs You’ve Applied to’ pop-up. 
	 * Email is sent to user after they have exited from a signed-in session where they have applied to jobs.  
	 * @param request
	 * @param appliedJobDTOList
	 */
	private void sendTotalAppliedJobEmail(HttpServletRequest request,
			List<AppliedJobDTO> appliedJobDTOList) {
		InternetAddress[] jsToAddress = new InternetAddress[1];
		UserDTO merUserdto = new UserDTO();
		StringBuffer stringBuffer = new StringBuffer();
		String loginPath = navigationPath.substring(2);
		String jonseekerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), emailConfiguration.getProperty("jobSeeker.email.login.url").trim());
//				+ dothtmlExtention + jobseekerPageExtention;
		EmailDTO emailDTO = new EmailDTO();
		String jobseekerApplyEmailBody = emailConfiguration.getProperty(
				"jobseeker.apply.email.body").trim();
		for (AppliedJobDTO admSaveJob : appliedJobDTOList) {
			try {
				merUserdto = userService.getUserByUserId(admSaveJob.getUserId());
				jsToAddress[0] = new InternetAddress(merUserdto.getEmailId());
			} catch (AddressException jbex) {
				LOGGER.error(jbex);
			}

			emailDTO.setToAddress(jsToAddress);
			emailDTO.setFromAddress(advanceWebAddress);
			emailDTO.setSubject(emailConfiguration.getProperty(
					"jobAppliedSubject").trim());

			jobseekerApplyEmailBody = jobseekerApplyEmailBody.replace(
					"?jobSeekerFirstName", merUserdto.getFirstName());

			String empName;
			if (null == admSaveJob.getFacilityName()) {
				empName = "";
			} else {
				empName = admSaveJob.getFacilityName();
			}

			jobseekerApplyEmailBody = jobseekerApplyEmailBody
					+ "<tr>  <td width=\"33%\" align=\"center\" valign=\"middle\" style=\"padding-top:10px; padding-bottom:10px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\"><span style=\"color: #333333\">"
					+ admSaveJob.getJobTitle()
					+ "</span></span>"
					+ "</td>  <td width=\"33%\" align=\"center\" valign=\"middle\" style=\"padding-top:10px; padding-bottom:10px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\"><span style=\"color: #333333\">"
					+ empName
					+ "</span></span>"
					+ "</td>  <td width=\"33%\" align=\"center\" valign=\"middle\" style=\"padding-top:10px; padding-bottom:10px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\">"
					+ admSaveJob
							.getAppliedDt() + "</span>" + "</td></tr>";

		}
		jobseekerApplyEmailBody = jobseekerApplyEmailBody
				+ emailConfiguration.getProperty("jobseeker.apply.email.body1")
						.trim().replace("?jsdashboardLink", jonseekerloginUrl);
		stringBuffer.append(emailConfiguration.getProperty(
				"jobseeker.email.header").trim());
		stringBuffer.append(jobseekerApplyEmailBody);
		stringBuffer.append(emailConfiguration.getProperty("email.footer")
				.trim());
		emailDTO.setBody(stringBuffer.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}
	
	/**
	 * Delete authentication cookie.
	 *
	 * @return the cookie
	 */
	private Cookie deleteAuthenticationCookie(){
		Cookie cookie=new Cookie(".ASPXAUTH","Deleted cookie");
			cookie.setDomain(".advanceweb.com");
			cookie.setPath("/");
			cookie.setMaxAge(0);
		return cookie;
	}
	/**
	 * Delete secure authentication cookie.
	 *
	 * @return the cookie
	 */
	private Cookie deleteSecureAuthenticationCookie(){
		Cookie cookie=new Cookie(".ASPXAUTH","Deleted cookie");
			cookie.setDomain("secure.advanceweb.com");
			cookie.setPath("/");
			cookie.setMaxAge(0);
		return cookie;
	}
	
	/**
	 * Delete user cookie.
	 *
	 * @return the cookie
	 */
	private Cookie deleteUserCookie(){
		Cookie cookie=new Cookie(".USERCOOKIE","Deleted cookie");
			cookie.setDomain(".advanceweb.com");
			cookie.setPath("/");
			cookie.setMaxAge(0);
		return cookie;
	}
}
