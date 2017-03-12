package com.mag.codepath.simpletweet.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.listeners.EndlessRecyclerViewScrollListener;
import com.mag.codepath.simpletweet.models.Tweet;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public class HomeTimelineFragment extends TweetsListFragment{

    static final String TAG = HomeTimelineFragment.class.getSimpleName();

    public static HomeTimelineFragment newInstance() {
        HomeTimelineFragment fragment = new HomeTimelineFragment();
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
//        homeTimeline(1);
    }

    @Override
    void listsLoadMore(long since_id) {
        homeTimeline(since_id);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void homeTimeline(long since_id) {
/*        if (tweets != null) {
//            tweets.clear();
//            adapter.notifyDataSetChanged();
//            scrollListener.resetState();
        }*/
Log.d(TAG, "homeTimeline since_id = " + since_id);

        twitterClient.getHomeTimeline(since_id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, "response size = " + response.length());
                addAll(Tweet.fromJSONArray(response));
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, "response string :" + responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e(TAG, "response statusCode :" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

}
