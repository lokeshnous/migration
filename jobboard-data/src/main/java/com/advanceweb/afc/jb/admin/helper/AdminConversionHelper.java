package com.advanceweb.afc.jb.admin.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;

/**
 * @author muralikc
 * 
 */
@Repository("adminConversionHelper")
public class AdminConversionHelper {

	private static final Logger LOGGER = Logger
			.getLogger("AdminConversionHelper.class");

	public List<EmpSearchDTO> convertEntityTodDTO(List<AdmFacility> userFacility) {
		EmpSearchDTO searchDto = null;
		List<EmpSearchDTO> dto = new ArrayList<EmpSearchDTO>();
		for (AdmFacility admFacility : userFacility) {
			searchDto = new EmpSearchDTO();
			searchDto.setFacilityId(admFacility.getFacilityId());
			searchDto.setCompanyName(admFacility.getName());
			searchDto.setFacParentId(admFacility.getFacilityParentId());
			searchDto.setFacilityType(admFacility.getFacilityType());
			searchDto.setNsId(admFacility.getNsCustomerID());
			dto.add(searchDto);
		}
		return dto;

	}

}
