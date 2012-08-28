package com.advanceweb.afc.jb.employer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.employer.dao.BrandingTemplateDAO;
import com.advanceweb.afc.jb.employer.service.BrandingTemplateService;

/**
 * <code> EmpBrandTempService </code> is a Service class for EmpBrandTemp.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Service("brandingTemplateService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class BrandingTemplateServiceImpl implements BrandingTemplateService {

	@Autowired
	private BrandingTemplateDAO brandingTemplateDAO;

	/**
	 * Fetch the job posting Branding Templates
	 */
	@Override
	public List<BrandingTemplateDTO> fetchEmpBrandTemp(MerUserDTO merUserDTO) {
		List<BrandingTemplateDTO> templatesDTOs = brandingTemplateDAO
				.fetchEmpBrandTemp(merUserDTO);
		return templatesDTOs;
	}

	/**
	 * Create the job posting Branding Template.
	 */
	@Override
	public Boolean createEmpBrandTemp(BrandingTemplateDTO brandingTemplatesDTO) {
		Boolean status = null;
		status = brandingTemplateDAO.createEmpBrandTemp(brandingTemplatesDTO);
		return status;
	}

	/**
	 * View the job posting Branding Template.
	 */
	@Override
	public BrandingTemplateDTO viewEmpBrandTemp(BrandingTemplateDTO brandingTemplatesDTO) {
		BrandingTemplateDTO empBrandTempDTO = null;
		empBrandTempDTO = brandingTemplateDAO
				.viewEmpBrandTemp(brandingTemplatesDTO);
		return empBrandTempDTO;
	}

	/**
	 * Edit the job posting Branding Template.
	 */
	@Override
	public BrandingTemplateDTO editEmpBrandTemp(BrandingTemplateDTO brandingTemplatesDTO) {
		BrandingTemplateDTO empBrandTempDTO = null;
		empBrandTempDTO = brandingTemplateDAO
				.editEmpBrandTemp(brandingTemplatesDTO);
		return empBrandTempDTO;
	}

	/**
	 * Delete the job posting Branding Template.
	 */
	@Override
	public Boolean deleteEmpBrandTemp(BrandingTemplateDTO brandingTemplatesDTO) {
		Boolean status = null;
		status = brandingTemplateDAO.deleteEmpBrandTemp(brandingTemplatesDTO);
		return status;
	}

}
