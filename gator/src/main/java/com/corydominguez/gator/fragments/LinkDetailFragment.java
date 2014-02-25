package com.corydominguez.gator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.corydominguez.gator.R;
import com.corydominguez.gator.adapters.TweetsAdapter;
import com.corydominguez.gator.models.Link;

/**
 * Created by coryd on 17/02/2014.
 */
public class LinkDetailFragment extends Fragment {
    private Link link;
    private MenuItem actionBookmark;
    private MenuItem actionRead;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvUrl;
    private ListView lvTweets;
    private TweetsAdapter tweetsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        link = getArguments().getParcelable("link");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linkdetail, container, false);
        assert (view != null);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvUrl = (TextView) view.findViewById(R.id.tvUrl);
        lvTweets = (ListView) view.findViewById(R.id.lvTweets);
        tweetsAdapter = new TweetsAdapter(getActivity(),link.getTweets());

        tvTitle.setText(link.getTitle());
        tvDescription.setText(link.getDescription());
        tvUrl.setText(link.getUrl());
        lvTweets.setAdapter(tweetsAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail, menu);

        actionBookmark = menu.findItem(R.id.action_bookmark);
        assert (actionBookmark != null);

        setBookmark(link.getIsBookmarked());
        actionBookmark.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                link.toggleBookmark();
                setBookmark(link.getIsBookmarked());
                return true;
            }
        });

        actionRead = menu.findItem(R.id.action_read);
        link.markRead();
        assert (actionRead != null);
        setRead(link.getIsRead());
        actionRead.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                link.toggleRead();
                setRead(link.getIsRead());
                return true;
            }
        });
    }

    private void setBookmark(Boolean isBookmarked) {
        if (isBookmarked) {
            actionBookmark.setIcon(R.drawable.ic_action_bookmarked);
        } else {
            actionBookmark.setIcon(R.drawable.ic_action_not_bookmarked);
        }
    }

    private void setRead(Boolean isRead) {
        if (isRead) {
            actionRead.setIcon(R.drawable.ic_marked);
        } else {
            actionRead.setIcon(R.drawable.ic_unmarked);
        }
    }

}
