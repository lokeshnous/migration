package com.advanceweb.afc.jb.agency.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.CountryDTO;
import com.advanceweb.afc.jb.common.StateDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService;
import com.advanceweb.afc.jb.employer.web.controller.EmployeeAccountForm;
import com.advanceweb.afc.jb.employer.web.controller.EmployerRegistrationValidation;
import com.advanceweb.afc.jb.employer.web.controller.TransformEmployerRegistration;
import com.advanceweb.afc.jb.lookup.service.PopulateDropdowns;
import com.advanceweb.afc.jb.pgi.service.FetchAdmFacilityConatact;
import com.advanceweb.afc.jb.pgi.web.controller.BillingAddressForm;
import com.advanceweb.afc.jb.pgi.web.controller.TransformPaymentMethod;


/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 21st August, 2012
 * 
 */

@Controller
@RequestMapping("/agency")
public class AgencyDashBoardController {
	
	private static final Logger LOGGER = Logger
			.getLogger("EmployerRegistrationController.class");
	
	private static final String _FORM_VIEW = "agencyDashboard";
	
	@Autowired	
	private EmloyerRegistartionService emloyerRegistartionService;
	@Autowired
	private TransformEmployerRegistration transformEmployerRegistration;
	@Autowired
	private EmployerRegistrationValidation registerValidation;
	@Autowired
	private FetchAdmFacilityConatact fetchAdmFacilityConatact;
	@Autowired
	private TransformPaymentMethod transformPaymentMethod;
	@Autowired
	private PopulateDropdowns populateDropdownsService;
	
	@RequestMapping("/agencyDashboard")
	public ModelAndView displayDashBoard() {
		return new ModelAndView(_FORM_VIEW);
	}
	
	
	
	/**
	 * This method is called to Account Setting update page
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeAccountSetting", method = RequestMethod.GET)
	public ModelAndView editAccountSetting(
			EmployeeAccountForm employeeAccountForm, BindingResult result,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session.getAttribute("userId");
		List<AdmFacilityContact> listProfAttribForms = emloyerRegistartionService
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);
		try {
		if (null == listProfAttribForms || listProfAttribForms.isEmpty()) {

			model.setViewName(_FORM_VIEW);
			return model;
		} else {

			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();

			AccountProfileDTO dto = transformEmployerRegistration
					.transformAccountProfileFormToDto(employeeAccountForm);

			if (!registerValidation
					.accountValidate(employeeAccountForm, result)) {
				result.rejectValue("email", "NotEmpty",
						"Email Id already Exists!");
				model.setViewName("accountSetting");
				return model;
			}

			// emloyerRegistartionService.editEmployeeAccount(dto,
			// admfacilityid);
			emloyerRegistartionService.editEmployeeAccount(dto, admfacilityid,
					userId, MMJBCommonConstants.PRIMARY);
		}
		
	} catch (Exception e) {
		model.setViewName(_FORM_VIEW);
		return model;
	}
		model.setViewName(_FORM_VIEW);
		return model;
		
	}

	/**
	 * This method is called to Account Setting update page
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeBillingSetting", method = RequestMethod.GET)
	public ModelAndView editBillingSetting(
			EmployeeAccountForm employeeBillingForm, BindingResult result,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		int userId = (Integer) session.getAttribute("userId");
		int facilityId = (Integer) session
				.getAttribute(MMJBCommonConstants.FACILITY_ID);
		// int facilityId =368;
		List<AdmFacilityContact> listProfAttribForms = emloyerRegistartionService
				.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
		try {
		if (null == listProfAttribForms || listProfAttribForms.isEmpty()) {

			BillingAddressForm billingAddressForm = employeeBillingForm.billingAddressForm;
			AccountBillingDTO billingAddressDTO = transformPaymentMethod
					.transformDataBillingAddreFormToDto(billingAddressForm);
			billingAddressDTO.setFacilityId(facilityId);
			billingAddressDTO.setCompanyName(employeeBillingForm.getCompany());
			billingAddressDTO.setEmail(employeeBillingForm.getEmail());
			billingAddressDTO.setPhone(employeeBillingForm.getPhone());
			billingAddressDTO.setCreateDate(new Date());
			fetchAdmFacilityConatact.saveDataBillingAddress(billingAddressDTO);

		} else {
			int admfacilityid = listProfAttribForms.get(0)
					.getFacilityContactId();
			AccountProfileDTO dto = transformEmployerRegistration
					.transformBillingProfileFormToDto(employeeBillingForm);
			// emloyerRegistartionService.editEmployeeAccount(dto,
			// admfacilityid);
			emloyerRegistartionService.editEmployeeAccount(dto, admfacilityid,
					userId, MMJBCommonConstants.PRIMARY);
		}
		
	} catch (Exception e) {
		model.setViewName(_FORM_VIEW);
		return model;
	}
		model.setViewName(_FORM_VIEW);
		return model;
	}

	/**
	 * This method is called to Account Setting display page
	 * 
	 * @author kartikm
	 * @param model
	 * @return true
	 */
	@RequestMapping(value = "/viewEmpAccountProfile", method = RequestMethod.GET)
	public ModelAndView viewEmpAccountProfileSettings(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			EmployeeAccountForm employeeAccountForm = new EmployeeAccountForm();
			EmployeeAccountForm employeeBillingForm = new EmployeeAccountForm();
			employeeBillingForm.setBillingAddressForm(new BillingAddressForm());

			int userId = (Integer) session.getAttribute("userId");
			// int userId=368;

			List<CountryDTO> countryList = populateDropdownsService
					.getCountryList();

			List<StateDTO> stateList = populateDropdownsService.getStateList();

			List<AdmFacilityContact> listProfAttribForms = emloyerRegistartionService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.PRIMARY);

