/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.advt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;

/**
 * Implementation of the Ad service. This class does nothing but to forward the
 * request to the delegate member variable
 * 
 * @author sukeshnambiar
 * 
 */
@Service("adService")
public class AdServiceImpl implements AdService {

	/** The ad service delegate. */
	@Autowired
	private AdServiceDelegate adServiceDelegate;

	/**
	 * Returns the Banner corresponding to the advertisement parameters. This
	 * method will forward the banner creation process to the delegate.
	 * 
	 * @param context
	 *            The Client context
	 * @param size
	 *            Size of the requested ad
	 * @param position
	 *            The position where the ad will be placed
	 * @return Banner containing the ad information and the ad-tag
	 */
	@Override
	public Banner getBanner(ClientContext context, AdSize size,
			AdPosition position) {
		return adServiceDelegate.getBanner(context, size, position);
	}

}
