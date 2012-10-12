package com.advanceweb.afc.jb.advt.service.impl;

import com.advanceweb.afc.jb.advt.service.AdSize;
import com.advanceweb.afc.jb.advt.service.Banner;
import com.advanceweb.afc.jb.advt.service.ClientContext;

public interface AdServiceDelegate {

	Banner getBanner(ClientContext context, AdSize size, String location);

}
