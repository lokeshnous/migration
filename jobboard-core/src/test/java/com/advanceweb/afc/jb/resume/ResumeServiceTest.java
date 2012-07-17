package com.advanceweb.afc.jb.resume;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.ResumeDTO;

public class ResumeServiceTest extends ServiceTest {

	@Autowired
	private ResumeService resumeService;

	
	@Test
	public void testCreateResumeUpload() {
		try {
			ResumeDTO createResumeDTO=new ResumeDTO();

			createResumeDTO.setUserId(Integer.parseInt("1"));
			createResumeDTO.setResumeType("Upload");
			createResumeDTO.setResume_name("Test");
			
			
			createResumeDTO.setDesired_job_title("Tilt");
			createResumeDTO.setDesired_employment_type("1");
			createResumeDTO.setWork_authorization_US("1");
			createResumeDTO.setWilling_to_relocate("Y");
			createResumeDTO.setResume_visibility("1");			

			
			createResumeDTO.setResumeText("Testttttttttttttttttt");
			createResumeDTO.setIsPublished("1");

			
			createResumeDTO.setFileServer("10.24.64.42");
			createResumeDTO.setFileName("Test");
			createResumeDTO.setFilePath("filePath");
			assertTrue("Upload Resume", resumeService.createResumeUpload(createResumeDTO));
			System.out.println("Resume Upload");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateResumeCopyPaste() {
		try {
			ResumeDTO createResumeDTO=new ResumeDTO();
			createResumeDTO.setUserId(Integer.parseInt("1"));
			createResumeDTO.setResumeType("CopyPaste");
			createResumeDTO.setResume_name("Test");
			
			
			createResumeDTO.setDesired_job_title("Tilt");
			createResumeDTO.setDesired_employment_type("1");
			createResumeDTO.setWork_authorization_US("1");
			createResumeDTO.setWilling_to_relocate("Y");
			createResumeDTO.setResume_visibility("1");			

			
			createResumeDTO.setResumeText("Testttttttttttttttttt");
			createResumeDTO.setIsPublished("1");
			assertTrue("Copy Paste Resume", resumeService.createResumeUpload(createResumeDTO));
			System.out.println("Resume Copy Paste");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testRetrieveAllResumes() {
		try {

			List<ResumeDTO> resumeDTOList = resumeService.retrieveAllResumes(2);
			assertTrue("Retrieve Resume", resumeDTOList.size() >= 0);
			for (ResumeDTO resumeDTO : resumeDTOList) {
				System.out.println(resumeDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEditResume() {
		try {

			ResumeDTO resumeDTO = resumeService.editResume(2);
			assertTrue("Edit Resume", resumeDTO != null);
			System.out.println(resumeDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void testDeleteResume() {
		try {
			assertTrue("Edit Resume", resumeService.deleteResume(22));
			System.out.println("Resume deleted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
