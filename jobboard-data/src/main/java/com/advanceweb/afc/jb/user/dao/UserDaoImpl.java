package com.advanceweb.afc.jb.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserFacility;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;
@Transactional
@Repository("userDAO")
public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplateTracker;
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactoryMerionTracker,SessionFactory sessionFactory) {
		this.hibernateTemplateTracker = new HibernateTemplate(sessionFactoryMerionTracker);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public MerUserDTO getUser(String email) {
		MerUserDTO userDTO=new MerUserDTO();
		List<MerUser> userList =hibernateTemplateTracker.find(" from  MerUser user where user.email=?",email);
		MerUser user=null;
		if(userList!= null && !userList.isEmpty()){
		user=userList.get(0);
		}
		userDTO.setEmailId(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setUserId(user.getUserId());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}

	@Override
	public List<UserRoleDTO> getUserRole(int userId) {
		List<AdmUserRole> roleList= hibernateTemplate.find("from AdmUserRole a where a.id.userId=?",userId);
		List<UserRoleDTO> userRoleDTOList=new ArrayList<UserRoleDTO>();
		
		
		for(AdmUserRole role:roleList){
			
			List<AdmUserFacility> userFacilityList=hibernateTemplate.find("from AdmUserFacility f where f.id.userId=? and f.id.roleId=?",role.getId().getUserId(),role.getId().getRoleId());
			
			for(AdmUserFacility facility:userFacilityList){
				UserRoleDTO dto=new UserRoleDTO();
				dto.setRoleId(facility.getAdmFacility().getFacilityId());
				dto.setRoleName(facility.getAdmFacility().getFacilityType());
				userRoleDTOList.add(dto);
				
			}
			
			UserRoleDTO userRole=new UserRoleDTO();
			userRole.setRoleId(role.getAdmRole().getRoleId());
			userRole.setRoleName(role.getAdmRole().getName());
			userRoleDTOList.add(userRole);
		}
		
		
		
		return userRoleDTOList;
	}

}
