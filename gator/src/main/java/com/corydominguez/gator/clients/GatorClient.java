package com.corydominguez.gator.clients;

import android.net.Uri;
import android.widget.ProgressBar;

import com.corydominguez.gator.adapters.LinkListAdapter;
import com.corydominguez.gator.handlers.GatorHttpHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//import com.corydominguez.gator.models.Tweet;


/**
 * Created by coryd on 2/13/14.
 */
public class GatorClient extends AsyncHttpClient {
    public static final String REST_URL = "http://lickstick.corp.ne1.yahoo.com";
    public static final String TWEETER_ID = "164865735";
    private DateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private GatorHttpHandler handler;

    public GatorClient(ProgressBar pb, LinkListAdapter adapter) {
        handler = new GatorHttpHandler(pb, adapter);

    }

    @Override
    public void get(String s, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        if (!handler.running) {
            super.get(s, asyncHttpResponseHandler);
        }
    }

    public void getPast24() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        String yesterday = df.format(cal.getTime());
        String url = getApiUrl("linkbundle", yesterday);
        handler.appendMode = false;
        get(url, handler);
    }

    private String getApiUrl(String endpoint, String since) {
        return REST_URL + "/" + endpoint + "/" + TWEETER_ID + "/" + Uri.encode(since);
    }

    private String getApiUrl(String endpoint, String tweeterId, String since) {
        return REST_URL + "/" + endpoint + "/" + tweeterId + "/" + Uri.encode(since);
    }
}
