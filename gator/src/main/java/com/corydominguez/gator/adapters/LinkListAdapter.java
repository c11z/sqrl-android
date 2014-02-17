package com.corydominguez.gator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.corydominguez.gator.R;
import com.corydominguez.gator.models.Link;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import static android.app.PendingIntent.getActivity;

public class LinkListAdapter extends ArrayAdapter<Link>{

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

        ImageView ivBookmarkStatus = (ImageView) view.findViewById(R.id.ivBookmarkStatus);
        String linkTitle = "EMPTY TITLE!";
        String linkMetaDescription = "EMPTY META DESCRIPTION!";
        String bookmarkLocation = "drawable://" + R.drawable.ic_not_bookmarked;
        Boolean boolBookmarkStatus = new Boolean(false);
        ivBookmarkStatus.setTag(boolBookmarkStatus);
        ivBookmarkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newImage = "drawable://" + R.drawable.ic_bookmarked;
                if ((Boolean) v.getTag()) {
                    v.setTag(new Boolean(true));
                } else {
                    v.setTag(new Boolean(false));
                    newImage = "drawable://" + R.drawable.ic_not_bookmarked;
                }
                ImageLoader.getInstance().displayImage(newImage, (ImageView) v);
            }
        });

        return view;
    }

}
