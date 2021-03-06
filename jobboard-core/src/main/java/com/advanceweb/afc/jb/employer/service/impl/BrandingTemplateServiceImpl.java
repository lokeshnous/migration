/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
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

	/** The branding template dao. */
	@Autowired
	private BrandingTemplateDAO brandingTemplateDAO;

	/** The branding template delegate. */
	@Autowired
	private BrandingTemplateDelegate brandingTemplateDelegate;

	/**
	 * Fetch the job posting Branding Templates
	 */
	@Override
	public List<BrandingTemplateDTO> getBrandingTemplate(int facilityId) {
		return brandingTemplateDAO.getBrandingTemplate(facilityId);
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
	public BrandingTemplateDTO viewEmpBrandTemp(
			BrandingTemplateDTO brandingTemplatesDTO) {
		BrandingTemplateDTO empBrandTempDTO = null;
		empBrandTempDTO = brandingTemplateDAO
				.viewEmpBrandTemp(brandingTemplatesDTO);
		return empBrandTempDTO;
	}

	/**
	 * Edit the job posting Branding Template.
	 */
	@Override
	public BrandingTemplateDTO editBrandingTemplate(int templateId) {
		return brandingTemplateDAO.editBrandingTemplate(templateId);
	}

	/**
	 * Delete the job posting Branding Template.
	 */
	@Override
	public boolean deleteBrandingTemplate(int templateId, int deleteUserId) {
		return brandingTemplateDAO.deleteBrandingTemplate(templateId,
				deleteUserId);
	}

	/**
	 * This method checks if any active job is using the template
	 * 
	 * @param templateId
	 * @return boolean
	 */
	@Override
	public boolean checkTemplateUsage(int templateId) {
		return brandingTemplateDAO.checkTemplateUsage(templateId);
	}

	/**
	 * This method checks if the template limit has exceeded the limit
	 * 
	 * @param facilityId
	 * @return boolean
	 */
	@Override
	public boolean checkTemplateLimit(int facilityId) {
		return brandingTemplateDAO.checkTemplateLimit(facilityId);
	}

	/**
	 * This method checks if the template Name already exists
	 * 
	 * @param facilityId
	 * @param templateName
	 * 
	 * @return boolean
	 */
	@Override
	public boolean checkTemplateName(int facilityId, String templateName) {
		return brandingTemplateDAO.checkTemplateName(facilityId, templateName);
	}

	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	public int getNSCustomerIDFromAdmFacility(int admFacilityID) {
		return brandingTemplateDelegate
				.getNSCustomerIDFromAdmFacility(admFacilityID);

	}

	/**
	 * Fetch the employer Branding information.
	 */
	@Override
	public List<String> getBrandingInformation(int nsCustomerID) {

		return brandingTemplateDelegate.getPurchaseInfo(nsCustomerID);

	}

	/**
	 * This method is used the get the Branding Template Purchase information
	 * 
	 * @param facilityId
	 * @return boolean
	 */
	@Override
	public boolean getBrandPurchaseInfo(int facilityId) {

		return brandingTemplateDAO.getBrandPurchaseInfo(facilityId);
	}

	/**
	 * This method is used the get the Branding Template Package information
	 * 
	 * @param productId
	 * @return boolean
	 */
	@Override
	public boolean getBrandPackage(int productId) {
		return brandingTemplateDAO.getBrandPackage(productId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.service.BrandingTemplateService#getParentId(int)
	 */
	@Override
	public int getParentId(int facilityId) {
		return brandingTemplateDAO.getParentId(facilityId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.service.BrandingTemplateService#getParentUserId(int, int)
	 */
	@Override
	public int getParentUserId(int userId, int parentFacilityId) {
		return brandingTemplateDAO.getParentUserId(userId, parentFacilityId);
	}

}
