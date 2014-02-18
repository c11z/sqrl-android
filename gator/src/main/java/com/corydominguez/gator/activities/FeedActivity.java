package com.corydominguez.gator.activities;

import android.app.ActionBar;
import android.content.Intent;
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
import com.corydominguez.gator.models.Link;

import java.util.ArrayList;

public class FeedActivity extends FragmentActivity {
    public FragmentManager manager;
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
        llf = new LinkListFragment();
        fts.replace(R.id.flContainer, llf);
        fts.commit();
    }

    public void onToDetailView(View view) {
        Integer pos = (Integer) view.getTag();
        ArrayList<Link> linkList = llf.getLinkList();

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putParcelableArrayListExtra("linkList", linkList);
        intent.putExtra("pos", pos);
        startActivity(intent);
    }

    public void onSearch(MenuItem item) {
    }

}
