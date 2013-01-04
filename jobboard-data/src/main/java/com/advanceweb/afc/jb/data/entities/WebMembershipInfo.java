package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the WebMembershipInfo database table.
 * 
 */
@Entity
@Table(name="WebMembershipInfo")
public class WebMembershipInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WebMembershipInfoID")
	private Integer webMembershipInfoID;

	@Column(name="BelievedSubNumber")
	private String believedSubNumber;

	@Column(name="CountryId")
	private Integer countryId;

	@Column(name="CreateDate")
	private Date createDate;

	@Column(name="CreateWebUserId")
	private Integer createWebUserId;

	@Column(name="DeleteDate")
	private Date deleteDate;

	@Column(name="DeleteWebUserId")
	private Integer deleteWebUserId;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;

	@Column(name="ModifyDate")
	private Date modifyDate;

	@Column(name="ModifyWebUserId")
	private Integer modifyWebUserId;

	@Column(name="ZipCode")
	private String zipCode;

	@OneToOne(fetch=FetchType.EAGER, mappedBy="webMembershipInfo")
	private WebMembership webMembership;

	public Integer getWebMembershipInfoID() {
		return webMembershipInfoID;
	}

	public void setWebMembershipInfoID(Integer webMembershipInfoID) {
		this.webMembershipInfoID = webMembershipInfoID;
	}

	public String getBelievedSubNumber() {
		return believedSubNumber;
	}

	public void setBelievedSubNumber(String believedSubNumber) {
		this.believedSubNumber = believedSubNumber;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateWebUserId() {
		return createWebUserId;
	}

	public void setCreateWebUserId(Integer createWebUserId) {
		this.createWebUserId = createWebUserId;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public Integer getDeleteWebUserId() {
		return deleteWebUserId;
	}

	public void setDeleteWebUserId(Integer deleteWebUserId) {
		this.deleteWebUserId = deleteWebUserId;
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

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifyWebUserId() {
		return modifyWebUserId;
	}

	public void setModifyWebUserId(Integer modifyWebUserId) {
		this.modifyWebUserId = modifyWebUserId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public WebMembership getWebMembership() {
		return webMembership;
	}

	public void setWebMembership(WebMembership webMembership) {
		this.webMembership = webMembership;
	}
	
}