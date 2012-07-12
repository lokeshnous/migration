package com.advanceweb.afc.jb.data.dropdowns;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.advanceweb.afc.jb.common.CountryDTO;
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
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.SubscriptionsDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.afc.jb.data.common.helpers.PopulateDropdownConversionHelper;
import com.advanceweb.afc.jb.data.entities.MerLookup;


public class PopulateDropdownsDAOImpl implements PopulateDropdownsDAO{
		
	@Autowired
	private PopulateDropdownConversionHelper dropdownHelper;
	
	@Autowired
	private SessionFactory sessionFactory;

	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactoryMerionTracker);
	}

	@Override
	public List<CountryDTO> getCountryList() {
		
		try {

			List<MerLookup> merUtilityList = hibernateTemplate.find("from MerLookup e where e.lookupCategory='Country' and e.lookupStatus='1'");
			return dropdownHelper.convertMerUtilityToCountryDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EmploymentInfoDTO> getEmployementInfoList() {
		
		try {
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='EmploymentInformation' and e.lookupStatus='1'"); 
			return dropdownHelper.convertMerUtilityToEmploymentInfoDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SubscriptionsDTO> getSubscriptionsList() {
		
		try {			
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='Subscriptions' and e.lookupStatus='1'");  
			return dropdownHelper.convertMerUtilityToSubscriptionsDTO(merUtilityList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GenderDTO> getGenderList() {

		try {
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='Gender' and e.lookupStatus='1'"); 
			return dropdownHelper.convertMerUtilityToGenderDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VeteranStatusDTO> getVeteranStatusList() {

		try {

			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='VeteranStatus' and e.lookupStatus='1'"); 

			return dropdownHelper.convertMerUtilityToVeteranStatusDTO(merUtilityList);

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EthenticityDTO> getEthenticityList() {

		try {
			List<MerLookup> merUtilityList =hibernateTemplate.find("from MerLookup e where e.lookupCategory='Ethnicity' and e.lookupStatus='1'"); 

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
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='Radius' and e.lookupStatus='1'").list();
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
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='ExcludeFrom' and e.lookupStatus='1'").list();
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
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='FromZipcode' and e.lookupStatus='1'").list();
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
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='State' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerLookupToStateListDTO(merLookupList);
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
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='MetroArea' and e.lookupStatus='1'").list();
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
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='EmploymentType' and e.lookupStatus='1'").list();
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
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='JobPostedDate' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerLookupToJobPostedDateListDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<JobAlertsDTO> getJobAlertsList() {
		
		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='JobAlerts' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerLookupToJobAlertsDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MagazinesDTO> getMagazinesList() {
		try {
			Session session= sessionFactory.openSession();
			List<MerLookup> merLookupList = session.createQuery("from MerLookup e where e.lookupCategory='Magazines' and e.lookupStatus='1'").list();
			return dropdownHelper.convertMerLookupToMagazinesDTO(merLookupList);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
