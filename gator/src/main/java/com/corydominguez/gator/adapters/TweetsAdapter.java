package com.corydominguez.gator.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.corydominguez.gator.R;
import com.corydominguez.gator.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TweetsAdapter extends ArrayAdapter<Tweet> {

    public TweetsAdapter(Context context, List<Tweet> tweets){
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_tweet, null);
        }

        Tweet tweet = getItem(position);

        ImageView ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
        String name = "USER MISSING!";
        String screenName = "USER MISSING!";
        String imageLocation = "";
        imageLocation= tweet.getProfileImageUrl();
        name = tweet.getName();
        screenName = tweet.getScreenName();
        ivProfile.setTag(tweet);

        ImageLoader.getInstance().displayImage(imageLocation, ivProfile);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        String formattedName = "<b>" + name + "</b>" + " <small><font color='#777777'>@" +
                screenName + "</font></small>";
        tvName.setText(Html.fromHtml(formattedName));

        TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
        tvBody.setText(Html.fromHtml(tweet.getText()));

        return view;
    }

}