package com.advanceweb.afc.jb.employer.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;

public class ManageFeatureEmployerProfileServiceTest extends ServiceTest {

	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;

	@Test
	public void testSavedJobs() {
		try {

			CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();
			companyProfileDTO.setCompanyEmail("mail");
			companyProfileDTO.setCompanyName("company");
			companyProfileDTO.setCompanyNews("news");
			companyProfileDTO.setCompanyOverview("overviews");
			companyProfileDTO.setCompanyWebsite("website");
			companyProfileDTO.setPositionTitle("title");

			assertTrue("Saved Job",
					manageFeatureEmployerProfile
							.saveEmployerProfile(companyProfileDTO));
//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetEmployerDetails() {
		try {

			CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile
					.getEmployerDetails(109);
			assertTrue("Get Employer Details", companyProfileDTO != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetEmployerAccountDetails() {
		try {

			List<EmployerProfileDTO> employerProfileDTO = manageFeatureEmployerProfile
					.getEmployerAccountDetails(2);
			assertTrue("Get Employer Account Details",
					employerProfileDTO != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
