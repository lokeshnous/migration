package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mer_profile_attrib_list database table.
 * 
 */
@Entity
@Table(name="mer_profile_attrib_list")
public class MerProfileAttribList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_attrib_list_id")
	private int profileAttribListId;

	private int active;

	@Column(name="list_value")
	private String listValue;

	private int position;

	//bi-directional many-to-one association to MerProfileAttrib
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_attrib_id")
	private MerProfileAttrib merProfileAttrib;

	public int getProfileAttribListId() {
		return this.profileAttribListId;
	}

	public void setProfileAttribListId(int profileAttribListId) {
		this.profileAttribListId = profileAttribListId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getListValue() {
		return this.listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public MerProfileAttrib getMerProfileAttrib() {
		return this.merProfileAttrib;
	}

	public void setMerProfileAttrib(MerProfileAttrib merProfileAttrib) {
		this.merProfileAttrib = merProfileAttrib;
	}
	
}