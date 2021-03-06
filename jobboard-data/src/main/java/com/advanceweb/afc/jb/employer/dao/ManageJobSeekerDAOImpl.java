/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AdmFolderDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.ManageJobSeekerDTO;
import com.advanceweb.afc.jb.data.entities.AdmApplicationStatus;
import com.advanceweb.afc.jb.data.entities.AdmFolder;
import com.advanceweb.afc.jb.data.entities.AdmFolderResume;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.helper.JobSeekerConversionHelper;

/**
 * @Author : Devi Prasad
 * @Version: 1.0
 * @Created: Oct 15, 2012
 * @Purpose: This class implements all the DAO operations related to Manage Job
 *           seeker
 */
@Transactional
@Repository("ManageJobSeekerDAO")
@SuppressWarnings("unchecked")
public class ManageJobSeekerDAOImpl implements ManageJobSeekerDAO {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ManageJobSeekerDAOImpl.class);
	
	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;
	
	/** The conversion helper. */
	@Autowired
	private JobSeekerConversionHelper conversionHelper;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactory the new hibernate template
	 */
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#retrieveAllResume(int)
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResume(int userId) {
		List<?> folderDetailList = new ArrayList<AdmFolderResume>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId,a.createDt,a.updateDt from AdmFolderResume a,ResUploadResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.uploadResumeId and c.userId="
									+ userId + "and a.deleteDt is NULL ");

			folderDetailList = query.list();
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return conversionHelper
				.transformFolderResumeToManageJobSeekerDTO(folderDetailList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#applicationStatusList()
	 */
	@Override
	public List<DropDownDTO> applicationStatusList()
			throws JobBoardDataException {
		List<AdmApplicationStatus> appStatusList = new ArrayList<AdmApplicationStatus>();
		try {
			appStatusList = hibernateTemplate.find("from AdmApplicationStatus");
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return conversionHelper
				.transformApplicationStatusToDropDownDTO(appStatusList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#folderDetailList(int)
	 */
	@Override
	public List<AdmFolderDTO> folderDetailList(int userId)
			throws JobBoardDataException {
		List<AdmFolder> folderDTOList = new ArrayList<AdmFolder>();
		try {
			folderDTOList = hibernateTemplate
					.find("from AdmFolder where userId=" + userId);
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return conversionHelper.transformAdmFolderToAdmFolderDTO(folderDTOList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#updateAppStatus(int, int)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateAppStatus(int appStatusId, int resumeId)
			throws JobBoardDataException {
		AdmFolderResume admFolderResume = new AdmFolderResume();

		admFolderResume = hibernateTemplate
				.get(AdmFolderResume.class, resumeId);
		admFolderResume.setApplicationStatusId(appStatusId);
		admFolderResume.setUpdateDt(new Date());
		hibernateTemplate.saveOrUpdate(admFolderResume);

		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#retrieveAllResumeByFolder(int, int)
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,
			int folderId) throws JobBoardDataException {
		List<?> folderDetailList = new ArrayList<AdmFolderResume>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId,a.createDt,a.updateDt from AdmFolderResume a,ResUploadResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.uploadResumeId and c.userId="
									+ userId
									+ "and a.folderId="
									+ folderId
									+ "and a.deleteDt is NULL ");

			folderDetailList = query.list();
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return conversionHelper
				.transformFolderResumeToManageJobSeekerDTO(folderDetailList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#updateResumeFolder(int, int)
	 */
	@Override
	public boolean updateResumeFolder(int folderId, int folderResumeId)
			throws JobBoardDataException {
		AdmFolderResume admFolderResume = new AdmFolderResume();

		admFolderResume = hibernateTemplate.get(AdmFolderResume.class,
				folderResumeId);
		admFolderResume.setFolderId(folderId);
		admFolderResume.setUpdateDt(new Date());
		hibernateTemplate.saveOrUpdate(admFolderResume);

		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#deleteJobSeeker(int)
	 */
	@Override
	public void deleteJobSeeker(int folderResumeId)
			throws JobBoardDataException {
		AdmFolderResume admFolderResume = new AdmFolderResume();

		admFolderResume = hibernateTemplate.get(AdmFolderResume.class,
				folderResumeId);
		//admFolderResume.setUpdateDt(new Date());
		//admFolderResume.setDeleteDt(new Date());
		hibernateTemplate.delete(admFolderResume);

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#addFolder(int, java.lang.String)
	 */
	@Override
	public void addFolder(int userId, String folderName)
			throws JobBoardDataException {
		StringBuffer fldrName = new StringBuffer(folderName);
		AdmFolder admFolder = new AdmFolder();
		List<AdmFolder> admFolderList = new ArrayList<AdmFolder>();
		// check if the folder with the given name is present for the user or
		// not
		admFolderList = getFolderDetails(userId, fldrName.toString());
		int count = 1;
		if (null != admFolderList) {
			while (!admFolderList.isEmpty()) {
				fldrName.append(count);
				admFolderList = getFolderDetails(userId, fldrName.toString());
			}
		}
		admFolder.setFolderName(fldrName.toString());
		admFolder.setUserId(userId);
		hibernateTemplate.saveOrUpdate(admFolder);

	}

	/**
	 * Get the folder detail by userid and folder name
	 * 
	 * @param userId
	 * @param folderName
	 */
	public List<AdmFolder> getFolderDetails(int userId, String folderName) {

		List<AdmFolder> admFolderList = hibernateTemplate
				.find("from AdmFolder a where a.userId=" + userId
						+ " and folderName= '" + folderName + "'");
		return admFolderList;
	}

	/**
	 * Get the folder resume detail by folder id
	 * 
	 * @param folderId
	 */
	public List<AdmFolderResume> getFolderResumeDetails(int folderId) {

		List<AdmFolderResume> admFolderResList = hibernateTemplate
				.find("from AdmFolderResume a where a.folderId=" + folderId);
		return admFolderResList;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#removeFolder(int, java.lang.String)
	 */
	@Override
	public void removeFolder(int userId, String folderName)
			throws JobBoardDataException {
		List<AdmFolder> admFolderList = new ArrayList<AdmFolder>();
		// check if the folder with the given name is present for the user or
		// not
		admFolderList = getFolderDetails(userId, folderName);
		if (null != admFolderList && !admFolderList.isEmpty()) {
			for (AdmFolder admFolder : admFolderList) {
				List<AdmFolderResume> admFolderResList = getFolderResumeDetails(admFolder
						.getFolderId());
				if (null != admFolderResList && !admFolderResList.isEmpty()){
					hibernateTemplate.deleteAll(admFolderResList);
				}
				hibernateTemplate.delete(admFolder);
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#updateRating(int, int)
	 */
	@Override
	public boolean updateRating(int rating, int resumeId)
			throws JobBoardDataException {
		AdmFolderResume admFolderResume = new AdmFolderResume();

		admFolderResume = hibernateTemplate
				.get(AdmFolderResume.class, resumeId);
		admFolderResume.setRating(rating);
		admFolderResume.setUpdateDt(new Date());
		hibernateTemplate.saveOrUpdate(admFolderResume);

		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#renameFolder(int, int, java.lang.String)
	 */
	@Override
	public void renameFolder(int userId, int folderId, String folderName)
			throws JobBoardDataException {
		AdmFolder admFolder = new AdmFolder();
		admFolder = hibernateTemplate.get(AdmFolder.class, folderId);
		if (null != admFolder) {
			admFolder.setFolderName(folderName);
			hibernateTemplate.saveOrUpdate(admFolder);

		}

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#retrieveAllResume(int, int, int)
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResume(int userId, int offset,
			int noOfRecords) throws JobBoardDataException {
		List<?> folderDetailList = new ArrayList<AdmFolderResume>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId,a.createDt,a.updateDt from AdmFolderResume a,ResUploadResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.uploadResumeId and c.userId="
									+ userId + "and a.deleteDt is NULL ");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			folderDetailList = query.list();
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return conversionHelper
				.transformFolderResumeToManageJobSeekerDTO(folderDetailList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#retrieveAllResumeByFolder(int, int, int, int)
	 */
	@Override
	public List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,
			int folderId, int offset, int noOfRecords)
			throws JobBoardDataException {
		List<?> folderDetailList = new ArrayList<AdmFolderResume>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId,a.createDt,a.updateDt  from AdmFolderResume a,ResUploadResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.uploadResumeId and c.userId="
									+ userId
									+ "and a.folderId="
									+ folderId
									+ "and a.deleteDt is NULL ");
			query.setFirstResult(offset);
			query.setMaxResults(noOfRecords);
			folderDetailList = query.list();
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return conversionHelper
				.transformFolderResumeToManageJobSeekerDTO(folderDetailList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.dao.ManageJobSeekerDAO#getTotalNumberOfJobRecords(int, int)
	 */
	@Override
	public int getTotalNumberOfJobRecords(int userId,int folderId)
			throws JobBoardDataException {
		String queryString = null;
		if (folderId > 0) {
			queryString = "SELECT count(a) from AdmFolderResume a,AdmFolder c where c.folderId=a.id.folderId and c.userId="
					+ userId
					+ "and a.id.folderId="
					+ folderId
					+ "and a.deleteDt is NULL ";
		} else {
			queryString = "SELECT count(a) from AdmFolderResume a,AdmFolder c where c.folderId=a.id.folderId and c.userId="
					+ userId + "and a.deleteDt is NULL ";
		}
		try {
			Long jobCount = (Long) hibernateTemplate.getSessionFactory()
					.getCurrentSession().createQuery(queryString)
					.uniqueResult();
			return jobCount.intValue();
		} catch (DataAccessException e) {
			LOGGER.error(e);
			return 0;
		}
	}

}
