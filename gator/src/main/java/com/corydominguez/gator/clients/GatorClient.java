package com.corydominguez.gator.clients;

import android.net.Uri;
import android.widget.ProgressBar;

import com.corydominguez.gator.adapters.LinkListAdapter;
import com.corydominguez.gator.handlers.GatorHttpHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.message.BasicNameValuePair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

//import com.corydominguez.gator.models.Tweet;


/**
 * Created by coryd on 2/13/14.
 */
public class GatorClient extends AsyncHttpClient {
    public static final String REST_URL = "http://ec2-54-84-52-116.compute-1.amazonaws.com";
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
        cal.add(Calendar.DATE, -1);
        String yesterday = df.format(cal.getTime());
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("startDate", yesterday.split(" ")[0]));
        String url = getApiUrlWithParams("linkbundle", params);
        handler.appendMode = false;
        get(url, handler);
    }

    private String getApiUrlWithParams(String endpoint, List<BasicNameValuePair> params) {
        String url = REST_URL + "/" + endpoint;
        String paramsAddition = "";
        for(int i = 0; i < params.size(); i++){
            BasicNameValuePair param = params.get(i);
            paramsAddition += param.getName() + "=" +param.getValue();
            if(i+1 < params.size()){
                paramsAddition += "&";
            }
        }
        if(paramsAddition!= ""){
            url += "?" + paramsAddition;
        }
        return url;
    }

    private String getApiUrl(String endpoint, String since) {
        //OLD URL: REST_URL + "/" + endpoint + "/" + TWEETER_ID + "/" + Uri.encode(since);
        String url = REST_URL + "/" + endpoint + "/" + Uri.encode(since.split(" ")[0]);
        return url;
    }

    private String getApiUrl(String endpoint, String tweeterId, String since) {
        //OLD URL: REST_URL + "/" + endpoint + "/" + tweeterId + "/" + Uri.encode(since);
        String url = REST_URL + "/" + endpoint + "/" + tweeterId + "/" + Uri.encode(since);
        return url;
    }
}
