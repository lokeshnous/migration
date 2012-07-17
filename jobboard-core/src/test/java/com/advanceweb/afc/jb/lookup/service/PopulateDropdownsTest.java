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
	private PopulateDropdowns populateDropdownsService;
	
	
	/**
	 * Test method for {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getRadiusList()}.
	 * @Author :Prince Mathew
	   @Created:Jul 17, 2012
	   @Param  :not required
	   @Return :List of RadiusDTO
	 */
	@Test
	public void testGetRadiusList() {
		List<RadiusDTO> radiusList=populateDropdownsService.getRadiusList();
		assertNotNull(radiusList);
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
		List<ExcludeFromDTO> excludeFromList=populateDropdownsService.getExcludeFromList();
		assertNotNull(excludeFromList);
		assertTrue("Radius List",excludeFromList.size()>=0);
		
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
		List<FromZipcodeDTO> fromZipcodeList=populateDropdownsService.getFromZipcodeList();
		assertNotNull(fromZipcodeList);
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
		List<StateDTO> stateList=populateDropdownsService.getStateList();
		assertNotNull(stateList);
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
		List<MetroAreaDTO> metroAreaList=populateDropdownsService.getMetroAreaList();
		assertNotNull(metroAreaList);
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
		List<EmploymentTypeDTO> employmentTypeList=populateDropdownsService.getEmploymentTypeList();
		assertNotNull(employmentTypeList);
		assertTrue("Employment Type List", employmentTypeList.size()>=0);
		
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
		List<JobPostedDateDTO> jobPostedDateList=populateDropdownsService.getJobPostedDateList();	
		assertNotNull(jobPostedDateList);
		assertTrue("Employment Type List", jobPostedDateList.size()>=0);
		
	}

}
