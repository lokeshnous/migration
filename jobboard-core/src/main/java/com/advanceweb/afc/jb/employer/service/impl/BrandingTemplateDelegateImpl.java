/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.BrandingTemplateDAO;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;



@Service("brandingTemplateDelegate")
public class BrandingTemplateDelegateImpl implements BrandingTemplateDelegate{

	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(BrandingTemplateDelegateImpl.class);
	
	/** The Constant CUSTOMER_STRING. */
	private static final String CUSTOMER_STRING = "customer";
	
	/** The ns customer service. */
	@Autowired
	private NSCustomerService nsCustomerService;
	
	
	/** The branding template dao. */
	@Autowired
	private BrandingTemplateDAO brandingTemplateDAO;
	
	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */

	public int getNSCustomerIDFromAdmFacility(int admFacilityID) {
		int nsCustomerID = 0;
		List<FacilityDTO> admFacilityDTOList = brandingTemplateDAO
				.getNSCustomerIDFromAdmFacility(admFacilityID);
		nsCustomerID = admFacilityDTOList.get(0).getNsCustomerID();
		return nsCustomerID;
	}
	
	/**
	 * This method is used to get the net suite customer purchased packages
	 * based on customer id.
	 * 
	 * @param int admFacilityID
	 * @return List<String>
	 */
	public List<String> getPurchaseInfo(int nsCustomerID) {
		List<String> listPackage = new ArrayList<String>();
		UserDTO userDTO = new UserDTO();
		userDTO.setEntityId(nsCustomerID);
		userDTO.setRecordType(CUSTOMER_STRING);

		try {

			listPackage = nsCustomerService.getNSCustomerPackages(userDTO);

		} catch (JobBoardNetSuiteServiceException jbns) {
			LOGGER.error("Error occurred while getting the Customer details from net suite..Please contact your administrator."
					+ jbns);
		}
		return listPackage;

	}
	
	
	

}
