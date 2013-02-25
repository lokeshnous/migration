/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the adm_subscription database table.
 * 
 */
@Entity
@Table(name="adm_subscription")
public class AdmSubscription implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The subscription id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subscription_id")
	private int subscriptionId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	/** The subscription type. */
	@Column(name="subscription_type")
	private String subscriptionType;

	//bi-directional many-to-one association to AdmFacilitySubscription
	/** The adm facility subscriptions. */
	@OneToMany(mappedBy="admSubscription")
	private List<AdmFacilitySubscription> admFacilitySubscriptions;

	//bi-directional many-to-one association to AdmUserSubscription
	/** The adm user subscriptions. */
	@OneToMany(mappedBy="admSubscription")
	private List<AdmUserSubscription> admUserSubscriptions;

	/**
	 * Gets the subscription id.
	 *
	 * @return the subscription id
	 */
	public int getSubscriptionId() {
		return this.subscriptionId;
	}

	/**
	 * Sets the subscription id.
	 *
	 * @param subscriptionId the new subscription id
	 */
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the subscription type.
	 *
	 * @return the subscription type
	 */
	public String getSubscriptionType() {
		return this.subscriptionType;
	}

	/**
	 * Sets the subscription type.
	 *
	 * @param subscriptionType the new subscription type
	 */
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	/**
	 * Gets the adm facility subscriptions.
	 *
	 * @return the adm facility subscriptions
	 */
	public List<AdmFacilitySubscription> getAdmFacilitySubscriptions() {
		return this.admFacilitySubscriptions;
	}

	/**
	 * Sets the adm facility subscriptions.
	 *
	 * @param admFacilitySubscriptions the new adm facility subscriptions
	 */
	public void setAdmFacilitySubscriptions(List<AdmFacilitySubscription> admFacilitySubscriptions) {
		this.admFacilitySubscriptions = admFacilitySubscriptions;
	}
	
	/**
	 * Gets the adm user subscriptions.
	 *
	 * @return the adm user subscriptions
	 */
	public List<AdmUserSubscription> getAdmUserSubscriptions() {
		return this.admUserSubscriptions;
	}

	/**
	 * Sets the adm user subscriptions.
	 *
	 * @param admUserSubscriptions the new adm user subscriptions
	 */
	public void setAdmUserSubscriptions(List<AdmUserSubscription> admUserSubscriptions) {
		this.admUserSubscriptions = admUserSubscriptions;
	}
	
}