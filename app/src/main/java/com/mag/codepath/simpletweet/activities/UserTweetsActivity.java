package com.mag.codepath.simpletweet.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.models.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserTweetsActivity extends AppCompatActivity {

    @BindView(R.id.user_tweets_img)
    ImageView userTweetsImg;
    @BindView(R.id.user_tweets_name)
    TextView userTweetsName;
    @BindView(R.id.user_tweets_screen_name)
    TextView userTweetsScreenName;
    @BindView(R.id.user_tweets_text)
    TextView userTweetsText;
    @BindView(R.id.user_tweets_follows)
    TextView userTweetsFollows;
    @BindView(R.id.user_tweets_friends)
    TextView userTweetsFriends;

    private UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tweets);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();

        userInfo = (UserInfo) bundle.getSerializable("USER");

        setupView();
    }

    private void setupView() {

        Glide.with(this).load(userInfo.getProfile_image_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(userTweetsImg) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                userTweetsImg.setImageDrawable(circularBitmapDrawable);
            }
        });
        userTweetsName.setText(userInfo.getName());
        userTweetsScreenName.setText(userInfo.getScreen_name());
        userTweetsText.setText(userInfo.getDescription());
        userTweetsFollows.setText("" + userInfo.getFollowers_count() + " followers");
        userTweetsFriends.setText("" + userInfo.getFriends_count() + " following");


    }
}
