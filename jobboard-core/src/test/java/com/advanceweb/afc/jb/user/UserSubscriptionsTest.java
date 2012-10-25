package com.advanceweb.afc.jb.user;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;
import com.advanceweb.jb.test.ServiceTest;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class UserSubscriptionsTest extends ServiceTest {

	@Autowired
	public UserSubscriptionService subscriptionService;

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#getSaveSubscriptionsList()}
	 * .
	 * 
	 * @Author :Sasibhushan
	 * @Created:Jul 17, 2012
	 * @Param :not required
	 * @Return :List of JobSeekerSubscriptionsDTO
	 */
	@Test
	public void getSaveSubscriptionsList() {
		List<UserSubscriptionsDTO> subsList = new ArrayList<UserSubscriptionsDTO>();
		// JobSeekerSubscriptionsDTO dto = new JobSeekerSubscriptionsDTO();
		// dto.setLookUpId("23");
		// JobSeekerSubscriptionsDTO dto1 = new JobSeekerSubscriptionsDTO();
		// dto1.setLookUpId("25");
		// JobSeekerSubscriptionsDTO dto2 = new JobSeekerSubscriptionsDTO();
		// dto2.setLookUpId("28");
		// JobSeekerSubscriptionsDTO dto3 = new JobSeekerSubscriptionsDTO();
		// dto3.setLookUpId("30");

		// subsList.add(dto);
		// subsList.add(dto1);
		// subsList.add(dto2);
		// subsList.add(dto3);

		// boolean bSaved =
		// jobSeekerSubscriptionsService.saveJobSeekerSubscription(subsList, 1);
		subscriptionService.saveJobSeekerSubscription(subsList, 1);
		assertNotNull("Subscriptions List", subsList);
		// assertTrue("Subscriptions List", subsList.size()>=0);
	}

	/**
	 * Test method for
	 * {@link com.advanceweb.afc.jb.lookup.service.PopulateDropdownsImpl#testGetSubscriptionList()}
	 * .
	 * 
	 * @Author :Sasibhushan
	 * @Created:Jul 17, 2012
	 * @Param :not required
	 * @Return :List of JobSeekerSubscriptionsDTO
	 */
	@Test
	public void getCurrentSubscriptionsList() {
		List<UserSubscriptionsDTO> subsList = subscriptionService
				.getCurrentSubscriptions(1);
		assertNotNull("Subscriptions List", subsList);
		// assertTrue("Subscriptions List", subsList.size()>=0);
	}

}
