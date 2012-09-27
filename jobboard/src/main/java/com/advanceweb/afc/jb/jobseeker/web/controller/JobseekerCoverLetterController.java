/**
 * 
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.io.FileOutputStream;

import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.exception.JobBoardException;
import com.advanceweb.afc.jb.jobseeker.service.CoverLetterService;

/**
 * This method is called for create cover letter
 * etc.
 * @author kartikm
 * @version V.0.1
 *
 */
@Controller
@RequestMapping("/jobSeekerCoverLetter")
@SessionAttributes("resCoverLetterForm")
@Scope("session")
public class JobseekerCoverLetterController {
	private static final Logger LOGGER = Logger
			.getLogger("JobseekerCoverLetterController.class");
	@Autowired
	CoverLetterService coverLetterService;
	
	private @Value("${resumeDeleteSuccess}")
	String resumeDeleteSuccess;
	
	private @Value("${resumeDeleteFailure}")
	String resumeDeleteFailure;

	@RequestMapping(value = "/createCoverLetter", method = RequestMethod.GET)
	public ModelAndView jobSeekerCoverPage(ModelMap map) {
		ResCoverLetterForm resCoverLetterForm = new ResCoverLetterForm();
		ModelAndView model = new ModelAndView();
		model.addObject(resCoverLetterForm);
		model.setViewName("jobseekerCreateCoverLetter");
		return model;
	}

	/**
	 * @author kartikm This is the save method call when date change and save
	 *         button click then it is Change from active, inactive, Draft,
	 *         Expired
	 * @param request
	 * @param session
	 * @param jobPostform
	 * @param result
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/jobseekerCoverLetterSub", method = RequestMethod.POST)
	public String getJobPostDetailsSave(HttpServletRequest request,
			HttpSession session, ResCoverLetterForm resCoverLetterForm,
			BindingResult result) {

		try {
			int userId = (Integer) session.getAttribute("userId");
			ResCoverLetterDTO dto = new ResCoverLetterDTO();
			dto.setName(resCoverLetterForm.getName());
			dto.setCoverletterText(resCoverLetterForm.getCoverletterText());
			dto.setActive(resCoverLetterForm.getActive());
			dto.setUserId(userId);
			//this for first time 
			boolean findFirstActive = coverLetterService.findFirstActiveStatus(
					userId, resCoverLetterForm.getActive());
			// this is for max and min count
			boolean findActive = coverLetterService.findActiveStatus(userId,
					resCoverLetterForm.getActive());
			//this is for duplicate name
			boolean findName = coverLetterService.findNameActiveStatus(userId,
					resCoverLetterForm.getName());
			//this is for one private to public convert checking it already present or not
			boolean findDuplicate = coverLetterService
					.findDuplicateActiveStatus(userId,
							resCoverLetterForm.getActive());
			if (findFirstActive) {
				coverLetterService.coverLetterSaveByjobSeeker(dto);
			} else if (!findActive) {
				return "max 5 cover letters can be created";
			} else if (findName) {
				return "Cover Letter Name already exists, Please try again";
			} else if (findDuplicate) {
				coverLetterService.coverLetterUpdateByjobSeeker(dto);
				coverLetterService.coverLetterSaveByjobSeeker(dto);
			} else {
				coverLetterService.coverLetterSaveByjobSeeker(dto);
			}

		} catch (Exception e) {
			LOGGER.info("error" + e);
			LOGGER.info("Manager Edit Job Posting Search Option");
		}
		return "";
	}

	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@RequestMapping(value = "/manageExistProfile", method = RequestMethod.GET)
	public ModelAndView manageExitProfileSettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		ResCoverLetterForm resCoverLetterForm = new ResCoverLetterForm();
		try {
			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			List<ResCoverLetterDTO> jbOwnerList = new ArrayList<ResCoverLetterDTO>();
			try {
				jbOwnerList = coverLetterService.getJobOwnerList(userId);
			} catch (JobBoardException jbex) {
				LOGGER.error("Error occured while updating the job owner", jbex);
			}
			model.addObject("jobOwners", jbOwnerList);
			model.addObject("employeeAccountForm", resCoverLetterForm);
			model.setViewName("jobSeekerManageExitWright");
		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error");
		}
		return model;
	}
	
	
	
	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	
	
	@RequestMapping(value = "/deleteManageExistCoverLetter", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteManageExistCoverLetter(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestParam("coverletterId") int coverletterId) {
		int userId = (Integer) session
				.getAttribute(MMJBCommonConstants.USER_ID);
		boolean isdelete=coverLetterService.isDelete(userId,coverletterId);
		
		JSONObject deleteStatusJson = new JSONObject();		
		if (isdelete) {
			deleteStatusJson.put("success", resumeDeleteSuccess);
			return deleteStatusJson;
		} else {
			deleteStatusJson.put("failed", resumeDeleteFailure);
			return deleteStatusJson;
		}
	}

	/**
	 * @author kartikm
	 * @version v.0.1
	 * @description this is for view option of cover letter
	 * @param request
	 * @param session
	 * @param resCoverLetterForm
	 * @param result
	 * @return
	 */
	
	
	@RequestMapping(value = "/jobseekerViewCoverLetter", method = RequestMethod.GET)
	public ModelAndView jobseekerViewCoverLetter(HttpServletRequest request,
			HttpSession session, ResCoverLetterForm resCoverLetterForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		
		try {
			//int userId = (Integer) session
			//		.getAttribute(MMJBCommonConstants.USER_ID);
			String covId=request.getParameter("coverletterId");
			String covType=request.getParameter("type");
			int coverletterId=Integer.parseInt(covId);
			ResCoverLetterDTO listOfCoverLetter=coverLetterService.getCoverList(coverletterId);
			if (listOfCoverLetter!=null){
				resCoverLetterForm.setActive(listOfCoverLetter.getActive());
				resCoverLetterForm.setCoverletterId(listOfCoverLetter.getCoverletterId());
				resCoverLetterForm.setCoverletterText(listOfCoverLetter.getCoverletterText());
				resCoverLetterForm.setName(listOfCoverLetter.getName());
				resCoverLetterForm.setUserId(listOfCoverLetter.getUserId());
			}
			model.addObject("covType", covType);
			model.addObject("employeeAccountForm", resCoverLetterForm);
			model.setViewName("viewEditCoverLetter");
		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error"+e);
		}
		return model;
	}
	/**
	 * @author kartikm
	 * @version v.0.1
	 * @date 22 Sept.2012
	 * @Need : This is for down load option
	 * @param request
	 * @param session
	 * @param resCoverLetterForm
	 * @param result
	 * @param response
	 * @return
	 */
	
