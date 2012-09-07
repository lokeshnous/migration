package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.ManageAccessPermissionDAO;
import com.advanceweb.afc.jb.job.service.ManageAccessPermissionService;
/**
 * 
 * @author deviprasadm
 * @Purpose: This class will act as a service impl for the Manage Access Permission
 */
@Service("manageAccessPermissionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class ManageAccessPermissionServiceImpl implements
		ManageAccessPermissionService {
	@Autowired
	public ManageAccessPermissionDAO accessPermissionDAO;

	@Override
	public UserDTO createJobOwner(EmployerProfileDTO profileDTO,
			int facilityIdP, int userIdP) {
		return accessPermissionDAO.createJobOwner(profileDTO, facilityIdP,
				userIdP);

	}

	@Override
	public boolean deleteJobOwner(int ownerId) {
		return accessPermissionDAO.deleteJobOwner(ownerId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateJobOwner(
			List<ManageAccessPermissionDTO> accessPermissionDTO) {
		return accessPermissionDAO.updateJobOwner(accessPermissionDTO);
	}

	@Override
	public List<ManageAccessPermissionDTO> getJobOwnerList(int facilityId,
			int userId) {
		return accessPermissionDAO.getJobOwnerList(facilityId, userId);
	}

}
