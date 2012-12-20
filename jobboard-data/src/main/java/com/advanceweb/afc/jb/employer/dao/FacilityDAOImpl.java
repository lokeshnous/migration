package com.advanceweb.afc.jb.employer.dao;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 3rd Oct 2012
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmRole;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.helper.EmpConversionHelper;
import com.advanceweb.afc.jb.user.dao.UserDao;

@Transactional
@Repository("facilityDAO")
public class FacilityDAOImpl implements FacilityDAO {

	private static final Logger LOGGER = Logger
			.getLogger(FacilityDAOImpl.class);

	private HibernateTemplate hibernateTemplate;

	@Autowired
	private EmpConversionHelper conversionHelper;
	
	@Autowired
	private UserDao userDAO;
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	/**
	 * This method is to get the facility id and role for a particular user
	 * 
	 * @param userId
	 * @return EmployerInfoDTO
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EmployerInfoDTO facilityDetails(int userId) {
		EmployerInfoDTO employerInfoDTO = null;
		AdmUserFacility user = null;

		List<AdmUserFacility> userList = hibernateTemplate
				.find("from AdmUserFacility e where e.id.userId = " + userId);
		if (userList != null && !userList.isEmpty()) {
			user = userList.get(0);
			employerInfoDTO = new EmployerInfoDTO();

			employerInfoDTO
					.setFacilityId(user.getAdmFacility().getFacilityId());
			employerInfoDTO.setRoleId(user.getAdmRole().getRoleId());
			employerInfoDTO.setCustomerNamel(user.getAdmFacility().getName());
		}
		return employerInfoDTO;
	}

	/**
	 * This method is get the metrics details for logged in employer
	 * 
	 * @param facilityId
	 * @return metricsDTO
	 */
	@Override
	public List<MetricsDTO> getJobPostTotal(int facilityId) {

		Query getMetricsData = hibernateTemplate.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(" { call GetMetricsData(?) }");
		getMetricsData.setInteger(0, facilityId);
		List<?> metricsDeatil = getMetricsData.list();
		Iterator<?> iterator = metricsDeatil.iterator();
		List<MetricsDTO> DTOs = new ArrayList<MetricsDTO>();
		while (iterator.hasNext()) {
			MetricsDTO dto = new MetricsDTO();
			Object[] row = (Object[]) iterator.next();
			dto.setViews((Integer) row[0]);
			dto.setClicks((Integer) row[1]);
			dto.setApplies((Integer) row[2]);
			DTOs.add(dto);
		}
		return DTOs;
	}

	/**
	 * This method is used to get all jobs stats for Site – wide average per job posting
	 * 
	 * @return metricsDTO
	 */
	@Override
	public MetricsDTO getAllJobStats() {
		List<?> metricsDeatil= (List<?>) hibernateTemplate
				.find("select sum(views), sum(clicks), sum(applies) from JpJobStat");
		Iterator<?> iterator = metricsDeatil.iterator();
		Object[] row = (Object[]) iterator.next();
		MetricsDTO metricsDTO = new MetricsDTO();
		metricsDTO.setViews((Long) row[0]);
		metricsDTO.setClicks((Long) row[1]);
		metricsDTO.setApplies((Long) row[2]);
		return metricsDTO;
	}

	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardDataException
	 */
	public long getEmployerCount() throws JobBoardDataException {
		long count = 0;
		try {
			String hql = "select count(*) from AdmUserRole where admRole = 3";
			Query query = hibernateTemplate.getSessionFactory()
					.getCurrentSession().createQuery(hql);
			count = ((Number) query.uniqueResult()).intValue();
		} catch (HibernateException he) {
			throw new JobBoardDataException(
					"Error occured while getting the Result from Database" + he);
		}
		return count;
	}

