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
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.support.v7.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import com.calabashCat.android.sample.presentation.R;

/**
 * Activity that shows a list of Users.
 */
public class BusinessListActivity extends BaseActivity{

	private Context mcontext;

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, BusinessListActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);	//For both ActionBarSherlock and AppCompat library, it's necessary to call requestFeature() before super.onCreate()
		super.onCreate(savedInstanceState);

		mcontext = getApplicationContext();

		DataBindingUtil.setContentView(this, R.layout.user_list_activity);

		Toolbar myToolbar = (Toolbar) findViewById(R.id.user_list_activity_toolbar);
		myToolbar.inflateMenu(R.menu.user_list_activity_menu);
		myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
					case R.id.action_settings:
						// User chose the "Settings" item, show the app settings UI...
						return true;
					default:
						// If we got here, the user's action was not recognized.
						// Invoke the superclass to handle it.
						return true;
				}
			}
		});
		setSupportActionBar(myToolbar);
	}

	//Show the overflow menu on the Action Bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_list_activity_menu, menu);

		MenuItem searchItem = menu.findItem(R.id.action_search);
        // with MenuItemCompat instead of your MenuItem
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //Callback when search submit
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
	}

}
