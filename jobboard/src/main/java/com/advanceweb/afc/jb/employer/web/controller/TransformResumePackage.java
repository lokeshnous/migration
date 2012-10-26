/**
 * 
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.advanceweb.afc.jb.common.ResumePackageDTO;

/**
 * This is a transformer class which convert Form to DTO and vice versa  
 * @author anilm
 * @created Aug 22, 2012
 */
@Component("transformResumePackage")
public class TransformResumePackage {
	
	/**
	 * This method will transform list of ResumePackageDTO to list of ResumeSearchPackageForm
	 * @param resSearchPackageDTOList
	 * @return resSearchPackageFormList - list of ResumeSearchPackageForm
	 */
	public List<ResumeSearchPackageForm> tranformToResumeSearchPackageFormList(
			List<ResumePackageDTO> resSearchPackageDTOList){
		List<ResumeSearchPackageForm> resSearchPackageFormList = new ArrayList<ResumeSearchPackageForm>();
		ResumeSearchPackageForm resSearchPackageform = null;
		for(ResumePackageDTO resSearchPackageDTO : resSearchPackageDTOList){
			
			resSearchPackageform = new ResumeSearchPackageForm();
			
			resSearchPackageform.setPackageId(resSearchPackageDTO.getPackageId());
			resSearchPackageform.setPackageType(resSearchPackageDTO.getPackageType());
			resSearchPackageform.setPackageName(resSearchPackageDTO.getPackageName());
			resSearchPackageform.setNetsuiteId(resSearchPackageDTO.getNetsuiteId());
			resSearchPackageform.setActive(resSearchPackageDTO.getActive());
			resSearchPackageform.setDuration(resSearchPackageDTO.getDuration());
			resSearchPackageform.setPriceAmt(resSearchPackageDTO.getPriceAmt());
			resSearchPackageform.setDiscount((int)resSearchPackageDTO.getDiscount());
			resSearchPackageFormList.add(resSearchPackageform);
		}
		return resSearchPackageFormList;
	}
}