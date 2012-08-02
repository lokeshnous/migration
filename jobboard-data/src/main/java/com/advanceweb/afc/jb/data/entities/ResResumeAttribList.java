package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the res_resume_attrib_list database table.
 * 
 */
@Entity
@Table(name="res_resume_attrib_list")
public class ResResumeAttribList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="resume_attrib_list_id")
	private int resumeAttribListId;

	private short active;

	@Column(name="list_value")
	private String listValue;

	private int position;

	//bi-directional many-to-one association to ResResumeAttrib
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resume_attrib_id")
	private ResResumeAttrib resResumeAttrib;

    public ResResumeAttribList() {
    }

	public int getResumeAttribListId() {
		return this.resumeAttribListId;
	}

	public void setResumeAttribListId(int resumeAttribListId) {
		this.resumeAttribListId = resumeAttribListId;
	}

	public short getActive() {
		return this.active;
	}

	public void setActive(short active) {
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

	public ResResumeAttrib getResResumeAttrib() {
		return this.resResumeAttrib;
	}

	public void setResResumeAttrib(ResResumeAttrib resResumeAttrib) {
		this.resResumeAttrib = resResumeAttrib;
	}
	
}