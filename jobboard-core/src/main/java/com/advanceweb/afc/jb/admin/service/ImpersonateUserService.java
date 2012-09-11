package com.advanceweb.afc.jb.admin.service;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.UserDTO;

/**
 * @author muralikc
 *
 */
public interface ImpersonateUserService {
	
	/**
	 * @param adminDTO
	 * @return
	 */
	public boolean impersonateUser(AdminDTO adminDTO);
	
	/**
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean validateAdminCredentials(String email, String password);

}
