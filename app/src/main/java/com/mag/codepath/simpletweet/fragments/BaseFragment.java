package com.mag.codepath.simpletweet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.codepath.simpletweet.TwitterApplication;
import com.mag.codepath.simpletweet.TwitterClient;

/**
 * Created by perfumekuo on 2017/3/10.
 */

public class BaseFragment extends Fragment {

    TwitterClient twitterClient;

    public BaseFragment(){}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        twitterClient = TwitterApplication.getRestClient();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
