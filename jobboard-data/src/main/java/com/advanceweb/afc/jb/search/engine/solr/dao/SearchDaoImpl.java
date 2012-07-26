package com.advanceweb.afc.jb.search.engine.solr.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.MetaSearchIndexDTO;
import com.advanceweb.afc.jb.common.MetaSearchInputDTO;
import com.advanceweb.afc.jb.data.entities.MetaSearchIndex;
import com.advanceweb.afc.jb.data.entities.MetaSearchInput;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: Jul 25, 2012
 * @Purpose: This class implements all the DAO operations related to solr search
 */
@Transactional
@Repository("searchDao")
public class SearchDaoImpl implements SearchDao {

	private static final Logger LOGGER = Logger
			.getLogger("SearchDaoImpl.class");
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used for getting the parameter lists from the DB for 
	 * creating the SOLR query.
	 */
	public List<MetaSearchInputDTO> getParamList(String searchIndexName,
			String environment, String searchTypeName) {
		
		List<MetaSearchInputDTO> srchInputList = new ArrayList<MetaSearchInputDTO>();
		try {
			@SuppressWarnings("unchecked")
			
			List<MetaSearchInput> mSrchInputList =hibernateTemplate.find("SELECT a from  MetaSearchInput a, MetaSearchIndex b, MetaSearchType c  where "+
					" a.searchIndexId = b.searchIndexId  and "+
					"a.searchTypeId = c.searchTypeId and b.searchIndexName = '"+searchIndexName+"' and "+ 
					"b.environment = '"+environment+"' and c.searchTypeName = '"+searchTypeName+"'");
			
			for(MetaSearchInput obj: mSrchInputList){
				MetaSearchInputDTO mSInputDTO = new MetaSearchInputDTO();
				mSInputDTO.setInputName(obj.getInputName());
				//mSInputDTO.setInputValue(obj.getInputValue());
				
				LOGGER.debug(mSInputDTO.getInputName());
				//LOGGER.debug(mSInputDTO.getInputValue());
				
				srchInputList.add(mSInputDTO);
			}

		} catch (HibernateException e) {
			LOGGER.info(e);
		}
		return srchInputList;
	}
	
	/**
	 * This method is used for getting the rqsults related to Solr server URL.
	 */
	
	public List<MetaSearchIndexDTO> getURLQuery(String searchIndexName,
			String environment, String searchIndexGroup) {
		
		List<MetaSearchIndexDTO> srchIndexList = new ArrayList<MetaSearchIndexDTO>();
		try {
			
			@SuppressWarnings("unchecked")
			List<MetaSearchIndex> mSrchIndexList =hibernateTemplate.find(" from MetaSearchIndex where searchIndexName = '"+searchIndexName+"'  and "+
					" environment = '"+environment+"'and searchIndexGroup = '"+searchIndexGroup+"'");
			
			for(MetaSearchIndex obj: mSrchIndexList){
				MetaSearchIndexDTO mSIndexDTO = new MetaSearchIndexDTO();
				mSIndexDTO.setSearchHost(obj.getSearchHost());
				mSIndexDTO.setSearchIndexGroup(obj.getSearchIndexGroup());
				mSIndexDTO.setSearchIndexName(obj.getSearchIndexName());
				
				LOGGER.debug(mSIndexDTO.getSearchIndexGroup());
				LOGGER.debug(mSIndexDTO.getSearchHost());
				LOGGER.debug(mSIndexDTO.getSearchIndexName());
				
				srchIndexList.add(mSIndexDTO);
			}

		} catch (HibernateException e) {
			LOGGER.info(e);
		}
		return srchIndexList;
	}
	
	
	
	

}
