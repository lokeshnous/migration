package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = -7570981110575694112L;
	private int userId;
	private String emailId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String password;
	private String industry;
	private String profession;
	private String speciality;
	private String jobTitle;
	private String memberShips;
	private String otherDetails;
	private String currentPassword;
	private String streetAddress;
	private String zipCode;
	private String state;
	private String country;
	private String position;
	private String company;
	private String primaryPhone;
	private String secondaryPhone;
	private String city;
	private int nsCustomerID;
	private String nsStatus;
	private Map<Integer,String> nsStatusCode;
	private String recordType;
	private int entityId;
	private boolean featured;
	private Date featuredStartDate;
	private Date featuredEndDate;
	private boolean invoiceEnabled;
	private boolean xmlFeedEnabled;
	private Date xmlFeedStartDate;
	private Date xmlFeedEndDate;
	private SalesOrderDTO salesOrderDTO;
	private String packageName;
	private List<String> emailList;
	private boolean helthSystem;
	private boolean admin;
	
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isHelthSystem() {
		return helthSystem;
	}
	public void setHelthSystem(boolean helthSystem) {
		this.helthSystem = helthSystem;
	}
	public List<String> getEmailList() {
		return emailList;
	}
	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getMemberShips() {
		return memberShips;
	}
	public void setMemberShips(String memberShips) {
		this.memberShips = memberShips;
	}
	public String getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	public String getSecondaryPhone() {
		return secondaryPhone;
	}
	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNsCustomerID() {
		return nsCustomerID;
	}
	public void setNsCustomerID(int nsCustomerID) {
		this.nsCustomerID = nsCustomerID;
	}
	public String getNsStatus() {
		return nsStatus;
	}
	public void setNsStatus(String nsStatus) {
		this.nsStatus = nsStatus;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public boolean isFeatured() {
		return featured;
	}
	public void setFeatured(boolean isFeatured) {
		this.featured = isFeatured;
	}
	public Date getFeaturedStartDate() {
		return featuredStartDate;
	}
	public void setFeaturedStartDate(Date featuredStartDate) {
		this.featuredStartDate = featuredStartDate;
	}
	public Date getFeaturedEndDate() {
		return featuredEndDate;
	}
	public void setFeaturedEndDate(Date featuredEndDate) {
		this.featuredEndDate = featuredEndDate;
	}
	public boolean isInvoiceEnabled() {
		return invoiceEnabled;
	}
	public void setInvoiceEnabled(boolean isInvoiceEnabled) {
		this.invoiceEnabled = isInvoiceEnabled;
	}
	public boolean isXmlFeedEnabled() {
		return xmlFeedEnabled;
	}
	public void setXmlFeedEnabled(boolean isXmlFeedEnabled) {
		this.xmlFeedEnabled = isXmlFeedEnabled;
	}
	public Date getXmlFeedStartDate() {
		return xmlFeedStartDate;
	}
	public void setXmlFeedStartDate(Date xmlFeedStartDate) {
		this.xmlFeedStartDate = xmlFeedStartDate;
	}
	public Date getXmlFeedEndDate() {
		return xmlFeedEndDate;
	}
	public void setXmlFeedEndDate(Date xmlFeedEndDate) {
		this.xmlFeedEndDate = xmlFeedEndDate;
	}
	public SalesOrderDTO getSalesOrderDTO() {
		return salesOrderDTO;
	}
	public void setSalesOrderDTO(SalesOrderDTO salesOrderDTO) {
		this.salesOrderDTO = salesOrderDTO;
	}
	public Map<Integer, String> getNsStatusCode() {
		return nsStatusCode;
	}
	public void setNsStatusCode(Map<Integer, String> nsStatusCode) {
		this.nsStatusCode = nsStatusCode;
	}
}
