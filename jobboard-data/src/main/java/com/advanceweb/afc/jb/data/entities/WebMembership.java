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
@Table(name="WebMembership")
public class WebMembership implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WebMembershipID ")
	private int webMembershipID;

	@Column(name="Password")
	private String password;

	@Column(name="Salt")
	private String salt;

	@Column(name="AddressInfoID")
	private int addressInfoID;

	@Column(name="SubRequestID")
	private int subRequestID;

	@Column(name="WebMembershipInfoID")
	private int WebMembershipInfoID;

	@Column(name="Activated")
	private boolean activated;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="WebMembershipLevelID")
	private int webMembershipLevelID;

	@Column(name="DisplayName")
	private String displayName;

	@Column(name="Deleted")
	private boolean deleted;

	@Column(name="obs_ACRUserID")
	private int obsACRUserID;
	@Column(name="MerionEmployeeID")
	private int merionEmployeeID;
	
	@Column(name="MyAdvanceID")
	private int myAdvanceID;
	@Column(name="EncryptPassword")
	private String encryptPassword;
	@Column(name="ClientEmployeeID")
	private int clientEmployeeID;
	@Column(name="CreateWebUserId")
	private int createWebUserId;
	@Column(name="ModifyWebUserId")
	private int modifyWebUserId;
	@Column(name="ModifyDate")
	private Timestamp modifyDate;
	@Column(name="DeleteWebUserId")
	private int deleteWebUserId;
	@Column(name="DeleteDate")
	private int deleteDate;
	@Column(name="ShopSalt")
	private String ShopSalt;
	@Column(name="ShopEncryptPassword")
	private String shopEncryptPassword;
	
	    public WebMembership() {
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
		public int getAddressInfoID() {
			return addressInfoID;
		}

		/**
		 * @param addressInfoID the addressInfoID to set
		 */
		public void setAddressInfoID(int addressInfoID) {
			this.addressInfoID = addressInfoID;
		}

		/**
		 * @return the subRequestID
		 */
		public int getSubRequestID() {
			return subRequestID;
		}

		/**
		 * @param subRequestID the subRequestID to set
		 */
		public void setSubRequestID(int subRequestID) {
			this.subRequestID = subRequestID;
		}

		/**
		 * @return the webMembershipInfoID
		 */
		public int getWebMembershipInfoID() {
			return WebMembershipInfoID;
		}

		/**
		 * @param webMembershipInfoID the webMembershipInfoID to set
		 */
		public void setWebMembershipInfoID(int webMembershipInfoID) {
			WebMembershipInfoID = webMembershipInfoID;
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
		public int getMerionEmployeeID() {
			return merionEmployeeID;
		}

		/**
		 * @param merionEmployeeID the merionEmployeeID to set
		 */
		public void setMerionEmployeeID(int merionEmployeeID) {
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
		public int getClientEmployeeID() {
			return clientEmployeeID;
		}

		/**
		 * @param clientEmployeeID the clientEmployeeID to set
		 */
		public void setClientEmployeeID(int clientEmployeeID) {
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
		 * @return the deleteDate
		 */
		public int getDeleteDate() {
			return deleteDate;
		}

		/**
		 * @param deleteDate the deleteDate to set
		 */
		public void setDeleteDate(int deleteDate) {
			this.deleteDate = deleteDate;
		}

		/**
		 * @return the shopSalt
		 */
		public String getShopSalt() {
			return ShopSalt;
		}

		/**
		 * @param shopSalt the shopSalt to set
		 */
		public void setShopSalt(String shopSalt) {
			ShopSalt = shopSalt;
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

	

}