/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.dao;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobApplicationDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class implements all the DAO operations related to applying job for Anonymous User

 */
@Repository
public class JobApplicationDAOImpl implements JobApplicationDAO {

	/*@Autowired
	AnonymousUserJobApplyConversionHelper conversionHelper;*/

	/** The hibernate template. */
	public HibernateTemplate hibernateTemplate;
	
	
	
	/**
	 * @param hibernateTemplate the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}



	/**
	   @Author :Prince Mathew
	   @Purpose:This method is use to apply a job for Anonymous User
	   @Created:Jul 12, 2012
	   @Param  :AnonymousUserJobApplyDTO
	   @Return :boolean value
	 * @see com.advanceweb.afc.jb.job.dao.JobApplicationDAO#applyJobAnonymousUser(com.advanceweb.afc.jb.common.JobApplicationDTO)
	 */
	@Override
	public boolean applyJobAnonymousUser(JobApplicationDTO dto) {
/*		EmailDTO emailDTO=new EmailDTO();
		List<AttachedFile> attachedFile=new ArrayList();
		AttachedFile file=new AttachedFile();
		file.setFilePath(dto.getFilePath());
		attachedFile.add(file);
*/		
		
		
		return false;
	}

}
