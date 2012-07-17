package com.advanceweb.afc.jb.resume;

import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.ResumeDTO;

public class ResumeServiceTest extends ServiceTest {

	@Autowired
	private ResumeService resumeService;

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
			assertTrue("Copy Paste Resume", resumeService.createResumeUpload(createResumeDTO));

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

			List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(2);
			for (ResumeDTO resumeDTO : resumeDTOList) {
				assertTrue("Delete Resume", resumeService.deleteResume(resumeDTO.getUploadResumeId()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
