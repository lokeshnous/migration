package com.advanceweb.afc.jb.employer.dao;
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

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.domain.Employer;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmRole;
import com.advanceweb.afc.jb.data.entities.AdmSubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.AdmUserRolePK;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.data.entities.MerProfileAttrib;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.employer.helper.EmployerRegistrationConversionHelper;
import com.advanceweb.afc.jb.user.helper.RegistrationConversionHelper;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:55 PM
 */
@Repository("employerRegistrationDAO")
public class EmployerRegistrationDAOImpl implements EmployerRegistrationDAO {
	
	private final String FIND_JOBSEEKER_ROLE_ID="from AdmRole role where role.name=?";
	private final String REGISTRATION_ATTRIBS = "from MerProfileAttrib prof";
	private final String FIND_JOBSEEKER_SUBSCRIPTIONS="from AdmSubscription sub where sub.subscriptionType=?";

	private HibernateTemplate hibernateTemplateTracker;
	
	@Autowired
	private EmployerRegistrationConversionHelper empHelper;
	
	@Autowired
	private RegistrationConversionHelper registrationConversionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	public EmployerRegistrationDAOImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * To save employer registrations
	 * 
	 * @param empDTO
	 * @return boolean
	 */
	public MerUserDTO createNewEmployer(EmployerProfileDTO empDTO){
			try {
				MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO.getMerUserDTO());
//				AdmFacility facility = 
				hibernateTemplateTracker.saveOrUpdate(merUser);
				registrationConversionHelper.transformMerUserToUserDTO(merUser);
				
				
				List<MerUserProfile> merUserProfiles = empHelper.transformMerUserDTOToMerUserProfiles(empDTO, merUser);
				if (merUserProfiles != null) {
					hibernateTemplateTracker.saveOrUpdateAll(merUserProfiles);
				}
				//Getting role
				@SuppressWarnings("unchecked")
				List<AdmRole> roleList = hibernateTemplateTracker.find(FIND_JOBSEEKER_ROLE_ID,"facility_admin");
				if(null != roleList && roleList.size()>0){
					AdmRole role = roleList.get(0);
					AdmUserRole userRole = new AdmUserRole();
					AdmUserRolePK pk = new AdmUserRolePK();
						pk.setUserId(merUser.getUserId());
						pk.setRoleId(role.getRoleId());
					userRole.setId(pk);
					hibernateTemplateTracker.saveOrUpdate(userRole);
				}
				
//				AdmFacility admFacility = new AdmFacility();
//				admFacility.setFacilityType("FACILITY");
				
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 
	 * @param employerId
	 */
	public boolean deleteEmployer(long employerId){
		return false;
	}

	/**
	 * 
	 * @param employerId
	 */
	public Employer getEmployerDetails(long employerId){
		return null;
	}

	/**
	 * 
	 * @param employer
	 */
	public boolean updateEmployerDetails(EmployerProfileDTO empDTO){
		return false;
	}
	
	/**
	 * To Change employer password
	 * 
	 * @param empDTO
	 * @return boolean
	 */
	@Override
// TODO: Parameter 'empDTO' is not assigned and could be declared final
	public boolean changePassword(EmployerProfileDTO empDTO) {
		try {
			MerUser merUser = empHelper.transformMerUserDTOToMerUser(empDTO.getMerUserDTO());
			hibernateTemplateTracker.saveOrUpdate(merUser);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	return false;
	}
	
	private List<DropDownDTO> getCountryList() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections.property("country")));
			@SuppressWarnings("unchecked")
			List<Object> merUtilityList = hibernateTemplateTracker.findByCriteria(criteria);
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
			@SuppressWarnings("unchecked")
			List<Object> merUtilityList = hibernateTemplateTracker
					.findByCriteria(criteria);
			return registrationConversionHelper.transformMerUtilityToDropDownDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO#
	 * getProfileAttributes()
	 */
	@Override
	public EmployerProfileDTO getProfileAttributes() {
		EmployerProfileDTO dto = null;
		try {
			@SuppressWarnings("unchecked")
			List<MerProfileAttrib> listProfAttrib = hibernateTemplateTracker
					.find(REGISTRATION_ATTRIBS);
			List<DropDownDTO> countryList = getCountryList();
			List<DropDownDTO> stateList = getStateList();
			dto = registrationConversionHelper.transformProfileAttrib(
					listProfAttrib, countryList, stateList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return dto;
	}

}