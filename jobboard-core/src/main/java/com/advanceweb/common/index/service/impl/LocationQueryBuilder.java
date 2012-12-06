package com.advanceweb.common.index.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.spatial.tier.DistanceQueryBuilder;

import com.advanceweb.common.index.lucene.LuceneQueryBuilder;

@SuppressWarnings("deprecation")
public class LocationQueryBuilder implements LuceneQueryBuilder {
	private static final Logger LOGGER = Logger
			.getLogger(LocationQueryBuilder.class);

	@Override
	public Query buildQuery(Map<String, String> params) {
		if (params.containsKey("radius")) {
			return buildRadiusQuery(params);
		} else {
			return buildNameQuery(params);
		}
	}

	private Query buildRadiusQuery(Map<String, String> params) {
		Double latitude = Double.parseDouble(params.get("latitude"));
		Double longitude = Double.parseDouble(params.get("longitude"));
		Double radius = Double.parseDouble(params.get("radius"));
		DistanceQueryBuilder dqb = new DistanceQueryBuilder(latitude,
				longitude, radius, "lat", "lon", "tier_", true, 15, 12);
		Query query = new MatchAllDocsQuery();
		return dqb.getQuery(query);
	}

	private Query buildNameQuery(Map<String, String> params) {
		BooleanQuery query = new BooleanQuery();

		BooleanQuery cityQuery = new BooleanQuery();
		BooleanQuery stateQuery = new BooleanQuery();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toLowerCase();

			if (key.equals("state")) {
				stateQuery.add(createPhraseQuery("state", value), Occur.SHOULD);
				stateQuery.add(createPhraseQuery("state_fullname", value),
						Occur.SHOULD);

			} else if (entry.getKey().equals("city")) {
				cityQuery.add(createPhraseQuery("city", value), Occur.SHOULD);
				cityQuery.add(createPhraseQuery("city_alias", value),
						Occur.SHOULD);
			}
		}

		if (params.containsKey("state")) {
			query.add(stateQuery, Occur.MUST);
		}
		if (params.containsKey("city")) {
			query.add(cityQuery, Occur.MUST);
		}

		LOGGER.debug("Generated Name query " + query);
		return query;
	}

	private Query createPhraseQuery(String key, String value) {
		PhraseQuery pq = new PhraseQuery();
		for (String token : value.split(" ")) {
			pq.add(new Term(key, token));
		}
		return pq;

	}

}
