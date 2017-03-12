package com.mag.codepath.simpletweet.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.models.Tweet;
import com.mag.codepath.simpletweet.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by perfumekuo on 2017/3/4.
 */

public class TweetsRecyclerAdapter extends RecyclerView.Adapter<TweetsRecyclerAdapter.ViewHolder> {
    private static final String TAG = TweetsRecyclerAdapter.class.getSimpleName();
    Context context;
    ArrayList<Tweet> tweets;


    public TweetsRecyclerAdapter(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
//        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.context == null)
            this.context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "position=" + position + " / size=" + tweets.size());
        if (holder == null || position > tweets.size()) return;
        holder.setData(this.context, tweets.get(position));
//        Log.d(TAG, "position = "+ position);


    }

    @Override
    public int getItemCount() {
        return (tweets == null ? 0 : tweets.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Tweet tweet;

        @BindView(R.id.imgUsr)
        ImageView imgUsr;
        @BindView(R.id.txtUserName)
        TextView txtUserName;
        @BindView(R.id.txtBody)
        TextView txtBody;
        @BindView(R.id.txtTwitter)
        TextView txtTwitter;
        @BindView(R.id.txtRelativeTime)
        TextView txtRelativeTime;
        @BindView(R.id.imgTweetPic)
        ImageView imgTweetPic;

        public ViewHolder(View itemView) {
            super(itemView);
//            setContentView(R.layout.item_tweet);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Context context, Tweet tweet) {
            this.tweet = tweet;

            imgUsr.setImageResource(android.R.color.transparent);
            Glide.with(context)
                    .load(tweet.getUser().getProfileImageUrl())
                    .into(imgUsr);
 /*           Picasso.with(context)
                    .load(tweet.getUser().getProfileImageUrl())
                    .into(imgUsr);
*/
            txtUserName.setText(tweet.getUser().getName());
            txtBody.setText(tweet.getBody());
            txtTwitter.setText(" @" + tweet.getUser().getScreenName()+" ");
            txtRelativeTime.setText("." + Utils.getRelativeTimeAgo(tweet.getCreateAt()));
            imgTweetPic.setVisibility(View.GONE);
        }
    }
}
