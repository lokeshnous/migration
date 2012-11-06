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
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.JpJobStat;
import com.advanceweb.afc.jb.data.entities.ResPublishResumeStat;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;

@Transactional
@Repository("clickDAO")
public class ClickDAOImpl implements ClickDAO {

	private HibernateTemplate hibernateTemplate;

	private static final Logger LOGGER = Logger.getLogger(ClickDAOImpl.class);

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void saveClickEvent(int jobId, String type) {
		JpJobStat jobStat;
		synchronized (this) {
			try {
				jobStat = hibernateTemplate.get(JpJobStat.class, jobId);

				if (null == jobStat) {
					jobStat = new JpJobStat();
				}

				if (jobId != 0) {
					jobStat.setJobId(jobId);
					if (type.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_CLICK)) {
						jobStat.setClicks(jobStat.getClicks() + 1);
					} else if (type
							.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_APPLY)) {
						jobStat.setApplies(jobStat.getApplies() + 1);
					} else if (type
							.equalsIgnoreCase(MMJBCommonConstants.CLICKTYPE_VIEW)) {
						jobStat.setViews(jobStat.getViews() + 1);
					}
					jobStat.setStatsDt(new Date());
					hibernateTemplate.saveOrUpdate(jobStat);
				}

			} catch (Exception e) {
				LOGGER.error("Error occured while saving job click event", e);
			}
		}
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
				JpJobStat jobStat = new JpJobStat();
				int jobId = 0;
				jobId = dto.getJobId();
				synchronized (jobStat) {
					try {
						jobStat = hibernateTemplate.get(JpJobStat.class, jobId);

						if (null == jobStat) {
							jobStat = new JpJobStat();
						}

						if (jobId != 0) {
							jobStat.setJobId(jobId);
							jobStat.setClicks(jobStat.getClicks());
							jobStat.setApplies(jobStat.getApplies());
							jobStat.setViews(jobStat.getViews() + 1);
							jobStat.setStatsDt(new Date());
							hibernateTemplate.saveOrUpdate(jobStat);
						}

					} catch (Exception e) {
						LOGGER.error(
								"Error occured while saving List of job views",
								e);
					}
				}
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
