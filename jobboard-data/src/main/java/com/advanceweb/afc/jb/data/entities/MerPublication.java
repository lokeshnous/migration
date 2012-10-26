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
 * The persistent class for the mer_user database table.
 * 
 */
@Entity
@Table(name = "mer_publication")
public class MerPublication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "publication_id")
	private int publicationId;

	@Column(name = "publication_code")
	private String publicationCode;

	@Column(name = "publication_name")
	private String publicationName;

	@Column(name = "publication_description")
	private String publicationdesc;

	@Column(name = "is_print")
	private int isPrint;

	@Column(name = "is_digital")
	private int isDigital;

	@Column(name = "is_enewsletter")
	private int isEnewsletter;

	@Column(name = "is_regional")
	private int isRegional;

	@Column(name = "active")
	private int active;

	@Column(name = "create_dt")
	private Timestamp createDt;

	public int getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	public String getPublicationCode() {
		return publicationCode;
	}

	public void setPublicationCode(String publicationCode) {
		this.publicationCode = publicationCode;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public String getPublicationdesc() {
		return publicationdesc;
	}

	public void setPublicationdesc(String publicationdesc) {
		this.publicationdesc = publicationdesc;
	}

	public int getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(int isPrint) {
		this.isPrint = isPrint;
	}

	public int getIsDigital() {
		return isDigital;
	}

	public void setIsDigital(int isDigital) {
		this.isDigital = isDigital;
	}

	public int getIsEnewsletter() {
		return isEnewsletter;
	}

	public void setIsEnewsletter(int isEnewsletter) {
		this.isEnewsletter = isEnewsletter;
	}

	public int getIsRegional() {
		return isRegional;
	}

	public void setIsRegional(int isRegional) {
		this.isRegional = isRegional;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
