package com.corydominguez.gator.handlers;

import android.content.Context;

import com.corydominguez.gator.adapters.LinksAdapter;
import com.corydominguez.gator.models.Link;

import java.util.ArrayList;

public class LinkListHandler extends GatorBaseHandler {

    private ArrayList<Link> links;
    private LinksAdapter linksAdapter;

    public LinkListHandler(Context context){
        super(context);
    }

    public LinkListHandler(Context context, ArrayList<Link> links, LinksAdapter linksAdapter){
        this(context);
        this.links = links;
        this.linksAdapter = linksAdapter;
    }

}
