package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.employer.helper.JobPostConversionHelper;
import com.advanceweb.afc.jb.lookup.helper.PopulateDropdownConversionHelper;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class implements all the DAO operations related to post new job 
 */
@Transactional 
@Repository("employerJobPostDAO")
@SuppressWarnings("unchecked")
public class JobPostDAOImpl implements JobPostDAO {

	private static final String FIND_ADM_USER_FACILITY="select facility from AdmUserFacility facility, AdmRole role where role.roleId=facility.id.roleId and facility.id.userId=? and role.name=?";
	
	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;
	

	
	@Autowired
	private JobPostConversionHelper jobPostConversionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
/*	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactory);
	}*/
	/**
	   @Author :Prince Mathew
	   @Purpose:To get the records of the Employer by userId from the DB
	   @Created:Jul 19, 2012
	   @Param  :userId
	   @Return :EmployerInfoDTO containing the information of corresponding Employer
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getEmployerInfo(int)
	 */
	@Override
	public EmployerInfoDTO getEmployerInfo(int userId, String roleName) {
		
		EmployerInfoDTO employerInfoDTO=new EmployerInfoDTO();
		
		Object[] inputs= {userId, roleName};

		List<AdmUserFacility> facilityList = hibernateTemplate.find(FIND_ADM_USER_FACILITY, inputs);
		
		if(null != facilityList && facilityList.size() != 0){
			AdmUserFacility userFacility = facilityList.get(0);
			AdmFacility facility = userFacility.getAdmFacility();
			if(null != facility){
				employerInfoDTO.setCustomerNamel(facility.getName());
				employerInfoDTO.setUserId(userFacility.getId().getUserId());
				employerInfoDTO.setFacilityId(userFacility.getId().getFacilityId());
				employerInfoDTO.setRoleId(userFacility.getId().getRoleId());
			}					
		}
		
		
		return employerInfoDTO;
	}
	/**
	   @Author :Prince Mathew
	   @Purpose:to get the List of the State for the drop down of state for Post New Job Screen 
	   @Created:Jul 19, 2012
	   @Param  :not required
	   @Return :List of StateDTO
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#getStateList()
	 */
	public List<StateDTO> getStateList() {
		
		List<JpAttribList> merLookupList =  hibernateTemplateTracker.find("from JpAttribList e where e.lookupCategory='State' and e.lookupStatus='1'");	

		return null;
	}
	/**
	   @Author :Prince Mathew
	   @Purpose:This method is called to post the new job
	   @Created:Jul 20, 2012
	   @Param  :EmployerInfoDTO object
	   @Return :boolean result
	 * @see com.advanceweb.afc.jb.employer.dao.JobPostDAO#savePostJob(com.advanceweb.afc.jb.common.EmployerInfoDTO)
	 */
	@Override
	public boolean savePostJob(JobPostDTO dto) {

			JpJob jobob=jobPostConversionHelper.transformJobDtoToJpJob(dto);
			hibernateTemplate.save(jobob);



		return false;
	}
	
	
	
}
