package com.advanceweb.afc.jb.admin.service;

import com.advanceweb.afc.jb.common.AdminDTO;

/**
 * @author muralikc
 *
 */
public interface ImpersonateUserService {
	
	/**
	 * @param adminDTO
	 * @return
	 */
	boolean impersonateUser(AdminDTO adminDTO);
	
	/**
	 * @param email
	 * @param password
	 * @return
	 */
	boolean validateAdminCredentials(String email, String password);

}
