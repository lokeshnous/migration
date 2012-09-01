package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adm_subscription database table.
 * 
 */
@Entity
@Table(name="adm_subscription")
public class AdmSubscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subscription_id")
	private int subscriptionId;

	private String description;

	private String name;

	@Column(name="subscription_type")
	private String subscriptionType;

	//bi-directional many-to-one association to AdmFacilitySubscription
	@OneToMany(mappedBy="admSubscription")
	private List<AdmFacilitySubscription> admFacilitySubscriptions;

	//bi-directional many-to-one association to AdmUserSubscription
	@OneToMany(mappedBy="admSubscription")
	private List<AdmUserSubscription> admUserSubscriptions;

	public int getSubscriptionId() {
		return this.subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubscriptionType() {
		return this.subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public List<AdmFacilitySubscription> getAdmFacilitySubscriptions() {
		return this.admFacilitySubscriptions;
	}

	public void setAdmFacilitySubscriptions(List<AdmFacilitySubscription> admFacilitySubscriptions) {
		this.admFacilitySubscriptions = admFacilitySubscriptions;
	}
	
	public List<AdmUserSubscription> getAdmUserSubscriptions() {
		return this.admUserSubscriptions;
	}

	public void setAdmUserSubscriptions(List<AdmUserSubscription> admUserSubscriptions) {
		this.admUserSubscriptions = admUserSubscriptions;
	}
	
}