	/**
	 * This method is to get the parent id of logged in facility
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public FacilityDTO getFacilityByFacilityId(int facilityId) {
		AdmFacility facility = (AdmFacility) DataAccessUtils
				.uniqueResult(hibernateTemplate
						.find("from AdmFacility facility where facility.facilityId=?",
								facilityId));
		FacilityDTO dto = new FacilityDTO();
		dto.setFacilityId(facility.getFacilityId());
		dto.setName(facility.getName());
		dto.setRoleId(facility.getAdmUserFacilities().get(0).getFacilityPK()
				.getRoleId());
		dto.setFacilityParentId(facility.getFacilityParentId());
		dto.setLogoPath(facility.getLogoPath());
		dto.setNsCustomerID(facility.getNsCustomerID());
		dto.setFacilityType(facility.getFacilityType());
		return dto;
	}

	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardDataException {

		AdmFacility admFacility = (AdmFacility) hibernateTemplate.find(
				"from AdmFacility e where e.facilityId=?", facilityId).get(0);

		List<AdmFacility> facilityList = new ArrayList<AdmFacility>();

		if (admFacility.getFacilityType().equals(
				MMJBCommonConstants.FACILITY_GROUP)) {
			try {
				List<AdmFacility> admFacilityList = hibernateTemplate
						.find("from AdmFacility e where e.facilityParentId=? and e.deleteDt is Null",
								facilityId);
				for (AdmFacility facility : admFacilityList) {
					// Add the facilities only by avoiding the job owners
					if (facility.getAdmUserFacilities() == null
							|| facility.getAdmUserFacilities().isEmpty()) {
						facilityList.add(facility);
					}
				}
			} catch (HibernateException e) {
				throw new JobBoardDataException(
						"Error occured while getting the facility details from Database"
								+ e);
			}
		}
		return conversionHelper.transformFacilityToDropDownDTO(facilityList,
				admFacility);
	}

	/**
	 * This method is to get facility parent id
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardDataException
	 * @throws JobBoardServiceException
	 */
	public int getFacilityParent(int facilityId) throws JobBoardDataException {
		int facilityParentId = 0;
		AdmFacility admFacility;
		try {
			admFacility = (AdmFacility) hibernateTemplate.find(
					"from AdmFacility e where e.facilityId=?", facilityId).get(
					0);
			facilityParentId = admFacility.getFacilityParentId();
		} catch (Exception e) {
			throw new JobBoardDataException(
					"Error occured while getting the facility parent id from Database"
							+ e);
		}

		return facilityParentId;
	}
	public int getfacilityUserId(int facilityId) {
		AdmRole role = (AdmRole) DataAccessUtils.uniqueResult(hibernateTemplate
				.find("from AdmRole role where role.name=?", MMJBCommonConstants.FACILITY_ADMIN));
		AdmUserFacility facility = (AdmUserFacility) DataAccessUtils
				.uniqueResult(hibernateTemplate
						.find("from AdmUserFacility af where af.facilityPK.roleId=? and af.facilityPK.facilityId=?",
								role.getRoleId(), facilityId));
		return facility.getFacilityPK().getUserId();
	}
	
	/**
	 * This method is used to get all facility list 
	 * @return List<SchedulerDTO>
	 */
	@Override
	public List<SchedulerDTO> getAllFacilityList() {
		List<AdmUserFacility> facility;
		List<SchedulerDTO> schedulerDTOList=new ArrayList<SchedulerDTO>();
		try{
			facility=(List<AdmUserFacility>)hibernateTemplate.find("from AdmUserFacility userFacility where userFacility.facilityPK.roleId=? and userFacility.deleteDt=NULL",MMJBCommonConstants.EMPLOYER_ROLE_ID);
		for(AdmUserFacility admUserFacility:facility){
			if(admUserFacility.getAdmFacility().getFacilityType().equals(MMJBCommonConstants.FACILITY)|| admUserFacility.getAdmFacility().getFacilityType().equals(MMJBCommonConstants.FACILITY_GROUP)){
				SchedulerDTO schedulerDTO=new SchedulerDTO();
				UserDTO userDTO=userDAO.getUserByUserId(admUserFacility.getFacilityPK().getUserId());
				if(userDTO!=null){
					schedulerDTO.setCompanyName(admUserFacility.getAdmFacility().getName());
					schedulerDTO.setUserId(userDTO.getUserId());
					schedulerDTO.setFirstName(userDTO.getFirstName());
					schedulerDTO.setLastName(userDTO.getLastName());
					schedulerDTO.setEmailId(userDTO.getEmailId());
					schedulerDTOList.add(schedulerDTO);
				}
			}
		}
		}catch(Exception e){
			LOGGER.error("Exception occur while getting all facility list for scheduler job"+e.getMessage());
		}
		return schedulerDTOList;
	}
}
