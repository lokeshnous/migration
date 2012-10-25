package com.advanceweb.afc.jb.advt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.advt.service.AdService;
import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;

@Service("adService")
public class AdServiceImpl implements AdService{
	
	@Autowired 
	private AdServiceDelegate adServiceDelegate;
	@Override
	public Banner getBanner(ClientContext context, AdSize size, AdPosition position) {
		return adServiceDelegate.getBanner(context, size, position);
	}

}
