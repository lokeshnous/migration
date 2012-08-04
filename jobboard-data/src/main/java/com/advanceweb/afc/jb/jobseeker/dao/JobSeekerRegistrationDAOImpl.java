package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;
import com.mysql.jdbc.StringUtils;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:56 PM
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("jobSeekerRegistrationDAO")
public class JobSeekerRegistrationDAOImpl implements JobSeekerRegistrationDAO {

	
	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;
	
	private HibernateTemplate hibernateTemplate;
	
	private final String VERIFY_EMAIL = "from MerUser e where e.email = ?";
	private final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof where prof.screenName = ?";
	
	@Autowired
	public void setHibernateTemplate(final SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	/**
	 * This method is called to save Job seeker registration information into
	 * Database
	 * 
	 * @param jobSeekerRegistrationDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public boolean createNewJobSeeker(JobSeekerRegistrationDTO jsDTO) {
		
		MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jsDTO);
		try {
			if (merUser != null) {
				hibernateTemplate.saveOrUpdate(merUser);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 
	 * @param jobSeekerId
	 */
	@Override
	public boolean deleteJobSeeker(int jobSeekerId) {
		return false;
	}

	@Override
	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param jobSeekerId
	 */
	@Override
	@Transactional(readOnly=true)
	public JobSeekerRegistrationDTO getJobSeekerDetails(int jobSeekerId) {
		JobSeekerRegistrationDTO jsRegistrationDTO = new JobSeekerRegistrationDTO();
		try {
			if (jobSeekerId != 0) {
				MerUser merUser = hibernateTemplate.load(MerUser.class, jobSeekerId);
				MerUserDTO merUserDTO = registrationConversionHelper.transformMerUserToMerUserDTO(merUser);
				AddressDTO addDTO = registrationConversionHelper.transformMerUserToAddDTO(merUser);
				JobSeekerProfileDTO profileDTO = registrationConversionHelper.transformMerUserToProfileDTO(merUser);
				jsRegistrationDTO.setMerUserDTO(merUserDTO);
				jsRegistrationDTO.setAddressDTO(addDTO);
				jsRegistrationDTO.setJobSeekerProfileDTO(profileDTO);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return jsRegistrationDTO;
	}

	/**
	 * 
	 * @param jobSeeker
	 */
	@Override
	public boolean updateJobSeekerDetails(JobSeekerRegistrationDTO jobSeekerRegistrationDTO) {
		
		MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jobSeekerRegistrationDTO);
		try {
			if (merUser != null) {
				hibernateTemplate.saveOrUpdate(merUser);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}



	@Override
	public boolean jsChangePassword( JobSeekerRegistrationDTO dto) {
		try {
			if (dto.getMerUserDTO() != null) {
				MerUser user = hibernateTemplate.get(MerUser.class, dto.getMerUserDTO().getUserId());
				if(null != user){
					user.setPassword(dto.getMerUserDTO().getPassword());
				}
				hibernateTemplate.saveOrUpdate(user);				
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean validatePassword(JobSeekerRegistrationDTO dto) {
		try {
			if (dto.getMerUserDTO() != null) {
				MerUser user = hibernateTemplate.get(MerUser.class, dto.getMerUserDTO().getUserId());
				if(null != user){
					return user.getPassword().equals(dto.getMerUserDTO().getCurrentPassword());
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean validateEmail(String email) {
		try {
			if (StringUtils.isEmptyOrWhitespaceOnly(email)) {
				MerUser user = (MerUser) hibernateTemplate.find(VERIFY_EMAIL,email);
				if(null != user){
					return true;
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public JobSeekerRegistrationDTO getProfileAttributes(String strScreenName) {
		JobSeekerRegistrationDTO dto = null;
		try {
			  List<MerProfileAttrib> listProfAttrib = hibernateTemplate.find(REGISTRATION_ATTRIBS,strScreenName);
			  List<DropDownDTO> countryList = getCountryList();
			  List<DropDownDTO> stateList = getStateList();
			  dto = registrationConversionHelper.transformProfileAttrib(listProfAttrib, countryList, stateList);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
	
	
	private List<DropDownDTO> getCountryList() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections.property("country")));
			List<Object> merUtilityList = hibernateTemplate.findByCriteria(criteria);
			return registrationConversionHelper.convertMerUtilityToDropDownDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	private List<DropDownDTO> getStateList() {
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections
					.property("state")));
			criteria.addOrder(Order.asc("state"));
			List<Object> merUtilityList = hibernateTemplate
					.findByCriteria(criteria);
			return registrationConversionHelper.convertMerUtilityToDropDownDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

}