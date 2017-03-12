package com.mag.codepath.simpletweet.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.adapters.TweetsRecyclerAdapter;
import com.mag.codepath.simpletweet.listeners.EndlessRecyclerViewScrollListener;
import com.mag.codepath.simpletweet.models.Tweet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A placeholder fragment containing a simple view.
 */
public abstract class TweetsListFragment extends BaseFragment{
    static final String TAG = TweetsListFragment.class.getSimpleName();

    @BindView(R.id.recycler_timeline)
    RecyclerView recyclerView;

    ArrayList<Tweet> tweets;
    TweetsRecyclerAdapter adapter;

    BroadcastReceiver receiver;
    Context context;
    Unbinder unbinder;

    public TweetsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        setViews();
//        populateTimeline(0, recyclerView);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listsLoadMore(1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(receiver);
    }

    private void setViews() {
        tweets = new ArrayList<Tweet>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TweetsRecyclerAdapter(tweets);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "onLoadMore page="+page);
                listsLoadMore(getLastId());
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data = intent.getStringExtra("tweet");
                try {
                    Tweet tweet = Tweet.fromJSON(new JSONObject(data));
                    tweets.add(0, tweet);
                    adapter.notifyItemChanged(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        context.registerReceiver(receiver, new IntentFilter("com.mag.codepath.new tweet"));
    }

    private long getLastId() {
        return tweets.size()>0 ? tweets.get(tweets.size()-1).getUid() : 1;
    }

    public void addAll(final ArrayList<Tweet> addTweets){
        Log.d(TAG, "adapter is null = " + (adapter==null));
        Log.d(TAG, "tweets size=" + tweets.size());
//        this.tweets = tweets;
        tweets.addAll(addTweets);
        adapter.setTweets(tweets);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemRangeInserted(adapter.getItemCount(), tweets.size() - 1);
//                adapter.notifyDataSetChanged();
            }
        });

    }

    abstract void listsLoadMore(long since_id);


}
