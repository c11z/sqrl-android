package com.corydominguez.gator.handlers;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

public class GatorBaseHandler extends JsonHttpResponseHandler {

    protected Context context;

    public GatorBaseHandler(Context context){
        this.context = context;
    }

    public void onFailure(final Throwable e, final JSONArray errorResponse){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < errorResponse.length(); i++){
            try{
                sb.append(errorResponse.get(i).toString());
            } catch (JSONException jsonEx){
                e.printStackTrace();
            }
            sb.append(" ----- ");
        }
        logError(sb.toString());
    }

    public void handleFailureMessage(Throwable e, String responseBody){
        logError(responseBody);
    }

    private void logError(String response){
        Log.w("BadResponse", response);
    }

}
