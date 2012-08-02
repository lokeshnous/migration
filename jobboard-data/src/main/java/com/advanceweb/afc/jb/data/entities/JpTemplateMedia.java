package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jp_template_media database table.
 * 
 */
@Entity
@Table(name="jp_template_media")
public class JpTemplateMedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="template_media_id")
	private int templateMediaId;

	@Column(name="media_path")
	private String mediaPath;

	@Column(name="media_type")
	private String mediaType;

	//bi-directional many-to-one association to JpTemplate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template_id")
	private JpTemplate jpTemplate;

    public JpTemplateMedia() {
    }

	public int getTemplateMediaId() {
		return this.templateMediaId;
	}

	public void setTemplateMediaId(int templateMediaId) {
		this.templateMediaId = templateMediaId;
	}

	public String getMediaPath() {
		return this.mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public JpTemplate getJpTemplate() {
		return this.jpTemplate;
	}

	public void setJpTemplate(JpTemplate jpTemplate) {
		this.jpTemplate = jpTemplate;
	}
	
}