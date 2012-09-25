package com.advanceweb.afc.jb.agency.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.agency.dao.ImpersonateAgencyDAO;
import com.advanceweb.afc.jb.agency.service.AgencyDelegate;
import com.advanceweb.afc.jb.agency.service.ImpersonateAgencyService;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.user.dao.UserDao;

@Service("impersonateAgencyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class ImpersonateAgencyServiceImpl implements ImpersonateAgencyService{

	@Autowired
	private UserDao userDAO;
	
	@Autowired
	private AgencyDelegate agencyDelegate;
	@Autowired
	private ImpersonateAgencyDAO impersonateAgencyDAO;
	@Override
	public int getfacility(int facilityId) {
		return userDAO.getfacility(facilityId);
	}
	@Override
	public UserDTO getUserByUserId(int userId) {
		return userDAO.getUserByUserId(userId);
	}
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	public int getNSCustomerIDFromAdmFacility(int admFacilityID){
		return agencyDelegate.getNSCustomerIDFromAdmFacility(admFacilityID);
		
	}
	
	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	
	public UserDTO getNSCustomerDetails(int nsCustomerID){
		return  agencyDelegate.getNSCustomerDetails(nsCustomerID);
	}
	
	@Override
	public boolean addEmployer(AccountProfileDTO accountDto,
			int agencyFacilityId, int userId) {
		return impersonateAgencyDAO.addEmployer(accountDto, agencyFacilityId,
				userId);
	}

	@Override
	public List<FacilityDTO> getAssocEmployerNames(int userId,
			int agencyFacilityId) {
		return impersonateAgencyDAO.getAssocEmployerNames(userId,
				agencyFacilityId);
	}

/*	@Override
	public boolean saveEmployerDetails(AccountProfileDTO dto) {
		return agencyRegistrationDAO.saveEmployerDetails(dto);
	}
*/
	@Override
	public boolean deleteAssocEmployer(String facilityId, int userId) {
		return impersonateAgencyDAO.deleteAssocEmployer(facilityId, userId);
	}
	@Override
	public List<FacilityDTO> getEmployerNamesList(String employerName) {
		return impersonateAgencyDAO.getEmployerNamesList(employerName);
	}

	@Override
	public Map getEmployerDetails(int facilityId) {
		return impersonateAgencyDAO.getEmployerDetails(facilityId);
	}

}
