package com.advanceweb.afc.jb.employer.service.impl;

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

	
	private static final Logger LOGGER = Logger
			.getLogger(BrandingTemplateDelegateImpl.class);
	
	private String CUSTOMER_STRING = "customer";
	
	@Autowired
	private NSCustomerService nsCustomerService;
	
	
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
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * @param int admFacilityID
	 * @return UserDTO
	 */
	public UserDTO getCustomerDetails(int nsCustomerID) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEntityId(nsCustomerID);
		userDTO.setRecordType(CUSTOMER_STRING);

		try {
			
			userDTO = nsCustomerService.getNSCustomerDetails(userDTO);
			
		} catch (JobBoardNetSuiteServiceException jbns) {
			LOGGER.info("Error occurred while getting the Customer details from net suite..Please contact your administrator."
					+ jbns);
		}
		return userDTO;
		
	}
	
	
	

}
