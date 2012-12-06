package com.advanceweb.common.index.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.lucene.LuceneIndex;
import com.advanceweb.common.index.lucene.LuceneResult;
import com.advanceweb.common.index.service.KeywordIndexService;

@Service
public class KeywordIndexServiceImpl implements KeywordIndexService {

	private static final Logger LOGGER = Logger
			.getLogger(KeywordIndexService.class);

	@Autowired
	private LuceneIndex keywordIndex;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.advanceweb.common.index.KeywordIndexService#findBestMatch(java.lang
	 * .String)
	 */
	@Override
	public String findBestMatch(String pattern) throws JobBoardServiceException {
		LOGGER.debug("Finding best match for " + pattern);
		List<LuceneResult> docs = keywordIndex.search(buildParamMap(pattern));
		String result = null;
		if (!docs.isEmpty()) {
			Document doc = docs.get(0).getDocument();
			Fieldable field = (Field) doc
					.getFieldable(LuceneIndex.FIELD_KEYWORD);
			result = field.stringValue();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.advanceweb.common.index.KeywordIndexService#findMatches(java.lang
	 * .String)
	 */
	@Override
	public List<String> findMatches(String pattern)
			throws JobBoardServiceException {
		LOGGER.debug("Finding all matches for " + pattern);
		List<LuceneResult> docList = keywordIndex
				.search(buildParamMap(pattern));
		List<String> result = new ArrayList<String>();
		for (LuceneResult doc : docList) {
			String keyword = doc.getDocument()
					.getFieldable(LuceneIndex.FIELD_KEYWORD).stringValue();
			LOGGER.debug("Keyword: " + keyword + ",\tScore: " + doc.getScore());
			result.add(keyword);
		}
		return result;

	}

	private Map<String, String> buildParamMap(String search) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("keyword", search);
		return params;
	}
}
