/**
 * 
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.ResumePackageDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacilityInventory;
import com.advanceweb.afc.jb.data.entities.AdmPackage;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.helper.ResumePackageConvertionHelper;


/**
 * This class is used to handle the Resume Search Packages functionalities
 * @author anilm
 * @version 1.0
 * @since 15 Oct 2012
 */
@SuppressWarnings("unchecked")
@Repository("resumePackageDAO")
public class ResumePackageDAOimpl implements ResumePackageDAO{

	
	private static final Logger LOGGER = Logger.getLogger(ResumePackageDAOimpl.class);
	@Autowired
	private ResumePackageConvertionHelper resumePackageConvertionHelper;
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public List<ResumePackageDTO> showResumeSearchPackages() throws JobBoardDataException{
		List<AdmPackage> admPackageList = null;
		try{
			admPackageList = hibernateTemplate.find("from AdmPackage where packageType = ?",MMJBCommonConstants.RESUME_SEARCH_PACKAGE);
		}
		catch (HibernateException e) {
			throw new JobBoardDataException("Error occured while fetching the Resume Search Packages "+ e);
		}
		return resumePackageConvertionHelper.transformToResumeSearchPackageDTOList(admPackageList);
	}

	
	public boolean isResumePackageActive(int facilityId) {
		boolean status = false;
		List<AdmFacilityInventory> admFIList = null; 
		try{
			
			admFIList = hibernateTemplate.find("select fi from AdmFacilityInventory fi, AdmInventoryDetail fd where fi.inventoryId = fd.admFacilityInventory.inventoryId and  "+
						" fd.productType = '"+ MMJBCommonConstants.RESUME_SEARCH_PACKAGE +"' and DATE(NOW()) < fi.expireDt and "+ 
						" fi.admFacility.facilityId = "+ facilityId);
			
			if(admFIList.size() > 0 ){
				status = true;
			}
			
			
		}catch (HibernateException e) {
			LOGGER.info("Hibernate Exception Occurred while checking the Resume package.");
		}
		
		
		return status;
	}

}
