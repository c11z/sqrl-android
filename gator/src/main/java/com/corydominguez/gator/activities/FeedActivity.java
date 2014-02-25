package com.corydominguez.gator.activities;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.corydominguez.gator.R;
import com.corydominguez.gator.fragments.LinkListFragment;
import com.corydominguez.gator.models.Link;

import java.util.ArrayList;

public class FeedActivity extends FragmentActivity implements TabListener {
    private static final String HTAG = "Home";
    private static final String BTAG = "Bookmarks";
    private static final int DETAIL_RESUlT_CODE = 34;
    private LinkListFragment llf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
        setUpFragment();
        setUpTabs();
    }

    private void setUpTabs() {
        ActionBar actionBar = getActionBar();
        assert(actionBar != null);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        Tab tabHome = actionBar.newTab().setText("Home").setTag(HTAG).setTabListener(this);
        Tab tabBookmark = actionBar.newTab().setText("Bookmarks").setTag(BTAG).setTabListener(this);
        actionBar.addTab(tabHome);
        actionBar.addTab(tabBookmark);
        actionBar.selectTab(tabHome);
    }

    private void setUpFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fts = manager.beginTransaction();
        llf = new LinkListFragment();
        fts.replace(R.id.flContainer, llf);
        fts.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

//        getActionBar().selectTab(tabHome);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == DETAIL_RESUlT_CODE) {
            ArrayList<Link> newLinkList = data.getParcelableArrayListExtra("linkList");
            llf.replaceLinkList(newLinkList);
        }
    }

    public void onToDetailView(View view) {
        Integer pos = (Integer) view.getTag();

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putParcelableArrayListExtra("linkList", llf.getLinkList());
        intent.putExtra("pos", pos);
        startActivityForResult(intent, DETAIL_RESUlT_CODE);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onDomain(View v) {
        String url = (String) v.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
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
        if (llf.getAdapter() != null) {
            if (tab.getTag() == HTAG) {
                llf.getAdapter().setBookmarkMode(false);
                llf.getLVLinks().setVerticalScrollBarEnabled(true);
            } else {
                llf.getAdapter().setBookmarkMode(true);
                llf.getLVLinks().setVerticalScrollBarEnabled(false);
            }
            llf.getAdapter().notifyDataSetInvalidated();
            llf.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

    }
}
