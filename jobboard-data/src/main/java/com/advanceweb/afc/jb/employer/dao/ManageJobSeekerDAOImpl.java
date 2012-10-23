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
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId,a.createDt,a.updateDt from AdmFolderResume a,ResPublishResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.publishResumeId and c.userId="
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
		AdmFolderResume admFolderResume = new AdmFolderResume();

		admFolderResume = hibernateTemplate
				.get(AdmFolderResume.class, resumeId);
		admFolderResume.setApplicationStatusId(appStatusId);
		admFolderResume.setUpdateDt(new Date());
		hibernateTemplate.saveOrUpdate(admFolderResume);
		
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
							"SELECT b.resumeName,a.rating,a.applicationStatusId,a.publishResumeId,a.folderResumeId,a.createDt,a.updateDt  from AdmFolderResume a,ResPublishResume b,AdmFolder c where c.folderId=a.id.folderId and a.id.publishResumeId=b.publishResumeId and c.userId="
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
		AdmFolderResume admFolderResume = new AdmFolderResume();

		admFolderResume = hibernateTemplate.get(AdmFolderResume.class,
				folderResumeId);
		admFolderResume.setFolderId(folderId);
		admFolderResume.setUpdateDt(new Date());
		hibernateTemplate.saveOrUpdate(admFolderResume);

		return true;
	}

	@Override
	public void deleteJobSeeker(int folderResumeId)
			throws JobBoardDataException {
		AdmFolderResume admFolderResume = new AdmFolderResume();

		admFolderResume = hibernateTemplate.get(AdmFolderResume.class,
				folderResumeId);
		admFolderResume.setUpdateDt(new Date());
		admFolderResume.setDeleteDt(new Date());
		hibernateTemplate.saveOrUpdate(admFolderResume);

	}
}
