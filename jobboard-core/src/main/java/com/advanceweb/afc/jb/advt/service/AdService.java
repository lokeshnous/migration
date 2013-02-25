/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.advt.service;

import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;

/**
 * The Ad Service returns the advertisement banner corresponding to the
 * requested parameter
 * 
 * @author sukeshnambiar
 * 
 */
public interface AdService {
	/**
	 * Returns the Banner corresponding to the advertisement parameters.
	 * 
	 * @param context
	 *            The Client context
	 * @param size
	 *            Size of the requested ad
	 * @param position
	 *            The position where the ad will be placed
	 * @return Banner containing the ad information and the ad-tag
	 */
	Banner getBanner(ClientContext context, AdSize size, AdPosition position);
}
