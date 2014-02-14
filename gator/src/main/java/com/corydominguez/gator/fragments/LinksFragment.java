package com.corydominguez.gator.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.corydominguez.gator.R;
import com.corydominguez.gator.adapters.LinksAdapter;
import com.corydominguez.gator.clients.GatorClient;
import com.corydominguez.gator.handlers.GatorHttpHandler;
import com.corydominguez.gator.models.Link;

import java.util.ArrayList;

/**
 * Created by jarrettcoggin on 2/13/14.
 */
public class LinksFragment extends Fragment {

    protected ListView lvLinks;
    protected ArrayList<Link> links;
    protected LinksAdapter linksAdapter;
    protected GatorHttpHandler gatorHttpHandler;
    protected GatorClient gatorClient;

    public ListView getLinksListView(){
        return this.lvLinks;
    }

    public ArrayList<Link> getLinks(){
        return this.links;
    }

    public LinksAdapter getLinksAdapter(){
        return this.linksAdapter;
    }

    // Probably should not use the handler from here, instead instantiate the client
    // that way we can only have one handler and manage whether it is running or not
    public GatorHttpHandler getGatorHttpHandler(){
        return this.gatorHttpHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_link_list, container, false);
    }

    protected void setupAdapter(){
        this.links = new ArrayList<Link>();
        this.linksAdapter = new LinksAdapter(getActivity(), this.links);
    }

    protected void setupClient(){
        Context context = getActivity().getBaseContext();
        this.gatorClient = new GatorClient(context, this.links, this.linksAdapter);
    }

    protected void setupViews(){
        this.lvLinks = (ListView) getActivity().findViewById(R.id.lvLinks);
        this.lvLinks.setAdapter(this.linksAdapter);
    }

    protected void setupHandlers(){
        Context context = getActivity().getBaseContext();
        this.gatorHttpHandler = new GatorHttpHandler(context, this.links, this.linksAdapter);
    }

}
