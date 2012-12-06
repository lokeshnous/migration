package com.advanceweb.common.index.service.impl;

import java.util.Map;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;
import org.springframework.stereotype.Component;

import com.advanceweb.common.index.lucene.LuceneIndex;
import com.advanceweb.common.index.lucene.LuceneQueryBuilder;

@Component
public class KeywordQueryBuilder implements LuceneQueryBuilder {

	private static final float KEYWORD_FIELD_BOOST = 1.25F;

	@Override
	public Query buildQuery(Map<String,String> params) {
		BooleanQuery query = new BooleanQuery();
		String queryString = params.get("keyword");
		for (String str : queryString.toLowerCase().split(" ")) {
			FuzzyQuery nameQuery = new FuzzyQuery(new Term(LuceneIndex.FIELD_KEYWORD, str));
			nameQuery.setBoost(KEYWORD_FIELD_BOOST);
			query.add(nameQuery, Occur.SHOULD);
			query.add(new FuzzyQuery(new Term(LuceneIndex.FIELD_RELATED, str)),
					Occur.SHOULD);
		}
		return query;
	}
}
