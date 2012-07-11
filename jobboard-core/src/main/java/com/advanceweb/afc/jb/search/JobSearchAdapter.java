package com.advanceweb.afc.jb.search;

import java.util.List;

import com.advanceweb.afc.jb.common.JobSeekerAdvanceSearchDTO;
import com.advanceweb.afc.jb.common.XmlResumeParser;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:23:54 PM
 */
public class JobSearchAdapter implements JobSearch {

	public SolrAdapter m_SolrAdapter;
	public XmlResumeParser m_XmlResumeParser;

	public JobSearchAdapter() {

	}

	@Override
	public void finalize() throws Throwable {

	}


}