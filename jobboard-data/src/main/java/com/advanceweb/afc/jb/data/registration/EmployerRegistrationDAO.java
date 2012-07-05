package com.advanceweb.afc.jb.data.registration;
import com.advanceweb.afc.jb.data.domain.Employer;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public interface EmployerRegistrationDAO {

	/**
	 * 
	 * @param employer
	 */
	public boolean createNewEmployer(Employer employer);

	/**
	 * 
	 * @param employerId
	 */
	public boolean deleteEmployer(long employerId);

	/**
	 * 
	 * @param employerId
	 */
	public Employer getEmployerDetails(long employerId);

	/**
	 * 
	 * @param employer
	 */
	public boolean updateEmployerDetails(Employer employer);

}