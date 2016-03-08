/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.calabashCat.android.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Window;

import com.calabashCat.android.sample.presentation.R;
import com.calabashCat.android.sample.presentation.view.fragment.BusinessDetailsFragment;

/**
 * Activity that shows details of a certain user.
 */
public class BusinessDetailsActivity extends BaseActivity {

	public static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
	private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";

	private int userId;

	public static Intent getCallingIntent(Context context, int userId) {
		Intent callingIntent = new Intent(context, BusinessDetailsActivity.class);
		callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);

		return callingIntent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		DataBindingUtil.setContentView(this, R.layout.activity_user_details);

		this.initializeActivity(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if (outState != null) {
			outState.putInt(INSTANCE_STATE_PARAM_USER_ID, this.userId);
		}
		super.onSaveInstanceState(outState);
	}

	/**
	 * Initializes this activity.
	 */
	private void initializeActivity(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			this.userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
			addFragment(R.id.fl_fragment, BusinessDetailsFragment.newInstance(this.userId), BusinessDetailsFragment.TAG);
		} else {
			this.userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
		}
	}

}
