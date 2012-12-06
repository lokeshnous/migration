package com.advanceweb.common.index.service.impl;

import java.io.IOException;
import java.util.List;

import jxl.common.Logger;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
import org.apache.lucene.spatial.tier.projections.IProjector;
import org.apache.lucene.spatial.tier.projections.SinusoidalProjector;
import org.apache.lucene.util.NumericUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.search.dao.LocationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.index.lucene.LuceneIndexer;

public class LocationIndexer implements LuceneIndexer {

	private static final Logger LOGGER = Logger
			.getLogger(LocationIndexer.class);

	private static final String LATITIDE_FIELD = "lat";
	private static final String LONGITUDE_FIELD = "lon";
	private static final String TIER_PREFIX_FIELD = "tier_";
	private static final int MAX_DISTANCE = 100;
	private static final int MIN_DISTANCE = 1;

	@Autowired
	LocationDAO locationDao;

	@Override
	public void index(IndexWriter indexWriter) throws JobBoardServiceException {
		try {
			List<LocationDTO> locationList = locationDao.findAll();
			for (LocationDTO location : locationList) {
				if (location.getLatitude() != null
						&& location.getLongitude() != null) {
					indexWriter.addDocument(createDocument(location));
				}
			}
			indexWriter.commit();
			LOGGER.debug("Finished Indexing  " + locationList.size()
					+ " Locations");
		} catch (CorruptIndexException ex) {
			LOGGER.error("Error indexing Locations ", ex);
			throw new JobBoardServiceException(ex.getMessage());
		} catch (IOException ex) {
			LOGGER.error("Error indexing Locations ", ex);
			throw new JobBoardServiceException(ex.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	private Document createDocument(LocationDTO location) {
		Document document = new Document();
		document.add(new Field("id", Integer.toString(location.getId()),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		document.add(new Field("city", location.getCity(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		document.add(new Field("city_alias", location.getCityAlias(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		document.add(new Field("state", location.getState(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		document.add(new Field("state", location.getStateFullName(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		document.add(new Field("postcode", location.getPostcode(),
				Field.Store.YES, Field.Index.ANALYZED));

		// Add the spatial fields ref
		// http://www.mhaller.de/archives/156-Spatial-search-with-Lucene.html
		document.add(new Field(LATITIDE_FIELD, NumericUtils
				.doubleToPrefixCoded(location.getLatitude()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		document.add(new Field(LONGITUDE_FIELD, NumericUtils
				.doubleToPrefixCoded(location.getLongitude()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));

		// Add the tiers
		IProjector projector = new SinusoidalProjector();
		CartesianTierPlotter plotter = new CartesianTierPlotter(0, projector,
				TIER_PREFIX_FIELD);
		int endTier = plotter.bestFit(MIN_DISTANCE);
		int startTier = plotter.bestFit(MAX_DISTANCE);
		for (int tier = startTier; tier <= endTier; tier += MIN_DISTANCE) {
			plotter = new CartesianTierPlotter(tier, projector,
					TIER_PREFIX_FIELD);
			double boxId = plotter.getTierBoxId(location.getLatitude(),
					location.getLongitude());
			document.add(new Field(plotter.getTierFieldName(), NumericUtils
					.doubleToPrefixCoded(boxId), Field.Store.YES,
					Field.Index.NOT_ANALYZED_NO_NORMS));
		}

		return document;
	}
}
