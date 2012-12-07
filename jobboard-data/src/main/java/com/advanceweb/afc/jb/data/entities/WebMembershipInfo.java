package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private int webMembershipInfoID;

	@Column(name="BelievedSubNumber")
	private String believedSubNumber;

	@Column(name="CountryId")
	private int countryId;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="CreateWebUserId")
	private int createWebUserId;

	@Column(name="DeleteDate")
	private Timestamp deleteDate;

	@Column(name="DeleteWebUserId")
	private int deleteWebUserId;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;

	@Column(name="ModifyDate")
	private Timestamp modifyDate;

	@Column(name="ModifyWebUserId")
	private int modifyWebUserId;

	@Column(name="ZipCode")
	private String zipCode;

    public WebMembershipInfo() {
    }

	public int getWebMembershipInfoID() {
		return this.webMembershipInfoID;
	}

	public void setWebMembershipInfoID(int webMembershipInfoID) {
		this.webMembershipInfoID = webMembershipInfoID;
	}

	public String getBelievedSubNumber() {
		return this.believedSubNumber;
	}

	public void setBelievedSubNumber(String believedSubNumber) {
		this.believedSubNumber = believedSubNumber;
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getCreateWebUserId() {
		return this.createWebUserId;
	}

	public void setCreateWebUserId(int createWebUserId) {
		this.createWebUserId = createWebUserId;
	}

	public Timestamp getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}

	public int getDeleteWebUserId() {
		return this.deleteWebUserId;
	}

	public void setDeleteWebUserId(int deleteWebUserId) {
		this.deleteWebUserId = deleteWebUserId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getModifyWebUserId() {
		return this.modifyWebUserId;
	}

	public void setModifyWebUserId(int modifyWebUserId) {
		this.modifyWebUserId = modifyWebUserId;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}