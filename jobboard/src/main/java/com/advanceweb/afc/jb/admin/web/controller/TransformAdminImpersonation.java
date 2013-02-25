/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/**
	 * Transform admin form to dto.
	 *
	 * @param form the form
	 * @return the admin dto
	 */
	public AdminDTO transformAdminFormToDTO(AdminLoginForm form){
		AdminDTO adminDTO = new AdminDTO();
		adminDTO.setEmpOrAgencyEmail(form.getEmpOrAgencyEmail());
//		adminDTO.setUserEmail(form.getUserEmail());
		adminDTO.setAdminUserId(form.getAdminUserId());
		return adminDTO;
	}
	
	/**
	 * Convert form to dto.
	 *
	 * @param form the form
	 * @return the emp search dto
	 */
	public EmpSearchDTO convertFormToDTO(AdminForm form){
		EmpSearchDTO dto = new EmpSearchDTO();
		dto.setCompanyName(form.getCompName());
		dto.setNsId(Integer.parseInt(form.getNsId()));
		dto.setHealthSystem(form.isHealthSystem());
		return dto;
	}

}
