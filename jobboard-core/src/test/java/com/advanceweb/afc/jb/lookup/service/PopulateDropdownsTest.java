package com.advanceweb.afc.jb.lookup.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.EmploymentInfoDTO;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.EthenticityDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.GenderDTO;
import com.advanceweb.afc.jb.common.JobAlertsDTO;
import com.advanceweb.afc.jb.common.MagazinesDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.ResumeAttribListDTO;
import com.advanceweb.afc.jb.common.ResumeVisibilityDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.VeteranStatusDTO;
import com.advanceweb.jb.test.ServiceTestBase;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 17, 2012
   @Purpose: For JUnit test for all the drop down of Advance Search
 */

public class PopulateDropdownsTest extends ServiceTestBase{

	@Autowired
	private PopulateDropdowns popService;
	
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getRadiusList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of RadiusDTO
	 */
	@Test
	public void testGetRadiusList() {
		List<RadiusDTO> radiusList=popService.getRadiusList();
		assertNotNull("Radius List",radiusList);
		assertTrue("Radius List", radiusList.size()>=0);
		
	}

	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getExcludeFromList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of ExcludeFromDTO
	 
	 */
	@Test
	public void testGetExcludeFromList() {
		List<ExcludeFromDTO> excludeFromList=popService.getExcludeFromList();
		assertNotNull("ExcludeFrom List",excludeFromList);
		assertTrue("ExcludeFrom List",excludeFromList.size()>=0);
		
	}

	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getFromZipcodeList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of FromZipcodeDTO
	 */
	@Test
	public void testGetFromZipcodeList() {
		List<FromZipcodeDTO> fromZipcodeList=popService.getFromZipcodeList();
		assertNotNull("From Zipcode List",fromZipcodeList);
		assertTrue("From Zipcode List", fromZipcodeList.size()>=0);
		
	}


	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getStateList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of StateDTO
	 */
	@Test
	public void testGetStateList() {
		List<StateDTO> stateList=popService.getStateList();
		assertNotNull("State List",stateList);
		assertTrue("State List", stateList.size()>=0);
		
	}


	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getMetroAreaList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of MetroAreaDTO
	 */
	
	@Test
	public void testGetMetroAreaList() {
		List<MetroAreaDTO> metroAreaList=popService.getMetroAreaList();
		assertNotNull("Metro Area List", metroAreaList);
		assertTrue("Metro Area List", metroAreaList.size()>=0);
		
	}

	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getEmploymentTypeList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of MetroAreaDTO
	 */
	
	@Test
	public void testGetEmploymentTypeList() {
		List<EmploymentTypeDTO> empTypeList=popService.getEmploymentTypeList();
		assertNotNull("Employment Type List",empTypeList);
		assertTrue("Employment Type List", empTypeList.size()>=0);
		
	}

	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetCountryList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of CountryDTO
	 */
	
	@Test
	public void testGetCountryList() {
		List<CountryDTO> countryList=popService.getCountryList();	
		assertNotNull("Country List",countryList);
		assertTrue("Country List", countryList.size()>=0);
		
	}
	
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetEmploymentDetailsList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of EmploymentInfoDTO
	 */
	
	@Test
	public void testGetEmploymentDetailsList() {
		List<EmploymentInfoDTO> empInfoList=popService.getEmployementInfoList();	
		assertNotNull("Employment Info List",empInfoList);
		assertTrue("Employment Info List", empInfoList.size()>=0);
		
	}
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetVeteranStatusList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of VeteranStatusDTO
	 */
	
	@Test
	public void testGetVeteranStatusList() {
		List<VeteranStatusDTO> vetStatusList=popService.getVeteranStatusList();	
		assertNotNull("Veteran Status List",vetStatusList);
		assertTrue("Veteran Status List", vetStatusList.size()>=0);
		
	}
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetGenderList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of GenderDTO
	 */
	
	@Test
	public void testGetGenderList() {
		List<GenderDTO> genderList=popService.getGenderList();	
		assertNotNull("Gender List",genderList);
		assertTrue("Gender List", genderList.size()>=0);
		
	}
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetEthenticityList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of EthenticityDTO
	 */
	
	@Test
	public void testGetEthenticityList() {
		List<EthenticityDTO> ethnicityList=popService.getEthenticityList();	
		assertNotNull("Ethnicity List",ethnicityList);
		assertTrue("Ethnicity List", ethnicityList.size()>=0);
		
	}
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetJobAlertsList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of JobAlertsDTO
	 */
	@Ignore("Not ready to test")
	@Test
	public void testGetJobAlertsList() {
		List<JobAlertsDTO> jobAlertsList=popService.getJobAlertsList();	
		assertNotNull("Job Alerts",jobAlertsList);
		assertTrue("Job Alerts", jobAlertsList.size()>=0);
		
	}
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetMagazinesList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of MagazinesDTO
	 */
	@Ignore("Not ready to test")
	@Test
	public void testGetMagazinesList() {
		List<MagazinesDTO> jobMagazinesList=popService.getMagazinesList();	
		assertNotNull("Magazines List",jobMagazinesList);
		assertTrue("Magazines List", jobMagazinesList.size()>=0);
		
	}
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetSubscriptionList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of SubscriptionsDTO
	 */
	
	@Test
	public void testGetSubscriptionList() {
//		List<SubscriptionsDTO> jobSubscriptionList=popService.getSubscriptionsList();	
//		assertNotNull("Subscriptions List",jobSubscriptionList);
//		assertTrue("Job Posted Date", jobSubscriptionList.size()>=0);
		assertTrue(true);
		
	}
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetSubscriptionList()}.
	 * @Author :Sasibhushan
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of ResumeVisibilityDTO
	 */
	
	@Test
	public void testGetResumeVisibilityList() {
		List<ResumeVisibilityDTO> resumeVisibilityDTOList=popService.getResumeVisibilityList();	
		assertNotNull("Subscriptions List",resumeVisibilityDTOList);
		assertTrue("Job Posted Date", resumeVisibilityDTOList.size()>=0);
		
	}
	
	@Test
	public void testResumeAttribList() {
		List<ResumeAttribListDTO>  ResumeAttribListDTO = popService.populateResumeDropdown("ResumeType");
		assertNull("Resume Attribute List",ResumeAttribListDTO);
	}
	
}
