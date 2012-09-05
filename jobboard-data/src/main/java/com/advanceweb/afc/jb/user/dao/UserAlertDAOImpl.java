package com.advanceweb.afc.jb.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.UserAlertDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept,2012
 */

@Transactional
@Repository("alertDAO")
public class UserAlertDAOImpl implements UserAlertDAO{

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public List<UserAlertDTO> viewalerts(int userId) {

		return null;
	}
}
