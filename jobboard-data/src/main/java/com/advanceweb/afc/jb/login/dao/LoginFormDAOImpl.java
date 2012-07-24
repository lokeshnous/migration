package com.advanceweb.afc.jb.login.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.LoginFormDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.AdmUserRolePK;
import com.advanceweb.afc.jb.data.entities.MerUser;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@SuppressWarnings("unchecked")
@Transactional
@Repository("loginFormDAO")
public class LoginFormDAOImpl implements LoginFormDAO {

	//private HibernateTemplate hibernateTemplate;
	
	private HibernateTemplate hibernateTemplateTracker;
    private HibernateTemplate hibernateTemplate;


	@Autowired
    public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
        this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	/**
	 * This method is used to get the userId and roleId of logged in user
	 * 
	 * @param email
	 *            and password
	 * 
	 */
	public LoginFormDTO validateLoginFormValues(String email, String password) {

		// Get the user id by passing the email address of user by passing the
		// email address
		// from MerUser entity
		LoginFormDTO loginFormDTO = new LoginFormDTO();
		List<MerUser> listMerUser = hibernateTemplateTracker
				.find("from MerUser where email = '" + email + "'");
		int userId = 0;
		if (listMerUser != null && listMerUser.size() > 0) {
			MerUser merUserNew = listMerUser.get(0);
			userId = merUserNew.getUserId();
			loginFormDTO.setEmailAddress(merUserNew.getEmail());
			loginFormDTO.setPassword(merUserNew.getPassword());
			loginFormDTO.setUserID(merUserNew.getUserId());
		}

		// Get the roleId for a logged in user by passing the userId from
		// AdmUserRole entity
		if (userId != 0) {
			AdmUserRolePK pk = new AdmUserRolePK();
			pk.setUserId(userId);
			List<AdmUserRole> admUserRolePK = hibernateTemplate
					.find("from AdmUserRole e where e.id = " + pk);
			if (admUserRolePK != null) {

			}
		}
		// Get the role id of user by passing the user id
		return loginFormDTO;
	}
}
