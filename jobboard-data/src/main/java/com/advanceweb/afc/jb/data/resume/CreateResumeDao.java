package com.advanceweb.afc.jb.data.resume;

import com.advanceweb.afc.jb.dto.CreateResumeDTO;

public interface CreateResumeDao {
	public void saveCreateResumeCopyPaste ( CreateResumeDTO createResumeDTO );
	public void saveCreateResumeUpload (CreateResumeDTO createResumeDTO);
}
