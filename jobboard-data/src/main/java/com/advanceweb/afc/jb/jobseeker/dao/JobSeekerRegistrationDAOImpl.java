package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.AdmRole;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.AdmUserRolePK;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
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
	private HibernateTemplate hibernateTemplateCareers;
	
	private final String VERIFY_EMAIL = "from MerUser e where e.email = ?";
	private final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof where prof.screenName = ?";
	private final String FIND_JOBSEEKER_ROLE_ID="from AdmRole role where role.name=?";
	private final String FIND_JOBSEEKER_SUBSCRIPTIONS="from AdmSubscription sub where sub.subscriptionType=?";
	
	@Autowired
	public void setHibernateTemplate(final SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
	}
	
	@Autowired
	public void setHibernateTemplateCareers(SessionFactory sessionFactory) {
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is called to save Job seeker registration information into
	 * Database
	 * 
	 * @param jobSeekerRegistrationDTO
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public boolean createNewJobSeeker(JobSeekerRegistrationDTO jsDTO) {
				
		try {
			MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jsDTO);
			if (merUser != null) {
				hibernateTemplate.saveOrUpdate(merUser);			
			}
			
			List<MerUserProfile> merUserProfiles = registrationConversionHelper.transformMerUserDTOToMerUserProfiles(jsDTO, merUser);
			if (merUserProfiles != null) {
				hibernateTemplate.saveOrUpdateAll(merUserProfiles);
			}
			
			List<AdmUserSubscription> admUserSubs = registrationConversionHelper.transformMerUserDTOToAdmUserSubs(jsDTO, merUser);
			if (admUserSubs != null) {
				hibernateTemplateCareers.saveOrUpdateAll(admUserSubs);
			}
			
			List<AdmRole> roleList = hibernateTemplateCareers.find(FIND_JOBSEEKER_ROLE_ID,"jobseeker");
			if(null != roleList && roleList.size()>0){
				AdmRole role = roleList.get(0);
				AdmUserRole userRole = new AdmUserRole();
				AdmUserRolePK pk = new AdmUserRolePK();
					pk.setUserId(merUser.getUserId());
					pk.setRoleId(role.getRoleId());
				userRole.setId(pk);
				hibernateTemplateCareers.saveOrUpdate(userRole);
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
			  List<DropDownDTO> subsList = getSubscriptions();
			  dto = registrationConversionHelper.transformProfileAttrib(listProfAttrib, countryList, stateList, subsList);
			
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
	
	/**
	 * 
	 * @return
	 */
	private List<DropDownDTO> getSubscriptions(){
		
		try {
			List<AdmSubscription> subsList = hibernateTemplateCareers.find(FIND_JOBSEEKER_SUBSCRIPTIONS,"jobseeker");
			return registrationConversionHelper.convertAdmSubscriptionToDropDownDTO(subsList);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}