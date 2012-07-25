package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.employer.dao.EmpBrandTempDAO;

/**
 * <code> EmpBrandTempService </code> is a Service class for EmpBrandTemp.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Service("empBrandTemp")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class EmpBrandTempService implements EmpBrandTemp {

	@Autowired
	private EmpBrandTempDAO empBrandTempDAO;

	/**
	 * Fetch the job posting Branding Templates
	 */
	@Override
	public List<EmpBrandTempDTO> fetchEmpBrandTemp(MerUserDTO merUserDTO) {
		List<EmpBrandTempDTO> templatesDTOs = empBrandTempDAO
				.fetchEmpBrandTemp(merUserDTO);
		return templatesDTOs;
	}

	/**
	 * Create the job posting Branding Template.
	 */
	@Override
	public Boolean createEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
		Boolean status = null;
		status = empBrandTempDAO.createEmpBrandTemp(brandingTemplatesDTO);
		return status;
	}

	/**
	 * View the job posting Branding Template.
	 */
	@Override
	public EmpBrandTempDTO viewEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
		EmpBrandTempDTO empBrandTempDTO = null;
		empBrandTempDTO = empBrandTempDAO
				.viewEmpBrandTemp(brandingTemplatesDTO);
		return empBrandTempDTO;
	}

	/**
	 * Edit the job posting Branding Template.
	 */
	@Override
	public EmpBrandTempDTO editEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
		EmpBrandTempDTO empBrandTempDTO = null;
		empBrandTempDTO = empBrandTempDAO
				.editEmpBrandTemp(brandingTemplatesDTO);
		return empBrandTempDTO;
	}

	/**
	 * Delete the job posting Branding Template.
	 */
	@Override
	public Boolean deleteEmpBrandTemp(EmpBrandTempDTO brandingTemplatesDTO) {
		Boolean status = null;
		status = empBrandTempDAO.deleteEmpBrandTemp(brandingTemplatesDTO);
		return status;
	}

}
