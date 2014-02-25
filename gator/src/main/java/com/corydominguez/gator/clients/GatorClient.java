package com.corydominguez.gator.clients;

import android.widget.ProgressBar;

import com.corydominguez.gator.adapters.LinkListAdapter;
import com.corydominguez.gator.handlers.GatorHttpHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.message.BasicNameValuePair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import com.corydominguez.gator.models.Tweet;


/**
 * Created by coryd on 2/13/14.
 */
public class GatorClient extends AsyncHttpClient {
    public static final String REST_URL = "http://ec2-54-84-52-116.compute-1.amazonaws.com";
    private DateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private static GatorHttpHandler handler;

    public GatorClient(ProgressBar pb, LinkListAdapter adapter) {
        handler = new GatorHttpHandler(pb, adapter);
    }

    @Override
    public void get(String s, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        if (!handler.running) {
            super.get(s, asyncHttpResponseHandler);
        }
    }

    @Override
    public void get(String s, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        if (!handler.running) {
            super.get(s, requestParams, asyncHttpResponseHandler);
        }
    }

    public void getPast24() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = df.format(cal.getTime());
        RequestParams params = new RequestParams();
        params.put("startDate", yesterday);
        String url = getApiUrl("linkbundle");
        handler.appendMode = false;
        get(url, params, handler);
    }

    public void getLast100() {
        RequestParams params = new RequestParams();
        params.put("limit", "100");
        String url = getApiUrl("linkbundle");
        handler.appendMode = false;
        get(url, handler);
    }

    public void getSince(Date since) {
        RequestParams params = new RequestParams();
        params.put("startDate", df.format(since));
        String url = getApiUrl("linkbundle");
        handler.appendMode = false;
        get(url, params, handler);

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

    private String getApiUrl(String endpoint) {
        //OLD URL: REST_URL + "/" + endpoint + "/" + TWEETER_ID + "/" + Uri.encode(since);
        String url = REST_URL + "/" + endpoint ;
        return url;
    }
}
