package com.advanceweb.afc.jb.employer.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.employer.dao.BrandingTemplateDAO;

/**
 * <code>EmpBrandTempServiceTest</code> is a Test class for EmpBrandTemp
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 24 July 2012
 * 
 */
public class EmpBrandTempServiceTest extends ServiceTest {

	@Autowired
	private BrandingTemplateDAO empBrandTempDAO;

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#fetchEmpBrandTemp(MerUserDTO)}
	 */
	@Test
	public void fetchEmpBrandTemp() {
		MerUserDTO merUserDTO = new MerUserDTO();
		merUserDTO.setUserId(33);
		List<EmpBrandTempDTO> templatesDTOs = empBrandTempDAO
				.fetchEmpBrandTemp(merUserDTO);
		assertNotNull("Fetching the job posting templates", templatesDTOs);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#createEmpBrandTemp(EmpBrandTempDTO)}
	 */
	@Test
	public void createEmpBrandTemp() {
		Boolean status = null;
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
//		empBrandTempDTO.setDescription("Test Template Desc");
		empBrandTempDTO.setEmployerId(33);
		empBrandTempDTO.setImagePath("c://image2.jpg");
		empBrandTempDTO.setLogoPath("c://logo2.jpg");
		empBrandTempDTO.setColor("#ff0000");
		empBrandTempDTO.setCreatedDate(new Date());
//		empBrandTempDTO.setUpdatedDate(null);
		status = empBrandTempDAO.createEmpBrandTemp(empBrandTempDTO);
		assertTrue("Create job posting template", status);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#viewEmpBrandTemp(EmpBrandTempDTO)}
	 */
	@Test
	public void viewEmpBrandTemp() {
		EmpBrandTempDTO empBrandTempDTO = null;
		EmpBrandTempDTO brandingTemplatesDTO = new EmpBrandTempDTO();
		brandingTemplatesDTO.setJpBrandTempId(11);
		empBrandTempDTO = empBrandTempDAO
				.viewEmpBrandTemp(brandingTemplatesDTO);
		assertNotNull("Fetch selected job posting template", empBrandTempDTO);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#editEmpBrandTemp(EmpBrandTempDTO)}
	 */
	@Test
	public void editEmpBrandTemp() {
		EmpBrandTempDTO empBrandTempDTO = null;
		EmpBrandTempDTO updatedEmpBrandTempDTO = new EmpBrandTempDTO();
//		updatedEmpBrandTempDTO.setDescription("Test Template Desc updated");
		updatedEmpBrandTempDTO.setEmployerId(36);
		updatedEmpBrandTempDTO.setJpBrandTempId(11);
		updatedEmpBrandTempDTO.setImagePath("c://imageupd2.jpg");
		updatedEmpBrandTempDTO.setLogoPath("c://logoupd2.jpg");
		updatedEmpBrandTempDTO.setColor("#ffff00");
//		updatedEmpBrandTempDTO.setUpdatedDate(new Date().toString());
		empBrandTempDTO = empBrandTempDAO
				.editEmpBrandTemp(updatedEmpBrandTempDTO);
		assertNotNull("Update selected job posting template", empBrandTempDTO);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.employer.service.impl.BrandingTemplateServiceImpl#deleteEmpBrandTemp(EmpBrandTempDTO)}
	 * .
	 */
	@Ignore("Test fails if repeatedly deleting deleted record")
	@Test
	public void deleteEmpBrandTemp() {
		Boolean status = null;
		EmpBrandTempDTO empBrandTempDTO = new EmpBrandTempDTO();
		empBrandTempDTO.setJpBrandTempId(8);
		status = empBrandTempDAO.deleteEmpBrandTemp(empBrandTempDTO);
		assertTrue("Delete job posting template", status);
	}
}