	//@ResponseBody
	@RequestMapping(value = "/jobseekerDownloadCoverLetter", method = RequestMethod.GET)
	public ModelAndView jobseekerDownloadCoverLetter(HttpServletRequest request,
			HttpSession session, ResCoverLetterForm resCoverLetterForm,
			BindingResult result,HttpServletResponse response) {
		//ModelAndView model = new ModelAndView();
		
		//String file = null;
		try {
		
			String covId=request.getParameter("coverletterId");
			
			int coverletterId=Integer.parseInt(covId);
			ResCoverLetterDTO listOfCoverLetter=coverLetterService.getCoverList(coverletterId);
			if (listOfCoverLetter!=null){
				resCoverLetterForm.setActive(listOfCoverLetter.getActive());
				resCoverLetterForm.setCoverletterId(listOfCoverLetter.getCoverletterId());
				resCoverLetterForm.setCoverletterText(listOfCoverLetter.getCoverletterText());
				resCoverLetterForm.setName(listOfCoverLetter.getName());
				resCoverLetterForm.setUserId(listOfCoverLetter.getUserId());
			
			    String fileName="";
				
			        fileName=listOfCoverLetter.getName();
			        LOGGER.info("Filename:"+fileName);
			        String fName=fileName;
			        FileOutputStream fs = new FileOutputStream(fName);
			        OutputStreamWriter out = new OutputStreamWriter(fs);
			        out.write(listOfCoverLetter.getName());
			        out.write(listOfCoverLetter.getCoverletterText());
			        response.setContentType( "application/msword");
			        response.setHeader("Content-Disposition","attachment; filename="+fName);
			        response.setHeader("Cache-Control", "no-cache");
			        byte[] bytesGot = listOfCoverLetter.getCoverletterText().getBytes();
			        ServletOutputStream outs = response.getOutputStream();
			        outs.write(bytesGot);
			        
			        
			       /* String filePath = "C:\\test reference-material";
			        File f=new File(filePath, fileName);
			        String fileType = fileName.substring(fileName.indexOf(".")+1,fileName.length());
			        LOGGER.info("Filetype:"+fileType+";"+f.length());*/

			       // if (fileType.trim().equalsIgnoreCase("txt")) {
			           // response.setContentType( "text/plain" );
			       /* } else if (fileType.trim().equalsIgnoreCase("doc")) {
			            response.setContentType( "application/msword" );
			        } else if (fileType.trim().equalsIgnoreCase("xls")) {
			            response.setContentType( "application/vnd.ms-excel" );
			        } else if (fileType.trim().equalsIgnoreCase("pdf")) {
			            response.setContentType( "application/pdf" );
			            LOGGER.info("content type set to pdf");
			        } else {
			            response.setContentType( "application/octet-stream" );
			        }
*/
			       // response.setContentLength((int)f.length());
			         /*   PrintWriter out = response.getWriter(); 
			            out.write(fileName);
			            out.write("\n");
			            out.write(listOfCoverLetter.getCoverletterText());
			         String fName=fileName+".txt";*/
			       //  response.setHeader("Content-Disposition","attachment; filename="+fName);

			      //  response.setHeader("Cache-Control", "no-cache");
			       /* byte[] buf = new byte[8192];
			        FileInputStream inStream = new FileInputStream(f);
			        int sizeRead = 0;
			        while ((sizeRead = inStream.read(buf, 0, buf.length)) > 0) {
			        	LOGGER.info("size:"+sizeRead);
			            outStream.write(buf, 0, sizeRead);
			        }
			        inStream.close();*/
			       // outStream.close();
			        
			       
			        
			        outs.flush();
			        outs.close(); 			        
			        out.close();
			        out.flush();
			    }			
			
		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error"+e);
		}
			
	     
		return new ModelAndView(); 
	}
	
