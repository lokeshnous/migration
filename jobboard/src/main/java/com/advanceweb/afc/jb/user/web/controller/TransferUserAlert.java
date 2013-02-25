/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.UserAlertDTO;
import com.advanceweb.afc.jb.employer.web.controller.UserAlertForm;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 6th sept, 2012
 */
@Repository("TransferUserAlert")
public class TransferUserAlert {

	/**
	 * This method is used to convert form values to DTO
	 * 
	 * @param alertForm
	 * @return
	 */
	public List<UserAlertDTO> jsAlertFormToUserAlertDTO(UserAlertForm alertForm) {

		List<UserAlertDTO> list = new ArrayList<UserAlertDTO>();
		if (null != alertForm.getSelectedAlerts()) {
			for (String selAlert : alertForm.getSelectedAlerts()) {
				UserAlertDTO dto = new UserAlertDTO();
				dto.setAlertId(Integer.valueOf(selAlert));
				dto.setUserId(Integer.parseInt(alertForm.getSelJobOwner()));
				dto.setFacilityId(alertForm.getFacilityId());
				list.add(dto);
			}
		}

		return list;

	}

	/**
	 * This method is called to select current alerts
	 * 
	 * @param currentSubsList
	 * @param form
	 * @param listSubscriptions
	 * @return
	 */
	public List<DropDownDTO> jsUserAlertDTOToUserAlerts(
			List<UserAlertDTO> userAlertDTOs, UserAlertForm form,
			List<DropDownDTO> alertList) {

		List<String> currSubList = new ArrayList<String>();
		List<DropDownDTO> selSubList = new ArrayList<DropDownDTO>();
		if (null != userAlertDTOs) {
			for (UserAlertDTO dto : userAlertDTOs) {
				for (DropDownDTO subdto : alertList) {
					if (subdto.getOptionId().equals(
							String.valueOf(dto.getAlertId()))) {
						currSubList.add(subdto.getOptionId());
						selSubList.add(subdto);
					}
				}
			}
			if (null != form) {
				form.setSelectedAlerts(currSubList
						.toArray(new String[currSubList.size()]));
			}
		}
		return selSubList;
	}
	
	/**
	 * Jb owner list to drop down dto.
	 *
	 * @param jbOwnerList the jb owner list
	 * @return the list
	 */
	public List<DropDownDTO> jbOwnerListTODropDownDTO(List<ManageAccessPermissionDTO> jbOwnerList){
		List<DropDownDTO> selOwnerList = new ArrayList<DropDownDTO>();
		for(ManageAccessPermissionDTO permissionDTO : jbOwnerList){
			DropDownDTO dropDownDTO = new DropDownDTO();
			dropDownDTO.setOptionId(String.valueOf(permissionDTO.getOwnerId()));
			dropDownDTO.setOptionName(permissionDTO.getOwnerName());
			selOwnerList.add(dropDownDTO);
		}
		
		return selOwnerList;
		
	}

}
