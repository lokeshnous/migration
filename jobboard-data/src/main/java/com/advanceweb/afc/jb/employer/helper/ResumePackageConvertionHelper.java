/**
 * 
 */
package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.data.entities.AdmPackage;

/**
 * @author anilm
 *
 */
@Component("resumePackageConvertionHelper")
public class ResumePackageConvertionHelper {
	
	public List<ResumePackageDTO> transformToResumeSearchPackageDTOList(List<AdmPackage> admPackageList){
		List<ResumePackageDTO> resSearchPackageDTOList = new ArrayList<ResumePackageDTO>();
		ResumePackageDTO resSearchPackageDTO = null;
		for(AdmPackage admPackage : admPackageList){
			
			resSearchPackageDTO = new ResumePackageDTO();
			
			resSearchPackageDTO.setPackageId(admPackage.getPackageId());
			resSearchPackageDTO.setPackageType(admPackage.getPackageType());
			resSearchPackageDTO.setPackageName(admPackage.getPackageName());
			resSearchPackageDTO.setNetsuiteId(admPackage.getNetsuiteId());
			resSearchPackageDTO.setActive(admPackage.getActive());
			resSearchPackageDTO.setDuration(admPackage.getDuration());
			resSearchPackageDTO.setPriceAmt(admPackage.getPriceAmt());
			resSearchPackageDTO.setDiscount(admPackage.getDiscount());
			resSearchPackageDTOList.add(resSearchPackageDTO);
		}
		return resSearchPackageDTOList;
		
	}
}
