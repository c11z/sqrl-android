package com.corydominguez.gator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.corydominguez.gator.R;
import com.corydominguez.gator.adapters.LinksAdapter;
import com.corydominguez.gator.clients.GatorClient;
import com.corydominguez.gator.handlers.LinkListHandler;
import com.corydominguez.gator.models.Link;

import java.util.ArrayList;

/**
 * Created by jarrettcoggin on 2/13/14.
 */
public class LinksFragment extends Fragment {

    protected ListView lvLinks;
    protected ArrayList<Link> links;
    protected LinksAdapter linksAdapter;
    protected LinkListHandler linkListHandler;
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

    public LinkListHandler getLinkListHandler(){
        return this.linkListHandler;
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
        this.gatorClient = new GatorClient();
    }

    protected void setupViews(){
        this.lvLinks = (ListView) getActivity().findViewById(R.id.lvLinks);
        this.lvLinks.setAdapter(this.linksAdapter);
    }

    protected void setupHandlers(){
        this.linkListHandler = new LinkListHandler(getActivity(), this.links, this.linksAdapter);
    }

}
