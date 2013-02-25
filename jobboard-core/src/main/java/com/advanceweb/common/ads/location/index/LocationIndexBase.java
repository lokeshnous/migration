/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.location.index;

import org.apache.log4j.Logger;
import org.apache.lucene.spatial.tier.projections.CartesianTierPlotter;
import org.apache.lucene.spatial.tier.projections.IProjector;
import org.apache.lucene.spatial.tier.projections.SinusoidalProjector;

/**
 * The base class for the Location Index services. As the implementation uses
 * Lucene spatial index, the tier information and the tier mappings needs are
 * required while forming the query and while querying the index. This class
 * contains the common information required by both the query and the indexer
 * 
 * @author sukeshnambiar
 * 
 */
@SuppressWarnings("deprecation")
public class LocationIndexBase {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(LocationIndexBase.class);

	/** The min tier. */
	protected int minTier;
	
	/** The max tier. */
	protected int maxTier;

	/**
	 * A protected constructor to make sure that the client applications does
	 * not create an instance elsewhere
	 */
	protected LocationIndexBase() {
		// Add the tiers
		IProjector projector = new SinusoidalProjector();
		CartesianTierPlotter plotter = new CartesianTierPlotter(0, projector,
				LocationIndex.TIER_PREFIX_FIELD);
		minTier = plotter.bestFit(LocationIndex.MIN_DISTANCE);
		maxTier = plotter.bestFit(LocationIndex.MAX_DISTANCE);
		LOGGER.debug("Max Tier -> " + maxTier);
		LOGGER.debug("Min Tier -> " + minTier);
	}

	/**
	 * The Minimum spatial tier for a radius range of 0 to MAX_DISTANCE
	 * 
	 * @return The Minimum tier value
	 */
	public int getMinTier() {
		return minTier;
	}

	/**
	 * The Maximum spatial tier for a radius range of 0 to MAX_DISTANCE
	 * 
	 * @return The maximum tier value
	 */
	public int getMaxTier() {
		return maxTier;
	}

}
