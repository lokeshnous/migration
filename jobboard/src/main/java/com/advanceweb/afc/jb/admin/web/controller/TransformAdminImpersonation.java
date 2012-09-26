package com.advanceweb.afc.jb.admin.web.controller;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.EmpSearchDTO;

/**
 * @author muralikc
 *
 */
@Repository("transformAdminImpersonation")
public class TransformAdminImpersonation {
	
	public AdminDTO transformAdminFormToDTO(AdminLoginForm form){
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setEmpOrAgencyEmail(form.getEmpOrAgencyEmail());
		adminDTO.setUserEmail(form.getUserEmail());
		return adminDTO;
	}
	
	public EmpSearchDTO convertFormToDTO(AdminForm form){
		EmpSearchDTO dto = new EmpSearchDTO();
		dto.setCompanyName(form.getCompName());
		dto.setNsId(Integer.parseInt(form.getNsId()));
		dto.setHealthSystem(form.isHealthSystem());
		return dto;
	}

}
