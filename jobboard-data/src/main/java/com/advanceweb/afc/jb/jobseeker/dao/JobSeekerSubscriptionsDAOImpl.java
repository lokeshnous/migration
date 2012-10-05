package com.advanceweb.afc.jb.jobseeker.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;
import com.advanceweb.afc.jb.common.ResCoverLetterDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserSubscription;
import com.advanceweb.afc.jb.data.entities.ResCoverletter;
import com.advanceweb.afc.jb.data.entities.ResCoverletterPriv;
import com.advanceweb.afc.jb.data.entities.ResPrivacy;
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerSubscriptionsConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@SuppressWarnings("unchecked")
@Repository("jobSeekerSubscriptionsDAO")
public class JobSeekerSubscriptionsDAOImpl implements JobSeekerSubscriptionsDAO {

	private static final String SELECTED_CURRENT_SUBS = "from AdmUserSubscription sub where sub.id.userId=?";
	private static final Logger LOGGER = Logger
			.getLogger(JobSeekerSubscriptionsDAOImpl.class);

	private static final String OLD_FORMAT = "MM/dd/yyyy HH:mm:ss";
	private static final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// private static final ResPrivacy ResCoverletter = null;
	// private static final
	// MAX_RESULT="select MAX(rs.coverletterId),rs.userId,rs.name,rs.coverletterText,rs.active,rs.createDt,rs.updateDt,rs.deleteDt";

	private HibernateTemplate hibernateTemplateCareers;

	@Autowired
	private JobSeekerSubscriptionsConversionHelper jsSubscriptionHelper;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);

	}

	/**
	 * save subscription
	 */

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveJobSeekerSubscription(
			List<JobSeekerSubscriptionsDTO> listSubsDTO, int userId) {
		try {

			if (userId != 0) {
				List<AdmUserSubscription> listSubsAlerts = hibernateTemplateCareers
						.find(SELECTED_CURRENT_SUBS, userId);
				List<AdmUserSubscription> userAlerts = jsSubscriptionHelper
						.transformjsSubsDTOToAdmUserSubs(listSubsDTO,
								listSubsAlerts);
				hibernateTemplateCareers.deleteAll(listSubsAlerts);
				hibernateTemplateCareers.saveOrUpdateAll(userAlerts);
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
	public List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(int userId) {

		List<JobSeekerSubscriptionsDTO> listSubscriptiosns = null;
		try {
			List<AdmUserSubscription> listSubs = hibernateTemplateCareers.find(
					SELECTED_CURRENT_SUBS, userId);
			listSubscriptiosns = jsSubscriptionHelper
					.transformMerUserAlertsTojsSubsDTO(listSubs);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}

		return listSubscriptiosns;
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
			SimpleDateFormat formatter = new SimpleDateFormat(
					OLD_FORMAT, Locale.US);
			try {
				String dateNow = formatter.format(currentDate.getTime());
				todayDate = dateConveter(dateNow);
			} catch (Exception ex) {
				LOGGER.info("Error:Cover letter save by job seeker" + ex);
			}

			// this is save option in ResCoverletter
			ResCoverletter res = new ResCoverletter();
			res.setUserId(rclDTO.getUserId());
			res.setName(rclDTO.getName());
			res.setCoverletterText(rclDTO.getCoverletterText());
			res.setActive(rclDTO.getActive());
			res.setCreateDt(todayDate);
			res.setUpdateDt(todayDate);
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
			LOGGER.info("Error" + e);
			LOGGER.error("Not save Cover letter");
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
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT,Locale.US);
		try {
			Date dateConv = sdf.parse(date);
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(dateConv);
			SimpleDateFormat sdfSource = new SimpleDateFormat(NEW_FORMAT,Locale.US);
			dateValue = sdfSource.parse(newDateString);
		} catch (ParseException e) {
			LOGGER.info("Date parsing in Job save by admin wrong");
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
	public boolean findNameActiveStatus(int userId, String name) {
		boolean isUpdate = false;
		try {
			List<ResCoverletter> res = hibernateTemplateCareers
					.find("from ResCoverletter rs where rs.userId=? and name=? and deleteDt is null",
							userId, name);
			if (null != res && !res.isEmpty()) {

				isUpdate = true;
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
			SimpleDateFormat formatter = new SimpleDateFormat(
					OLD_FORMAT,Locale.US);
			try {
				String dateNow = formatter.format(currentDate.getTime());
				todayDate = dateConveter(dateNow);
			} catch (Exception e) {
				LOGGER.info("Info data error date conversion");
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
			LOGGER.info("Error" + e);
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
		//List<ResCoverLetterDTO> resCov = new ArrayList<ResCoverLetterDTO>();
		List<ResCoverletter> resCov = new ArrayList<ResCoverletter>();
		try {
			Query query = hibernateTemplateCareers
					.getSessionFactory()
					.getCurrentSession()
					.createQuery("Select rs from ResCoverletter rs where rs.userId="+userId+ "and deleteDt is null");
			
			resCov=query.list();
			/*resCov = hibernateTemplateCareers
					.find("from ResCoverletter rs where rs.userId=? and deleteDt is null",
							userId);*/

		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return jsSubscriptionHelper.transformCoverLeterlistToDTO(resCov);

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
		SimpleDateFormat formatter = new SimpleDateFormat(OLD_FORMAT,Locale.US);
		try {
			String dateNow = formatter.format(currentDate.getTime());
			todayDate = dateConveter(dateNow);
		} catch (Exception e) {
			LOGGER.info("Info data error date conversion");
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
			LOGGER.info("Error: Delete Cover Letter" + ex);

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
			resCovDTO = jsSubscriptionHelper.toTransFormListToDTO(resList);
		} catch (DataAccessException e) {
			LOGGER.info("Error for update of employee data");
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
			SimpleDateFormat formatter = new SimpleDateFormat(
					OLD_FORMAT,Locale.US);
			try {
				String dateNow = formatter.format(currentDate.getTime());
				todayDate = dateConveter(dateNow);
			} catch (Exception e) {
				LOGGER.info("Info data error date conversion");
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
			LOGGER.info("Error" + e);
			LOGGER.error("Not save Cover letter");
		}
		return isUpdate;
	}

}
