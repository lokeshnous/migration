package com.advanceweb.afc.jb.admin.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AdminDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;
import com.mysql.jdbc.StringUtils;

@Repository("impersonateDAO")
public class AdminDAOImpl implements AdminDAO {

	private static final Logger LOGGER = Logger
			.getLogger("ImpersonateDAOImpl.class");

	private static final String GET_EMAIL = "from MerUser e where e.email = ?";
	private static final String USER_ROLE = "from AdmUserRole aur where aur.rolePK.userId = ?";
	private static final String FACILITY_ID = "from AdmUserFacility auf where auf.facilityPK.userId =?";
	private static final String ADM_FACILITY = "from AdmFacility af where af.facilityId=?";
	private static final String VALIDATE_ADMIN = "from MerUser e where e.email=? and e.password=?";
	private static final String VALIDATE_ADM_USERID = "from AdmFacility af where af.adminUserId =?";

	private HibernateTemplate hibernateTemplateTracker;

	private HibernateTemplate hibernateTemplateCareers;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
	}
	@Override
	public boolean validateEmail(String email) {
		boolean status = false;
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				@SuppressWarnings("unchecked")
				List<MerUser> usersList = hibernateTemplateTracker.find(
						GET_EMAIL, email);

				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					@SuppressWarnings("unchecked")
					List<AdmUserRole> useList = hibernateTemplateCareers.find(
							USER_ROLE, user.getUserId());
					if (null != useList && !useList.isEmpty()) {
						if (useList.get(0).getAdmRole().getRoleId() == 3) {
							status = true;
						}
					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return status;
	}
	
	
	@Override
	public boolean validateAdminCredentials(String email, String password) {
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)
					&& !StringUtils.isEmptyOrWhitespaceOnly(password)) {
				@SuppressWarnings("unchecked")
				List<MerUser> usersList = hibernateTemplateTracker.find(
						VALIDATE_ADMIN, email, password);

				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					@SuppressWarnings("unchecked")
					List<AdmUserRole> useList = hibernateTemplateCareers.find(
							USER_ROLE, user.getUserId());
					if (null != useList && !useList.isEmpty()) {
						if (useList.get(0).getAdmRole().getRoleId() == 1) {
							return (null != useList ? true : false);
						}
					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean impersonateUser(AdminDTO adminDTO) {
		boolean status = true;
		try {
			// getting Admin User Id
			List<MerUser> usersList = hibernateTemplateTracker.find(GET_EMAIL,
					adminDTO.getUserEmail());
			int admUserId = 0;
			if (null != usersList && !usersList.isEmpty()) {
				MerUser user = usersList.get(0);
				admUserId = user.getUserId();
			}
			//getting user Id for emp / agency
			List<MerUser> usersList1 = hibernateTemplateTracker.find(GET_EMAIL,
					adminDTO.getEmpOrAgencyEmail());
			int facilityId = 0;
			AdmUserFacility facility = null;
			if (null != usersList1 && !usersList1.isEmpty()) {
				MerUser user1 = usersList1.get(0);
				List<AdmUserFacility> facilityList = hibernateTemplateCareers.find(
						FACILITY_ID, user1.getUserId());
				if (null != facilityList && !facilityList.isEmpty()) {
					facility = facilityList.get(0);
					facilityId = facility.getFacilityPK().getFacilityId();
				}
			}
			List<AdmFacility> admFacilityList = hibernateTemplateCareers.find(ADM_FACILITY,
					facilityId);
			AdmFacility admfacility =admFacilityList.get(0);
			admfacility.setAdminUserId(admUserId);
			List<AdmFacility> admList = hibernateTemplateCareers.find(VALIDATE_ADM_USERID,
					admUserId);
			
			if (admList.isEmpty()) {
				hibernateTemplateCareers.update(admfacility);
			}else{
				AdmFacility fac = admList.get(0);
				fac.setAdminUserId(0);
				hibernateTemplateCareers.saveOrUpdate(fac);
				hibernateTemplateCareers.update(admfacility);
			}
			
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return status;

	}
}
