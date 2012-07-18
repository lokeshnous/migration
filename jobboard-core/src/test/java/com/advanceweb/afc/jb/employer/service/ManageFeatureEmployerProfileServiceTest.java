package com.advanceweb.afc.jb.employer.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.job.service.JobSeekerActivity;

public class ManageFeatureEmployerProfileServiceTest extends ServiceTest{

	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;

	@Test
	public void testGetEmployerDetails() {
		try {

			CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile
					.getEmployerDetails(13100);
			assertTrue("Get Applied Job", companyProfileDTO != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	@Test
	public void testDeleteAppliedJobs() {
		try {

			assertTrue("Delete Applied Job",
					jobSeekerActivity.deleteAppliedJobs(13100));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	@Test
	public void testSavedJobs() {
		try {

			List<EmployerProfileDTO> employerProfileDTO = manageFeatureEmployerProfile
					.getEmployerAccountDetails(13100);
			assertTrue("Get Applied Job", employerProfileDTO != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	



}
