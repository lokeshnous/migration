package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

public interface UserDao {
	MerUserDTO getUser(String email);
	List<UserRoleDTO> getUserRole(int userId);
}
