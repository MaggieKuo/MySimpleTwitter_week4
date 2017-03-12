package com.mag.codepath.simpletweet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mag.codepath.simpletweet.models.Tweet;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;


public class MentionsTimelineFragment extends TweetsListFragment{

    static final String TAG = MentionsTimelineFragment.class.getSimpleName();

    public static MentionsTimelineFragment newInstance() {
        MentionsTimelineFragment fragment = new MentionsTimelineFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mentionsTimeline(0);
    }

    @Override
    void listsLoadMore(long since_id) {
        mentionsTimeline(since_id);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void mentionsTimeline(long since_id) {
/*        if (tweets != null) {
//            tweets.clear();
//            adapter.notifyDataSetChanged();
//            scrollListener.resetState();
        }*/
//Log.d(TAG, "homeTimeline page = " + page);

        twitterClient.getMentionsTimeline(since_id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, "response size = " + response.length());
                addAll(Tweet.fromJSONArray(response));
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d(TAG, "response string :" + responseString);
            }

        });
    }

}
