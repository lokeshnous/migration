package com.advanceweb.afc.jb.data.common.helpers;

import java.util.ArrayList;
import java.util.List;


import com.advanceweb.afc.jb.data.entities.ResUploadResume;
import com.advanceweb.afc.jb.common.ResumeDTO;


/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */
public class ResumeConversionHelper {
	
	public List<ResumeDTO> transformResUploadResumeToResumeDTO(List<ResUploadResume> resumes){
		List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
		
		for(ResUploadResume resume : resumes ){
			ResumeDTO resumeDTO = new ResumeDTO();
			resumeDTO.setUploadResumeId(resume.getUploadResumeId());
			resumeDTO.setResume_name(resume.getResumeName());
			resumeDTO.setUpdateDt(resume.getUpdateDt());			
			resumeDTOList.add(resumeDTO);
		}
		
		return resumeDTOList;
		
	}


}

