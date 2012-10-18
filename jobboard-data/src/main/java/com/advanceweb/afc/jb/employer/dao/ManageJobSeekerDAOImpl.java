package com.advanceweb.afc.jb.employer.dao;

import java.util.ArrayList;
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
	private static final Logger LOGGER = Logger
			.getLogger(ManageJobSeekerDAOImpl.class);
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private JobSeekerConversionHelper conversionHelper;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public List<ManageJobSeekerDTO> retrieveAllResume(int userId) {
		List<?> folderDetailList = new ArrayList<AdmFolderResume>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId from AdmFolderResume a,ResPublishResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.publishResumeId and c.userId="
									+ userId + "and a.deleteDt is NULL ");

			folderDetailList = query.list();
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return conversionHelper
				.transformFolderResumeToManageJobSeekerDTO(folderDetailList);
	}

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

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateJobSeeker(int appStatusId, int resumeId, int rating)
			throws JobBoardDataException {
		List<AdmFolderResume> folderList = new ArrayList<AdmFolderResume>();
		try {
			folderList = hibernateTemplate
					.find("from AdmFolderResume a where a.folderResumeId="
							+ resumeId);
			if (null != folderList && folderList.size() > 0) {
				String hqlUpdate = "UPDATE AdmFolderResume a set a.applicationStatusId = :applicationStatusId "
						+ "WHERE a.folderResumeId = :folderResumeId";
				Query query = hibernateTemplate.getSessionFactory()
						.getCurrentSession().createQuery(hqlUpdate);
				query.setParameter("applicationStatusId", appStatusId);
				query.setParameter("folderResumeId", folderList.get(0)
						.getFolderResumeId());

				int result = query.executeUpdate();
				LOGGER.info("Rows affected: " + result);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return true;
	}

	@Override
	public List<ManageJobSeekerDTO> retrieveAllResumeByFolder(int userId,
			int folderId) throws JobBoardDataException {
		List<?> folderDetailList = new ArrayList<AdmFolderResume>();
		try {
			Query query = hibernateTemplate
					.getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId  from AdmFolderResume a,ResPublishResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.publishResumeId and c.userId="
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

	@Override
	public boolean updateResumeFolder(int folderId, int folderResumeId)
			throws JobBoardDataException {
		List<AdmFolderResume> folderList = new ArrayList<AdmFolderResume>();
		try {
			folderList = hibernateTemplate
					.find("from AdmFolderResume a where a.folderResumeId="
							+ folderResumeId);
			if (null != folderList && folderList.size() > 0) {
				String hqlUpdate = "UPDATE AdmFolderResume a set a.folderId = :folderId "
						+ "WHERE a.id.folderResumeId = :folderResumeId";
				Query query = hibernateTemplate.getSessionFactory()
						.getCurrentSession().createQuery(hqlUpdate);
				query.setParameter("folderId", folderId);
				query.setParameter("folderResumeId", folderList.get(0)
						.getFolderResumeId());

				int result = query.executeUpdate();
				LOGGER.info("Rows affected: " + result);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e);
		}
		return true;
	}
}
