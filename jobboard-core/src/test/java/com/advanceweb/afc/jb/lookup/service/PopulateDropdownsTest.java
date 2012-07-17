package com.advanceweb.afc.jb.lookup.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.EmploymentTypeDTO;
import com.advanceweb.afc.jb.common.ExcludeFromDTO;
import com.advanceweb.afc.jb.common.FromZipcodeDTO;
import com.advanceweb.afc.jb.common.JobPostedDateDTO;
import com.advanceweb.afc.jb.common.MetroAreaDTO;
import com.advanceweb.afc.jb.common.RadiusDTO;
import com.advanceweb.afc.jb.common.StateDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 17, 2012
   @Purpose: For JUnit test for all the drop down of Advance Search
 */

public class PopulateDropdownsTest extends ServiceTest{

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
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getJobPostedDateList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of JobPostedDateDTO
	 */
	
	@Test
	public void testGetJobPostedDateList() {
		List<JobPostedDateDTO> jobPostedDateList=popService.getJobPostedDateList();	
		assertNotNull("Job Posted Date",jobPostedDateList);
		assertTrue("Job Posted Date", jobPostedDateList.size()>=0);
		
	}

}
