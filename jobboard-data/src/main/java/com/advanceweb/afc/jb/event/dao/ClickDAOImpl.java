package com.advanceweb.afc.jb.event.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.data.entities.ResPublishResumeStat;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;
import com.advanceweb.afc.jb.data.entities.VstClickthroughNew;
import com.advanceweb.afc.jb.data.entities.VstClickthroughNewPK;
import com.advanceweb.afc.jb.data.entities.VstClickthroughType;
import com.advanceweb.afc.jb.data.entities.VstSearchResultNew;
import com.advanceweb.afc.jb.data.entities.VstSearchResultNewPK;

@Transactional
@Repository("clickDAO")
public class ClickDAOImpl implements ClickDAO {

	private HibernateTemplate hibernateTemplate;

	private HibernateTemplate hibernateTemplateTracker;
	
	private static final Logger LOGGER = Logger.getLogger(ClickDAOImpl.class);

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void saveClickEvent(int jobId, String type) {
		VstClickthroughNew clickthroughNew;
		try {
		VstClickthroughType clickthroughType;
		clickthroughType = hibernateTemplateTracker.get(VstClickthroughType.class,
				Integer.parseInt(type));

		VstClickthroughNewPK clickthroughNewPK = new VstClickthroughNewPK();
		clickthroughNewPK.setClickthroughDt(new Date());
		clickthroughNewPK.setKeyId(jobId);
		clickthroughNewPK.setVstClickthroughType(clickthroughType);

		// synchronized (this) {
		
			clickthroughNew = hibernateTemplateTracker.get(VstClickthroughNew.class,
					clickthroughNewPK);

			if (null == clickthroughNew) {
				clickthroughNew = new VstClickthroughNew();
				clickthroughNew.setClickthroughNewPK(clickthroughNewPK);
			}

			if (jobId > 0) {
				clickthroughNew
						.setClickCount(clickthroughNew.getClickCount() + 1);
				hibernateTemplateTracker.saveOrUpdate(clickthroughNew);
			}

		} catch (Exception e) {
			LOGGER.error("Error occured while saving job click event", e);
		}
		// }
	}

	/**
	 * This method updates the Views whenever the job appears in job search
	 * 
	 * @param jobDTOList
	 */
	@Override
	public void saveJobViews(List<JobDTO> jobDTOList) {
		if (null != jobDTOList) {
			for (JobDTO dto : jobDTOList) {
				VstSearchResultNew searchResult;
				VstSearchResultNewPK searchResultPK = new VstSearchResultNewPK();
				int jobId = 0;
				jobId = dto.getJobId();
//				synchronized (searchResultPK) {
					try {
						searchResultPK.setResult(jobId);
						searchResultPK.setSearchDate(new Date());
						searchResult = hibernateTemplateTracker.get(VstSearchResultNew.class, searchResultPK);

						if (null == searchResult) {
							searchResult = new VstSearchResultNew();
							searchResult.setSearchResultNewPK(searchResultPK);
						}

						if (jobId > 0) {
							searchResult.setResultCount(searchResult.getResultCount()+1);
							hibernateTemplateTracker.saveOrUpdate(searchResult);
						}

					} catch (Exception e) {
						LOGGER.error(
								"Error occured while saving List of job views",
								e);
					}
//				}
			}
		}
	}

	/**
	 * This method updates the number of times the resume was viewed by an
	 * Employer
	 * 
	 * @param resumeId
	 */
	@Override
	public void saveResumeEmpViews(int resumeId) {
		ResPublishResumeStat resumeStat = new ResPublishResumeStat();
		int publishResumeId = 0;
		synchronized (resumeStat) {
			try {
				ResUploadResume resume = hibernateTemplate.get(
						ResUploadResume.class, resumeId);

				if (null != resume.getResPublishResume()) {
					publishResumeId = resume.getResPublishResume()
							.getPublishResumeId();
				}
				resumeStat = hibernateTemplate.get(ResPublishResumeStat.class,
						publishResumeId);

				if (null == resumeStat) {
					resumeStat = new ResPublishResumeStat();
				}

				if (publishResumeId != 0) {
					resumeStat.setPublishResumeId(publishResumeId);
					resumeStat
							.setEmployerViews(resumeStat.getEmployerViews() + 1);
					resumeStat.setEmployerImpressions(resumeStat
							.getEmployerImpressions());
					resumeStat.setStatsDt(new Date());
					hibernateTemplate.saveOrUpdate(resumeStat);
				}
			} catch (Exception e) {
				LOGGER.error("Error occured while saving resume views", e);
			}
		}
	}

	/**
	 * This method updates the number of times the resume appeared in resume
	 * search
	 * 
	 * @param resumeDTOList
	 */
	@Override
	public void saveResAppearance(final List<ResumeDTO> resumeDTOList) {
		for (ResumeDTO dto : resumeDTOList) {
			ResPublishResumeStat resumeStat = new ResPublishResumeStat();
			int publishResumeId = 0;
			publishResumeId = dto.getPublishResumeId();
			synchronized (resumeStat) {

				try {
					resumeStat = hibernateTemplate.get(
							ResPublishResumeStat.class, publishResumeId);

					if (null == resumeStat) {
						resumeStat = new ResPublishResumeStat();
					}

					if (publishResumeId != 0) {
						resumeStat.setPublishResumeId(publishResumeId);
						resumeStat.setEmployerViews(resumeStat
								.getEmployerViews());
						resumeStat.setEmployerImpressions(resumeStat
								.getEmployerImpressions() + 1);
						resumeStat.setStatsDt(new Date());
						hibernateTemplate.saveOrUpdate(resumeStat);
					}

				} catch (Exception e) {
					LOGGER.error(
							"Error occured while saving resume appearances", e);
				}
			}
		}
	}

}
