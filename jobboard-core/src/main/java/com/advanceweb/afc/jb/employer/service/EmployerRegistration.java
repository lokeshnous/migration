package com.advanceweb.afc.jb.employer.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO;
import com.advanceweb.afc.jb.user.ProfileRegistration;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("employerRegistration")
public class EmployerRegistration implements ProfileRegistration,EmloyerRegistartionService {
	private static final Logger LOGGER = Logger
			.getLogger("EmployerRegistration.class");
	@Autowired
	public EmployerRegistrationDAO employerRegistrationDAO;

	@Autowired
	private EmployerDelegate employerDelegate;
	
	public EmployerRegistration(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param profileDTO
	 */
	public UserDTO createEmployer(ProfileDTO profileDTO) {
		try {
			EmployerProfileDTO empProfileDTO = (EmployerProfileDTO) profileDTO;
			return employerDelegate.createEmployer(empProfileDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteProfile(int profileId){
		return false;
	}

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean modifyProfile(ProfileDTO profileDTO){
		return false;
	}

	/**
	 * 
	 * @param profileId
	 */
	public ProfileDTO viewProfile(int profileId){
		return null;
	}

	@Override
	public boolean changePassword(ProfileDTO profileDTO) {
		try {
			EmployerProfileDTO empProfileDTO = (EmployerProfileDTO) profileDTO;
			return employerRegistrationDAO.changePassword(empProfileDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean validatePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateEmail(String email) {
		return employerRegistrationDAO.validateEmail(email);
		}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public ProfileDTO getProfileAttributes() {
		return employerRegistrationDAO.getProfileAttributes();
	}
	

	@Override
	public List<AdmFacilityContact> getEmployeeData(int userId,
			String contactType) {
		List<AdmFacilityContact> accountProfileDTO = new ArrayList<AdmFacilityContact>();
		try {

			accountProfileDTO = employerRegistrationDAO.getEmployeeData(userId,
					contactType);
		} catch (Exception e) {
			LOGGER.info("Error for employee registration edit");
		}
		return accountProfileDTO;
	}

	@Override
	public void editEmployeeAccount(AccountProfileDTO apd, int admfacilityid,
			int userId, String billing) {
		employerRegistrationDAO.editEmployeeAccount(apd, admfacilityid, userId,
				billing);

	}

	@Override
	public AdmFacilityContactDTO getEmployeePrimaryKey(int userId,
			String contactType) {
		AdmFacilityContactDTO accountProfileDTO = new AdmFacilityContactDTO();
		try {

			accountProfileDTO = employerRegistrationDAO.getEmployeePrimaryKey(
					userId, contactType);
		} catch (Exception e) {
			LOGGER.info("Error for employee registration edit");
		}
		return accountProfileDTO;
	}

	@Override
	public boolean validateProfileAttributes(int jobseekerId) {
		
		return employerRegistrationDAO.validateProfileAttributes(jobseekerId);
	}
	
	
	
}