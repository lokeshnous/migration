package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the res_builder_attrib_list database table.
 * 
 */
@Entity
@Table(name="res_builder_attrib_list")
public class ResBuilderAttribList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="builder_attrib_list_id")
	private int builderAttribListId;

	@Column(name="attrib_type")
	private String attribType;

	@Column(name="attrib_value")
	private String attribValue;

	private int position;

	public int getBuilderAttribListId() {
		return this.builderAttribListId;
	}

	public void setBuilderAttribListId(int builderAttribListId) {
		this.builderAttribListId = builderAttribListId;
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