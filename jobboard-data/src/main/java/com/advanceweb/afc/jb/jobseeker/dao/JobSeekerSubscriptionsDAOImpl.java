package com.advanceweb.afc.jb.jobseeker.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.advanceweb.afc.jb.jobseeker.helper.JobSeekerSubscriptionsConversionHelper;

/**
 * @version 1.0
 * @author sharadk
 * 
 */
@SuppressWarnings("unchecked")
@Repository("jobSeekerSubscriptionsDAO")
public class JobSeekerSubscriptionsDAOImpl implements JobSeekerSubscriptionsDAO {
	
	private static final String SELECTED_CURRENT_SUBS="from AdmUserSubscription sub where sub.id.userId=?";
	private static final Logger LOGGER = Logger.getLogger(JobSeekerSubscriptionsDAOImpl.class);


	
	private HibernateTemplate hibernateTemplateCareers;
	
	@Autowired
	private JobSeekerSubscriptionsConversionHelper jsSubscriptionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker, SessionFactory sessionFactory) {
		this.hibernateTemplateCareers = new HibernateTemplate(sessionFactory);
		
	}
	
	

	/**
	 * save subscription
	 */

	@Override
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public boolean saveJobSeekerSubscription(List<JobSeekerSubscriptionsDTO> listSubsDTO, int userId) {
		try {
			
			if(userId != 0){
				List<AdmUserSubscription> listSubsAlerts= hibernateTemplateCareers.find(SELECTED_CURRENT_SUBS,userId);				
				List<AdmUserSubscription> userAlerts = jsSubscriptionHelper.transformjsSubsDTOToAdmUserSubs(listSubsDTO, listSubsAlerts);
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
	 * @param userId
	 * @return
	 */
	@Override
	public List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(int userId) {
		
		List<JobSeekerSubscriptionsDTO> listSubscriptiosns = null;
		try {
			List<AdmUserSubscription> listSubs= hibernateTemplateCareers.find(SELECTED_CURRENT_SUBS,userId);
			listSubscriptiosns = jsSubscriptionHelper.transformMerUserAlertsTojsSubsDTO(listSubs);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		
		return listSubscriptiosns;
	}
	
	/**
	 * @author kartikm 
	 * @Purpose:Save of Cover letter
	 * when submit the cover letter text
	 * @Created:Sept 14, 2012
	 * @param rclDTO
	 * @return isUpdate
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean coverLetterSaveByjobSeeker(ResCoverLetterDTO rclDTO) {
		ResCoverletter resUpdate=new ResCoverletter();		
		boolean isUpdate = false;
		try{
			
			if(rclDTO.getActive()==1){
				Date todayDate = null;
				Calendar currentDate = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm:ss");
				try {
					String dateNow = formatter.format(currentDate.getTime());
					todayDate = dateConveter(dateNow);
				} catch (Exception e) {
					LOGGER.info("Info data error date conversion");
				}
			List<ResCoverletter> resData=hibernateTemplateCareers.find("from ResCoverletter rs where rs.userId=? and rs.active=?",rclDTO.getUserId(),rclDTO.getActive());
			if(null != resData && !resData.isEmpty()){	
			int primaryIdData=resData.get(0).getCoverletterId();
			resUpdate=hibernateTemplateCareers.get(ResCoverletter.class, primaryIdData);
			resUpdate.setActive(5);
			resUpdate.setUpdateDt(todayDate);
			hibernateTemplateCareers.update(resUpdate);			
			ResCoverletter res=new ResCoverletter();
			res.setUserId(rclDTO.getUserId());
			res.setName(rclDTO.getName());
			res.setCoverletterText(rclDTO.getCoverletterText());
			res.setActive(rclDTO.getActive());				
			res.setCreateDt(todayDate);
			res.setUpdateDt(todayDate);
		
			hibernateTemplateCareers.save(res);
			
			}
			}else{			
		Date todayDate = null;
		//try{
		ResCoverletter res=new ResCoverletter();
		//ResCoverletterPriv resPriv=new ResCoverletterPriv();
		res.setUserId(rclDTO.getUserId());
		res.setName(rclDTO.getName());
		res.setCoverletterText(rclDTO.getCoverletterText());
		res.setActive(rclDTO.getActive());
				
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		try {
			String dateNow = formatter.format(currentDate.getTime());
			todayDate = dateConveter(dateNow);
		} catch (Exception e) {
			LOGGER.info("Info data error date conversion");
		}
		
		//Date createDate=dateConveter(rclDTO.getCreateDt());
		//Date updateDate=dateConveter(rclDTO.getUpdateDt());
		//Date deleteDate=dateConveter(rclDTO.getDeleteDt());
			
		
		res.setCreateDt(todayDate);
		res.setUpdateDt(todayDate);
		//res.setDeleteDt(null);
		hibernateTemplateCareers.save(res);
		//This is ResCoverletterPriv table data save
		/*resPriv.setDeleteUserId(rclDTO.getUserId());
		resPriv.setActive(rclDTO.getActive());
		resPriv.setCreateDt(todayDate);
		resPriv.setDeleteDt(todayDate);
		resPriv.setResCoverletter(res);
		resPriv.setResPrivacy(null);
		hibernateTemplateCareers.save(resPriv);*/
			}
		}catch(DataAccessException e){
			LOGGER.error("Not save Cover letter");
		}
		return isUpdate;		
	}
	
	/**
	 * @author kartikm 
	 * @Purpose:Date Conversion method name dateConveter that is for
	 *         Converting from MM/dd/yyyy HH:mm:ss to yyyy-MM-dd HH:mm:ss
	 * @Created:Sept 12, 2012
	 * @param date
	 * @return dateValue
	 */
	public Date dateConveter(String date) {
		Date dateValue = null;
		final String OLD_FORMAT = "MM/dd/yyyy HH:mm:ss";
		final String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
		String newDateString;
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		try {
			Date d = sdf.parse(date);
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(d);
			SimpleDateFormat sdfSource = new SimpleDateFormat(NEW_FORMAT);
			dateValue = sdfSource.parse(newDateString);
		} catch (ParseException e) {
			LOGGER.info("Date parsing in Job save by admin wrong");
		}

		return dateValue;
	}
	
	/**
	 * @author kartikm 
	 * @Purpose:Find the status of the cover letter that is 
	 *  public or private
	 * @Created:Sept 14, 2012
	 * @param userId
	 * @param status
	 * @return dateValue
	 */
	@Override
	public boolean findActiveStatus(int userId,int status){
		boolean isUpdate = false;
		/*try{
			ResCoverLetterDTO rcl=new ResCoverLetterDTO();
			
			List<ResCoverletter> res=hibernateTemplateCareers.find("from ResCoverletter rs where rs.userId=? and rs.active=?",userId,status);
			if(null != res && !res.isEmpty()){
				rcl=jsSubscriptionHelper.transFromListToDTO(res);
				isUpdate=true;
			}
			else{
				isUpdate=false;
			}
		}catch(DataAccessException e){
			LOGGER.error("Not find  Cover letter status");	
		}*/
		return isUpdate;
	}
	
}
