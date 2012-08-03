package com.advanceweb.afc.jb.lookup.dao;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.MerLocation;
import com.advanceweb.afc.jb.lookup.helper.PopulateDropdownConversionHelper;

@Repository("populateDropdownsDAO")
@SuppressWarnings("unchecked")
public class PopulateDropdownsDAOImpl implements PopulateDropdownsDAO{
		
	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;
	
	private HibernateTemplate hibernateTemplate;
	private HibernateTemplate hibernateTemplateTracker;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Autowired
	public void setHibernateTemplateTracker(SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	@Override
	public List<CountryDTO> getCountryList() {		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections.property("country")));
			List<Object> merUtilityList = hibernateTemplateTracker.findByCriteria(criteria);
			return dropdownHelper.convertMerUtilityToCountryDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EmploymentInfoDTO> getEmployementInfoList() {
		
		try {
			List<JpAttribList> merUtilityList =hibernateTemplate.find("from JpAttribList e where e.attribType='EmploymentInformation' order by e.position"); 
			return dropdownHelper.convertMerUtilityToEmploymentInfoDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SubscriptionsDTO> getSubscriptionsList() {
		
		try {			
			List<JpAttribList> merUtilityList =hibernateTemplate.find("from JpAttribList e where e.attribType='Subscriptions' order by e.position");  
			return dropdownHelper.convertMerUtilityToSubscriptionsDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GenderDTO> getGenderList() {

		try {
			List<JpAttribList> merUtilityList =hibernateTemplate.find("from JpAttribList e where e.attribType='Gender' order by e.position"); 
			return dropdownHelper.convertMerUtilityToGenderDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VeteranStatusDTO> getVeteranStatusList() {

		try {

			List<JpAttribList> merUtilityList =hibernateTemplate.find("from JpAttribList e where e.attribType='VeteranStatus' order by e.position"); 

			return dropdownHelper.convertMerUtilityToVeteranStatusDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EthenticityDTO> getEthenticityList() {

		try {
			List<JpAttribList> merUtilityList =hibernateTemplate.find("from JpAttribList e where e.attribType='Ethnicity' order by e.position"); 

			return dropdownHelper.convertMerUtilityToEthenticityDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of RadiusDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of RadiusDTO
	   @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getRadiusList()
	 */
	@Override
	public List<RadiusDTO> getRadiusList() {
		try {
			
			List<JpAttribList> merLookupList = hibernateTemplate.find("from JpAttribList e where e.lookupCategory='Radius' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToRadiusListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of ExcludeFromDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of ExcludeFromDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getExcludeFromList()
	 */
	@Override
	public List<ExcludeFromDTO> getExcludeFromList() {
		try {
			List<JpAttribList> merLookupList =  hibernateTemplate.find("from JpAttribList e where e.lookupCategory='ExcludeFrom' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToExcludeFromListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of FromZipcodeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of FromZipcodeDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getFromZipcodeList()
	 */
	@Override
	public List<FromZipcodeDTO> getFromZipcodeList() {
		try {
			List<JpAttribList> merLookupList =  hibernateTemplate.find("from JpAttribList e where e.lookupCategory='FromZipcode' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToFromZipcodeListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of StateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of StateDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getStateList()
	 */
	@Override
	public List<StateDTO> getStateList() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MerLocation.class);
			criteria.setProjection(Projections.distinct(Projections.property("state")));
			List<Object> merUtilityList = hibernateTemplateTracker.findByCriteria(criteria);		
			return dropdownHelper.convertMerLookupToStateListDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of MetroAreaDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of MetroAreaDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getMetroAreaList()
	 */
	@Override
	public List<MetroAreaDTO> getMetroAreaList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate.find("from JpAttribList e where e.lookupCategory='MetroArea' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToMetroAreaListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of EmploymentTypeDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of EmploymentTypeDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getEmploymentTypeList()
	 */
	@Override
	public List<EmploymentTypeDTO> getEmploymentTypeList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate.find("from JpAttribList e where e.attribType='EmploymentType' order by e.position");
			return dropdownHelper.convertMerLookupToEmploymentTypeListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the list of JobPostedDateDTO for the job seeker's advance search
	   @Created:Jul 10, 2012
	   @Param  :not required
	   @Return :List of JobPostedDateDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getJobPostedDateList()
	 */
	@Override
	public List<JobPostedDateDTO> getJobPostedDateList() {
		try {
			List<JpAttribList> merLookupList =hibernateTemplate.find("from JpAttribList e where e.lookupCategory='JobPostedDate' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToJobPostedDateListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<JobAlertsDTO> getJobAlertsList() {
		
		try {
			List<JpAttribList> merLookupList = hibernateTemplate.find("from JpAttribList e where e.lookupCategory='JobAlerts' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToJobAlertsDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MagazinesDTO> getMagazinesList() {
		try {
			List<JpAttribList> merLookupList = hibernateTemplate.find("from JpAttribList e where e.lookupCategory='Magazines' and e.lookupStatus='1'");
			return dropdownHelper.convertMerLookupToMagazinesDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	   @Author :Anil Malali
	   @Purpose:To get the list of ResumeVisibilityDTO for resume
	   @Created:Jul 24, 2012
	   @Param  :not required
	   @Return :List of ResumeVisibilityDTO
	 * @see com.advanceweb.afc.jb.dropdowns.PopulateDropdowns#getResumeVisibilityList()
	 */
	@Override
	public List<ResumeVisibilityDTO> getResumeVisibilityList() {
		List<JpAttribList> merLookupList= hibernateTemplate.find("from JpAttribList e where e.lookupCategory='Visibility' and e.lookupStatus='1'");
		return dropdownHelper.convertMerLookupToVisibilityDTO(merLookupList);
	}
	
	/**
	   @Author :Anil Malali
	   @Purpose:To get the dropdown 
	   @Created:Jul 24, 2012
	   @Param  :not required
	   @Return :List of dropdown values 
	 */
	@Override
	public List<DropDownDTO> populateDropdown(String dropDownName) {
		List<JpAttribList> merLookupList= hibernateTemplate.find("from JpAttribList e where e.attribType='"+ dropDownName +"' order by e.position");
		return dropdownHelper.convertMerLookupToLookUpDTO(merLookupList);
	}
}
