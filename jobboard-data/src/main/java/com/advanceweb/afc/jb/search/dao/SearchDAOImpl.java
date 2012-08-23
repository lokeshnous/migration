package com.advanceweb.afc.jb.search.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.advanceweb.afc.jb.common.QueryDTO;
import com.advanceweb.afc.jb.data.entities.MetaSearchParam;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.SearchParamDTO;

/**
 * @Author : Reetesh RN
 * @Version: 1.0s
 * @Created: Jul 25, 2012
 * @Purpose: This class implements all the DAO operations related to solr search
 */
@Transactional
@Repository("searchDAO")
public class SearchDAOImpl implements SearchDAO{
	
	private static final Logger LOGGER = Logger
			.getLogger("SearchDAOImpl.class");
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used for getting the parameter lists from the DB for creating the SOLR query.
	 * @param searchIndexName represents search index name
	 * @param environment
	 * @param searchIndexGroup represents search index group
	 * @param searchTypeName represents search type name
	 * @return QueryDTO object of QueryDTO
	 * @throws JobBoardDataException
	 */
	public QueryDTO getSearchQueryDTO(String searchIndexName,
			String environment, String searchIndexGroup, String searchTypeName) throws JobBoardDataException {
		
		QueryDTO queryDTO = new QueryDTO();
		List<SearchParamDTO> srchParamDTOList = new ArrayList<SearchParamDTO>();
		try {
			@SuppressWarnings("unchecked")
			List<MetaSearchParam> mSrchParamList = hibernateTemplate.find("SELECT a from  MetaSearchParam a, MetaSearchIndex b, MetaSearchType c  where "+
					" a.metaSearchIndex.searchIndexId = b.searchIndexId  and "+
					"a.metaSearchType.searchTypeId = c.searchTypeId and b.searchIndexName = '"+searchIndexName+"' and "+ 
					"b.environment = '"+environment+"' and c.searchTypeName = '"+searchTypeName+"'and b.searchIndexGroup = '"+searchIndexGroup+"' order by a.seq");
			
			for(MetaSearchParam obj: mSrchParamList){
				SearchParamDTO msParamDTO = new SearchParamDTO();
				msParamDTO.setParameterName(obj.getParameterName());
				msParamDTO.setParameterValue(obj.getParameterValue());
				srchParamDTOList.add(msParamDTO);
			}
			
			if(mSrchParamList != null && mSrchParamList.size() > 0){
				queryDTO.setSearchHost(mSrchParamList.get(0).getMetaSearchIndex().getSearchHost());
				queryDTO.setmSrchParamList(srchParamDTOList);
			}
			queryDTO.setEnvironment(environment);
			queryDTO.setSearchIndexName(searchIndexName);
			queryDTO.setSearchIndexGroup(searchIndexGroup);
			queryDTO.setSearchName(searchTypeName);
			
		} catch (HibernateException e) {
			LOGGER.debug(e);
			throw new JobBoardDataException("Error while fetching the SOLR parameters from the Database..."+e);
		}
		return queryDTO;
	}
	
	

}
