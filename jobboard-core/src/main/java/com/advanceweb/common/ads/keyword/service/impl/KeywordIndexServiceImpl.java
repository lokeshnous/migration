/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.keyword.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.ContentTopic;
import com.advanceweb.common.ads.keyword.index.KeywordIndex;
import com.advanceweb.common.ads.keyword.service.KeywordIndexService;
import com.advanceweb.common.index.lucene.LuceneIndex;
import com.advanceweb.common.index.lucene.LuceneResult;

/**
 * This class provide the implementation of the KeywordIndex using the
 * Luceneindex. The index used for is injected into this clasd
 * 
 * @author sukeshnambiar
 * 
 */
@Service
public class KeywordIndexServiceImpl implements KeywordIndexService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(KeywordIndexService.class);

	/** The keyword index. */
	@Autowired
	private LuceneIndex keywordIndex;

	/**
	 * Find the best match using the lucene query. This method performs a search
	 * used by finding all matches and returns the first item. This is
	 * appropriate as the lucene search results are always ordered with the best
	 * match first.
	 * 
	 * @see com.advanceweb.common.index.KeywordIndexService#findBestMatch(java.lang
	 *      .String)
	 */
	@Override
	public ContentTopic findBestMatch(String pattern)
			throws JobBoardServiceException {
		LOGGER.debug("Finding best match for " + pattern);
		List<LuceneResult> docs = keywordIndex.search(buildParamMap(pattern));
		ContentTopic result = null;
		if (!docs.isEmpty()) {
			Document doc = docs.get(0).getDocument();
			result = createContentTopic(doc);
		}
		return result;
	}

	/**
	 * Finds all the matches for the given pattern using the lucene index.
	 * 
	 * @see com.advanceweb.common.index.KeywordIndexService#findMatches(java.lang
	 *      .String)
	 */
	@Override
	public List<ContentTopic> findMatches(String pattern)
			throws JobBoardServiceException {
		LOGGER.debug("Finding all matches for " + pattern);
		List<LuceneResult> docList = keywordIndex
				.search(buildParamMap(pattern));
		List<ContentTopic> result = new ArrayList<ContentTopic>();
		for (LuceneResult doc : docList) {
			result.add(createContentTopic(doc.getDocument()));
		}
		return result;

	}

	/**
	 * Builds the param map.
	 *
	 * @param search the search
	 * @return the map
	 */
	private Map<String, String> buildParamMap(String search) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("keyword", search);
		return params;
	}

	/**
	 * Creates the content topic.
	 *
	 * @param doc the doc
	 * @return the content topic
	 */
	private ContentTopic createContentTopic(Document doc) {
		String text = doc.getFieldable(KeywordIndex.KEYWORD_FIELD)
				.stringValue();
		int id = Integer.valueOf(doc.getFieldable(KeywordIndex.TOPIC_ID_FIELD)
				.stringValue());

		return new ContentTopic(id, text);
	}
}
