package com.advanceweb.afc.jb.data.entities;



import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

    public AdmFacilityJpAudit() {
    }

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