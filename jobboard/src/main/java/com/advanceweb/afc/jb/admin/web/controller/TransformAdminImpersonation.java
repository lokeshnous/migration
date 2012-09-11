package com.advanceweb.afc.jb.admin.web.controller;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AdminDTO;

@Repository("transformAdminImpersonation")
public class TransformAdminImpersonation {
	
	public AdminDTO transformAdminFormToDTO(AdminLoginForm form){
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setEmpOrAgencyEmail(form.getEmpOrAgencyEmail());
		adminDTO.setUserEmail(form.getUserEmail());
		return adminDTO;
	}

}
