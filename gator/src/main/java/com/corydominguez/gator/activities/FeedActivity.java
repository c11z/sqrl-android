package com.corydominguez.gator.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.corydominguez.gator.R;
import com.corydominguez.gator.fragments.LinkListFragment;
import com.corydominguez.gator.models.Link;

import java.util.ArrayList;

public class FeedActivity extends FragmentActivity {
    public FragmentManager manager;
    private LinkListFragment llf;

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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onSearch(MenuItem item) {

    }

    public void onShow(MenuItem item) {
        if(item.getTitle().equals("Show All")) {
            item.setTitle("Show Unread");
        } else {
            item.setTitle("Show All");
        }
    }

    public void onMarkAllRead(MenuItem item) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.feed, menu);
        MenuItem actionShow = menu.findItem(R.id.action_show);
        assert (actionShow != null);
        actionShow.setTitle("Show All");
        return true;
    }

}
