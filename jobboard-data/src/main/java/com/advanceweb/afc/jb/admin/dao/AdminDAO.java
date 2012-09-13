package com.advanceweb.afc.jb.admin.dao;

import com.advanceweb.afc.jb.common.AdminDTO;
//import com.advanceweb.afc.jb.common.UserDTO;

public interface AdminDAO {
	
	/**
	 * @param email
	 * @return
	 */
	boolean validateEmail(String email);
	
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
