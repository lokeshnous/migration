package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;

public interface UserDao {
	MerUser getUser(String email);
	List<AdmUserRole> getUserRole(int userId);
}
