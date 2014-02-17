package com.corydominguez.gator.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.corydominguez.gator.R;
import com.corydominguez.gator.fragments.LinkListFragment;

public class FeedActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fts = manager.beginTransaction();
        LinkListFragment llf = new LinkListFragment();
        fts.replace(R.id.flContainer, llf);
        fts.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feed, menu);
		return true;
	}

}
