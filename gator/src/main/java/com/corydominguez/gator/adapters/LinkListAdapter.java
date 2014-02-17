package com.corydominguez.gator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.corydominguez.gator.R;
import com.corydominguez.gator.models.Link;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class LinkListAdapter extends ArrayAdapter<Link> {
    public LinkListAdapter(Context context, List<Link> links){
        super(context, 0, links);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.link_item, null);
        }

        Link link = getItem(position);
        assert (view != null);

        ImageView ivIsBookmarked = (ImageView) view.findViewById(R.id.ivIsBookmarked);
        ivIsBookmarked.setTag(link);
        setBookmark(link.getIsBookmarked(), ivIsBookmarked);
        ivIsBookmarked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link link = (Link) v.getTag();
                link.setIsBookmarked(!link.getIsBookmarked());
                setBookmark(link.getIsBookmarked(), (ImageView) v);
            }
        });
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(link.getTitle());
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvDescription.setText(link.getDescription());

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
