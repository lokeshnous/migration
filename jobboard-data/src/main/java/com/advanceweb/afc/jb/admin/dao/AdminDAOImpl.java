package com.advanceweb.afc.jb.admin.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.data.entities.MerUser;
import com.mysql.jdbc.StringUtils;

@Repository("impersonateDAO")
public class AdminDAOImpl implements AdminDAO {

	private static final Logger LOGGER = Logger
			.getLogger("ImpersonateDAOImpl.class");

	private static final String VERIFY_EMAIL = "from MerUser e where e.email = ?";

	private HibernateTemplate hibernateTemplateTracker;

	@Autowired
	public void setHibernateTemplate(
			SessionFactory sessionFactoryMerionTracker,
			SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(
				sessionFactoryMerionTracker);
	}

	@Override
	public boolean validateEmail(String email) {
		try {
			if (!StringUtils.isEmptyOrWhitespaceOnly(email)) {
				boolean result;
				@SuppressWarnings("unchecked")
				List<MerUser> usersList = hibernateTemplateTracker.find(
						VERIFY_EMAIL, email);
				if (null != usersList && !usersList.isEmpty()) {
					MerUser user = usersList.get(0);
					return (null != user ? true : false);
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e);
		}
		return false;
	}

}
