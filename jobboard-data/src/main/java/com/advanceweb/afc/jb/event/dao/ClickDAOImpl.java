package com.advanceweb.afc.jb.event.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.ClickEventDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobStat;
import com.advanceweb.afc.jb.data.entities.ResPublishResumeStat;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;

@SuppressWarnings("unchecked")
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
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean saveClickEvent(ClickEventDTO clickEventDTO) {
		Boolean result=false;
		JpJobStat jpJobStat=new JpJobStat();
		try {
			jpJobStat.setJobId(clickEventDTO.getJobid());
			
			JpJob jpJob = new JpJob();
			jpJob.setJobId(clickEventDTO.getJobid());
			jpJobStat.setJpJob(jpJob );
			
			jpJobStat.setApplies(clickEventDTO.getApplies());
			jpJobStat.setClicks(clickEventDTO.getClicks());
			//jpJobStat.setStatsDt(clickEventDTO.getStatdate());

			jpJobStat.setStatsDt(new Timestamp(new Date().getTime()));
			jpJobStat.setViews(clickEventDTO.getViews());
			if(jpJobStat.getClicks() > 0){
				hibernateTemplate.saveOrUpdate(jpJobStat);
			}else{
				hibernateTemplate.save(jpJobStat);
			}
			result=true;
		} catch (HibernateException e) {
			result=false;
			LOGGER.info("ERROR");
		}		
		return result;

	}



	@Override
	public ClickEventDTO retrieveAllClicks(int jobId) {
		ClickEventDTO clickEventDTO=new ClickEventDTO();
		try{
			List<JpJobStat> clicks = hibernateTemplate.find("from JpJobStat where jobId= "+jobId);
			for(JpJobStat jpJobStat:clicks){
				clickEventDTO.setApplies(jpJobStat.getApplies());
				clickEventDTO.setClicks(jpJobStat.getClicks());
				clickEventDTO.setStatdate(jpJobStat.getStatsDt());
				clickEventDTO.setViews(jpJobStat.getViews());
				clickEventDTO.setJobid(jpJobStat.getJobId());
			}

		}catch (Exception e) {
			// TODO: handle exception
			LOGGER.info("ERROR");
		}
		return clickEventDTO;
	}

	/**
	 * This method updates the number of times the resume was viewed by an
	 * Employer
	 * 
	 * @param resumeId
	 */
	@Override
	public void saveResumeEmpViews(int resumeId) {
		ResPublishResumeStat resumeStat;
		int publishResumeId = 0;
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
				resumeStat.setEmployerViews(resumeStat.getEmployerViews() + 1);
				resumeStat.setEmployerImpressions(resumeStat
						.getEmployerImpressions());
				resumeStat.setStatsDt(new Date());
				hibernateTemplate.saveOrUpdate(resumeStat);
			}
		} catch (Exception e) {
			LOGGER.error(e);
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
			ResPublishResumeStat resumeStat;
			int publishResumeId = 0;
			publishResumeId = dto.getPublishResumeId();

			try {
				resumeStat = hibernateTemplate.get(ResPublishResumeStat.class,
						publishResumeId);

				if (null == resumeStat) {
					resumeStat = new ResPublishResumeStat();
				}

				if (publishResumeId != 0) {
					resumeStat.setPublishResumeId(publishResumeId);
					resumeStat.setEmployerViews(resumeStat.getEmployerViews());
					resumeStat.setEmployerImpressions(resumeStat
							.getEmployerImpressions() + 1);
					resumeStat.setStatsDt(new Date());
					hibernateTemplate.saveOrUpdate(resumeStat);
				}

			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}

}
