package com.corydominguez.gator.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.corydominguez.gator.R;
import com.corydominguez.gator.models.Link;
import com.corydominguez.gator.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class LinkListAdapter extends ArrayAdapter<Link> {
    public LinkListAdapter(Context context, List<Link> links){
        super(context, 0, links);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_link, null);
        }

        assert (view != null);

        Link link = getItem(position);
        view.setTag(link);

        ImageView ivIsBookmarked = (ImageView) view.findViewById(R.id.ivIsBookmarked);
        ivIsBookmarked.setTag(link);
        setBookmark(link.getIsBookmarked(), ivIsBookmarked);
        ivIsBookmarked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View) v.getParent();
                assert(parent != null);
                Link link = (Link) parent.getTag();
                link.toggleBookmark();
                setBookmark(link.getIsBookmarked(), (ImageView) v);
            }
        });

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(link.getTitle());

        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvDescription.setText(link.getDescription());

        TextView tvDomain = (TextView) view.findViewById(R.id.tvDomain);
        tvDomain.setText(link.getDomain());

        TextView tvTweetCount = (TextView) view.findViewById(R.id.tvTweetCount);
        tvTweetCount.setText(String.valueOf(link.getTweets().size()));

        TextView tvTweetHandles = (TextView) view.findViewById(R.id.tvTweetHandles);
        String handles = "";
        HashSet<String> handleSet = new HashSet<String>();

        for (Tweet tweet : link.getTweets()) {
            handleSet.add("@" + tweet.getScreenName());
        }
        if (handleSet.size() < 4) {
            handles = TextUtils.join(", ", handleSet);
        } else {
            Iterator<String> handleIter = handleSet.iterator();
            handles = handleIter.next();
            handles += ", ";
            handles += handleIter.next();
            handles += ", ";
            handles += handleIter.next();
            handles += "...";
        }

        tvTweetHandles.setText(handles);

        LinearLayout llItem = (LinearLayout) view.findViewById(R.id.llItem);
        llItem.setTag(position);

        return view;
    }

    private void setBookmark(Boolean isBookmarked, ImageView ivIsBookmarked) {
        if (isBookmarked) {
            String bookmarked = "drawable://" + R.drawable.ic_bookmarked;
            ImageLoader.getInstance().displayImage(bookmarked, ivIsBookmarked);
        } else {
            String notBookmarked = "drawable://" + R.drawable.ic_not_bookmarked;
            ImageLoader.getInstance().displayImage(notBookmarked, ivIsBookmarked);
        }
    }

}
