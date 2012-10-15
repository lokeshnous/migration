package com.advanceweb.afc.jb.advt.service;

import com.advanceweb.common.ads.AdPosition;
import com.advanceweb.common.ads.AdSize;
import com.advanceweb.common.ads.Banner;
import com.advanceweb.common.client.ClientContext;

public interface AdService {

	Banner getBanner(ClientContext context, AdSize size, AdPosition position);
}
