/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmFacilitySubscription;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.MerProfileAttribList;
import com.advanceweb.afc.jb.data.entities.MerPublication;
import com.advanceweb.afc.jb.data.entities.MerPublicationProfession;
import com.advanceweb.afc.jb.data.entities.MerUserProfile;
import com.advanceweb.afc.jb.data.entities.ResCoverletter;
import com.advanceweb.afc.jb.data.entities.ResCoverletterPriv;
import com.advanceweb.afc.jb.data.entities.ResPrivacy;
import com.advanceweb.afc.jb.jobseeker.helper.UserSubscriptionsConversionHelper;
import com.advanceweb.afc.jb.lookup.helper.PopulateDropdownConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@SuppressWarnings("unchecked")
@Repository("subscriptionsDAO")
public class UserSubscriptionsDAOImpl implements UserSubscriptionsDAO {

	/** The Constant FETCH_USER_SUBSCRIPTIONS. */
	private static final String FETCH_USER_SUBSCRIPTIONS = "select usersubs from AdmUserSubscription usersubs where usersubs.id.userId=:userId and usersubs.deleteDt is null";
	
	/** The Constant SELECTED_CURRENT_SUBS. */
	private static final String SELECTED_CURRENT_SUBS = "from AdmUserSubscription sub where sub.id.userId=?";
	
	/** The Constant SELECTED_FACILITY_SUBS. */
	private static final String SELECTED_FACILITY_SUBS = "from AdmFacilitySubscription e where e.admFacility.facilityId=?";
	// private static final String FIND_USER_SUBSCRIPTIONS =
	// "from AdmSubscription sub where sub.subscriptionType=?";
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(UserSubscriptionsDAOImpl.class);

	/** The Constant OLD_FORMAT. */
	private static final String OLD_FORMAT = "MM/dd/yyyy HH:mm:ss";
	
	/** The Constant NEW_FORMAT. */
	private static final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// private static final ResPrivacy ResCoverletter = null;
	// private static final
	// MAX_RESULT="select MAX(rs.coverletterId),rs.userId,rs.name,rs.coverletterText,rs.active,rs.createDt,rs.updateDt,rs.deleteDt";

	/** The hibernate template careers. */
	private HibernateTemplate hibernateTemplateCareers;

	/** The hibernate template tracker. */
	private HibernateTemplate hibernateTemplateTracker;

	/** The subscription helper. */
	@Autowired
	private UserSubscriptionsConversionHelper subscriptionHelper;

