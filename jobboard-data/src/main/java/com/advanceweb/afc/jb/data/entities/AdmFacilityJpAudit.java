package com.advanceweb.afc.jb.data.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_facility_jp_audit database table.
 * 
 */
@Entity
@Table(name="adm_facility_jp_audit")
public class AdmFacilityJpAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdmFacilityJpAuditPK id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;
    
// Commented unused constructor
//    public AdmFacilityJpAudit() {
//    }

	public AdmFacilityJpAuditPK getId() {
		return this.id;
	}

	public void setId(AdmFacilityJpAuditPK id) {
		this.id = id;
	}
	
	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

}