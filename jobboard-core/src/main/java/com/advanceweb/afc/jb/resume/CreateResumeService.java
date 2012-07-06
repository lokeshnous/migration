package com.advanceweb.afc.jb.resume;

import com.advanceweb.afc.jb.dto.CreateResumeDTO;

public interface CreateResumeService {

	public CreateResumeDTO createResumeDTO = null;

	public void addCreateResumeCopyPaste(CreateResumeDTO createResumeDTO);
	public void addCreateResumeUpload(CreateResumeDTO createResumeDTO);

}