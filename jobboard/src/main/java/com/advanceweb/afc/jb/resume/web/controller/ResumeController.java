package com.advanceweb.afc.jb.resume.web.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.TransformJobSeekerRegistration;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.web.utils.CopyUtil;
import com.advanceweb.afc.jb.web.utils.ReadDocFile;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 9, 2012
 */

@Controller
@RequestMapping(value="/jobSeekerResume")
@SessionAttributes("createResume")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private TransformCreateResume transCreateResume;
	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

	@Autowired
	PopulateDropdowns populateDropdownsService;
	
	private @Value("${basedirectorypathUpload}") String basedirectorypathUpload;

	
	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/manageResume", method = RequestMethod.GET)
	public String getResumes(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {
		
		List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(30);
		List<DropDownDTO> visibilityList=populateDropdownsService.populateDropdown(MMJBCommonConstants.VISIBILITY);
		Map<String,String> visibilityMap = new HashMap<String , String>();
		
		visibilityMap.put(visibilityList.get(0).getOptionId(), visibilityList.get(0).getOptionName());
		visibilityMap.put(visibilityList.get(1).getOptionId(), visibilityList.get(1).getOptionName());
		
		List<ResumeDTO> resumeDTOListNew = new ArrayList<ResumeDTO>();  
		
		for(ResumeDTO resumeDTO : resumeDTOList){
			resumeDTO.setResume_visibility((visibilityMap.get(resumeDTO.getResume_visibility())));
			resumeDTOListNew.add(resumeDTO);
		}
		map.put("resumeList", resumeDTOListNew);
		return "manageResumePopup";
	}



	/**
	 * This method is called to display resume list belonging to a logged in
	 * jobSeeker
	 * 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/createResumeBuilder", method = RequestMethod.GET)
	public ModelAndView getResumes(CreateResume createResume) {
		
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO=new ResumeDTO();
		resumeDTO.setUserId(30);
		resumeDTO.setResumeType(createResume.getResumeType());
		resumeDTO.setResume_name(createResume.getResume_name());
		resumeDTO.setDesired_job_title(createResume.getDesired_job_title());
		resumeDTO.setDesired_employment_type(createResume.getDesired_employment_type());
		resumeDTO.setResume_visibility(createResume.getResume_visibility());
		resumeDTO.setWork_authorization_US(createResume.getWork_authorization_US());
		resumeDTO.setWilling_to_relocate(createResume.getWilling_to_relocate());
		resumeDTO.setResume_visibility(createResume.getResume_visibility());
		resumeService.createResumeCopyPaste(resumeDTO);
		
		CertificationsForm certForm = new CertificationsForm();
		EducationForm eduForm = new EducationForm();
		LanguageForm langForm = new LanguageForm();
		ReferenceForm refForm = new ReferenceForm();
		WorkExpForm workExpForm = new WorkExpForm();
		ContactInfoForm contactInfoForm = new ContactInfoForm();
		List<CertificationsForm> listCertForm = new ArrayList<CertificationsForm>();
		List<EducationForm> listEduForm = new ArrayList<EducationForm>();
		List<LanguageForm> listLangForm = new ArrayList<LanguageForm>();
		List<ReferenceForm> listRefForm = new ArrayList<ReferenceForm>();
		List<WorkExpForm> listWorkExpForm = new ArrayList<WorkExpForm>();
		listCertForm.add(certForm);
		listEduForm.add(eduForm);
		listLangForm.add(langForm);
		listRefForm.add(refForm);
		listWorkExpForm.add(workExpForm);
		createResume.setContactInfoForm(contactInfoForm);
		createResume.setListCertForm(listCertForm);
		createResume.setListEduForm(listEduForm);
		createResume.setListLangForm(listLangForm);
		createResume.setListRefForm(listRefForm);
		createResume.setListWorkExpForm(listWorkExpForm);
		model.addObject("createResume", createResume);
		model.setViewName("createResumeBuilder");
		return model;
	}


	/**
	 * Called to create resume
	 * it Contains 
	 * 		1.Contact information
	 * 		2.Objective
	 * 		3.Work Experience
	 * 		4.Education
	 * 		5.Certifiation
	 * 		6.Skills
	 * 		7.Awards
	 * 		8.Memberships
	 * 		9.Other Details
	 * 		10.References	
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST)
	public String saveResumeBuilder(@ModelAttribute("saveResumeBuilder")
	CreateResume createResume, BindingResult result,Model model){		

		ResumeDTO resumeDTO = new ResumeDTO();
		AddressDTO addDTO = transformJobSeekerRegistration.createAddressDTO(createResume.getContactInfoForm());
		ContactInformationDTO contactInfoDTO = transCreateResume.transformContactInfoDTO(createResume.getContactInfoForm());
		contactInfoDTO.setAddressDTO(addDTO);
		List<CertificationDTO> listCertDTO = transCreateResume.transformCertificationDTO(createResume.getListCertForm());
		List<ReferenceDTO> listRefDTO = transCreateResume.transformReferenceDTO(createResume.getListRefForm());
		List<WorkExpDTO> listWorkExpDTO = transCreateResume.transformWorkExpDTO(createResume.getListWorkExpForm());
		List<EducationDTO> listEduDTO = transCreateResume.transformEducationDTO(createResume.getListEduForm());
		List<LanguageDTO> listLangDTO = transCreateResume.transformLanguageDTO(createResume.getListLangForm());
		resumeDTO = transCreateResume.transformResumeDTO(resumeDTO, createResume);
		resumeDTO.setContactInfoDTO(contactInfoDTO);
		resumeDTO.setListCertDTO(listCertDTO);
		resumeDTO.setListEduDTO(listEduDTO);
		resumeDTO.setListLangDTO(listLangDTO);
		resumeDTO.setListRefDTO(listRefDTO);
		resumeDTO.setListWorkExpDTO(listWorkExpDTO);
		boolean binsterted = resumeService.createResumeBuilder(resumeDTO);
		return null;

	}


	/**
	 * This method is called to fetch the resume data to edit
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/editResume", method = RequestMethod.GET)
	public ModelAndView editResume(CreateResume createResume,
			@RequestParam("resumeId") int resumeId) {

		//CreateResume resumeForm = new CreateResume(); 
		ModelAndView model = new ModelAndView();
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		
		createResume.setUploadResumeId(String.valueOf(resumeDTO.getUploadResumeId()));
		createResume.setResume_name(resumeDTO.getResume_name());
		createResume.setResumeType(resumeDTO.getResumeType());
		createResume.setDesired_job_title(resumeDTO.getDesired_job_title());
		createResume.setDesired_employment_type(resumeDTO.getEmploymentType());
		createResume.setWork_authorization_US(resumeDTO.getWork_authorization_US());
		createResume.setWilling_to_relocate(resumeDTO.getWilling_to_relocate());
		createResume.setResume_visibility(resumeDTO.getResume_visibility());
		
		List<DropDownDTO> employmentTypeList = populateDropdownsService.populateDropdown(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> workAuthUSList = populateDropdownsService.populateDropdown(MMJBCommonConstants.WORK_AUTH_US);
		List<DropDownDTO> relocateList = populateDropdownsService.populateDropdown(MMJBCommonConstants.RELOCATE);
		List<DropDownDTO> visibilityList = populateDropdownsService.populateDropdown(MMJBCommonConstants.VISIBILITY);
		
		model.addObject("resumeForm", createResume);
		model.addObject("resumeDetail", resumeDTO);
		model.addObject("employmentType", employmentTypeList);
		model.addObject("workAuthUS", workAuthUSList);
		model.addObject("resumeVisibility", visibilityList);
		model.addObject("relocate", relocateList);
		model.setViewName("editresumepopup");
		
		return model;
	}
	
	/**
	 * This method is called to fetch the resume data to edit
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateResumePopup", method = RequestMethod.GET)
	public ModelAndView updateResumePopup(CreateResume createResume) {

		ModelAndView model = new ModelAndView();
		
		ResumeDTO resumeDTO = new ResumeDTO();
		resumeDTO.setUserId(30);
		
		resumeDTO.setUploadResumeId(Integer.parseInt((createResume.getUploadResumeId())));
		resumeDTO.setResume_name(createResume.getResume_name());
		resumeDTO.setResumeType(createResume.getResumeType());
		resumeDTO.setDesired_job_title(createResume.getDesired_job_title());
		resumeDTO.setDesired_employment_type(createResume.getDesired_employment_type());
		resumeDTO.setWork_authorization_US(createResume.getWork_authorization_US());
		resumeDTO.setResume_visibility(createResume.getResume_visibility());
		resumeDTO.setWilling_to_relocate(createResume.getWilling_to_relocate());
				
		resumeService.updateResume(resumeDTO);
		
		//depending on the resume type either move to resume builder or show excel file 
		
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO.getResumeType())){ 
			resumeDTO = resumeService.editResume(resumeDTO.getUploadResumeId());
			
			transCreateResume.transformCreateResumeForm(resumeDTO);
			List<CertificationsForm> listCertForm = transCreateResume.transformCertForm(resumeDTO.getListCertDTO());
			List<ReferenceForm> listRefForm = transCreateResume.transformReferenceForm(resumeDTO.getListRefDTO());
			List<EducationForm> listEduForm = transCreateResume.transformEducationForm(resumeDTO.getListEduDTO());
			List<WorkExpForm> listWorkExpForm = transCreateResume.transformWorkExpForm(resumeDTO.getListWorkExpDTO());
			List<LanguageForm> listLangForm = transCreateResume.transformLanguageForm(resumeDTO.getListLangDTO());
			ContactInfoForm contactForm = transCreateResume.transformContactInfoForm(resumeDTO.getContactInfoDTO());
	
			createResume.setListCertForm(listCertForm);
			createResume.setListEduForm(listEduForm);
			createResume.setListLangForm(listLangForm);
			createResume.setListRefForm(listRefForm);
			createResume.setListWorkExpForm(listWorkExpForm);
			createResume.setContactInfoForm(contactForm);
			resumeDTO.getContactInfoDTO();
			model.addObject("createResume",createResume);
			model.setViewName("createResumeBuilder");
		}
		else if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType()))
		{
			//Show excel file 
		}
		else{
			//Show copy paste resume page
		}
		return model;
	}
	

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/deleteResume", method = RequestMethod.GET)
	public @ResponseBody JSONObject deleteResume(HttpServletRequest request,HttpServletResponse response, HttpSession session, @RequestParam("resumeId") int resumeId) {

		boolean deleteStatus = resumeService.deleteResume(resumeId);
		JSONObject deleteStatusJson = new JSONObject();
		if(deleteStatus){
			deleteStatusJson.put("success", "Profile Deleted Succesfully");
			return deleteStatusJson;
		}
		else{
			deleteStatusJson.put("failed", "Failed to Delete this record");
			return deleteStatusJson;
		}
	}


	/**
	 * This method is called to save work experience
	 * Ajax call
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addWorkExp", method = RequestMethod.POST)
	public String addWorkExp(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<WorkExpDTO> listWorkExpDTO = transCreateResume.transformWorkExpDTO(createResume.getListWorkExpForm());
		boolean bCertSaved = resumeService.addWorkExp(listWorkExpDTO);
		return null;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addCertifications", method = RequestMethod.POST)
	public String addCertifications(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<CertificationDTO> listCertDTO = transCreateResume.transformCertificationDTO(createResume.getListCertForm());
		boolean bCertSaved = resumeService.addCertifications(listCertDTO);
		return null;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addEducationDetails", method = RequestMethod.POST)
	public String addEducationDetails(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<EducationDTO> listEduDTO = transCreateResume.transformEducationDTO(createResume.getListEduForm());
		boolean bCertSaved = resumeService.addEducation(listEduDTO);
		return null;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addLanguages", method = RequestMethod.POST)
	public String addLanguage(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<LanguageDTO> listLangDTO = transCreateResume.transformLanguageDTO(createResume.getListLangForm());
		boolean bCertSaved = resumeService.addLanguage(listLangDTO);
		return null;
	}

	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/addReferences", method = RequestMethod.POST)
	public String addReference(HttpServletRequest request, HttpSession session,
			CreateResume createResume, Model model, Map<String, Object> map) {
		List<ReferenceDTO> listRefDTO = transCreateResume.transformReferenceDTO(createResume.getListRefForm());
		boolean bCertSaved = resumeService.addReference(listRefDTO);
		return null;
	}


	@RequestMapping(value = "/createResumePopUp", method = RequestMethod.GET)
	public ModelAndView createResumePopUp(CreateResume createResume, @RequestParam("resumeType") String resumeType) {

		List<DropDownDTO> employmentTypeList = populateDropdownsService.populateDropdown(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<DropDownDTO> workAuthUSList = populateDropdownsService.populateDropdown(MMJBCommonConstants.WORK_AUTH_US);
		List<DropDownDTO> relocateList = populateDropdownsService.populateDropdown(MMJBCommonConstants.RELOCATE);
		List<DropDownDTO> visibilityList = populateDropdownsService.populateDropdown(MMJBCommonConstants.VISIBILITY);
		
		createResume = new CreateResume();
		
		ModelAndView model = new ModelAndView();
		model.addObject("createResume", createResume);
		model.addObject("employmentType", employmentTypeList);
		model.addObject("workAuthUS", workAuthUSList);
		model.addObject("resumeVisibility", visibilityList);
		model.addObject("relocate", relocateList);
		
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeType)){
			model.setViewName("createresumepopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD .equals(resumeType)){
			model.setViewName("uploadreumepopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeType)){
			model.setViewName("copypasteresumepopup");
			return model;
		}
		model.setViewName("createresumepopup");
		return model;
	}
	
	
	/**
	 * This method is called to hold the values of create resume popup 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.GET)
	public ModelAndView saveCreateResume(@ModelAttribute("createResume") CreateResume resumeform ,HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {
		
		System.out.println(resumeform);
		
		model.addAttribute("resumeform", resumeform);
		
		return new ModelAndView("jobseekereditresume");
	}
	
	
	
	

	@RequestMapping(value = "/copyPasteResume", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		if(createResume.getResumeType().equalsIgnoreCase("CP")){
			model.addAttribute("createResume", createResume);
			return "copyPasteResumeText";
		}else if (createResume.getResumeType().equalsIgnoreCase("UPL")) {
			try {
				MultipartFile file = createResume.getFileData();
				String fileName = null;
				InputStream inputStream = null;
				OutputStream outputStream = null;
				if (file.getSize() > 0) {
					inputStream = file.getInputStream();
					if (file.getSize() > 10000) {
						//return "/uploadfile";
					}
					fileName = request.getRealPath("") + "/resources/images/"
							+ file.getOriginalFilename();

					outputStream = new FileOutputStream(fileName);

					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}


					outputStream.close();
					inputStream.close();
				}

				// ..........................................
				session.setAttribute("uploadFile", file.getOriginalFilename());


				//==============================
				//Reading File
				//==============================
				FileInputStream fstream = new FileInputStream(fileName);
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF8"));
				String strLine;
				//Read File Line By Line
				StringBuffer resumeTextData=new StringBuffer();
				while ((strLine = br.readLine()) != null)   {
					// Print the content on the console
					resumeTextData.append(strLine+"\n");
				}
				InetAddress ownIP=InetAddress.getLocalHost();
				//POI File Reader


				String fname="";
				String ext="";
				int mid= fileName.lastIndexOf(".");
				fname=fileName.substring(0,mid);
				ext=fileName.substring(mid+1,fileName.length());  
				if(ext.equalsIgnoreCase("doc")){
					resumeTextData.delete(0, resumeTextData.length());
					new ReadDocFile().readMyDocument(fileName, resumeTextData);
				}else if (ext.equalsIgnoreCase("docx")) {
					resumeTextData.delete(0, resumeTextData.length());
					new ReadDocFile().DocxFileReader(fileName, resumeTextData);
				}
				//Data Insertion part   
				ResumeDTO createResumeDTO=new ResumeDTO();
				createResumeDTO.setResumeType(createResume.getResumeType());
				createResumeDTO.setResume_name(createResume.getResume_name());
				createResumeDTO.setDesired_job_title(createResume.getDesired_job_title());
				createResumeDTO.setDesired_employment_type(createResume.getDesired_employment_type());
				createResumeDTO.setResume_visibility(createResume.getResume_visibility());
				createResumeDTO.setWork_authorization_US(createResume.getWork_authorization_US());
				createResumeDTO.setResumeText(resumeTextData.toString());
				createResumeDTO.setFileServer(ownIP.getHostAddress());
				createResumeDTO.setFilePath(basedirectorypathUpload);
				createResumeDTO.setFileName(file.getOriginalFilename());
				createResumeDTO.setIsPublished("12");
				//resumeService.addCreateResumeUpload(createResumeDTO);
				resumeService.createResumeUpload(createResumeDTO);
				//Close the input stream
				fstream.close();
				in.close();
				br.close();
				resumeTextData.delete(0, resumeTextData.length());
				(new File(basedirectorypathUpload)).mkdir();
				CopyUtil.Move(fileName.replace("\\", "\\\\").replace("/", "\\\\"),basedirectorypathUpload.replace("\\", "\\\\")+file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."))+"_UserId_"+new Timestamp(new Date().getTime()).toString().split(" ")[0]+"_"+new Timestamp(new Date().getTime()).toString().split(" ")[1].split(":")[0]+"-"+new Timestamp(new Date().getTime()).toString().split(" ")[1].split(":")[1]+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length()));


			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/jobSeekerResume/createResumePopUp.html";
		}else if(createResume.getResumeType().equalsIgnoreCase("CR")){
			ResumeDTO createResumeDTO=new ResumeDTO();

			createResumeDTO.setResumeType(createResume.getResumeType());
			createResumeDTO.setResume_name(createResume.getResume_name());
			createResumeDTO.setDesired_job_title(createResume.getDesired_job_title());
			createResumeDTO.setDesired_employment_type(createResume.getDesired_employment_type());
			createResumeDTO.setResume_visibility(createResume.getResume_visibility());
			createResumeDTO.setWork_authorization_US(createResume.getWork_authorization_US());
			//createResumeDTO.setResumeText(createResume.getResumeText());
			createResumeDTO.setIsPublished("12");

			//resumeService.addCreateResumeCopyPaste(createResumeDTO);
			resumeService.createResumeCopyPaste(createResumeDTO);

			return "redirect:/jobSeekerResume/createresumepopup.html";
		}else{
			return "redirect:/jobSeekerResume/createresumepopup.html";

		}
	}

	@RequestMapping(value = "/copyPasteResumeSubmit", method = RequestMethod.POST)
	public String addResume(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model) throws Exception {
		ResumeDTO createResumeDTO=new ResumeDTO();

		createResumeDTO.setResumeType(createResume.getResumeType());
		createResumeDTO.setResume_name(createResume.getResume_name());
		createResumeDTO.setDesired_job_title(createResume.getDesired_job_title());
		createResumeDTO.setDesired_employment_type(createResume.getDesired_employment_type());
		createResumeDTO.setResume_visibility(createResume.getResume_visibility());
		createResumeDTO.setWork_authorization_US(createResume.getWork_authorization_US());
		createResumeDTO.setResumeText(createResume.getResumeText());
		createResumeDTO.setIsPublished("12");

		//resumeService.addCreateResumeCopyPaste(createResumeDTO);
		resumeService.createResumeCopyPaste(createResumeDTO);

		return "redirect:/jobSeekerResume/createResumePopUp.html";
	}

	/**
	 * Called to create resume
	 * it Contains 
	 * 		1.Contact information
	 * 		2.Objective
	 * 		3.Work Experience
	 * 		4.Education
	 * 		5.Certifiation
	 * 		6.Skills
	 * 		7.Awards
	 * 		8.Memberships
	 * 		9.Other Details
	 * 		10.References	
	 * @param createResume
	 * @param result
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewResumeBuilder", method = RequestMethod.POST)
	public String viewResumeBuilder(CreateResume createResume, BindingResult result,Map model, @RequestParam("resumeId") int resumeId){

//		ResumeDTO resumeDTO = resumeService.editResume(createResume.getBuilderResumeId());
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		transCreateResume.transformCreateResumeForm(resumeDTO);
		List<CertificationsForm> listCertForm = transCreateResume.transformCertForm(resumeDTO.getListCertDTO());
		List<ReferenceForm> listRefForm = transCreateResume.transformReferenceForm(resumeDTO.getListRefDTO());
		List<EducationForm> listEduForm = transCreateResume.transformEducationForm(resumeDTO.getListEduDTO());
		List<WorkExpForm> listWorkExpForm = transCreateResume.transformWorkExpForm(resumeDTO.getListWorkExpDTO());
		List<LanguageForm> listLangForm = transCreateResume.transformLanguageForm(resumeDTO.getListLangDTO());
		ContactInfoForm contactForm = transCreateResume.transformContactInfoForm(resumeDTO.getContactInfoDTO());

		createResume.setListCertForm(listCertForm);
		createResume.setListEduForm(listEduForm);
		createResume.setListLangForm(listLangForm);
		createResume.setListRefForm(listRefForm);
		createResume.setListWorkExpForm(listWorkExpForm);
		createResume.setContactInfoForm(contactForm);
		resumeDTO.getContactInfoDTO();
		
		model.put("createResume", createResume);
		
		return "viewresume";

	}


}
