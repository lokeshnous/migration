package com.advanceweb.afc.jb.resume;

import java.util.List;
import com.advanceweb.afc.jb.common.ResumeDTO;


/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */

public interface ResumeService {

	public List<ResumeDTO> retrieveAllResumes(long jobSeekerId);
}

