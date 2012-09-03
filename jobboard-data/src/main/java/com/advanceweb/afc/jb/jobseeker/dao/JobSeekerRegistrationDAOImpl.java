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

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
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
	private final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	private final String FIND_JOBSEEKER_ROLE_ID="from AdmRole role where role.name=?";
	private final String FIND_JOBSEEKER_SUBSCRIPTIONS="from AdmSubscription sub where sub.subscriptionType=?";
	private final String FIND_JOBSEEKER_PROFILE="from MerUserProfile prof where prof.id.userId=?";
	
	@Autowired
	public void setHibernateTemplate(final SessionFactory sessionFactoryMerionTracker, SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
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
	public UserDTO createNewJobSeeker(JobSeekerRegistrationDTO jsDTO) {
				
		try {
			MerUser merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jsDTO, null);
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
				userRole.setCreateUserId(MMJBCommonConstants.ZERO_INT);
				AdmUserRolePK pk = new AdmUserRolePK();
					pk.setUserId(merUser.getUserId());
					pk.setRoleId(role.getRoleId());
				userRole.setRolePK(pk);
				hibernateTemplateCareers.saveOrUpdate(userRole);
			}
			
			return registrationConversionHelper.transformMerUserToUserDTO(merUser);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
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
				JobSeekerRegistrationDTO jsDTO = getProfileAttributes();
				List<MerUserProfile> profiles = hibernateTemplate.find(FIND_JOBSEEKER_PROFILE,jobSeekerId);
								
				jsRegistrationDTO = registrationConversionHelper.transformMerUserProfilesToDTO(merUser, jsDTO, profiles);
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
	public boolean updateJobSeekerDetails(JobSeekerRegistrationDTO jsDTO) {

		try {				
				MerUser merUser = hibernateTemplate.get(MerUser.class,jsDTO.getMerUserDTO().getUserId());
				
				merUser = registrationConversionHelper.transformMerUserDTOToMerUser(jsDTO, merUser);
				
				if (merUser != null) {
					hibernateTemplate.saveOrUpdate(merUser);			
				}
				
				List<MerUserProfile> merUserProfiles = registrationConversionHelper.transformMerUserDTOToMerUserProfiles(jsDTO, merUser);
				if (merUserProfiles != null) {
					hibernateTemplate.saveOrUpdateAll(merUserProfiles);
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
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				List<MerUser> usersList = hibernateTemplate.find(VERIFY_EMAIL,email);
				if(null != usersList && usersList.size()>0){
					MerUser user = usersList.get(0);
					return (null != user ? true : false);
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public JobSeekerRegistrationDTO getProfileAttributes() {
		JobSeekerRegistrationDTO dto = null;
		try {
			  List<MerProfileAttrib> listProfAttrib = hibernateTemplate.find(REGISTRATION_ATTRIBS);
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
			return registrationConversionHelper.transformMerUtilityToDropDownDTO(merUtilityList);

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
			return registrationConversionHelper.transformMerUtilityToDropDownDTO(merUtilityList);
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
			return registrationConversionHelper.transformAdmSubscriptionToDropDownDTO(subsList);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean validateProfileAttributes(int jobseekerId){
		try {
			List<MerUserProfile> profAttribList = hibernateTemplate.find(" from MerUserProfile prof where prof.profilePK.userId=?",jobseekerId);
			if(null != profAttribList && !profAttribList.isEmpty()){
				return true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}