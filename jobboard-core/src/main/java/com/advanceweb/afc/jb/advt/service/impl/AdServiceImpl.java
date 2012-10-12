package com.advanceweb.afc.jb.advt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;

public class AdServiceImpl implements AdService{
	
	@Autowired 
	private AdServiceDelegate delegate;
	@Override
	public Banner getBannner(ClientContext context, AdSize size, String location) {
		return delegate.getBanner(context, size, location);
	}

}
