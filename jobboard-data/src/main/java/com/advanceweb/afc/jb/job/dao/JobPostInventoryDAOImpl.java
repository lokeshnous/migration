package com.advanceweb.afc.jb.job.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobPostingInventoryDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th sept, 2012
 */

@Transactional
@Repository("inventoryDAO")
public class JobPostInventoryDAOImpl implements JobPostInventoryDAO {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method to get job posting inventory details
	 * 
	 * @param userId
	 * @param facilityId
	 * @return JobPostingInventoryDTO
	 */
	public List<JobPostingInventoryDTO> getInventoryDetails(int userId,
			int facilityId) {
		Query getInventoryData = hibernateTemplate.getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(" { call GetInventoryDetails(?) }");
		getInventoryData.setInteger(0, facilityId);
		List<?> invetoryDeatil = getInventoryData.list();
		Iterator<?> iterator = invetoryDeatil.iterator();
		List<JobPostingInventoryDTO> inventoryDTOs = new ArrayList<JobPostingInventoryDTO>();
		while (iterator.hasNext()) {
			JobPostingInventoryDTO dto = new JobPostingInventoryDTO();
			Object[] row = (Object[]) iterator.next();
			BigDecimal qty = (BigDecimal) row[4];
			BigDecimal availqty = (BigDecimal) row[5];
			dto.setProductId((Integer) row[0]);
			dto.setProductType((String) row[1]);
			dto.setJbType((String) row[2]);
			dto.setAddon((String) row[3]);
			dto.setQuantity(qty.intValue());
			dto.setAvailableQty(availqty.intValue());
			dto.setInvDetailId((Integer) row[6]);
			inventoryDTOs.add(dto);
		}
		return inventoryDTOs;
	}
}