			employeeAccountForm.setFirstName(listProfAttribForms.get(0)
					.getFirstName());
			employeeAccountForm.setLastName(listProfAttribForms.get(0)
					.getLastName());
			employeeAccountForm.setCompany(listProfAttribForms.get(0)
					.getCompany());
			employeeAccountForm.setStreetAddress(listProfAttribForms.get(0)
					.getStreet());
			employeeAccountForm.setCityOrTown(listProfAttribForms.get(0)
					.getCity());
			employeeAccountForm.setState(listProfAttribForms.get(0).getState());
			employeeAccountForm.setCountry(listProfAttribForms.get(0)
					.getCountry());
			employeeAccountForm.setEmail(listProfAttribForms.get(0).getEmail());
			employeeAccountForm.setZipCode(listProfAttribForms.get(0)
					.getPostcode());
			employeeAccountForm.setPhone(listProfAttribForms.get(0).getPhone());

			/**
			 * this is for billing pages
			 */
			int count = 0;
			List<AdmFacilityContact> listBillingForms = emloyerRegistartionService
					.getEmployeePrimaryKey(userId, MMJBCommonConstants.BILLING);
			if ((listBillingForms.size() <= 0) || ("".equals(listBillingForms))) {
				count = listBillingForms.size();

			} else {
				employeeBillingForm.getBillingAddressForm()
						.setFnameForBillingAddr(
								listBillingForms.get(0).getFirstName());
				employeeBillingForm.getBillingAddressForm()
						.setLnameForBillingAddr(
								listProfAttribForms.get(0).getLastName());
				employeeBillingForm.setCompany(listBillingForms.get(0)
						.getCompany());
				employeeBillingForm.getBillingAddressForm()
						.setStreetForBillingAddr(
								listBillingForms.get(0).getStreet());
				employeeBillingForm.getBillingAddressForm()
						.setCityOrTownForBillingAddr(
								listBillingForms.get(0).getCity());
				employeeBillingForm
						.setEmail(listBillingForms.get(0).getEmail());
				employeeBillingForm.getBillingAddressForm()
						.setZipCodeForBillingAddr(
								listBillingForms.get(0).getPostcode());
				employeeBillingForm
						.setPhone(listBillingForms.get(0).getPhone());
				employeeBillingForm.getBillingAddressForm()
						.setStateBillingAddress(
								listBillingForms.get(0).getState());
				employeeBillingForm.getBillingAddressForm()
						.setCountryForBillingAddr(
								listBillingForms.get(0).getCountry());
			}
			model.addObject("countryList", countryList);
			model.addObject("stateList", stateList);
			model.addObject("listProfAttribForms", listProfAttribForms);
			model.addObject("count", count);
			model.setViewName("agencyAccountSetting");
			model.addObject("employeeAccountForm", employeeAccountForm);
			model.addObject("employeeBillingForm", employeeBillingForm);

		} catch (Exception e) {
			LOGGER.info("Error For controller");
		}

		return model;
	}

}
