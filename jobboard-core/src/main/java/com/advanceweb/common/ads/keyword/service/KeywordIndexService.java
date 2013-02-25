/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.common.ads.keyword.service;

import java.util.List;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.common.ads.ContentTopic;

/**
 * The KeywordIndexService provide service for finding keyword matches for Ad
 * serving purpose.
 * 
 * @author sukeshnambiar
 * 
 */
public interface KeywordIndexService {

	/**
	 * Returns the ContentTopic containing the best match for the pattern passed
	 * into the method.
	 * 
	 * @param pattern
	 *            The pattern to match
	 * @return The ContentTopic that matches best with the pattern passed into
	 *         the method.
	 * @throws JobBoardServiceException
	 *             If any exception happened during the matching process
	 */
	ContentTopic findBestMatch(String pattern) throws JobBoardServiceException;

	/**
	 * Returns a list of ContentTopic that matches with the pattern passed in.
	 * The Order of the list is corresponding to the amount of match such that
	 * the lower the index better is the match. The best matching topic is found
	 * at index 0
	 * 
	 * @param pattern
	 *            The pattern to match
	 * @return The ContentTopic that matches best with the pattern passed into
	 *         the method.
	 * @throws JobBoardServiceException
	 *             If any exception happened during the matching process
	 */
	List<ContentTopic> findMatches(String pattern)
			throws JobBoardServiceException;

}