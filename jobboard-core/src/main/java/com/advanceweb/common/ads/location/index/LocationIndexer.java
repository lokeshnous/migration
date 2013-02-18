package com.advanceweb.common.ads.location.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
import org.apache.lucene.spatial.tier.projections.IProjector;
import org.apache.lucene.spatial.tier.projections.SinusoidalProjector;
import org.apache.lucene.util.NumericUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.AdLocation;
import com.advanceweb.common.index.lucene.LuceneIndexer;

/**
 * The locationIndexer create a lucene index for the location database using the
 * Spatial query parameters in Lucene
 * 
 * @author sukeshnambiar
 * 
 */
@SuppressWarnings("deprecation")
public class LocationIndexer extends LocationIndexBase implements LuceneIndexer {

	private static final Logger LOGGER = Logger
			.getLogger(LocationIndexer.class);

	private Map<String, Document> documentCache;

	@Autowired
	LocationIndexSource locationSource;

	/**
	 * Create an index of the Ad Locations for Ad serving queries
	 */
	@Override
	public void index(IndexWriter indexWriter) throws JobBoardServiceException {
		try {
			// Create document cache
			documentCache = new HashMap<String, Document>();

			List<AdLocation> locationList = locationSource.findAll();
			LOGGER.debug("Found " + locationList.size() + " locations");
			for (AdLocation location : locationList) {
				if (location.getLatitude() != null
						&& location.getLongitude() != null) {

					String key = location.getLabel();
					Document document;
					if (documentCache.containsKey(key)) {
						document = documentCache.get(key);
					} else {
						document = new Document();
						documentCache.put(key, document);
					}

					updateDocument(document, location);
				} else {
					LOGGER.debug("Skipping location " + location
							+ " for lack of coordinates");
				}
			}

			for (Map.Entry<String, Document> docEntry : documentCache
					.entrySet()) {
				indexWriter.addDocument(docEntry.getValue());
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

	/**
	 * Create a luncene document form the given location. The documents stores
	 * all the fields of the Ad Location, and also creates the spatial query
	 * index entries based ont he latitude and longitude.
	 * 
	 * @param document
	 *            The Lucene Index document to be updated
	 * @param location
	 *            The AD Location to be added to the document
	 * @return The updated document that can be written into the index
	 */
	private Document updateDocument(Document document, AdLocation location) {
		LOGGER.debug("Adding " + location + " to index");
		document.add(new Field("city", location.getCity(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		document.add(new Field("state", location.getState(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		document.add(new Field("country", location.getCountry(),
				Field.Store.YES, Field.Index.ANALYZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS));
		document.add(new Field("label", location.getLabel(), Field.Store.YES,
				Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

		document.add(new Field("latitude", Float.toString(location
				.getLatitude()), Field.Store.YES, Field.Index.NOT_ANALYZED));
		document.add(new Field("longitude", Float.toString(location
				.getLongitude()), Field.Store.YES, Field.Index.NOT_ANALYZED));

		// Add the spatial fields ref
		// http://www.mhaller.de/archives/156-Spatial-search-with-Lucene.html
		document.add(new Field(LocationIndex.LATITIDE_VALUE_FIELD, NumericUtils
				.doubleToPrefixCoded(location.getLatitude()), Field.Store.YES,
				Field.Index.NOT_ANALYZED));
		document.add(new Field(LocationIndex.LONGITUDE_VALUE_FIELD,
				NumericUtils.doubleToPrefixCoded(location.getLongitude()),
				Field.Store.YES, Field.Index.NOT_ANALYZED));

		int startTier = getMaxTier();
		int endTier = getMinTier();
		IProjector projector = new SinusoidalProjector();
		for (int tier = startTier; tier <= endTier; tier += 1) {
			CartesianTierPlotter plotter = new CartesianTierPlotter(tier,
					projector, LocationIndex.TIER_PREFIX_FIELD);
			double boxId = plotter.getTierBoxId(location.getLatitude(),
					location.getLongitude());
			document.add(new Field(plotter.getTierFieldName(), NumericUtils
					.doubleToPrefixCoded(boxId), Field.Store.YES,
					Field.Index.NOT_ANALYZED_NO_NORMS));
		}

		return document;
	}
}
