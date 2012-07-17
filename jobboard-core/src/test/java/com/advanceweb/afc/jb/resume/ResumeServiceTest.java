package com.advanceweb.afc.jb.resume;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.resume.ResumeService;

public class ResumeServiceTest extends ServiceTest {

	@Autowired
	private ResumeService resumeService;

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
