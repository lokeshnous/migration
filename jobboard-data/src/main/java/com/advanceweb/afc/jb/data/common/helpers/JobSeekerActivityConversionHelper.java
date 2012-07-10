package com.advanceweb.afc.jb.data.common.helpers;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.MerUser;


/**
 * @version 1.0
 * @author sharadk
 *
 */
public class JobSeekerActivityConversionHelper {

	/**
	 * Entity to applied job dto
	 * @param entity
	 * @return
	 */
	public AppliedJobDTO transformJpJobToApplidJobDTO(JpJob entity) {
		AppliedJobDTO appliedJobDTO = new AppliedJobDTO();
		if (entity != null) {
			appliedJobDTO.setJobTitle(entity.getJobtitle());
			appliedJobDTO.setAppliedDate(entity.getCreateDt());

		}
		return appliedJobDTO;

	}

	/**
	 * Entity to saved job dto
	 * @param entity
	 * @return
	 */
	public SavedJobDTO transformJpJobToSavedJobDTO(JpJob entity) {
		SavedJobDTO savedJobDTO = new SavedJobDTO();
		if (entity != null) {
			savedJobDTO.setJobTitle(entity.getJobtitle());

		}
		return savedJobDTO;

	}

}
