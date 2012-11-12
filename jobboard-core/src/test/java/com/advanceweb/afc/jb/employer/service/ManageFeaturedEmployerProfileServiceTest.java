package com.advanceweb.afc.jb.employer.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.jb.test.ServiceTestBase;

public class ManageFeaturedEmployerProfileServiceTest extends ServiceTestBase {

	@Autowired
	private ManageFeaturedEmployerProfile manageFeaturedEmployerProfile;
	private static final Logger LOGGER = Logger.getLogger(ManageFeaturedEmployerProfileServiceTest.class);
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
					manageFeaturedEmployerProfile
							.saveEmployerProfile(companyProfileDTO));

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Test
	public void testGetEmployerDetails() {
		try {

			CompanyProfileDTO companyProfileDTO = manageFeaturedEmployerProfile
					.getEmployerDetails(109);
			assertTrue("Get Employer Details", companyProfileDTO != null);

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Test
	public void testGetEmployerAccountDetails() {
		try {

			List<EmployerProfileDTO> employerProfileDTO = manageFeaturedEmployerProfile
					.getEmployerAccountDetails(2);
			assertTrue("Get Employer Account Details",
					employerProfileDTO != null);

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

}
