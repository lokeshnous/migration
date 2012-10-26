package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.data.entities.AdmApplicationStatus;
import com.advanceweb.afc.jb.data.entities.AdmFolder;


/**
 * @Author : Devi Prasad
 * @Version: 1.0
 * @Created: Oct 15, 2012
 * @Purpose: This class will work as a converter from ManageJobSeekerDTO to
 *           Entity class Object or from Entity class Object to
 *           ManageJobSeekerDTO
 */
@Repository("JobSeekerConversionHelper")
public class JobSeekerConversionHelper {
	/**
	 * Method to prepare jobseekerDTO from the folder detail list 
	 * @param folderDetailList
	 * @return
	 */
	public List<ManageJobSeekerDTO>  transformFolderResumeToManageJobSeekerDTO(List<?> folderDetailList){
		List<ManageJobSeekerDTO> manageJobSeekerDTOs = new ArrayList<ManageJobSeekerDTO>();
		if(null!=folderDetailList&&folderDetailList.size()>0){
			Iterator<?> itr = folderDetailList.iterator();
			while (itr.hasNext()) {
				ManageJobSeekerDTO jobSeekerDTO = new ManageJobSeekerDTO();
			
				Object[] row = (Object[]) itr.next();
				jobSeekerDTO.setResumeName(String.valueOf(row[0]));
				jobSeekerDTO.setRating((Integer) row[1]);
				jobSeekerDTO.setApplicationStatus((Integer) row[2]);
				jobSeekerDTO.setResumeId((Integer) row[3]);
				jobSeekerDTO.setFolderResumeId((Integer) row[4]);
				Date update = null;
				String savedDate=null;
				if(null!=row[6]){
					update=(Date)row[6];
					savedDate=CommonUtil.convertToReqdDateString(update);
				} else {
					if (null != row[5]) {
						update = (Date) row[5];
						savedDate = CommonUtil.convertToReqdDateString(update);
					}
				}
				if(null!=row[7]){
					jobSeekerDTO.setOrgResumeId((Integer) row[7]);
				}
				jobSeekerDTO.setSavedDate(savedDate);
				manageJobSeekerDTOs.add(jobSeekerDTO);
			}
				
			
		}
		return manageJobSeekerDTOs;
		
	}
	/**
	 * Method to prepare the application status drop down
	 * @param appStatusDetailList
	 * @return
	 */
	public List<DropDownDTO> transformApplicationStatusToDropDownDTO(
			List<AdmApplicationStatus> appStatusDetailList) {
		List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
		if (null != appStatusDetailList && appStatusDetailList.size() > 0) {
			for (AdmApplicationStatus admApplicationStatus : appStatusDetailList) {

				DropDownDTO dropDownDTO = new DropDownDTO();
				dropDownDTO.setOptionId(String.valueOf(admApplicationStatus
						.getApplicationStatusId()));
				dropDownDTO.setOptionName(admApplicationStatus.getName());
				dropDownDTOList.add(dropDownDTO);
			}

		}
		return dropDownDTOList;

	}
	/**
	 * Method to prepare list for total available folder as per the user
	 * @param admFolderDetailList
	 * @return
	 */
	public List<AdmFolderDTO> transformAdmFolderToAdmFolderDTO(
			List<AdmFolder> admFolderDetailList) {
		List<AdmFolderDTO> admFolderDTOList = new ArrayList<AdmFolderDTO>();
		if (null != admFolderDetailList && admFolderDetailList.size() > 0) {
			for (AdmFolder admFolder : admFolderDetailList) {

				AdmFolderDTO admFolderDTO = new AdmFolderDTO();
				admFolderDTO.setFolderId(admFolder
						.getFolderId());
				admFolderDTO.setFolderName(admFolder.getFolderName());
				admFolderDTOList.add(admFolderDTO);
			}

		}
		return admFolderDTOList;

	}
	
}
