package com.advanceweb.afc.jb.search;

import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.XmlResumeParser;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:23:53 PM
 */
@Service
public class ResumeSearchAdapter implements ResumeSearch {

	public SolrAdapter m_SolrAdapter;
	public XmlResumeParser m_XmlResumeParser;

	public ResumeSearchAdapter() {

	}

	@Override
	public void finalize() throws Throwable {

	}

}