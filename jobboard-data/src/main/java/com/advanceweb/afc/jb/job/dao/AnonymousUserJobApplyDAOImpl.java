package com.advanceweb.afc.jb.job.dao;


import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 12, 2012
   @Purpose: This class implements all the DAO operations related to applying job for Anonymous User

 */
@Repository
public class AnonymousUserJobApplyDAOImpl implements AnonymousUserJobApplyDAO {

	/*@Autowired
	AnonymousUserJobApplyConversionHelper conversionHelper;*/

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
	 * @see com.advanceweb.afc.jb.job.dao.AnonymousUserJobApplyDAO#applyJobAnonymousUser(com.advanceweb.afc.jb.common.AnonymousUserJobApplyDTO)
	 */
	@Override
	public boolean applyJobAnonymousUser(AnonymousUserJobApplyDTO dto) {
/*		EmailDTO emailDTO=new EmailDTO();
		List<AttachedFile> attachedFile=new ArrayList();
		AttachedFile file=new AttachedFile();
		file.setFilePath(dto.getFilePath());
		attachedFile.add(file);
*/		
		
		
		return false;
	}

}
