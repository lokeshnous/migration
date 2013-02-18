package com.advanceweb.afc.jb.admin.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmpSearchDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;

/**
 * @author muralikc
 * 
 */
@Repository("adminConversionHelper")
public class AdminConversionHelper {

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
			// set facility group
			if (admFacility.getFacilityType().equalsIgnoreCase(
					MMJBCommonConstants.FACILITY_GROUP)) {
				searchDto.setHealthSystem(true);
			} else {
				searchDto.setHealthSystem(false);
			}
			
			dto.add(searchDto);
		}
		return dto;

	}

}
