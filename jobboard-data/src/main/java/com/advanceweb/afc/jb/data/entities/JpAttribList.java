package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jp_attrib_list database table.
 * 
 */
@Entity
@Table(name="jp_attrib_list")
public class JpAttribList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attrib_list_id")
	private int attribListId;

	@Column(name="attrib_type")
	private String attribType;

	@Column(name="attrib_value")
	private String attribValue;

	@Column(name="position")
	private int position;

	public int getAttribListId() {
		return this.attribListId;
	}

	public void setAttribListId(int attribListId) {
		this.attribListId = attribListId;
	}

	public String getAttribType() {
		return this.attribType;
	}

	public void setAttribType(String attribType) {
		this.attribType = attribType;
	}

	public String getAttribValue() {
		return this.attribValue;
	}

	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}