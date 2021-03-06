package com.calabashCat.android.sample.test.viewmodel;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.calabashCat.android.sample.data.dto.User;
import com.calabashCat.android.sample.presentation.R;
import com.calabashCat.android.sample.presentation.mapper.UserModelDataMapper;
import com.calabashCat.android.sample.presentation.view.activity.BusinessDetailsActivity;
import com.calabashCat.android.sample.presentation.view.fragment.BusinessDetailsFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rocko on 15-11-6.
 */
@RunWith(AndroidJUnit4.class)
public class UserDetailsViewModelTest {

	private final static int FAKE_USER_ID = 1;
	private final static String FAKE_USER_NAME = "FAKE_USER_NAME";
	private final static String FAKE_EMAIL = "FAKE_EMAIL@fake.com";
	private final static String FAKE_DESCRIPTION = "FAKE_DESCRIPTION";
	private final static int FAKE_FOLLOWERS = 300;

	private User fakeUser;
	private BusinessDetailsFragment detailsFragment;
	private UserModelDataMapper userModelDataMapper;


	@Rule
	public ActivityTestRule<BusinessDetailsActivity> mActivityRule = new ActivityTestRule(BusinessDetailsActivity.class);

	@Before
	public void setUp() {
		fakeUser = makeFakeUser();
		userModelDataMapper = new UserModelDataMapper();
		detailsFragment = (BusinessDetailsFragment) mActivityRule.getActivity().getFragment(BusinessDetailsFragment.TAG);
	}

	@Test
	public void testShowUser() throws Exception {
		detailsFragment.getViewModel().showUserDetails(userModelDataMapper.transformUser(fakeUser));
//		UserDetailsBinding userDetailsBinding = detailsFragment.getBinding();
		onView(withId(R.id.tv_fullname)).check(matches(withText(FAKE_USER_NAME)));
		onView(withId(R.id.tv_email)).check(matches(withText(FAKE_EMAIL)));
		onView(withId(R.id.tv_followers)).check(matches(withText(String.valueOf(FAKE_FOLLOWERS))));
		onView(withId(R.id.tv_description)).check(matches(withText(FAKE_DESCRIPTION)));
	}


	private User makeFakeUser() {
		User fakeUser = new User(FAKE_USER_ID);
		fakeUser.setFullName(FAKE_USER_NAME);
		fakeUser.setEmail(FAKE_EMAIL);
		fakeUser.setFollowers(FAKE_FOLLOWERS);
		fakeUser.setDescription(FAKE_DESCRIPTION);
		return fakeUser;
	}
}
