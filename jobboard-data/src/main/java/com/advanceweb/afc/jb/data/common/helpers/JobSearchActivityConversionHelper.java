package com.advanceweb.afc.jb.data.common.helpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.entities.JpJob;


/**
 * <code> JobSearchActivityConversionHelper </code> is a Conversion Helper class for jobs search. 
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 *  
 */
public class JobSearchActivityConversionHelper {

	/**
	 * Entity to view job dto
	 * 
	 * @param entity
	 * @return
	 */
	public SearchedJobDTO transformJpJobToSearchedJobDTO(JpJob entity) {
		SearchedJobDTO searchedJobDTO = new SearchedJobDTO();
		if (entity != null) {
			searchedJobDTO.setJobTitle(entity.getJobtitle());

		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Iterator<Entry<Integer, Integer>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<Integer, Integer> entry = entries.next();
		    
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		return searchedJobDTO;

	}

}
