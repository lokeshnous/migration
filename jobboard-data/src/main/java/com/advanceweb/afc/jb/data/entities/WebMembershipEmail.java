/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the WebMembershipInfo database table.
 * 
 */
@Entity
@Table(name="WebMembershipEmail")
public class WebMembershipEmail implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The web membership email id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WebMembershipEmailID")
	private Integer webMembershipEmailID;

//	@Column(name="WebMembershipID")
//	private int webMembershipID;
//	
	/** The web membership. */
@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WebMembershipID")
	private WebMembership webMembership;

	/** The email. */
	@Column(name="Email")
	private String email;

	/** The create date. */
	@Column(name="CreateDate")
	private Date createDate;

	/** The primary email. */
	@Column(name="PrimaryEmail")
	private boolean primaryEmail;
	
	/** The welcome e mail history id. */
	@Column(name="WelcomeEMailHistoryID")
	private Integer welcomeEMailHistoryID;
	
	/** The delete date. */
	@Column(name="DeleteDate")
	private Date deleteDate;

	/** The email status id. */
	@Column(name="EmailStatusID")
	private Integer emailStatusID;

	/** The modify date. */
	@Column(name="ModifyDate")
	private Date modifyDate;
	
	/** The create web user id. */
	@Column(name="CreateWebUserId")
	private Integer createWebUserId;
	
	/** The modify web user id. */
	@Column(name="ModifyWebUserId")
	private Integer modifyWebUserId;
	
	/** The delete web user id. */
	@Column(name="DeleteWebUserId")
	private Integer deleteWebUserId;
	
	/**
	 * Gets the web membership email id.
	 *
	 * @return the web membership email id
	 */
	public Integer getWebMembershipEmailID() {
		return webMembershipEmailID;
	}
	
	/**
	 * Sets the web membership email id.
	 *
	 * @param webMembershipEmailID the new web membership email id
	 */
	public void setWebMembershipEmailID(Integer webMembershipEmailID) {
		this.webMembershipEmailID = webMembershipEmailID;
	}
	
	/**
	 * Gets the web membership.
	 *
	 * @return the web membership
	 */
	public WebMembership getWebMembership() {
		return webMembership;
	}
	
	/**
	 * Sets the web membership.
	 *
	 * @param webMembership the new web membership
	 */
	public void setWebMembership(WebMembership webMembership) {
		this.webMembership = webMembership;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}
	
	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * Checks if is primary email.
	 *
	 * @return true, if is primary email
	 */
	public boolean isPrimaryEmail() {
		return primaryEmail;
	}
	
	/**
	 * Sets the primary email.
	 *
	 * @param primaryEmail the new primary email
	 */
	public void setPrimaryEmail(boolean primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	
	/**
	 * Gets the welcome e mail history id.
	 *
	 * @return the welcome e mail history id
	 */
	public Integer getWelcomeEMailHistoryID() {
		return welcomeEMailHistoryID;
	}
	
	/**
	 * Sets the welcome e mail history id.
	 *
	 * @param welcomeEMailHistoryID the new welcome e mail history id
	 */
	public void setWelcomeEMailHistoryID(Integer welcomeEMailHistoryID) {
		this.welcomeEMailHistoryID = welcomeEMailHistoryID;
	}
	
	/**
	 * Gets the delete date.
	 *
	 * @return the delete date
	 */
	public Date getDeleteDate() {
		return deleteDate;
	}
	
	/**
	 * Sets the delete date.
	 *
	 * @param deleteDate the new delete date
	 */
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	/**
	 * Gets the email status id.
	 *
	 * @return the email status id
	 */
	public int getEmailStatusID() {
		return emailStatusID;
	}
	
	/**
	 * Sets the email status id.
	 *
	 * @param emailStatusID the new email status id
	 */
	public void setEmailStatusID(int emailStatusID) {
		this.emailStatusID = emailStatusID;
	}
	
	/**
	 * Gets the modify date.
	 *
	 * @return the modify date
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	
	/**
	 * Sets the modify date.
	 *
	 * @param modifyDate the new modify date
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * Gets the creates the web user id.
	 *
	 * @return the creates the web user id
	 */
	public Integer getCreateWebUserId() {
		return createWebUserId;
	}
	
	/**
	 * Sets the creates the web user id.
	 *
	 * @param createWebUserId the new creates the web user id
	 */
	public void setCreateWebUserId(Integer createWebUserId) {
		this.createWebUserId = createWebUserId;
	}
	
	/**
	 * Gets the modify web user id.
	 *
	 * @return the modify web user id
	 */
	public Integer getModifyWebUserId() {
		return modifyWebUserId;
	}
	
	/**
	 * Sets the modify web user id.
	 *
	 * @param modifyWebUserId the new modify web user id
	 */
	public void setModifyWebUserId(Integer modifyWebUserId) {
		this.modifyWebUserId = modifyWebUserId;
	}
	
	/**
	 * Gets the delete web user id.
	 *
	 * @return the delete web user id
	 */
	public Integer getDeleteWebUserId() {
		return deleteWebUserId;
	}
	
	/**
	 * Sets the delete web user id.
	 *
	 * @param deleteWebUserId the new delete web user id
	 */
	public void setDeleteWebUserId(Integer deleteWebUserId) {
		this.deleteWebUserId = deleteWebUserId;
	}


}