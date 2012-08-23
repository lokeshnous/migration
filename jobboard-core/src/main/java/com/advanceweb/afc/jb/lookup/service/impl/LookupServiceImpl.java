package com.advanceweb.afc.jb.lookup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.common.util.MMUtils;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.search.dao.LocationDAO;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 21st Aug 2012 onwards
 */

@Service("lookupService")
public class LookupServiceImpl implements LookupService{
	
	
	@Autowired
	private LocationDAO locationDAO;
	
	/**
	 * This method will do the location search for city and state
	 * to display in the auto complete box in the UI.
	 * @param keywords represents  search word for auto complete
	 * @return List<LocationDTO> which contains the city and state or postcode 
	 */
	
	public List<LocationDTO> locationSearch(String keywords){
		
		if(MMUtils.isIntNumber(keywords)){
			return locationDAO.getPostcodeLocationByKeyword(keywords);
		}else{
			return locationDAO.getCityStateLocationByKeyword(keywords);
		}
		
	}

}
