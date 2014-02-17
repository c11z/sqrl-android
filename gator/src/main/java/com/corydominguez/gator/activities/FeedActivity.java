package com.corydominguez.gator.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.corydominguez.gator.R;
import com.corydominguez.gator.fragments.LinkDetailFragment;
import com.corydominguez.gator.fragments.LinkListFragment;

public class FeedActivity extends FragmentActivity {
    private FragmentManager manager;
    private LinkListFragment llf;
    private LinkDetailFragment ldf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);

        ActionBar ab = getActionBar();
        if(ab == null){
            Toast.makeText(this,"ActionBar failed", Toast.LENGTH_LONG).show();
        }

        manager = getSupportFragmentManager();
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

    public void onToDetailView(View view) {

    }

    public void onSearch(MenuItem item) {
    }
}
