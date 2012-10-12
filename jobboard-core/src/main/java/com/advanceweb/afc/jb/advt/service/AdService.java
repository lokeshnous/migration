package com.advanceweb.afc.jb.advt.service;

import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;

public interface AdService {

	Banner getBannner(ClientContext context, AdSize size, String location);
}
