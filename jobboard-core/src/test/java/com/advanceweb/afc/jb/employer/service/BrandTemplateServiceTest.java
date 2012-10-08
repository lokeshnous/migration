package com.advanceweb.afc.jb.employer.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.employer.dao.BrandingTemplateDAO;

/**
 * <code>BrandTemplateServiceTest</code> is a Test class for EmpBrandTemp
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 24 July 2012
 * 
 */
public class BrandTemplateServiceTest extends ServiceTest {

	@Autowired
	private BrandingTemplateDAO empBrandTempDAO;

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#getBrandingTemplate(templateId)}
	 */
	@Test
	public void testFetchEmpBrandTemp() {
		int templateId = 1;
		List<BrandingTemplateDTO> templatesDTOs = empBrandTempDAO
				.getBrandingTemplate(templateId);
		assertNotNull("Fetching the job posting templates", templatesDTOs);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#createEmpBrandTemp(empBrandTempDTO)}
	 */
	@Test
	public void testCreateEmpBrandTemp() {
		Boolean status = null;
		BrandingTemplateDTO empBrandTempDTO = new BrandingTemplateDTO();

		empBrandTempDTO.setEmployerId(33);
		empBrandTempDTO.setMainImagePath("c://image2.jpg");
		empBrandTempDTO.setLogoPath("c://logo2.jpg");
		empBrandTempDTO.setColor("#ff0000");
		empBrandTempDTO.setCreatedDate(new Date().toString());
		empBrandTempDTO.setCompanyOverview("Overview test");
		status = empBrandTempDAO.createEmpBrandTemp(empBrandTempDTO);
		assertTrue("Create job posting template", status);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#editBrandingTemplate(templateId)}
	 */
	@Test
	public void testEditEmpBrandTemp() {
		int templateId = 1;
		BrandingTemplateDTO templatesDTO = empBrandTempDAO
				.editBrandingTemplate(templateId);
		assertNotNull("Update selected job posting template", templatesDTO);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#deleteBrandingTemplate(templateId,userId)}
	 * .
	 */
	@Ignore("Test fails if repeatedly deleting deleted record")
	@Test
	public void testDeleteEmpBrandTemp() {
		Boolean status = null;
		int templateId = 1;
		int userId = 1;
		status = empBrandTempDAO.deleteBrandingTemplate(templateId, userId);
		assertTrue("Delete job posting template", status);
	}
}
