package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminUserRoleDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

public interface UserDao {
	MerUserDTO getUser(String email);
	List<AdminUserRoleDTO> getUserRole(int userId);
}
