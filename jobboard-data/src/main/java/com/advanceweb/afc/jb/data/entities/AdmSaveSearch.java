package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adm_save_search database table.
 * 
 */
@Entity
@Table(name="adm_save_search")
public class AdmSaveSearch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="save_search_id")
	private int saveSearchId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	@Column(name="email_frequency")
	private String emailFrequency;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="modify_dt")
	private Date modifyDt;

	@Column(name="search_name")
	private String searchName;

	private String url;

	@Column(name="user_id")
	private int userId;

	public int getSaveSearchId() {
		return this.saveSearchId;
	}

	public void setSaveSearchId(int saveSearchId) {
		this.saveSearchId = saveSearchId;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public String getEmailFrequency() {
		return this.emailFrequency;
	}

	public void setEmailFrequency(String emailFrequency) {
		this.emailFrequency = emailFrequency;
	}

	public Date getModifyDt() {
		return this.modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	public String getSearchName() {
		return this.searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}