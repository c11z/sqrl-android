package com.corydominguez.gator.handlers;

import android.util.Log;
import android.widget.ProgressBar;

import com.corydominguez.gator.adapters.LinkListAdapter;
import com.corydominguez.gator.models.Link;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;
import java.util.ArrayList;

public class GatorHttpHandler extends AsyncHttpResponseHandler {
    // Removed the LinksListHandler since I thought it was too much of an abstraction
    protected LinkListAdapter linkListAdapter;
    public Boolean running;
    protected ProgressBar pb;
    public static final ObjectMapper mapper = new ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public GatorHttpHandler(LinkListAdapter linkListAdapter) {
        // not sure if we absolutely need both the adapter and the arraylist it is built on
        // in my experience if you change one the other is updated.
        this.linkListAdapter = linkListAdapter;
    }

    @Override
    public void onStart() {
        super.onStart();
        pb.setVisibility(ProgressBar.VISIBLE);
        running = true;
    }

    @Override
    public void onSuccess(String s) {
        try {
            Log.d("DEBUG", "Making api Call");
            TypeReference<ArrayList<Link>> tr = new TypeReference<ArrayList<Link>>() {};
            ArrayList<Link> newLinks;
            newLinks = mapper.readValue(s, tr);
            dealWithNewLinks(newLinks);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Throwable throwable, String s) {
       Log.d("ERROR", "Everyone Panic! " + throwable.toString());
       throwable.printStackTrace();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        pb.setVisibility(ProgressBar.INVISIBLE);
        running = false;
    }

    private void dealWithNewLinks(ArrayList<Link> newLinks) {
        // TODO: Add new links to the adapter or arraylist
    }
}

