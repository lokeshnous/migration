package com.advanceweb.afc.jb.employer.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;

public class ManageFeatureEmployerProfileServiceTest extends ServiceTest {

	@Autowired
	private ManageFeatureEmployerProfile manageFeatureEmployerProfile;
	private static final Logger LOGGER = Logger.getLogger(ManageFeatureEmployerProfileServiceTest.class);
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
			companyProfileDTO.setLogoPath("Logo Path");

			assertTrue("Saved Job",
					manageFeatureEmployerProfile
							.saveEmployerProfile(companyProfileDTO));

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Test
	public void testGetEmployerDetails() {
		try {

			CompanyProfileDTO companyProfileDTO = manageFeatureEmployerProfile
					.getEmployerDetails(109);
			assertTrue("Get Employer Details", companyProfileDTO != null);

		} catch (Exception e) {
			LOGGER.error(e);
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
			LOGGER.error(e);
		}
	}

}
