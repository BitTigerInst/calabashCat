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

/**
 * Activity that shows a list of Users.
 */
public class BusinessListActivity extends BaseActivity{

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, BusinessListActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);	//For both ActionBarSherlock and AppCompat library, it's necessary to call requestFeature() before super.onCreate()
		super.onCreate(savedInstanceState);

		DataBindingUtil.setContentView(this, R.layout.user_list_activity);
	}

}
