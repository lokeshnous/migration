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
@Table(name="WebMembership")
public class WebMembership implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The web membership id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WebMembershipID")
	private Integer webMembershipID;

	/** The password. */
	@Column(name="Password")
	private String password;

	/** The salt. */
	@Column(name="Salt")
	private String salt;

	/** The address info id. */
	@Column(name="AddressInfoID")
//	private Integer addressInfoID;
	private String addressInfoID;
	
	/** The sub request id. */
	@Column(name="SubRequestID")
//	private Integer subRequestID;
	private String subRequestID;
//	@Column(name="WebMembershipInfoID")
//	private int WebMembershipInfoID;
	
	/** The web membership email. */
@OneToOne(fetch=FetchType.EAGER, mappedBy="webMembership")
	private WebMembershipEmail webMembershipEmail;
	
	/** The web membership info. */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WebMembershipInfoID")
	private WebMembershipInfo webMembershipInfo;
	
	/** The activated. */
	@Column(name="Activated")
	private boolean activated;

	/** The create date. */
	@Column(name="CreateDate")
	private Date createDate;

	/** The web membership level id. */
	@Column(name="WebMembershipLevelID")
	private Integer webMembershipLevelID;

	/** The display name. */
	@Column(name="DisplayName")
	private String displayName;

	/** The deleted. */
	@Column(name="Deleted")
	private boolean deleted;

	/** The obs acr user id. */
	@Column(name="obs_ACRUserID")
	private Integer obsACRUserID;
	
	/** The merion employee id. */
	@Column(name="MerionEmployeeID")
//	private Integer merionEmployeeID;
	private String merionEmployeeID;
	
	/** The my advance id. */
	@Column(name="MyAdvanceID")
	private Integer myAdvanceID;
	
	/** The encrypt password. */
	@Column(name="EncryptPassword")
	private String encryptPassword;
	
	/** The client employee id. */
	@Column(name="ClientEmployeeID")
//	private Integer clientEmployeeID;
	private String clientEmployeeID;
	
	/** The create web user id. */
	@Column(name="CreateWebUserId")
	private Integer createWebUserId;
	
	/** The modify web user id. */
	@Column(name="ModifyWebUserId")
	private Integer modifyWebUserId;
	
	/** The modify date. */
	@Column(name="ModifyDate")
	private Date modifyDate;
	
	/** The delete web user id. */
	@Column(name="DeleteWebUserId")
	private Integer deleteWebUserId;
	
	/** The delete date. */
	@Column(name="DeleteDate")
	private Date deleteDate;
	
	/** The shop salt. */
	@Column(name="ShopSalt")
	private String shopSalt;
	
	/** The shop encrypt password. */
	@Column(name="ShopEncryptPassword")
	private String shopEncryptPassword;
	

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
		 * @return the webMembershipInfo
		 */
		public WebMembershipInfo getWebMembershipInfo() {
			return webMembershipInfo;
		}

		/**
		 * @param webMembershipInfo the webMembershipInfo to set
		 */
		public void setWebMembershipInfo(WebMembershipInfo webMembershipInfo) {
			this.webMembershipInfo = webMembershipInfo;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the salt
		 */
		public String getSalt() {
			return salt;
		}

		/**
		 * @param salt the salt to set
		 */
		public void setSalt(String salt) {
			this.salt = salt;
		}

		/**
		 * @return the addressInfoID
		 */
		public String getAddressInfoID() {
			return addressInfoID;
		}

		/**
		 * @param addressInfoID the addressInfoID to set
		 */
		public void setAddressInfoID(String addressInfoID) {
			this.addressInfoID = addressInfoID;
		}

		/**
		 * @return the subRequestID
		 */
		public String getSubRequestID() {
			return subRequestID;
		}

		/**
		 * @param subRequestID the subRequestID to set
		 */
		public void setSubRequestID(String subRequestID) {
			this.subRequestID = subRequestID;
		}


		/**
		 * @return the webMembershipEmail
		 */
		public WebMembershipEmail getWebMembershipEmail() {
			return webMembershipEmail;
		}

		/**
		 * @param webMembershipEmail the webMembershipEmail to set
		 */
		public void setWebMembershipEmail(WebMembershipEmail webMembershipEmail) {
			this.webMembershipEmail = webMembershipEmail;
		}

		/**
		 * @return the activated
		 */
		public boolean isActivated() {
			return activated;
		}

		/**
		 * @param activated the activated to set
		 */
		public void setActivated(boolean activated) {
			this.activated = activated;
		}

		/**
		 * @return the webMembershipLevelID
		 */
		public int getWebMembershipLevelID() {
			return webMembershipLevelID;
		}

		/**
		 * @param webMembershipLevelID the webMembershipLevelID to set
		 */
		public void setWebMembershipLevelID(int webMembershipLevelID) {
			this.webMembershipLevelID = webMembershipLevelID;
		}

		/**
		 * @return the displayName
		 */
		public String getDisplayName() {
			return displayName;
		}

		/**
		 * @param displayName the displayName to set
		 */
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		/**
		 * @return the deleted
		 */
		public boolean isDeleted() {
			return deleted;
		}

		/**
		 * @param deleted the deleted to set
		 */
		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}

		/**
		 * @return the obsACRUserID
		 */
		public int getObsACRUserID() {
			return obsACRUserID;
		}

		/**
		 * @param obsACRUserID the obsACRUserID to set
		 */
		public void setObsACRUserID(int obsACRUserID) {
			this.obsACRUserID = obsACRUserID;
		}

		/**
		 * @return the merionEmployeeID
		 */
		public String getMerionEmployeeID() {
			return merionEmployeeID;
		}

		/**
		 * @param merionEmployeeID the merionEmployeeID to set
		 */
		public void setMerionEmployeeID(String merionEmployeeID) {
			this.merionEmployeeID = merionEmployeeID;
		}

		/**
		 * @return the myAdvanceID
		 */
		public int getMyAdvanceID() {
			return myAdvanceID;
		}

		/**
		 * @param myAdvanceID the myAdvanceID to set
		 */
		public void setMyAdvanceID(int myAdvanceID) {
			this.myAdvanceID = myAdvanceID;
		}

		/**
		 * @return the encryptPassword
		 */
		public String getEncryptPassword() {
			return encryptPassword;
		}

		/**
		 * @param encryptPassword the encryptPassword to set
		 */
		public void setEncryptPassword(String encryptPassword) {
			this.encryptPassword = encryptPassword;
		}

		/**
		 * @return the clientEmployeeID
		 */
		public String getClientEmployeeID() {
			return clientEmployeeID;
		}

		/**
		 * @param clientEmployeeID the clientEmployeeID to set
		 */
		public void setClientEmployeeID(String clientEmployeeID) {
			this.clientEmployeeID = clientEmployeeID;
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
		 * @return the shopSalt
		 */
		public String getShopSalt() {
			return shopSalt;
		}

		/**
		 * @param shopSalt the shopSalt to set
		 */
		public void setShopSalt(String shopSalt) {
			this.shopSalt = shopSalt;
		}

		/**
		 * @return the shopEncryptPassword
		 */
		public String getShopEncryptPassword() {
			return shopEncryptPassword;
		}

		/**
		 * @param shopEncryptPassword the shopEncryptPassword to set
		 */
		public void setShopEncryptPassword(String shopEncryptPassword) {
			this.shopEncryptPassword = shopEncryptPassword;
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

}