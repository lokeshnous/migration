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
@Table(name="WebMembershipEmail")
public class WebMembershipEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WebMembershipEmailID")
	private int webMembershipEmailID;

	@Column(name="WebMembershipID")
	private int webMembershipID;

	@Column(name="Email")
	private String email;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="PrimaryEmail")
	private boolean primaryEmail;
	@Column(name="WelcomeEMailHistoryID")
	private int welcomeEMailHistoryID;
	
	@Column(name="DeleteDate")
	private Timestamp deleteDate;

	@Column(name="EmailStatusID")
	private int emailStatusID;

	@Column(name="ModifyDate")
	private Timestamp modifyDate;
	
	@Column(name="CreateWebUserId")
	private int createWebUserId;
	@Column(name="ModifyWebUserId")
	private int modifyWebUserId;
	@Column(name="DeleteWebUserId")
	private int deleteWebUserId;

	

    public WebMembershipEmail() {
    }



	/**
	 * @return the webMembershipEmailID
	 */
	public int getWebMembershipEmailID() {
		return webMembershipEmailID;
	}



	/**
	 * @param webMembershipEmailID the webMembershipEmailID to set
	 */
	public void setWebMembershipEmailID(int webMembershipEmailID) {
		this.webMembershipEmailID = webMembershipEmailID;
	}



	/**
	 * @return the webMembershipID
	 */
	public int getWebMembershipID() {
		return webMembershipID;
	}



	/**
	 * @param webMembershipID the webMembershipID to set
	 */
	public void setWebMembershipID(int webMembershipID) {
		this.webMembershipID = webMembershipID;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the createDate
	 */
	public Timestamp getCreateDate() {
		return createDate;
	}



	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}



	/**
	 * @return the primaryEmail
	 */
	public boolean isPrimaryEmail() {
		return primaryEmail;
	}



	/**
	 * @param primaryEmail the primaryEmail to set
	 */
	public void setPrimaryEmail(boolean primaryEmail) {
		this.primaryEmail = primaryEmail;
	}



	/**
	 * @return the welcomeEMailHistoryID
	 */
	public int getWelcomeEMailHistoryID() {
		return welcomeEMailHistoryID;
	}



	/**
	 * @param welcomeEMailHistoryID the welcomeEMailHistoryID to set
	 */
	public void setWelcomeEMailHistoryID(int welcomeEMailHistoryID) {
		this.welcomeEMailHistoryID = welcomeEMailHistoryID;
	}



	/**
	 * @return the deleteDate
	 */
	public Timestamp getDeleteDate() {
		return deleteDate;
	}



	/**
	 * @param deleteDate the deleteDate to set
	 */
	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}



	/**
	 * @return the emailStatusID
	 */
	public int getEmailStatusID() {
		return emailStatusID;
	}



	/**
	 * @param emailStatusID the emailStatusID to set
	 */
	public void setEmailStatusID(int emailStatusID) {
		this.emailStatusID = emailStatusID;
	}



	/**
	 * @return the modifyDate
	 */
	public Timestamp getModifyDate() {
		return modifyDate;
	}



	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}



	/**
	 * @return the createWebUserId
	 */
	public int getCreateWebUserId() {
		return createWebUserId;
	}



	/**
	 * @param createWebUserId the createWebUserId to set
	 */
	public void setCreateWebUserId(int createWebUserId) {
		this.createWebUserId = createWebUserId;
	}



	/**
	 * @return the modifyWebUserId
	 */
	public int getModifyWebUserId() {
		return modifyWebUserId;
	}



	/**
	 * @param modifyWebUserId the modifyWebUserId to set
	 */
	public void setModifyWebUserId(int modifyWebUserId) {
		this.modifyWebUserId = modifyWebUserId;
	}



	/**
	 * @return the deleteWebUserId
	 */
	public int getDeleteWebUserId() {
		return deleteWebUserId;
	}



	/**
	 * @param deleteWebUserId the deleteWebUserId to set
	 */
	public void setDeleteWebUserId(int deleteWebUserId) {
		this.deleteWebUserId = deleteWebUserId;
	}



	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}