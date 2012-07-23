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
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.MerLookup;
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
public class JobPostDAOImpl implements JobPostDAO {

	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;
	
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
	public EmployerInfoDTO getEmployerInfo(int userId) {
		
		EmployerInfoDTO employerInfoDTO=new EmployerInfoDTO();
		
		//MerUser empEntity=hibernateTemplateTracker.get(MerUser.class, userId);
		
		employerInfoDTO.setCustomerNo("ABC12345");
		employerInfoDTO.setCustomerNamel("Merion Matters");
		
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
		
		/*List<StateDTO> stateList=new ArrayList<StateDTO>();
		try {
			List<MerLocation> merLocation =(List<MerLocation>) hibernateTemplateTracker.find("from MerLocation m");
			
			StateDTO state=null;
			for(MerLocation merLok:merLocation)
			{
				state=new StateDTO();
				state.setStateId(merLok.getLocationId());
				state.setStateValue(merLok.getState());
				stateList.add(state);
			}
			return stateList;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;*/
		
		try {
			List<MerLookup> merLookupList =  hibernateTemplateTracker.find("from MerLookup e where e.lookupCategory='State' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToStateListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
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
	@Transactional(readOnly=false)
	public boolean savePostJob(JobPostDTO dto) {
		try {
			JpJob jobob=jobPostConversionHelper.transformJobDtoToJpJob(dto);
			hibernateTemplate.save(jobob);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	
}
