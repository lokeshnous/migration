package com.advanceweb.afc.jb.login.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.jobseeker.service.JobSeekerJobDetailService;
import com.advanceweb.afc.jb.mail.service.EmailDTO;
import com.advanceweb.afc.jb.mail.service.MMEmailService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserDao;

public class LogoutManager extends SimpleUrlLogoutSuccessHandler {

	@Autowired
	private JobSeekerJobDetailService jobSeekerJobDetailService;
	@Autowired
	private UserDao userDAO;
	@Autowired
	private MMEmailService emailService;
	@Value("${advanceWebAddress}")
	private String advanceWebAddress;
	@Value("${dothtmlExtention}")
	private String dothtmlExtention;
	@Value("${navigationPath}")
	private String navigationPath;
	@Value("${jobseekerPageExtention}")
	private String jobseekerPageExtention;
	@Value("${jobAppliedSubject}")
	private String jobAppliedSubject;
	
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
		HttpSession session = request.getSession(false);
		if (null == authentication) {
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect(request.getContextPath()
					+ "/healthcarejobs/advanceweb.html");
		} else {

			if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_JOB_SEEKER))) {
				Date loginDate = (Date) session
						.getAttribute(MMJBCommonConstants.LOGIN_DATE_TIME);
				Date logoutDate = MMUtils.getCurrentDateAndTime();
				SimpleDateFormat formatter = new SimpleDateFormat(
						MMJBCommonConstants.SQL_DATE_PATTERN, Locale.US);

				String loginDateStr = formatter.format(loginDate);
				String logoutDateStr = formatter.format(logoutDate);

				List<AdmSaveJob> appliedJobDTOList;
				try {
					appliedJobDTOList = jobSeekerJobDetailService
							.getAppliedJobsByCriteria((Integer) session
									.getAttribute(MMJBCommonConstants.USER_ID),
									loginDateStr, logoutDateStr);
					if (null != appliedJobDTOList
							&& appliedJobDTOList.size() > 0) {
						sendTotalAppliedJobEmail(request, appliedJobDTOList);

					}
				} catch (JobBoardServiceException e) {
					e.printStackTrace();
				}
				if (session != null) {
					session.invalidate();
				}
				response.sendRedirect(request.getContextPath()
						+ "/commonLogin/login.html?page=jobSeeker");
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY))
					|| authentication.getAuthorities().contains(
							new SimpleGrantedAuthority(
									MMJBCommonConstants.ROLE_FACILITY_GROUP))) {
				if (session != null) {
					session.invalidate();
				}
				response.sendRedirect(request.getContextPath()
						+ "/commonLogin/login.html?page=employer");
			} else if (authentication.getAuthorities().contains(
					new SimpleGrantedAuthority(
							MMJBCommonConstants.ROLE_FACILITY_SYSTEM))) {
				if (session != null) {
					session.invalidate();
				}
				response.sendRedirect(request.getContextPath()
						+ "/commonLogin/login.html?page=agency");
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
			List<AdmSaveJob> appliedJobDTOList) {
		InternetAddress[] jsToAddress = new InternetAddress[1];
		UserDTO merUserdto = new UserDTO();
		StringBuffer stringBuffer = new StringBuffer();
		String loginPath = navigationPath.substring(2);
		String jonseekerloginUrl = request.getRequestURL().toString()
				.replace(request.getServletPath(), loginPath)
				+ dothtmlExtention + jobseekerPageExtention;
		EmailDTO emailDTO = new EmailDTO();
		for (AdmSaveJob admSaveJob : appliedJobDTOList) {
			try {
				merUserdto = userDAO.getUserByUserId(admSaveJob.getUserId());
				jsToAddress[0] = new InternetAddress(merUserdto.getEmailId());
			} catch (AddressException jbex) {
				jbex.printStackTrace();
			}

			emailDTO.setToAddress(jsToAddress);
			emailDTO.setFromAddress(advanceWebAddress);
			emailDTO.setSubject(jobAppliedSubject);
			int start, end;

			start = MMJBCommonConstants.JOBSEEKER_APPLY_EMAIL_BODY.toString()
					.indexOf("?jobSeekerFirstName");
			end = start + "?jobSeekerFirstName".length();
			if (start > 0 && end > 0) {
				MMJBCommonConstants.JOBSEEKER_APPLY_EMAIL_BODY.replace(start,
						end, merUserdto.getFirstName());
			}
			String empName;
			if (null == admSaveJob.getFacilityName()) {
				empName = "";
			} else {
				empName = admSaveJob.getFacilityName();
			}
			
			MMJBCommonConstants.JOBSEEKER_APPLY_EMAIL_BODY
					.append("<tr>  <td width=\"33%\" align=\"center\" valign=\"middle\" style=\"padding-top:10px; padding-bottom:10px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\"><span style=\"color: #333333\">"
							+ admSaveJob.getJobtitle()
							+ "</span></span>"
							+ "</td>  <td width=\"33%\" align=\"center\" valign=\"middle\" style=\"padding-top:10px; padding-bottom:10px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\"><span style=\"color: #333333\">"
							+ empName
							+ "</span></span>"
							+ "</td>  <td width=\"33%\" align=\"center\" valign=\"middle\" style=\"padding-top:10px; padding-bottom:10px; border:1px solid #cccccc;\"><span style=\"font-family:Arial, Helvetica, sans-serif; font-size:14px;\">"
							+ CommonUtil.convertToReqdDateString(admSaveJob.getAppliedDt())
							+ "</span>"
							+ "</td></tr>");

		}
		MMJBCommonConstants.JOBSEEKER_APPLY_EMAIL_BODY
				.append(MMJBCommonConstants.JOBSEEKER_APPLY_EMAIL_BODY_1
						.replace("?jsdashboardLink", jonseekerloginUrl));
		stringBuffer.append(MMJBCommonConstants.JOBSEEKEREMAILHEADER);
		stringBuffer.append(MMJBCommonConstants.JOBSEEKER_APPLY_EMAIL_BODY);
		stringBuffer.append(MMJBCommonConstants.EMAILFOOTER);
		emailDTO.setBody(stringBuffer.toString());
		emailDTO.setHtmlFormat(true);
		emailService.sendEmail(emailDTO);
	}
}
