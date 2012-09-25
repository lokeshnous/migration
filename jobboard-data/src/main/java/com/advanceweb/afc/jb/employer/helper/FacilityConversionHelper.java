package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
/**
 * <code>FacilityConversionHelper</code> is a Conversion Helper class
 * 
 * @author Prince Mathew
 * @version 1.0
 * @since 11 Sep 2012
 * 
 */
@Repository("facilityConversionHelper")
public class FacilityConversionHelper {

	/**
	 * Converting AdmFacility to AdmFacilityDTO
	 * 
	 * @param List of AdmFacility 
	 * @return List of AdmFacilityDTO
	 */
	public List<FacilityDTO> transformToFacilityDTO(List<AdmFacility> facility){
		List<FacilityDTO> facilityDTO=new ArrayList<FacilityDTO>();
		for(AdmFacility admFacility:facility){
			FacilityDTO dto=new FacilityDTO();
			dto.setFacilityId(admFacility.getFacilityId());
			dto.setAdminUserId(admFacility.getAdminUserId());
			dto.setCity(admFacility.getCity());
			dto.setCountry(admFacility.getCountry());
			dto.setCreateDt(admFacility.getCreateDt());
			dto.setDeleteDt(admFacility.getDeleteDt());
			dto.setFacilityParentId(admFacility.getFacilityParentId());
			dto.setName(admFacility.getName());
			dto.setState(admFacility.getState());
			facilityDTO.add(dto);
		}
		return facilityDTO;
	}
	
}
