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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WebMembershipEmailID")
	private Integer webMembershipEmailID;

//	@Column(name="WebMembershipID")
//	private int webMembershipID;
//	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WebMembershipID")
	private WebMembership webMembership;

	@Column(name="Email")
	private String email;

	@Column(name="CreateDate")
	private Date createDate;

	@Column(name="PrimaryEmail")
	private boolean primaryEmail;
	@Column(name="WelcomeEMailHistoryID")
	private Integer welcomeEMailHistoryID;
	
	@Column(name="DeleteDate")
	private Date deleteDate;

	@Column(name="EmailStatusID")
	private Integer emailStatusID;

	@Column(name="ModifyDate")
	private Date modifyDate;
	
	@Column(name="CreateWebUserId")
	private Integer createWebUserId;
	@Column(name="ModifyWebUserId")
	private Integer modifyWebUserId;
	@Column(name="DeleteWebUserId")
	private Integer deleteWebUserId;
	public Integer getWebMembershipEmailID() {
		return webMembershipEmailID;
	}
	public void setWebMembershipEmailID(Integer webMembershipEmailID) {
		this.webMembershipEmailID = webMembershipEmailID;
	}
	public WebMembership getWebMembership() {
		return webMembership;
	}
	public void setWebMembership(WebMembership webMembership) {
		this.webMembership = webMembership;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(boolean primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public Integer getWelcomeEMailHistoryID() {
		return welcomeEMailHistoryID;
	}
	public void setWelcomeEMailHistoryID(Integer welcomeEMailHistoryID) {
		this.welcomeEMailHistoryID = welcomeEMailHistoryID;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public int getEmailStatusID() {
		return emailStatusID;
	}
	public void setEmailStatusID(int emailStatusID) {
		this.emailStatusID = emailStatusID;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getCreateWebUserId() {
		return createWebUserId;
	}
	public void setCreateWebUserId(Integer createWebUserId) {
		this.createWebUserId = createWebUserId;
	}
	public Integer getModifyWebUserId() {
		return modifyWebUserId;
	}
	public void setModifyWebUserId(Integer modifyWebUserId) {
		this.modifyWebUserId = modifyWebUserId;
	}
	public Integer getDeleteWebUserId() {
		return deleteWebUserId;
	}
	public void setDeleteWebUserId(Integer deleteWebUserId) {
		this.deleteWebUserId = deleteWebUserId;
	}


}