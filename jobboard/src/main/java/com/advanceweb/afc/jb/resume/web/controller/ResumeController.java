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

import org.apache.log4j.Logger;
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
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeAttribListDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
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
	private PopulateDropdowns populateDropdownsService;
	
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
		//set this from session
		int userId = 2;
		List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(userId);		
		
		List<ResumeVisibilityDTO> visiblityList= populateDropdownsService.getResumeVisibilityList();
		Map<String,String> visibilityMap = new HashMap<String , String>();		
		for(int i = 0 ; i< visiblityList.size() ; i++){
			visibilityMap.put(visiblityList.get(i).getVisibilityId(), visiblityList.get(i).getVisibilityName());
		}
		
		List<ResumeDTO> resumeDTOListNew = new ArrayList<ResumeDTO>();  
		for(ResumeDTO resumeDTO : resumeDTOList){
			resumeDTO.setResumeVisibility(visibilityMap.get(resumeDTO.getResumeVisibility()));
			resumeDTOListNew.add(resumeDTO);
		}
		map.put("resumeList", resumeDTOList);
		return "manageResumePopup";
	}
	
	private ModelAndView populateResumeDropDowns(CreateResume createResume) {
		ModelAndView model = new ModelAndView();
		List<ResumeAttribListDTO> resumeTypeList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.RESUME_TYPE);
		List<ResumeAttribListDTO> employmentTypeList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.EMPLOYMENT_TYPE);
		List<ResumeAttribListDTO> workAuthUSList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.WORK_AUTH_US);
		List<ResumeAttribListDTO> relocateList = populateDropdownsService.populateResumeDropdown(MMJBCommonConstants.RELOCATE);
		List<ResumeVisibilityDTO> visibilityList = populateDropdownsService.getResumeVisibilityList();
		
		model.addObject("resumeTypeList", resumeTypeList);
		model.addObject("employmentType", employmentTypeList);
		model.addObject("workAuthUS", workAuthUSList);
		model.addObject("resumeVisibility", visibilityList);
		model.addObject("relocate", relocateList);
		return model;
	}
	
	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/validateCreateResumePopUp", method = RequestMethod.GET)
	public @ResponseBody JSONObject validateCreateResumePopUp(@RequestParam("resumeName") String resumeName,@RequestParam("resumeId") String resumeId) {
		//set this from session
		int userId = 2;
		JSONObject warningMessage = new JSONObject();
		if("".equals(resumeId) || resumeId == null){
			int resumeCount = resumeService.findResumeCount(userId);
			if(resumeCount == 5){
				warningMessage.put("maxResume", "You can create 5 resume at max.");
				return warningMessage;
			}
		}
		if(!("".equals(resumeName)) && resumeService.checkDuplicateResumeName(resumeId,resumeName, userId)){
			warningMessage.put("duplicateResume", "Resume Name already exists, Please try again.");
			return warningMessage;
		}
		return warningMessage;		
	}
	
	/**
	 * This method is called to fetch the resume data to edit
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/editResume", method = RequestMethod.GET)
	public ModelAndView editResume(CreateResume createResume,@RequestParam("resumeId") int resumeId) {
		ResumeDTO resumeDTO = resumeService.editResume(resumeId);
		
		transCreateResume.transformResumeDTOToCreateResume(createResume, resumeDTO);
		ModelAndView model = populateResumeDropDowns(createResume);
		model.addObject("createResume", createResume);
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO.getResumeType())){
			model.setViewName("editresumepopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType())){
			model.setViewName("editCopyPasteResumePopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO.getResumeType())){
			model.setViewName("editCopyPasteResumePopup");
			return model;
		}	
		model.setViewName("editresumepopup");
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
		//Integer.parseInt(String.valueOf(session.getAttribute("userId")));
		int userId = 2;
		boolean deleteStatus = resumeService.deleteResume(resumeId,userId);
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
	 * This method is called to fetch the resume data to edit
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateResumePopup", method = RequestMethod.GET)
	public ModelAndView updateResumePopup(CreateResume createResume) {

		ModelAndView model = new ModelAndView();
		
		ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		//set it from session
		resumeDTO.setUserId(2);				
		//depending on the resume type either move to resume builder or show excel file 
		
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeDTO.getResumeType())){
			resumeService.updateResume(resumeDTO);
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
		/*else if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType()))
		{
			//Show excel file 
		}
		else{
			//Show copy paste resume page
		}*/
		return model;
	}
	
	
	@RequestMapping(value = "/createResumePopUp", method = RequestMethod.GET)
	public ModelAndView createResumePopUp(@RequestParam("resumeType") String resumeType) {
		
		CreateResume createResume = new CreateResume();
		
		//ResumeDTO resumeDTO = resumeService.getProfileAttributes();
		//createResume.setResumeProfileAttribForm(transformDTOToProfileAttribForm(resumeDTO));
		
		createResume.setWillingToRelocate(MMJBCommonConstants.RELOCATE_YES);
		createResume.setResumeVisibility(MMJBCommonConstants.VISIBILITY_PRIVATE);
		
		createResume.setResumeType(resumeType);
		
		ModelAndView model = populateResumeDropDowns(createResume);
		model.addObject("createResume", createResume);
		
		if(MMJBCommonConstants.RESUME_TYPE_RESUME_BUILDER.equals(resumeType)){
			model.setViewName("createresumepopup");
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD .equals(resumeType)){
			model.setViewName("createResumeUploadPopup");			
			return model;
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeType)){
			model.setViewName("createResumeCopyPastePopup");
			return model;
		}
		model.setViewName("createresumepopup");
		return model;
	}
	
	@RequestMapping(value = "/copyPasteResume", method = RequestMethod.GET)
	public ModelAndView createCopyPasteResume(CreateResume createResume, HttpSession session) {
		ModelAndView model = populateResumeDropDowns(createResume);
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(createResume.getResumeType())){
			
			ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
			//set it from session
			resumeDTO.setUserId(2);
			resumeService.createResumeCopyPaste(resumeDTO);
			model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		}
		return model;
	}
	
	@RequestMapping(value = "/updateCopyPasteResume", method = RequestMethod.GET)
	public ModelAndView updateCopyPasteResume(CreateResume createResume, HttpSession session) {
		ModelAndView model = populateResumeDropDowns(createResume);
		ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		//set it from session
		resumeDTO.setUserId(2);
		resumeService.updateResumeCopyPaste(resumeDTO);
		model.setViewName("redirect:/jobSeeker/jobSeekerDashBoard.html");
		return model;
	}

	@RequestMapping(value = "/copyPasteResumeSubmit", method = RequestMethod.POST)
	public String addResume(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model){
		ResumeDTO createResumeDTO=new ResumeDTO();

		createResumeDTO.setResumeType(createResume.getResumeType());
		createResumeDTO.setResumeName(createResume.getResumeName());
		createResumeDTO.setDesiredJobTitle(createResume.getDesiredJobTitle());
		createResumeDTO.setDesiredEmploymentType(createResume.getDesiredEmploymentType());
		createResumeDTO.setResumeVisibility(createResume.getResumeVisibility());
		createResumeDTO.setWorkAuthorizationUS(createResume.getWorkAuthorizationUS());
		createResumeDTO.setResumeText(createResume.getResumeText());
		createResumeDTO.setIsPublished("12");

		//resumeService.addCreateResumeCopyPaste(createResumeDTO);
		resumeService.createResumeCopyPaste(createResumeDTO);

		return "redirect:/jobSeekerResume/createResumePopUp.html";
		
		/*else if (createResume.getResumeType().equalsIgnoreCase("UPL")) {
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
					readBytes = inputStream.read(buffer, 0, 10000);
					while (readBytes != -1) {
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
				DataInputStream dataInputStream = new DataInputStream(fstream);
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(dataInputStream,"UTF8"));
				String strLine;
				//Read File Line By Line
				StringBuffer resumeTextData=new StringBuffer();
				strLine = bufferReader.readLine();
				while (strLine != null)   {
					// Print the content on the console
					resumeTextData.append(strLine+"\n");
				}
				InetAddress ownIP=InetAddress.getLocalHost();
				//POI File Reader


				String ext="";
				int mid= fileName.lastIndexOf(".");
				fileName.substring(0,mid);
				ext=fileName.substring(mid+1,fileName.length());  
				if(ext.equalsIgnoreCase("doc")){
					resumeTextData.delete(0, resumeTextData.length());
					new ReadDocFile().readMyDocument(fileName, resumeTextData);
				}else if (ext.equalsIgnoreCase("docx")) {
					resumeTextData.delete(0, resumeTextData.length());
					new ReadDocFile().docxFileReader(fileName, resumeTextData);
				}
				//Data Insertion part   
				ResumeDTO createResumeDTO=new ResumeDTO();
				createResumeDTO.setResumeType(createResume.getResumeType());
				createResumeDTO.setResumeName(createResume.getResumeName());
				createResumeDTO.setDesiredJobTitle(createResume.getDesiredJobTitle());
				createResumeDTO.setDesiredEmploymentType(createResume.getDesiredEmploymentType());
				createResumeDTO.setResumeVisibility(createResume.getResumeVisibility());
				createResumeDTO.setWorkAuthorizationUS(createResume.getWorkAuthorizationUS());
				createResumeDTO.setResumeText(resumeTextData.toString());
				createResumeDTO.setFileServer(ownIP.getHostAddress());
				createResumeDTO.setFilePath(basedirectorypathUpload);
				createResumeDTO.setFileName(file.getOriginalFilename());
				createResumeDTO.setIsPublished("12");
				//resumeService.addCreateResumeUpload(createResumeDTO);
				resumeService.createResumeUpload(createResumeDTO);
				//Close the input stream
				fstream.close();
				dataInputStream.close();
				bufferReader.close();
				resumeTextData.delete(0, resumeTextData.length());
				(new File(basedirectorypathUpload)).mkdir();
				CopyUtil.move(fileName.replace("\\", "\\\\").replace("/", "\\\\"),basedirectorypathUpload.replace("\\", "\\\\")+file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."))+"_UserId_"+new Timestamp(new Date().getTime()).toString().split(" ")[0]+"_"+new Timestamp(new Date().getTime()).toString().split(" ")[1].split(":")[0]+"-"+new Timestamp(new Date().getTime()).toString().split(" ")[1].split(":")[1]+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length()));


			} catch (Exception e) {
				Logger.getLogger("");
			}

			return "redirect:/jobSeekerResume/createResumePopUp.html";
		}*/
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
		ResumeDTO resumeDTO = transCreateResume.transformCreateResumeToResumeDTO(createResume);
		//set it from session
		resumeDTO.setUserId(2);
		resumeService.createResume(resumeDTO);
		
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
	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params="Save")
	public String saveResumeBuilder(CreateResume createResume, BindingResult result,Model model){		

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
		resumeService.createResumeBuilder(resumeDTO);
		return null;

	}

	@RequestMapping(value = "/saveResumeBuilder", method = RequestMethod.POST, params="Preview")
	public ModelAndView previewResumeBuilder(CreateResume createResume){		
		ModelAndView model = new ModelAndView();
		model.addObject("createResume", createResume);
		model.setViewName("viewresume");
		return model;

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
		resumeService.addWorkExp(listWorkExpDTO);
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
		resumeService.addCertifications(listCertDTO);
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
		resumeService.addEducation(listEduDTO);
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
		resumeService.addLanguage(listLangDTO);
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
		resumeService.addReference(listRefDTO);
		return null;
	}

	/*public List<ResumeProfileAttribForm> transformDTOToProfileAttribForm(ResumeDTO resumeDTO){
		
		List<ResumeProfileAttribForm> listForms = new ArrayList<ResumeProfileAttribForm>();
		
		if(null != resumeDTO.getResumeAttribList()){
			for(MerProfileAttribDTO dto : resumeDTO.getResumeAttribList()){
				ResumeProfileAttribForm form = new ResumeProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				//form.setStrScreenName(dto.getStrScreenName());
				//form.setStrSectionName(dto.getStrSectionName());
				
				listForms.add(form);
			}
		}
		
		return listForms;
		
	}
	
	public ResumeDTO ProfileAttribFormTotransformDTO(List<ResumeProfileAttribForm> resumeProfileAttribForm){
		
		List<MerProfileAttribDTO> list = new ArrayList<MerProfileAttribDTO>();
		
		if(null != resumeProfileAttribForm){
			for(ResumeProfileAttribForm form : resumeProfileAttribForm){
				MerProfileAttribDTO dto = new MerProfileAttribDTO();
				dto.setDropdown(form.getDropdown());
				dto.setStrAttribType(form.getStrAttribType());
				dto.setStrLabelName(form.getStrLabelName());
				dto.setStrLabelName(form.getStrLabelName());
				dto.setStrProfileAttribId(form.getStrProfileAttribId());
				//dto.setStrScreenName(form.getStrScreenName());
				//dto.setStrSectionName(form.getStrSectionName());
				list.add(dto);
			}
		}
		ResumeDTO resumeDTO = new ResumeDTO();
		resumeDTO.setResumeAttribList(list);
		return resumeDTO;
		
	}*/

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
