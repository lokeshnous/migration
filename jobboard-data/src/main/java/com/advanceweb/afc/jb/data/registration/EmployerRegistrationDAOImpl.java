package com.advanceweb.afc.jb.data.registration;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.data.domain.Employer;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:55 PM
 */
@Repository
public class EmployerRegistrationDAOImpl implements EmployerRegistrationDAO {

	public EmployerRegistrationDAOImpl(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param employer
	 */
	public boolean createNewEmployer(Employer employer){
		return false;
	}

	/**
	 * 
	 * @param employerId
	 */
	public boolean deleteEmployer(long employerId){
		return false;
	}

	/**
	 * 
	 * @param employerId
	 */
	public Employer getEmployerDetails(long employerId){
		return null;
	}

	/**
	 * 
	 * @param employer
	 */
	public boolean updateEmployerDetails(Employer employer){
		return false;
	}

}