	/** The dropdown helper. */
	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactoryMerionTracker the session factory merion tracker
	 * @param sessionFactory the session factory
	 */
	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

	}

	/**
	 * save subscription
	 */

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveJobSeekerSubscription(
			List<UserSubscriptionsDTO> listSubsDTO, int userId) {
		try {

			if (userId != 0) {
				List<AdmUserSubscription> listSubsAlerts = hibernateTemplateCareers
						.find(SELECTED_CURRENT_SUBS, userId);
				List<AdmUserSubscription> userAlerts = subscriptionHelper
						.transformjsSubsDTOToAdmUserSubs(listSubsDTO,
								listSubsAlerts);
				hibernateTemplateCareers.deleteAll(listSubsAlerts);
				for (AdmUserSubscription sub : userAlerts) {
					hibernateTemplateCareers.merge(sub);
				}
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return true;
	}

	/**
	 * To get current subscriptions of the user
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserSubscriptionsDTO> getCurrentSubscriptions(int userId) {

		List<UserSubscriptionsDTO> listSubscriptiosns = null;
		try {
			List<AdmUserSubscription> listSubs = hibernateTemplateCareers.find(
					SELECTED_CURRENT_SUBS, userId);
			listSubscriptiosns = subscriptionHelper
					.transformMerUserAlertsTojsSubsDTO(listSubs);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return listSubscriptiosns;
	}

	/**
	 * Delete the subscription of user by user Id
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public void deleteSubscriptionsById(int userId) {
		try {
			Query query = hibernateTemplateCareers.getSessionFactory()
					.getCurrentSession().createQuery(FETCH_USER_SUBSCRIPTIONS);
			query.setParameter("userId", userId);
			query.setFirstResult(0);
			// TODO: Remove hard code 100
			query.setMaxResults(100);
			hibernateTemplateCareers.deleteAll(query.list());
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

	}

	/**
	 * @author kartikm
	 * @Purpose:Save of Cover letter when submit the cover letter text
	 * @Created:Sept 14, 2012
	 * @param rclDTO
	 * @return isUpdate
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO) {
		ResCoverletter resUpdate = new ResCoverletter();
		// ResCoverletter resUpd = new ResCoverletter();
		ResPrivacy rpupdate = new ResPrivacy();
		boolean isUpdate = false;
		try {
			Date todayDate = null;
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat(OLD_FORMAT,
					Locale.US);
			try {
				String dateNow = formatter.format(currentDate.getTime());
				todayDate = dateConveter(dateNow);
			} catch (Exception ex) {
				LOGGER.error("Error:Cover letter save by job seeker" + ex);
			}

			// this is save option in ResCoverletter
			ResCoverletter res = new ResCoverletter();
			res.setUserId(rclDTO.getUserId());
			res.setName(rclDTO.getName());
			res.setCoverletterText(rclDTO.getCoverletterText());
			res.setActive(rclDTO.getActive());
			res.setCreateDt(todayDate);
			// res.setUpdateDt(null);
			hibernateTemplateCareers.save(res);
			// this is save option in ResCoverletterPriv
			List<ResPrivacy> rpData = hibernateTemplateCareers.find(
					"from ResPrivacy rp where rp.description=?",
					rclDTO.getActive());
			if (null != rpData && !rpData.isEmpty()) {
				int privId = rpData.get(0).getPrivacyId();
				int primaryId = 0;
				primaryId = (Integer) hibernateTemplateCareers
						.getSessionFactory()
						.getCurrentSession()
						.createQuery(
								"select MAX(rs.coverletterId) from ResCoverletter rs")
						.uniqueResult();
				// int primaryId = resIndex.get(0).getCoverletterId();
				ResCoverletterPriv resPriv = new ResCoverletterPriv();
				resPriv.setResCoverletter(resUpdate);
				resPriv.setResPrivacy(rpupdate);
				resPriv.getResCoverletter().setCoverletterId(primaryId);
				resPriv.getResPrivacy().setPrivacyId(privId);
				resPriv.setActive(rclDTO.getActive());
				resPriv.setCreateDt(todayDate);
				resPriv.setCreateUserId(rclDTO.getUserId());
				hibernateTemplateCareers.save(resPriv);
			}

		} catch (DataAccessException e) {
			LOGGER.error("Could not save Cover letter" + e);
		}
		return isUpdate;
	}

	/**
	 * @author kartikm
	 * @Purpose:Date Conversion method name dateConveter that is for Converting
	 *               from MM/dd/yyyy HH:mm:ss to yyyy-MM-dd HH:mm:ss
	 * @Created:Sept 12, 2012
	 * @param date
	 * @return dateValue
	 */
	public Date dateConveter(String date) {
		Date dateValue = null;
		String newDateString;
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, Locale.US);
		try {
			Date dateConv = sdf.parse(date);
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(dateConv);
			SimpleDateFormat sdfSource = new SimpleDateFormat(NEW_FORMAT,
					Locale.US);
			dateValue = sdfSource.parse(newDateString);
		} catch (ParseException e) {
			LOGGER.error("Date parsing in Job save by admin wrong",e);
		}

		return dateValue;
	}

	/**
	 * @author kartikm
	 * @Purpose:Find the status of the cover letter that is public or private
	 * @Created:Sept 14, 2012
	 * @param userId
	 * @param status
	 * @return dateValue
	 */
	@Override
	public boolean findActiveStatus(int userId, int status) {
		boolean isUpdate = false;
		try {
			List<ResCoverletter> res = hibernateTemplateCareers
					.find("from ResCoverletter rs where rs.userId=? and deleteDt is null",
							userId);
			if (null != res && !res.isEmpty()) {
				int countData = res.size();

				if ((countData < 5) && (countData >= 0)) {
					isUpdate = true;
				} else {
					isUpdate = false;
				}
			}

		} catch (DataAccessException ex) {
			LOGGER.error("Error: Find count for active status" + ex);
		}
		return isUpdate;
	}

	/**
	 * @author kartikm
	 * @Purpose:Find the status of the cover letter that is public or private
	 * @Created:Sept 14, 2012
	 * @param userId
	 * @param status
	 * @return dateValue
	 */
	@Override
	public boolean findFirstActiveStatus(int userId, int status) {
		boolean isUpdate = false;
		try {
			List<ResCoverletter> res = hibernateTemplateCareers
					.find("from ResCoverletter rs where rs.userId=? and deleteDt is null",
							userId);
			if (null == res || res.isEmpty()) {
				isUpdate = true;

			} else {
				isUpdate = false;
			}

		} catch (DataAccessException e) {
			LOGGER.error("Not find  Cover letter count");
		}
		return isUpdate;
	}

	/**
	 * @author kartikm
	 * @Purpose:Find the status of the cover letter that is public or private
	 * @Created:Sept 14, 2012
	 * @param userId
	 * @param status
	 * @return dateValue
	 */
	@Override
	public boolean findNameActiveStatus(int userId, String name,
			int coverLetterId) {
		boolean isUpdate = false;
		try {
			List<ResCoverletter> res = hibernateTemplateCareers
					.find("from ResCoverletter rs where rs.userId=? and name=? and deleteDt is null",
							userId, name);
			if (null != res && !res.isEmpty()) {
				if (!(res.get(0).getCoverletterId() == coverLetterId)) {
					isUpdate = true;
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error("Not find  Cover letter count");
		}
		return isUpdate;
	}

	/**
	 * @author kartikm
	 * @Purpose:Find the status of the cover letter that is public or private
	 * @Created:Sept 14, 2012
	 * @param userId
	 * @param status
	 * @return dateValue
	 */
	@Override
	public boolean findDuplicateActiveStatus(int userId, int status) {
		boolean isUpdate = false;
		try {
			List<ResCoverletter> res = hibernateTemplateCareers
					.find("from ResCoverletter rs where rs.userId=? and rs.active=? and deleteDt is null",
							userId, status);
			if (null != res && !res.isEmpty()) {
				int countData = res.size();

				if (countData > 0) {
					isUpdate = true;
				} else {
					isUpdate = false;
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error("Not find  Cover letter count");
		}
		return isUpdate;
	}

	/**
	 * @author kartikm
	 * @Purpose:Save of Cover letter when submit the cover letter text
	 * @Created:Sept 14, 2012
	 * @param rclDTO
	 * @return isUpdate
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean coverLetterUpdateByjobSeeker(ResCoverLetterDTO rclDTO) {
		ResCoverletter resUpdate = new ResCoverletter();
		// ResCoverletter resUpd = new ResCoverletter();
		ResPrivacy rpupdate = new ResPrivacy();
		boolean isUpdate = false;
		try {
			Date todayDate = null;
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat(OLD_FORMAT,
					Locale.US);
			try {
				String dateNow = formatter.format(currentDate.getTime());
				todayDate = dateConveter(dateNow);
			} catch (Exception e) {
				LOGGER.error("Info data error date conversion",e);
			}

			if (rclDTO.getActive() == 1) {

				int primaryIdData = 0;
				List<ResCoverletter> resData = hibernateTemplateCareers
						.find("from ResCoverletter rs where rs.userId=? and rs.active=? and deleteDt is null",
								rclDTO.getUserId(), rclDTO.getActive());
				if (null != resData && !resData.isEmpty()) {
					primaryIdData = resData.get(0).getCoverletterId();
					// this is public to private convert method
					ResCoverletter resUpdateCov = hibernateTemplateCareers.get(
							ResCoverletter.class, primaryIdData);
					resUpdateCov.setActive(0);
					resUpdateCov.setUpdateDt(todayDate);
					hibernateTemplateCareers.update(resUpdateCov);

					List<ResPrivacy> rpData = hibernateTemplateCareers.find(
							"from ResPrivacy rp where rp.description=?", 0);
					if (null != rpData && !rpData.isEmpty()) {
						// int privId = rpData.get(0).getPrivacyId();
						int coverLetterPrivId = 0;
						List<ResCoverletterPriv> resCovPriv = hibernateTemplateCareers
								.find("from ResCoverletterPriv rs where rs.createUserId=? and rs.resPrivacy.privacyId=?",
										rclDTO.getUserId(), 1);
						// ResCoverletterPriv list = resCovPriv.get(0);

						coverLetterPrivId = resCovPriv.get(0)
								.getCoverletterPrivId();
						ResCoverletterPriv resPriv = new ResCoverletterPriv();
						resPriv.setResCoverletter(resUpdate);
						resPriv.setResPrivacy(rpupdate);
						resPriv = hibernateTemplateCareers.get(
								ResCoverletterPriv.class, coverLetterPrivId);
						// resPriv.setResPrivacy();
						// resPriv.getResPrivacy().setPrivacyId(privId);
						resPriv.setActive(0);
						// resPriv.set
						hibernateTemplateCareers.saveOrUpdate(resPriv);
						isUpdate = true;
					}
				}
			}

		} catch (DataAccessException e) {
			LOGGER.error("Error" + e);
			LOGGER.error("Not save Cover letter");
		}
		return isUpdate;
	}

	/**
	 * display option of cover letter
	 * 
	 * @author kartikm
	 * @version 1.0.1
	 * @param userId
	 * @return list
	 */
	@Override
	public List<ResCoverLetterDTO> getJobOwnerList(int userId) {
		// List<ResCoverLetterDTO> resCov = new ArrayList<ResCoverLetterDTO>();
		List<ResCoverletter> resCov = new ArrayList<ResCoverletter>();
		try {
			Query query = hibernateTemplateCareers
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"Select rs from ResCoverletter rs where rs.userId="
									+ userId + "and deleteDt is null");

			resCov = query.list();
			/*
			 * resCov = hibernateTemplateCareers
			 * .find("from ResCoverletter rs where rs.userId=? and deleteDt is null"
			 * , userId);
			 */

		} catch (Exception e) {
			LOGGER.error(e);
		}

		return subscriptionHelper.transformCoverLeterlistToDTO(resCov);

	}

	/**
	 * Delete option of cover letter
	 * 
	 * @author kartikm
	 * @version 1.0.1
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean isDelete(int userId, int coverLetterId) {
		boolean isUpdate = false;
		Date todayDate = null;
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(OLD_FORMAT, Locale.US);
		try {
			String dateNow = formatter.format(currentDate.getTime());
			todayDate = dateConveter(dateNow);
		} catch (Exception e) {
			LOGGER.error("Info data error date conversion",e);
		}
		try {
			ResCoverletter resCov = new ResCoverletter();
			ResCoverletter resUpdate = new ResCoverletter();
			ResPrivacy rpupdate = new ResPrivacy();
			resCov = hibernateTemplateCareers.get(ResCoverletter.class,
					coverLetterId);
			resCov.setDeleteDt(todayDate);
			hibernateTemplateCareers.update(resCov);
			// int primaryIdData=0;
			List<ResCoverletterPriv> resCovPriv = hibernateTemplateCareers
					.find("from ResCoverletterPriv rs where rs.resCoverletter.coverletterId=?",
							coverLetterId);

			int coverLetterPrivId = 0;
			coverLetterPrivId = resCovPriv.get(0).getCoverletterPrivId();
			ResCoverletterPriv resPriv = new ResCoverletterPriv();
			resPriv.setResCoverletter(resUpdate);
			resPriv.setResPrivacy(rpupdate);
			resPriv = hibernateTemplateCareers.get(ResCoverletterPriv.class,
					coverLetterPrivId);
			resPriv.setDeleteUserId(userId);
			resPriv.setDeleteDt(todayDate);
			hibernateTemplateCareers.update(resPriv);
			isUpdate = true;
		} catch (Exception ex) {
			LOGGER.error("Error: Delete Cover Letter" + ex);

		}
		return isUpdate;
	}

	/**
	 * Delete option of cover letter
	 * 
	 * @author kartikm
	 * @version 1.0.1
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	public boolean isupDateCover(int userId, int coverLetterId) {

		return true;
	}

	/**
	 * view and update option of cover letter
	 * 
	 * @author kartikm
	 * @version 1.0.1
	 * @param userId
	 * @param status
	 * @return ResCoverLetterDTO
	 */

	public ResCoverLetterDTO getCoverList(int coverletterId) {
		ResCoverLetterDTO resCovDTO = new ResCoverLetterDTO();
		try {
			List<ResCoverletter> resList = new ArrayList<ResCoverletter>();
			if (coverletterId > 0) {
				resList = hibernateTemplateCareers
						.find("from ResCoverletter rs where rs.coverletterId=? and deleteDt is null",
								coverletterId);
			}
			resCovDTO = subscriptionHelper.toTransFormListToDTO(resList);
		} catch (DataAccessException e) {
			LOGGER.error("Error for update of employee data",e);
		}
		return resCovDTO;

	}

	/**
	 * @author kartikm
	 * @Purpose:Save of Cover letter when submit the cover letter text
	 * @Created:Sept 14, 2012
	 * @param rclDTO
	 * @return isUpdate
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean coverLetterEditByjobSeeker(ResCoverLetterDTO rclDTO) {
		ResCoverletter resUpdate = new ResCoverletter();
		ResPrivacy rpupdate = new ResPrivacy();
		boolean isUpdate = false;
		try {
			Date todayDate = null;
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat(OLD_FORMAT,
					Locale.US);
			try {
				String dateNow = formatter.format(currentDate.getTime());
				todayDate = dateConveter(dateNow);
			} catch (Exception e) {
				LOGGER.error("Info data error date conversion",e);
			}
			int primaryIdData = 0;

			primaryIdData = rclDTO.getCoverletterId();
			ResCoverletter resUpdateCov = hibernateTemplateCareers.get(
					ResCoverletter.class, primaryIdData);
			resUpdateCov.setActive(rclDTO.getActive());
			resUpdateCov.setName(rclDTO.getName());
			resUpdateCov.setCoverletterText(rclDTO.getCoverletterText());
			resUpdateCov.setUpdateDt(todayDate);
			hibernateTemplateCareers.update(resUpdateCov);

			List<ResCoverletterPriv> resCovPriv = hibernateTemplateCareers
					.find("from ResCoverletterPriv rs where rs.resCoverletter.coverletterId=?",
							primaryIdData);

			int coverLetterPrivId = 0;
			coverLetterPrivId = resCovPriv.get(0).getCoverletterPrivId();
			ResCoverletterPriv resPriv = new ResCoverletterPriv();
			resPriv.setResCoverletter(resUpdate);
			resPriv.setResPrivacy(rpupdate);
			resPriv = hibernateTemplateCareers.get(ResCoverletterPriv.class,
					coverLetterPrivId);
			resPriv.setActive(rclDTO.getActive());
			hibernateTemplateCareers.update(resPriv);
			isUpdate = true;

		} catch (DataAccessException e) {
			LOGGER.error("Could not save Cover letter" + e);
		}
		return isUpdate;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserSubscriptionsDAO#fetchPublicCoverLetter(long, java.lang.String)
	 */
	@Override
	public ResCoverLetterDTO fetchPublicCoverLetter(long jobSeekerId,
			String coverLetterId) {

		ResCoverLetterDTO resCovDTO = new ResCoverLetterDTO();
		try {
			List<ResCoverletter> resList = new ArrayList<ResCoverletter>();
			if (jobSeekerId > 0) {
				resList = hibernateTemplateCareers
						.find("from ResCoverletter where userId = "
								+ jobSeekerId + " AND coverletterId = "
								+ coverLetterId + "and deleteDt is null");
			}
			resCovDTO = subscriptionHelper.toTransFormListToDTO(resList);
		} catch (DataAccessException e) {
			LOGGER.error("Error for update of employee data" + e);
		}
		return resCovDTO;
	}

	/**
	 * To get current subscription List for Facility
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserSubscriptionsDTO> getCurrentFacilitySub(int facilityId) {

		List<UserSubscriptionsDTO> listSubscriptiosns = null;
		try {
			List<AdmFacilitySubscription> listSubs = hibernateTemplateCareers
					.find("from AdmFacilitySubscription sub where sub.admFacilitySubscriptionPK.facilityId=?",
							facilityId);
			listSubscriptiosns = subscriptionHelper
					.transformFacilitySubTojsSubsDTO(listSubs);
		} catch (DataAccessException e) {
			LOGGER.error(
					"Error while getting current subscription list from DB", e);
		}

		return listSubscriptiosns;
	}

	/**
	 * This Method to get the digital subscription list
	 */
	@Override
	public List<UserSubscriptionsDTO> getDigitalSubList() {
		List<UserSubscriptionsDTO> listDTOs = new ArrayList<UserSubscriptionsDTO>();

		try {
			List<MerPublication> digSubList = hibernateTemplateTracker
					.find("from MerPublication p where p.isDigital = 1 and p.active = 1");
			listDTOs = subscriptionHelper
					.transferDigitalSubToSubDTO(digSubList);
		} catch (DataAccessException e) {
			LOGGER.error(
					"Error while getting data for digital magazine subscriptions",
					e);
		}
		return listDTOs;
	}

	/**
	 * Method to get the e-news letter subscription details
	 * 
	 * @return subscriptionDTO
	 */
	@Override
	public List<UserSubscriptionsDTO> getEnewsLetterSubList() {
		List<UserSubscriptionsDTO> listDTOs = new ArrayList<UserSubscriptionsDTO>();

		try {
			List<MerPublication> digSubList = hibernateTemplateTracker
					.find("from MerPublication p where p.isEnewsletter = 1 and p.active = 1");
			listDTOs = subscriptionHelper
					.transferDigitalSubToSubDTO(digSubList);
		} catch (DataAccessException e) {
			LOGGER.error(
					"Error while getting data for digital magazine subscriptions",
					e);
		}
		return listDTOs;
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserSubscriptionsDAO#getSubEmailerList()
	 */
	@Override
	public List<DropDownDTO> getSubEmailerList(){
		
		try{
			List<MerPublication> emailSubList = hibernateTemplateTracker
					.find("from MerPublication p where p.isEmail = 1 and p.active = 1");
			return subscriptionHelper
					.transferEmailSubToSubDTO(emailSubList);
		}catch (DataAccessException e) {
			LOGGER.error(
					"Error while getting data for Emailer magazine subscriptions",
					e);
		}
		return null;
	}

	/**
	 * Method to save the selected facility subscriptions to the DB
	 * 
	 * @param listSubsDTO
	 * @param facilityId
	 *            return boolean value
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveFacilitySubscription(
			List<UserSubscriptionsDTO> listSubsDTO, int facilityId) {
		List<AdmFacilitySubscription> facSubscriptions = new ArrayList<AdmFacilitySubscription>();
		try {

			if (facilityId != 0) {
				List<AdmFacilitySubscription> listSubsAlerts = hibernateTemplateCareers
						.find(SELECTED_FACILITY_SUBS, facilityId);

				facSubscriptions = subscriptionHelper
						.transformjsSubsDTOToAdmFacilitySubs(listSubsDTO,
								listSubsAlerts);
				hibernateTemplateCareers.deleteAll(listSubsAlerts);
				for (AdmFacilitySubscription sub : facSubscriptions) {
					hibernateTemplateCareers.merge(sub);
				}
			}
		} catch (DataAccessException e) {
			LOGGER.error("Error while saveing the selected facility subscriptions dta to DB"
					+ e);
		}
		return true;
	}

	/**
	 * This method is to get the available print magazines for job seeker
	 * through the procedure getSubscriptionData()
	 * 
	 * @param userId
	 * @return DropDownDTo
	 */
	@Override
	public List<DropDownDTO> getSubscriptionscheck(int userId) {
		// TODO Auto-generated method stub
		List<MerPublication> listSubscriptiosnscheck = null;
		MerUserProfile profile = new MerUserProfile();
		MerProfileAttribList attribList = new MerProfileAttribList();
		try {

			List<MerUserProfile> userProfiles = hibernateTemplateTracker
					.find("from MerUserProfile m where m.profilePK.userId=? and m.merProfileAttrib.profileAttribId = 13",
							userId);
			profile = userProfiles.get(0);
			int profileAttribId = Integer.parseInt(profile.getAttribValue());

			List<MerProfileAttribList> attribLists = hibernateTemplateTracker
					.find("from MerProfileAttribList e where e.profileAttribListId=?",
							profileAttribId);
			attribList = attribLists.get(0);
			String profession = attribList.getListValue().trim();

			Query getSubscriptionData = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getSubscriptionData(?) }");

			getSubscriptionData.setString(0, profession.trim());
			List<MerPublication> ListSubCheck = getSubscriptionData.list();

			Iterator<?> iterator = ListSubCheck.iterator();
			listSubscriptiosnscheck = new ArrayList<MerPublication>();
			while (iterator.hasNext()) {
				MerPublication dto = new MerPublication();
				Object[] row = (Object[]) iterator.next();
				dto.setPublicationId((Integer) row[0]);
				dto.setPublicationName((String) row[1]);
				listSubscriptiosnscheck.add(dto);
			}
			return dropdownHelper
					.convertMerPublicationToDropDownDTO(listSubscriptiosnscheck);
		} catch (DataAccessException e) {
			LOGGER.error("Error while getting emailer publications  from DB"
					+ e);
		}

		return null;

	}

	/**
	 * This method is to get the available Digital magazines for job seeker
	 * through the procedure getSubscriptionDigital()
	 * 
	 * @param userId
	 * @return DropDownDTo
	 */
	@Override
	public List<DropDownDTO> getSubscriptionsdigital(int userId) {
		// TODO Auto-generated method stub
		List<MerPublication> listSubscriptiosnsDigital = null;
		MerUserProfile profile = new MerUserProfile();
		MerProfileAttribList attribList = new MerProfileAttribList();
		try {

			List<MerUserProfile> userProfiles = hibernateTemplateTracker
					.find("from MerUserProfile m where m.profilePK.userId=? and m.merProfileAttrib.profileAttribId = 13",
							userId);
			profile = userProfiles.get(0);
			int profileAttribId = Integer.parseInt(profile.getAttribValue());

			List<MerProfileAttribList> attribLists = hibernateTemplateTracker
					.find("from MerProfileAttribList e where e.profileAttribListId=?",
							profileAttribId);
			attribList = attribLists.get(0);
			String profession = attribList.getListValue().trim();

			Query getSubscriptionDigital = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getSubscriptionDigital(?) }");

			getSubscriptionDigital.setString(0, profession.trim());
			List<MerPublication> ListSubDigital = getSubscriptionDigital.list();

			Iterator<?> iterator = ListSubDigital.iterator();
			listSubscriptiosnsDigital = new ArrayList<MerPublication>();
			while (iterator.hasNext()) {
				MerPublication dto = new MerPublication();
				Object[] row = (Object[]) iterator.next();
				dto.setPublicationId((Integer) row[0]);
				dto.setPublicationName((String) row[1]);
				listSubscriptiosnsDigital.add(dto);
			}
			return dropdownHelper
					.convertMerPublicationToDropDownDTO(listSubscriptiosnsDigital);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return null;
	}

	/**
	 * This method is to get the available enews_letter magazines for job seeker
	 * through the procedure getSubscriptionLetter()
	 * 
	 * @param userId
	 * @return DropDownDTo
	 */
	@Override
	public List<DropDownDTO> getSubscriptionsletter(int userId) {
		// TODO Auto-generated method stub
		// List<MerPublication> listSubscriptiosnsLetter = null;
		List<MerPublication> listSubscriptiosnsLetter = new ArrayList<MerPublication>();
		MerUserProfile profile = new MerUserProfile();
		MerProfileAttribList attribList = new MerProfileAttribList();
		try {
			List<MerUserProfile> userProfiles = hibernateTemplateTracker
					.find("from MerUserProfile m where m.profilePK.userId=? and m.merProfileAttrib.profileAttribId = 13",
							userId);
			profile = userProfiles.get(0);
			int profileAttribId = Integer.parseInt(profile.getAttribValue());

			List<MerProfileAttribList> attribLists = hibernateTemplateTracker
					.find("from MerProfileAttribList e where e.profileAttribListId=?",
							profileAttribId);
			attribList = attribLists.get(0);
			String profession = attribList.getListValue().trim();

			Query getSubscriptionLetter = hibernateTemplateTracker
					.getSessionFactory().getCurrentSession()
					.createSQLQuery(" { call getSubscriptionLetter(?) }");

			getSubscriptionLetter.setString(0, profession.trim());
			List<MerPublication> ListSubLetter = getSubscriptionLetter.list();

			Iterator<?> iterator = ListSubLetter.iterator();
			listSubscriptiosnsLetter = new ArrayList<MerPublication>();
			while (iterator.hasNext()) {
				MerPublication dto = new MerPublication();
				Object[] row = (Object[]) iterator.next();
				dto.setPublicationId((Integer) row[0]);
				dto.setPublicationName((String) row[1]);
				listSubscriptiosnsLetter.add(dto);
			}
			return dropdownHelper
					.convertMerPublicationToDropDownDTO(listSubscriptiosnsLetter);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return null;
	}

	/**
	 * This method is get the E-mailer publications for user based on
	 */
	@Override
	public List<DropDownDTO> getSubscriptionsEmailer(int userId) {
		List<MerPublication> list = new ArrayList<MerPublication>();
		MerUserProfile profile = new MerUserProfile();
		MerProfileAttribList attribList = new MerProfileAttribList();
		List<MerPublicationProfession> pubProfList = new ArrayList<MerPublicationProfession>();
		try {
			List<MerUserProfile> userProfiles = hibernateTemplateTracker
					.find("from MerUserProfile m where m.profilePK.userId=? and m.merProfileAttrib.profileAttribId = 13",
							userId);
			profile = userProfiles.get(0);
			int profileAttribId = Integer.parseInt(profile.getAttribValue());

			List<MerProfileAttribList> attribLists = hibernateTemplateTracker
					.find("from MerProfileAttribList e where e.profileAttribListId=?",
							profileAttribId);
			attribList = attribLists.get(0);
			String profession = attribList.getListValue().trim();

			pubProfList = hibernateTemplateTracker
					.find("from MerPublicationProfession p where p.profession=? and p.subSubType = 'EMAILER'",
							profession);
			if (null != pubProfList) {
				for (MerPublicationProfession merProf : pubProfList) {
					int publicationId = merProf.getPublicationId();
					list = hibernateTemplateTracker.find(
							"from MerPublication mp where mp.publicationId=?",
							publicationId);
				}
			}
			return dropdownHelper.convertMerPublicationToDropDownDTO(list);
		} catch (DataAccessException e) {
			LOGGER.error("Error while getting emailer publications  from DB"
					+ e);
		}
		return null;
	}

	/**
	 * Get the subscription list which selected during registration for logged
	 * in user
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserSubscriptionsDTO> getSelectedSub(int userId) {
		List<UserSubscriptionsDTO> subscriptionsDTOs = new ArrayList<UserSubscriptionsDTO>();
		List<MerUserProfile> userProfiles = new ArrayList<MerUserProfile>();
		try {
			userProfiles = hibernateTemplateTracker.find(
					"from MerUserProfile m where m.merUser.userId=?", userId);
			subscriptionsDTOs = dropdownHelper
					.convertMerUserProfileToUserDTO(userProfiles);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return subscriptionsDTOs;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.dao.UserSubscriptionsDAO#getParentId(int)
	 */
	@Override
	public int getParentId(int facilityId) {
		int roleId = 0;
		List<AdmUserFacility> userFacility = new ArrayList<AdmUserFacility>();
		List<AdmFacility> facilities = new ArrayList<AdmFacility>();
		AdmUserFacility facility = new AdmUserFacility();
		AdmFacility admFacility = new AdmFacility();
		try {
			userFacility = hibernateTemplateCareers.find(
					"from AdmUserFacility a where a.facilityPK.facilityId=?",
					facilityId);
			facility = userFacility.get(0);
			roleId = facility.getFacilityPK().getRoleId();
			if (roleId == 5 || roleId == 6) {
				facilities = hibernateTemplateCareers.find(
						"from AdmFacility a where a.facilityId=?", facilityId);
				admFacility = facilities.get(0);
				facilityId = admFacility.getFacilityParentId();
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return facilityId;
	}

	/**
	 * This method is to get the publications based on the profession
	 * 
	 * @param professionId
	 * @return
	 */
	@Override
	public List<List<DropDownDTO>> getPublications(int professionId) {
		List<List<DropDownDTO>> dtos = new ArrayList<List<DropDownDTO>>();
		MerProfileAttribList profList = new MerProfileAttribList();
		List<DropDownDTO> digDTOList = new ArrayList<DropDownDTO>();
		List<DropDownDTO> printDTOList = new ArrayList<DropDownDTO>();
		List<DropDownDTO> newsDTOList = new ArrayList<DropDownDTO>();
		List<DropDownDTO> EmailerList = new ArrayList<DropDownDTO>();
		MerPublication publication = new MerPublication();
		try {

			if (professionId > 0) {

				List<MerProfileAttribList> professions = hibernateTemplateTracker
						.find("from MerProfileAttribList p where p.profileAttribListId=?",
								professionId);
				profList = professions.get(0);
				String profession = profList.getListValue();

				List<MerPublicationProfession> merPubProfessions = hibernateTemplateTracker
						.find("from MerPublicationProfession m where m.profession=?",
								profession.trim());

				if (null != merPubProfessions) {
					for (MerPublicationProfession profession2 : merPubProfessions) {
						DropDownDTO downDTO = new DropDownDTO();
						if (MMJBCommonConstants.SUBSCRIPTION_SUBTYPE_PRINT
								.equalsIgnoreCase(profession2.getSubSubType())) {
							int publicationId = profession2.getPublicationId();
							List<MerPublication> list = hibernateTemplateTracker
									.find("from MerPublication e where e.publicationId=?",
											publicationId);
							publication = list.get(0);
							if (publication.getIsPrint() == 1
									&& publication.getActive() == 1) {
								downDTO.setOptionId(Integer
										.toString(publication
												.getPublicationId()));
								downDTO.setOptionName(publication
										.getPublicationName());
								printDTOList.add(downDTO);
							}
						} else if (MMJBCommonConstants.SUBSCRIPTION_SUBTYPE_DIG
								.equalsIgnoreCase(profession2.getSubSubType())) {
							int publicationId = profession2.getPublicationId();
							List<MerPublication> list = hibernateTemplateTracker
									.find("from MerPublication e where e.publicationId=?",
											publicationId);
							publication = list.get(0);
							if (publication.getIsDigital() == 1
									&& publication.getActive() == 1) {
								downDTO.setOptionId(Integer
										.toString(publication
												.getPublicationId()));
								downDTO.setOptionName(publication
										.getPublicationName());
								digDTOList.add(downDTO);
							}
						} else if (MMJBCommonConstants.SUBSCRIPTION_SUBTYPE_NEWS
								.equalsIgnoreCase(profession2.getSubSubType())) {
							int publicationId = profession2.getPublicationId();
							List<MerPublication> list = hibernateTemplateTracker
									.find("from MerPublication e where e.publicationId=?",
											publicationId);
							publication = list.get(0);
							if (publication.getIsEnewsletter() == 1
									&& publication.getActive() == 1) {
								downDTO.setOptionId(Integer
										.toString(publication
												.getPublicationId()));
								downDTO.setOptionName(publication
										.getPublicationName());
								newsDTOList.add(downDTO);
							}
						} else if (MMJBCommonConstants.SUBSCRIPTION_SUBTYPE_EMAIL
								.equalsIgnoreCase(profession2.getSubSubType())) {
							int publicationId = profession2.getPublicationId();
							List<MerPublication> list = hibernateTemplateTracker
									.find("from MerPublication mp where mp.publicationId=?",
											publicationId);
							publication = list.get(0);
							if (publication.getActive() == 1
									&& publication.getIsEmail() == 1) {
								downDTO.setOptionId(Integer
										.toString(publication
												.getPublicationId()));
								downDTO.setOptionName(publication
										.getPublicationName());
								EmailerList.add(downDTO);
							}
						}
					}
					HashSet h = new HashSet(printDTOList);
					printDTOList.clear();
					printDTOList.addAll(h);
					dtos.add(printDTOList);
					HashSet h1 = new HashSet(digDTOList);
					digDTOList.clear();
					digDTOList.addAll(h1);
					dtos.add(digDTOList);
					HashSet h2 = new HashSet(newsDTOList);
					newsDTOList.clear();
					newsDTOList.addAll(h2);
					dtos.add(newsDTOList);
					HashSet h3 = new HashSet(EmailerList);
					EmailerList.clear();
					EmailerList.addAll(h3);
					dtos.add(EmailerList);
				}
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return dtos;
	}
}
