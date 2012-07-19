package com.advanceweb.afc.jb.job.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.ServiceTest;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SavedJobDTO;

public class JobSeekerActivityServiceTest extends ServiceTest {

	@Autowired
	private JobSeekerActivity jobSeekerActivity;

	@Test
	public void testGetAppliedJobs() {
		try {

			List<AppliedJobDTO> appliedJobDTO = jobSeekerActivity
					.getAppliedJobs(13100);
			assertTrue("Get Applied Job", appliedJobDTO != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore("Not Ready to test")
	@Test
	public void testDeleteAppliedJobs() {
		try {

			assertTrue("Delete Applied Job",
					jobSeekerActivity.deleteAppliedJobs(13100));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSavedJobs() {
		try {

			List<SavedJobDTO> savedJobDTO = jobSeekerActivity
					.getSavedJobs(13100);
			assertTrue("Get Applied Job", savedJobDTO != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteSavedJobs() {
		try {

			assertTrue("Delete Applied Job",
					jobSeekerActivity.deleteSavedJobs(13100));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
