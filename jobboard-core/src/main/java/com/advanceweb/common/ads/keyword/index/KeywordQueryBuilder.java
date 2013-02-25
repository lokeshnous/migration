/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.keyword.index;

import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Component;

import com.advanceweb.common.index.lucene.LuceneQueryBuilder;

/**
 * Keyword query builder is used to build lucene queries for searching for
 * keywords. For providing a better result based on the user input, this
 * implementation uses the FuzzyQuery definition in lucene
 * 
 * @author sukeshnambiar
 * 
 */
@Component
public class KeywordQueryBuilder implements LuceneQueryBuilder {

	/** The Constant KEYWORD_FIELD_BOOST. */
	private static final float KEYWORD_FIELD_BOOST = 1.25F;

	/**
	 * Creates a query based on the input. The params passed to this method is
	 * expected to have the search keyword with a key <code> keyword</code>
	 * 
	 * The search query will split the kewords based on the white space
	 * character and create multiple search queries. This is done to avoid the
	 * dependency on the order of the keywords.
	 */
	@Override
	public Query buildQuery(Map<String, String> params) {
		BooleanQuery query = new BooleanQuery();
		String queryString = params.get("keyword");
		for (String str : queryString.toLowerCase().split(" ")) {
			FuzzyQuery nameQuery = new FuzzyQuery(new Term(
					KeywordIndex.KEYWORD_FIELD, str));
			nameQuery.setBoost(KEYWORD_FIELD_BOOST);
			query.add(nameQuery, Occur.SHOULD);
			query.add(
					new FuzzyQuery(new Term(KeywordIndex.RELATED_FIELD, str)),
					Occur.SHOULD);
		}
		return query;
	}
}
