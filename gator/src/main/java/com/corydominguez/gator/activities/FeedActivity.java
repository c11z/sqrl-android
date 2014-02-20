package com.corydominguez.gator.activities;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.ActionBar.Tab;
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
import com.corydominguez.gator.fragments.BookmarkFragment;
import com.corydominguez.gator.fragments.HomeFragment;
import com.corydominguez.gator.models.Link;

import java.util.ArrayList;

public class FeedActivity extends FragmentActivity implements TabListener {
    Boolean inHomeFragment;
    private HomeFragment hf;
    private BookmarkFragment bf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
        setUpTabs();
    }

    private void setUpTabs() {
        ActionBar actionBar = getActionBar();
        assert(actionBar != null);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        Tab tabHome = actionBar.newTab().setText("Home").setTag("HomeFragement").setTabListener(this);
        Tab tabBookmark = actionBar.newTab().setText("Bookmarks").setTag("BookmarkFragment").setTabListener(this);
        actionBar.addTab(tabHome);
        actionBar.addTab(tabBookmark);
        actionBar.selectTab(tabHome);
    }

    public void onToDetailView(View view) {
        Integer pos = (Integer) view.getTag();

        ArrayList<Link> linkList;
        if (inHomeFragment) {
           linkList = hf.getLinkList();
        } else {
           linkList = bf.getLinkList();
        }

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


    @Override
    public void onTabSelected(Tab tab, android.app.FragmentTransaction fragmentTransaction) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fts = manager.beginTransaction();
        if (tab.getTag() == "HomeFragment") {
            if (hf == null) {
                hf = new HomeFragment();
            }
            inHomeFragment = true;
            fts.replace(R.id.flContainer, hf);
        } else {
            if (bf == null) {
                bf = new BookmarkFragment();
            }
            fts.replace(R.id.flContainer, bf);
            inHomeFragment = false;
        }
        fts.commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }
}
