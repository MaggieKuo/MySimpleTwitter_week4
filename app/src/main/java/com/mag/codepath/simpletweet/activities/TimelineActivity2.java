package com.mag.codepath.simpletweet.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.adapters.TweetsRecyclerAdapter;
import com.mag.codepath.simpletweet.TwitterApplication;
import com.mag.codepath.simpletweet.TwitterClient;
import com.mag.codepath.simpletweet.listeners.EndlessRecyclerViewScrollListener;
import com.mag.codepath.simpletweet.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity2 extends AppCompatActivity {

    private static final String TAG = TimelineActivity2.class.getSimpleName();
    @BindView(R.id.recycler_timeline)
    RecyclerView recyclerTimeline;
    private TwitterClient twitterClient;
    private ArrayList<Tweet> tweets;
    private TweetsRecyclerAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_timeline);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        ButterKnife.bind(this);

        twitterClient = TwitterApplication.getRestClient();
        setViews();
        populateTimeline(0, recyclerTimeline);


    }

    private void setViews() {
        tweets = new ArrayList<Tweet>();
        recyclerTimeline.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerTimeline.setLayoutManager(layoutManager);

        adapter = new TweetsRecyclerAdapter(tweets);
        recyclerTimeline.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "onLoadMore page="+page + " / total items count = " + totalItemsCount);
                populateTimeline(page, view);

            }
        };

        recyclerTimeline.addOnScrollListener(scrollListener);
    }


    private void populateTimeline(int page, final RecyclerView view) {
        if (tweets!=null) {
//            tweets.clear();
//            adapter.notifyDataSetChanged();
//            scrollListener.resetState();
        }


        twitterClient.getHomeTimeline(page,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                tweets.addAll(Tweet.fromJSONArray(response));
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemRangeInserted(adapter.getItemCount(), tweets.size() - 1);
                    }
                });
//                refreshRecyclerView(tweets);

//                Log.d(TAG, "statusCode = " + statusCode);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d(TAG, "response string :" + responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e(TAG, "statusCode="+statusCode);
//                Log.e(TAG, errorResponse.toString());
//                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }


        });
    }

}
