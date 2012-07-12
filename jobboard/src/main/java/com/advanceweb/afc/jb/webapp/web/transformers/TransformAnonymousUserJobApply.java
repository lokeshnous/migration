package com.advanceweb.afc.jb.webapp.web.transformers;

import java.util.ArrayList;
import java.util.List;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;
import com.advanceweb.afc.jb.webapp.web.forms.jobapply.AnonymousUserJobApply;
import com.advanceweb.afc.jb.webapp.web.forms.resume.CertificationsForm;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class will act as a Converter from Form Bean to DTO or from DTO to Form Bean
 */
public class TransformAnonymousUserJobApply {

public List<AnonymousUserJobApplyDTO> transformAnonymousUserJobApplyDTO(List<AnonymousUserJobApply> listAnoUserForms){
		
		List<AnonymousUserJobApplyDTO> listAnoUserDTO = new ArrayList<AnonymousUserJobApplyDTO>();
		if(null != listAnoUserForms){
			for(AnonymousUserJobApply anoForm : listAnoUserForms){
				AnonymousUserJobApplyDTO dto = new AnonymousUserJobApplyDTO();
				dto.setName(anoForm.getName());
				dto.setEmail(anoForm.getEmail());
				dto.setFileContent(anoForm.getFileContent());
				dto.setFileName(anoForm.getFileName());
				
				listAnoUserDTO.add(dto);
			}
		}
		return listAnoUserDTO;
	}
	
}
