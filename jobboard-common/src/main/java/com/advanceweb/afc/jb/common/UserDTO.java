/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7570981110575694112L;
	
	/** The user id. */
	private int userId;
	
	/** The email id. */
	private String emailId;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The middle name. */
	private String middleName;
	
	/** The password. */
	private String password;
	
	/** The industry. */
	private String industry;
	
	/** The profession. */
	private String profession;
	
	/** The speciality. */
	private String speciality;
	
	/** The job title. */
	private String jobTitle;
	
	/** The member ships. */
	private String memberShips;
	
	/** The other details. */
	private String otherDetails;
	
	/** The current password. */
	private String currentPassword;
	
	/** The street address. */
	private String streetAddress;
	
	/** The zip code. */
	private String zipCode;
	
	/** The state. */
	private String state;
	
	/** The country. */
	private String country;
	
	/** The position. */
	private String position;
	
	/** The company. */
	private String company;
	
	/** The primary phone. */
	private String primaryPhone;
	
	/** The secondary phone. */
	private String secondaryPhone;
	
	/** The city. */
	private String city;
	
	/** The ns customer id. */
	private int nsCustomerID;
	
	/** The ns status. */
	private String nsStatus;
	
	/** The ns status code. */
	private Map<Integer,String> nsStatusCode;
	
	/** The record type. */
	private String recordType;
	
	/** The entity id. */
	private int entityId;
	
	/** The featured. */
	private boolean featured;
	
	/** The featured start date. */
	private Date featuredStartDate;
	
	/** The featured end date. */
	private Date featuredEndDate;
	
	/** The invoice enabled. */
	private boolean invoiceEnabled;
	
	/** The xml feed enabled. */
	private boolean xmlFeedEnabled;
	
	/** The xml feed start date. */
	private Date xmlFeedStartDate;
	
	/** The xml feed end date. */
	private Date xmlFeedEndDate;
	
	/** The sales order dto. */
	private SalesOrderDTO salesOrderDTO;
	
	/** The package name. */
	private String packageName;
	
	/** The email list. */
	private List<String> emailList;
	
	/** The helth system. */
	private boolean helthSystem;
	
	/** The admin. */
	private boolean admin;
	
	/** The old user. */
	private boolean oldUser;
	
	/** The adv pass user. */
	private boolean advPassUser;
	
	/** The discount item. */
	private String discountItem;
	
	
	/**
	 * Checks if is adv pass user.
	 *
	 * @return true, if is adv pass user
	 */
	public boolean isAdvPassUser() {
		return advPassUser;
	}
	
	/**
	 * Sets the adv pass user.
	 *
	 * @param advPassUser the new adv pass user
	 */
	public void setAdvPassUser(boolean advPassUser) {
		this.advPassUser = advPassUser;
	}
	
	/**
	 * Checks if is old user.
	 *
	 * @return true, if is old user
	 */
	public boolean isOldUser() {
		return oldUser;
	}
	
	/**
	 * Sets the old user.
	 *
	 * @param oldUser the new old user
	 */
	public void setOldUser(boolean oldUser) {
		this.oldUser = oldUser;
	}
	
	/**
	 * Checks if is admin.
	 *
	 * @return true, if is admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	
	/**
	 * Sets the admin.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	/**
	 * Checks if is helth system.
	 *
	 * @return true, if is helth system
	 */
	public boolean isHelthSystem() {
		return helthSystem;
	}
	
	/**
	 * Sets the helth system.
	 *
	 * @param helthSystem the new helth system
	 */
	public void setHelthSystem(boolean helthSystem) {
		this.helthSystem = helthSystem;
	}
	
	/**
	 * Gets the email list.
	 *
	 * @return the email list
	 */
	public List<String> getEmailList() {
		return emailList;
	}
	
	/**
	 * Sets the email list.
	 *
	 * @param emailList the new email list
	 */
	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
	
	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the industry.
	 *
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}
	
	/**
	 * Sets the industry.
	 *
	 * @param industry the new industry
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	/**
	 * Gets the profession.
	 *
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}
	
	/**
	 * Sets the profession.
	 *
	 * @param profession the new profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	/**
	 * Gets the speciality.
	 *
	 * @return the speciality
	 */
	public String getSpeciality() {
		return speciality;
	}
	
	/**
	 * Sets the speciality.
	 *
	 * @param speciality the new speciality
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Gets the member ships.
	 *
	 * @return the member ships
	 */
	public String getMemberShips() {
		return memberShips;
	}
	
	/**
	 * Sets the member ships.
	 *
	 * @param memberShips the new member ships
	 */
	public void setMemberShips(String memberShips) {
		this.memberShips = memberShips;
	}
	
	/**
	 * Gets the other details.
	 *
	 * @return the other details
	 */
	public String getOtherDetails() {
		return otherDetails;
	}
	
	/**
	 * Sets the other details.
	 *
	 * @param otherDetails the new other details
	 */
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}
	
	/**
	 * Gets the current password.
	 *
	 * @return the current password
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}
	
	/**
	 * Sets the current password.
	 *
	 * @param currentPassword the new current password
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	/**
	 * Gets the street address.
	 *
	 * @return the street address
	 */
	public String getStreetAddress() {
		return streetAddress;
	}
	
	/**
	 * Sets the street address.
	 *
	 * @param streetAddress the new street address
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * Sets the zip code.
	 *
	 * @param zipCode the new zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	
	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * Gets the primary phone.
	 *
	 * @return the primary phone
	 */
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	
	/**
	 * Sets the primary phone.
	 *
	 * @param primaryPhone the new primary phone
	 */
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	
	/**
	 * Gets the secondary phone.
	 *
	 * @return the secondary phone
	 */
	public String getSecondaryPhone() {
		return secondaryPhone;
	}
	
	/**
	 * Sets the secondary phone.
	 *
	 * @param secondaryPhone the new secondary phone
	 */
	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the ns customer id.
	 *
	 * @return the ns customer id
	 */
	public int getNsCustomerID() {
		return nsCustomerID;
	}
	
	/**
	 * Sets the ns customer id.
	 *
	 * @param nsCustomerID the new ns customer id
	 */
	public void setNsCustomerID(int nsCustomerID) {
		this.nsCustomerID = nsCustomerID;
	}
	
	/**
	 * Gets the ns status.
	 *
	 * @return the ns status
	 */
	public String getNsStatus() {
		return nsStatus;
	}
	
	/**
	 * Sets the ns status.
	 *
	 * @param nsStatus the new ns status
	 */
	public void setNsStatus(String nsStatus) {
		this.nsStatus = nsStatus;
	}
	
	/**
	 * Gets the record type.
	 *
	 * @return the record type
	 */
	public String getRecordType() {
		return recordType;
	}
	
	/**
	 * Sets the record type.
	 *
	 * @param recordType the new record type
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	/**
	 * Gets the entity id.
	 *
	 * @return the entity id
	 */
	public int getEntityId() {
		return entityId;
	}
	
	/**
	 * Sets the entity id.
	 *
	 * @param entityId the new entity id
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	
	/**
	 * Checks if is featured.
	 *
	 * @return true, if is featured
	 */
	public boolean isFeatured() {
		return featured;
	}
	
	/**
	 * Sets the featured.
	 *
	 * @param isFeatured the new featured
	 */
	public void setFeatured(boolean isFeatured) {
		this.featured = isFeatured;
	}
	
	/**
	 * Gets the featured start date.
	 *
	 * @return the featured start date
	 */
	public Date getFeaturedStartDate() {
		return featuredStartDate;
	}
	
	/**
	 * Sets the featured start date.
	 *
	 * @param featuredStartDate the new featured start date
	 */
	public void setFeaturedStartDate(Date featuredStartDate) {
		this.featuredStartDate = featuredStartDate;
	}
	
	/**
	 * Gets the featured end date.
	 *
	 * @return the featured end date
	 */
	public Date getFeaturedEndDate() {
		return featuredEndDate;
	}
	
	/**
	 * Sets the featured end date.
	 *
	 * @param featuredEndDate the new featured end date
	 */
	public void setFeaturedEndDate(Date featuredEndDate) {
		this.featuredEndDate = featuredEndDate;
	}
	
	/**
	 * Checks if is invoice enabled.
	 *
	 * @return true, if is invoice enabled
	 */
	public boolean isInvoiceEnabled() {
		return invoiceEnabled;
	}
	
	/**
	 * Sets the invoice enabled.
	 *
	 * @param isInvoiceEnabled the new invoice enabled
	 */
	public void setInvoiceEnabled(boolean isInvoiceEnabled) {
		this.invoiceEnabled = isInvoiceEnabled;
	}
	
	/**
	 * Checks if is xml feed enabled.
	 *
	 * @return true, if is xml feed enabled
	 */
	public boolean isXmlFeedEnabled() {
		return xmlFeedEnabled;
	}
	
	/**
	 * Sets the xml feed enabled.
	 *
	 * @param isXmlFeedEnabled the new xml feed enabled
	 */
	public void setXmlFeedEnabled(boolean isXmlFeedEnabled) {
		this.xmlFeedEnabled = isXmlFeedEnabled;
	}
	
	/**
	 * Gets the xml feed start date.
	 *
	 * @return the xml feed start date
	 */
	public Date getXmlFeedStartDate() {
		return xmlFeedStartDate;
	}
	
	/**
	 * Sets the xml feed start date.
	 *
	 * @param xmlFeedStartDate the new xml feed start date
	 */
	public void setXmlFeedStartDate(Date xmlFeedStartDate) {
		this.xmlFeedStartDate = xmlFeedStartDate;
	}
	
	/**
	 * Gets the xml feed end date.
	 *
	 * @return the xml feed end date
	 */
	public Date getXmlFeedEndDate() {
		return xmlFeedEndDate;
	}
	
	/**
	 * Sets the xml feed end date.
	 *
	 * @param xmlFeedEndDate the new xml feed end date
	 */
	public void setXmlFeedEndDate(Date xmlFeedEndDate) {
		this.xmlFeedEndDate = xmlFeedEndDate;
	}
	
	/**
	 * Gets the sales order dto.
	 *
	 * @return the sales order dto
	 */
	public SalesOrderDTO getSalesOrderDTO() {
		return salesOrderDTO;
	}
	
	/**
	 * Sets the sales order dto.
	 *
	 * @param salesOrderDTO the new sales order dto
	 */
	public void setSalesOrderDTO(SalesOrderDTO salesOrderDTO) {
		this.salesOrderDTO = salesOrderDTO;
	}
	
	/**
	 * Gets the ns status code.
	 *
	 * @return the ns status code
	 */
	public Map<Integer, String> getNsStatusCode() {
		return nsStatusCode;
	}
	
	/**
	 * Sets the ns status code.
	 *
	 * @param nsStatusCode the ns status code
	 */
	public void setNsStatusCode(Map<Integer, String> nsStatusCode) {
		this.nsStatusCode = nsStatusCode;
	}
	/**
	 * @return the discountItem
	 */
	public String getDiscountItem() {
		return discountItem;
	}
	/**
	 * @param discountItem the discountItem to set
	 */
	public void setDiscountItem(String discountItem) {
		this.discountItem = discountItem;
	}
}
