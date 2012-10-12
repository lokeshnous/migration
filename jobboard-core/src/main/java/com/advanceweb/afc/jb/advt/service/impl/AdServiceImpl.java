package com.advanceweb.afc.jb.advt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.afc.jb.advt.service.AdSize;
import com.advanceweb.afc.jb.advt.service.Banner;
import com.advanceweb.afc.jb.advt.service.ClientContext;

public class AdServiceImpl implements AdService{
	
	@Autowired 
	private AdServiceDelegate delegate;
	@Override
	public Banner getBannner(ClientContext context, AdSize size, String location) {
		return delegate.getBanner(context, size, location);
	}

}
