package com.advanceweb.afc.jb.resume;

import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.EduDegreeDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;

public class ResumeServiceTest extends ServiceTest {

	@Autowired
	private ResumeService resumeService;
	
	
	/**
	 * 
	 * Create resume for copy paste for userid=2
	 */
	@Test
	public void testCreateResume() {
		try {
			ResumeDTO createResumeDTO=new ResumeDTO();
			createResumeDTO.setUserId(Integer.parseInt("10"));
			createResumeDTO.setResumeType("Create");
			createResumeDTO.setResume_name("Test");
			createResumeDTO.setDesired_job_title("Tilt");
			createResumeDTO.setDesired_employment_type("31");
			createResumeDTO.setWork_authorization_US("35");
			createResumeDTO.setWilling_to_relocate("Y");
			createResumeDTO.setResume_visibility("1");			
			createResumeDTO.setIsPublished("1");
			assertTrue("Create Resume", resumeService.createResumeCopyPaste(createResumeDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateResume2() {
		try {
			for(int i=0 ; i< 5 ; i++){
				ResumeDTO createResumeDTO=new ResumeDTO();
				createResumeDTO.setUserId(Integer.parseInt("10"));
				createResumeDTO.setResumeType("Create");
				createResumeDTO.setResume_name("Nurse Resume "+i);
				createResumeDTO.setDesired_job_title("Resume Tilte "+i);
				createResumeDTO.setDesired_employment_type("31");
				createResumeDTO.setWork_authorization_US("35");
				createResumeDTO.setWilling_to_relocate("Y");
				if(i==0)
					createResumeDTO.setResume_visibility("63");
				else 
					createResumeDTO.setResume_visibility("64");
				createResumeDTO.setIsPublished("1");
				createResumeDTO.setUpdateDt(new Date().toString());
				assertTrue("Create Resume", resumeService.createResumeCopyPaste(createResumeDTO));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create Upload Resume for UserID=2
	 */
	@Test
	public void testCreateResumeUpload() {
		try {
			ResumeDTO createResumeDTO=new ResumeDTO();
			createResumeDTO.setUserId(Integer.parseInt("2"));
			createResumeDTO.setResumeType("Upload");
			createResumeDTO.setResume_name("Test");
			createResumeDTO.setDesired_job_title("Tilt");
			createResumeDTO.setDesired_employment_type("31");
			createResumeDTO.setWork_authorization_US("35");
			createResumeDTO.setWilling_to_relocate("Y");
			createResumeDTO.setResume_visibility("1");			
			createResumeDTO.setResumeText("Testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
			createResumeDTO.setIsPublished("1");

			InetAddress ownIP=InetAddress.getLocalHost();

			createResumeDTO.setFileServer(ownIP.getHostAddress());
			createResumeDTO.setFileName("Test");
			createResumeDTO.setFilePath("filePath");
			assertTrue("Upload Resume", resumeService.createResumeUpload(createResumeDTO));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Create resume for copy paste for userid=2
	 */
	@Test
	public void testCreateResumeCopyPaste() {
		try {
			ResumeDTO createResumeDTO=new ResumeDTO();
			createResumeDTO.setUserId(Integer.parseInt("2"));
			createResumeDTO.setResumeType("CopyPaste");
			createResumeDTO.setResume_name("Test");
			createResumeDTO.setDesired_job_title("Tilt");
			createResumeDTO.setDesired_employment_type("31");
			createResumeDTO.setWork_authorization_US("35");
			createResumeDTO.setWilling_to_relocate("Y");
			createResumeDTO.setResume_visibility("1");			
			createResumeDTO.setResumeText("Testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
			createResumeDTO.setIsPublished("1");
			assertTrue("Copy Paste Resume", resumeService.createResumeCopyPaste(createResumeDTO));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Retrive resume for userid=2
	 */
	@Test
	public void testRetrieveAllResumes() {
		try {

			List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(2);
			assertTrue("Retrieve Resume", resumeDTOList.size() >= 0);
			for (ResumeDTO resumeDTO : resumeDTOList) {
				//System.out.println(resumeDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Edit resume for userid=2
	 * 
	 */
	@Test
	public void testEditResume() {
		try {

			List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(2);
			for (ResumeDTO resumeDTO : resumeDTOList) {
				ResumeDTO resumeDTOInternal = resumeService.editResume(resumeDTO.getUploadResumeId());
				assertTrue("Edit Resume", resumeDTOInternal != null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete all created resumes for userid=2
	 * 
	 */
	@Test
	public void testDeleteResume() {
		try {

			List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(10);
			for (ResumeDTO resumeDTO : resumeDTOList) {
				assertTrue("Delete Resume", resumeService.deleteResume(resumeDTO.getUploadResumeId()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creating resume builder
	 */
	@Test
	public void createResumeBuilder(){
		ResumeDTO dto = new ResumeDTO();
		dto.setAwards("Awards");
		dto.setResume_name("Sasibhushana");
		ContactInformationDTO contactDTO = new ContactInformationDTO();
		AddressDTO addDTO = new AddressDTO();
		addDTO.setAddress1("address1");
		addDTO.setAddress2("address2");
		addDTO.setCity("Bang");
		addDTO.setCountry("india");
		addDTO.setMobileNumber("8888888888");
		addDTO.setPhone("080454645646");
		addDTO.setState("KA");
		addDTO.setStreet("Koramangala");
		addDTO.setZipCode("56003");
		contactDTO.setAddressDTO(addDTO);
		contactDTO.setEmail("sasibhushanam@sasi.com");
		contactDTO.setFirstName("sasibhushana");
		contactDTO.setLastName("Matcha");
		contactDTO.setMiddleNameInitial("M");
		dto.setContactInfoDTO(contactDTO);
		dto.setListCertDTO(getListCerts());		
		dto.setListWorkExpDTO(getListWorkExp());
//		dto.setListEduDTO(getListEducations());
		dto.setListRefDTO(getListReference());
		boolean bSaved = resumeService.createResumeBuilder(dto);
		Assert.assertTrue("Data Saved Successfully", bSaved);
	}
	
	/**
	 * Creating resume builder
	 */
	@Ignore("Not ready to test")
	@Test
	public void saveCertifications(){
		boolean bSaved = resumeService.addCertifications(getListCerts());		
		Assert.assertTrue("Data Saved Successfully", bSaved);
	}
	
	
	/**
	 * Create certificate dto's
	 * @return List
	 */
	private List<CertificationDTO> getListCerts(){
		List<CertificationDTO> listCerts = new ArrayList<CertificationDTO>();
		CertificationDTO certDTO = new CertificationDTO();
		certDTO.setCertificationName("Cert Nam1");
		certDTO.setInstituteName("Nous 1");
		certDTO.setSummary("summary 1");
//		certDTO.setBuilderCertId(57);
		
		CertificationDTO certDTO1 = new CertificationDTO();
		certDTO1.setCertificationName("Cert Nam2");
		certDTO1.setInstituteName("Nous 2");
		certDTO1.setSummary("summary 2");
//		certDTO1.setBuilderCertId(57);
		
		listCerts.add(certDTO1);
		listCerts.add(certDTO);
		
		return listCerts;
	}
	
	/**
	 * Creating work experience
	 * @return List
	 */
	private List<WorkExpDTO> getListWorkExp(){
		List<WorkExpDTO> listWorkExp = new ArrayList<WorkExpDTO>();
		WorkExpDTO dto = new WorkExpDTO();
		dto.setAnnualSalary("700000");
		dto.setCurrentCareerLvl("Sr Soft Engg");
		dto.setDescription("How are you?");
		dto.setEmployerName("L&T Infotech");
		dto.setEmploymentType("Full Time");
		dto.setHrlyPayRate("5");
		dto.setJobTitle("Job Title");
		dto.setYrsAtPostion("2");
		
		WorkExpDTO dto1 = new WorkExpDTO();
		dto1.setAnnualSalary("800000");
		dto1.setCurrentCareerLvl("Soft Engg");
		dto1.setDescription("How are you`?");
		dto1.setEmployerName("Sonata Soft");
		dto1.setEmploymentType("Full Time`");
		dto1.setHrlyPayRate("5");
		dto1.setJobTitle("Job Title`");
		dto1.setYrsAtPostion("21");
		
		listWorkExp.add(dto);
		listWorkExp.add(dto1);
		
		return listWorkExp;
	}
	
	/**
	 * Creationg education
	 * @return List
	 */
	private List<EducationDTO> getListEducations(){
		List<EducationDTO> listEdu = new ArrayList<EducationDTO>();
		EducationDTO dto1 = new EducationDTO();
		dto1.setCertifications("Cert1");
		dto1.setDegreeLvl("Inter");
		dto1.setDegrees("Inter");
		dto1.setInstituteName("St Joseph");
		dto1.setFieldOfStudy("HSC");
		dto1.setLanguage("English");
		EduDegreeDTO eduDegreeDTO1 = new EduDegreeDTO();
		eduDegreeDTO1.setDescription("Degree DTO !");
		eduDegreeDTO1.setName("Degree Name 1");
		dto1.setEduDegreeDTO(eduDegreeDTO1);
		
		EducationDTO dto2 = new EducationDTO();
		dto2.setCertifications("Cert2");
		dto2.setDegreeLvl("SSC");
		dto2.setDegrees("10th Class");
		dto2.setInstituteName("APRS");
		dto2.setFieldOfStudy("SSC");
		dto2.setLanguage("Telugu");
		EduDegreeDTO eduDegreeDTO2 = new EduDegreeDTO();
		eduDegreeDTO2.setDescription("Degree DTO !");
		eduDegreeDTO2.setName("Degree Name 1");
		dto2.setEduDegreeDTO(eduDegreeDTO2);
		
		EducationDTO dto3 = new EducationDTO();
		dto3.setCertifications("Cert3");
		dto3.setDegreeLvl("Degree");
		dto3.setDegrees("BSc");
		dto3.setInstituteName("SVDC");
		dto3.setFieldOfStudy("MPC");
		dto3.setLanguage("English");
		EduDegreeDTO eduDegreeDTO3 = new EduDegreeDTO();
		eduDegreeDTO3.setDescription("Degree DTO !");
		eduDegreeDTO3.setName("Degree Name 1");
		dto3.setEduDegreeDTO(eduDegreeDTO3);
		
		listEdu.add(dto1);
		listEdu.add(dto2);
		listEdu.add(dto3);
		
		return listEdu;
	}
	
	/**
	 * Creating emp references
	 * @return List
	 */
	private List<ReferenceDTO> getListReference(){
		List<ReferenceDTO> listRefs = new ArrayList<ReferenceDTO>();
		ReferenceDTO dto = new ReferenceDTO();
		dto.setCompanyName("Sonata");
		dto.setEmail("sonat-software.com");
		dto.setJobTitle("Project Lead");
		dto.setName("Name 1");
		dto.setPhoneNo("99999999999");
		
		ReferenceDTO dto1 = new ReferenceDTO();
		dto1.setCompanyName("L&T");
		dto1.setEmail("lntinfotech.com");
		dto1.setJobTitle("Big Boss");
		dto1.setName("Name 2");
		dto1.setPhoneNo("88888888888");
		
		listRefs.add(dto);
		listRefs.add(dto1);
		
		return listRefs;
	}
	
	
}
