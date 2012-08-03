package com.advanceweb.afc.jb.event.service;

import com.advanceweb.afc.jb.common.ClickEventDTO;

public interface ClickService {
	boolean saveClickEvent(ClickEventDTO clickEventDTO);
	ClickEventDTO retrieveAllClicks(int jobId);

}
