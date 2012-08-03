package com.advanceweb.afc.jb.event.dao;

import com.advanceweb.afc.jb.common.ClickEventDTO;

public interface ClickDAO {

	boolean saveClickEvent(ClickEventDTO clickEventDTO);
	ClickEventDTO retrieveAllClicks(int jobId);

}
