package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jp_template_testimonial database table.
 * 
 */
@Entity
@Table(name="jp_template_testimonial")
public class JpTemplateTestimonial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="template_testimonial_id")
	private int templateTestimonialId;

    @Lob()
	private String testimonial;

	//bi-directional many-to-one association to JpTemplate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template_id")
	private JpTemplate jpTemplate;

    public JpTemplateTestimonial() {
    }

	public int getTemplateTestimonialId() {
		return this.templateTestimonialId;
	}

	public void setTemplateTestimonialId(int templateTestimonialId) {
		this.templateTestimonialId = templateTestimonialId;
	}

	public String getTestimonial() {
		return this.testimonial;
	}

	public void setTestimonial(String testimonial) {
		this.testimonial = testimonial;
	}

	public JpTemplate getJpTemplate() {
		return this.jpTemplate;
	}

	public void setJpTemplate(JpTemplate jpTemplate) {
		this.jpTemplate = jpTemplate;
	}
	
}