	/**
	 * This method is called to Account Setting update page and
	 * editAccountSetting method take Bean class binding result from Jsp pages
	 * Seession for UserId and FacilityId.
	 * 
	 * @author kartikm
	 * @param model
	 * @return String value
	 */
	@ResponseBody
	@RequestMapping(value = "/jobseekerupdateCoverLetter", method = RequestMethod.POST)
	public String updateCoverLetter(ResCoverLetterForm resCoverLetterForm,BindingResult result, HttpSession session){
	//	boolean isUpdated = false;
		try{
			int userId = (Integer) session
					.getAttribute(MMJBCommonConstants.USER_ID);
			ResCoverLetterDTO dto = new ResCoverLetterDTO();
			if(("".equals(resCoverLetterForm.getName()))||(null==resCoverLetterForm.getName()))
			{
				return "";
			}else{
				dto.setName(resCoverLetterForm.getName());
			}
			if(("".equals(resCoverLetterForm.getCoverletterText()))||(null==resCoverLetterForm.getCoverletterText()))
			{
				return "";
			}else{
				dto.setCoverletterText(resCoverLetterForm.getCoverletterText());
			}
			dto.setActive(resCoverLetterForm.getActive());
			dto.setCoverletterId(resCoverLetterForm.getCoverletterId());
			dto.setUserId(userId);
			dto.setCoverletterId(resCoverLetterForm.getCoverletterId());
			boolean findName = coverLetterService.findNameActiveStatus(userId,
					resCoverLetterForm.getName());
			boolean findDuplicate = coverLetterService
					.findDuplicateActiveStatus(userId,
							resCoverLetterForm.getActive());
			
			if (findName) {
				return "Cover Letter Name already exists, Please try again";
			}else if (resCoverLetterForm.getActive()==1){
					if (findDuplicate) {
					coverLetterService.coverLetterUpdateByjobSeeker(dto);
					coverLetterService.coverLetterEditByjobSeeker(dto);				
				    }
					 else{
							coverLetterService.coverLetterEditByjobSeeker(dto);
						}
			}else{
				coverLetterService.coverLetterEditByjobSeeker(dto);
			}
			
			
		}catch(Exception e)
		{
			LOGGER.info("This is cover letter update option give error");	
		}
		
		return "";
		
	}
	/**
	 * @author kartikm
	 * @version v.0.1
	 * @date 20Sept.2012
	 * @perpous This is for print cover letter 
	 * @param request
	 * @param session
	 * @param resCoverLetterForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/jobseekerPrintCoverLetter", method = RequestMethod.GET)
	public ModelAndView jobseekerPrintCoverLetter(HttpServletRequest request,
			HttpSession session, ResCoverLetterForm resCoverLetterForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		
		try {			
			String covId=request.getParameter("coverletterId");
			String covType=request.getParameter("type");
			int coverletterId=Integer.parseInt(covId);
			ResCoverLetterDTO listOfCoverLetter=coverLetterService.getCoverList(coverletterId);
			if (listOfCoverLetter!=null){
				resCoverLetterForm.setActive(listOfCoverLetter.getActive());
				resCoverLetterForm.setCoverletterId(listOfCoverLetter.getCoverletterId());
				resCoverLetterForm.setCoverletterText(listOfCoverLetter.getCoverletterText());
				resCoverLetterForm.setName(listOfCoverLetter.getName());
				resCoverLetterForm.setUserId(listOfCoverLetter.getUserId());
			}
			
			model.addObject("covType", covType);
			model.addObject("employeeAccountForm", resCoverLetterForm);
			model.setViewName("printCoverLetter");
		} catch (Exception e) {

			LOGGER.info("This is Account Addresss edite option error"+e);
		}
		return model;
	}
}
