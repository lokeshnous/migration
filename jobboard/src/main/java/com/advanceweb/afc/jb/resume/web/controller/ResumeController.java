package com.advanceweb.afc.jb.resume.web.controller;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.jobseeker.web.controller.TransformJobSeekerRegistration;
import com.advanceweb.afc.jb.resume.ResumeService;
import com.advanceweb.afc.jb.web.utils.ReadDocFile;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 9, 2012
 */

@Controller
@RequestMapping(value="/jobSeekerResume")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private TransformCreateResume transCreateResume;
	@Autowired
	private TransformJobSeekerRegistration transformJobSeekerRegistration;

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

		List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(2);

		for (ResumeDTO resumeDTO : resumeDTOList) {
			System.out.println(resumeDTO);
		}

		return "manageResume";
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
	public ModelAndView getResumes(HttpServletRequest request, HttpSession session, Map model) {

		CreateResume form = new CreateResume();
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
		form.setContactInfoForm(contactInfoForm);
		form.setListCertForm(listCertForm);
		form.setListEduForm(listEduForm);
		form.setListLangForm(listLangForm);
		form.setListRefForm(listRefForm);
		form.setListWorkExpForm(listWorkExpForm);
		model.put("createResumeForm", form);
		return new ModelAndView("createresumebuilder");
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
	public String editResume(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {

		ResumeDTO resumeDTO = resumeService.editResume(1);

		System.out.println(resumeDTO);

		return "manageResume";
	}
	
	/**
	 * This method is called to delete a resume 
	 * @param model
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/deleteResume", method = RequestMethod.GET)
	public String deleteResume(HttpServletRequest request, HttpSession session,
			Model model, Map<String, Object> map) {

		boolean deleteStatus = resumeService.deleteResume(24);
		
		if(deleteStatus){
			System.out.println("Resume has been deleted succesfully ........");
		}

		return "manageResume";
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
	public String getTime(HttpServletRequest request, HttpSession session,Model model,Map<String, Object> map) {

		CreateResume createResume=new CreateResume();
		Map<String,String> resumeType = new LinkedHashMap<String,String>();
		resumeType.put("CR", "Create");
		resumeType.put("UPL", "Upload");
		resumeType.put("CP", "CopyPaste");
		model.addAttribute("resumeTypeList", resumeType);  
		Map<String,String> employmentType = new LinkedHashMap<String,String>();
		employmentType.put("31", "Full Time");
		employmentType.put("32", "Part Time");
		employmentType.put("33", "Per Diem");
		employmentType.put("34", "Contract/Travel");
		model.addAttribute("employmentTypeList", employmentType);  
		Map<String,String> workauthUS = new LinkedHashMap<String,String>();
		workauthUS.put("35", "1");
		workauthUS.put("36", "2");
		workauthUS.put("37", "3");
		model.addAttribute("workauthUSList", workauthUS);  
		createResume.setResume_visibility("P");
		createResume.setWilling_to_relocate("Y");
		map.put("createResume", createResume);

		return "createResume";
	}

	@RequestMapping(value = "/copyPasteResume", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		if(createResume.getResumeType().equalsIgnoreCase("CP")){
			model.addAttribute("createResume", createResume);
			return "copyPasteResumeText";
		}else if (createResume.getResumeType().equalsIgnoreCase("UPL")) {
			System.out.println("In upload++++++++++");
			System.err.println("-------------------------------------------");
			try {
				MultipartFile file = createResume.getFileData();
				String fileName = null;
				InputStream inputStream = null;
				OutputStream outputStream = null;
				if (file.getSize() > 0) {
					inputStream = file.getInputStream();
					if (file.getSize() > 10000) {
						System.out.println("File Size:::" + file.getSize());
						//return "/uploadfile";
					}
					System.out.println("size::" + file.getSize());
					fileName = request.getRealPath("") + "/resources/images/"
							+ file.getOriginalFilename();
					outputStream = new FileOutputStream(fileName);
					System.out.println("fileName:" + file.getOriginalFilename()+"+++++++++++++++++++++"+fileName);

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
					System.out.println (strLine);
					resumeTextData.append(strLine+"\n");
				}

				InetAddress ownIP=InetAddress.getLocalHost();
				//POI File Reader


				String fname="";
				String ext="";
				int mid= fileName.lastIndexOf(".");
				fname=fileName.substring(0,mid);
				ext=fileName.substring(mid+1,fileName.length());  
				System.out.println("File name ="+fname);
				System.out.println("Extension ="+ext); 
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
				createResumeDTO.setFilePath("fileName");
				createResumeDTO.setFileName(file.getOriginalFilename());
				createResumeDTO.setIsPublished("12");
				//resumeService.addCreateResumeUpload(createResumeDTO);
				resumeService.createResumeUpload(createResumeDTO);
				//Close the input stream
				in.close();
				resumeTextData.delete(0, resumeTextData.length());

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/jobSeekerResume/createResumePopUp.html";
		}else{
			return "copyPasteResumeText";
		}
	}

	@RequestMapping(value = "/copyPasteResumeSubmit", method = RequestMethod.POST)
	public String addResume(@ModelAttribute("createResume")
	CreateResume createResume, BindingResult result,Model model) throws Exception {
		ResumeDTO createResumeDTO=new ResumeDTO();
		System.out.println(createResume.getResumeType()+"=========\n"+createResume.getResume_name()+"=========\n"+createResume.getDesired_job_title()+"=========\n"+createResume.getDesired_employment_type()+"=========\n"+createResume.getWorkauthUS()+"=========\n"+createResume.getResume_visibility());

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
	 @RequestMapping(value = "/viewResumeBuilder", method = RequestMethod.GET)
	public String viewResumeBuilder(@ModelAttribute("saveResumeBuilder")
		CreateResume createResume, BindingResult result,Model model){
		 
		 ResumeDTO resumeDTO = resumeService.editResume(createResume.getBuilderResumeId());
		 
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
		return null;
		
	}
	
	
}
