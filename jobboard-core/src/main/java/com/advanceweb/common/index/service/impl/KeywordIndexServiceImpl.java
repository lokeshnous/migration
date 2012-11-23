package com.advanceweb.common.index.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Fieldable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.lucene.LuceneKeywordIndex;
import com.advanceweb.common.index.lucene.LuceneResult;
import com.advanceweb.common.index.service.KeywordIndexService;

@Service("keywordIndexService")
public class KeywordIndexServiceImpl implements KeywordIndexService {
	
	private static final Logger LOGGER = Logger.getLogger(KeywordIndexService.class);

	@Autowired
	LuceneKeywordIndex luceneKeywordIndex;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.advanceweb.common.index.KeywordIndexService#findBestMatch(java.lang
	 * .String)
	 */
	@Override
	public String findBestMatch(String search) throws JobBoardServiceException {
		List<LuceneResult> docs = luceneKeywordIndex.search(search);
		String result = null;
		if (!docs.isEmpty()) {
			Document doc = docs.get(0).getDocument();
			Fieldable field = (Field) doc.getFieldable(LuceneKeywordIndex.FIELD_KEYWORD);
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
	public List<String> findMatches(String search)
			throws JobBoardServiceException {
		List<LuceneResult> docList = luceneKeywordIndex.search(search);
		List<String> result = new ArrayList<String>();
		for (LuceneResult doc : docList) {
			String keyword = doc.getDocument().getFieldable(LuceneKeywordIndex.FIELD_KEYWORD)
					.stringValue();
			LOGGER.debug("Keyword: " + keyword + ",\tScore: " + doc.getScore());
			result.add(keyword);
		}
		return result;

	}
}
