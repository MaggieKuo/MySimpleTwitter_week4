package com.mag.codepath.simpletweet.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.adapters.UserTweetsFragmentPagerAdapter;
import com.mag.codepath.simpletweet.models.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserTweetsActivity2 extends AppCompatActivity {

    private static final String TAG = UserTweetsActivity2.class.getSimpleName();
    @BindView(R.id.user_toolbar)
    Toolbar userToolbar;
    @BindView(R.id.user_toolbar_layout)
    CollapsingToolbarLayout userToolbarLayout;
    @BindView(R.id.user_tablayout)
    TabLayout userTablayout;
    @BindView(R.id.user_app_bar)
    AppBarLayout userAppBar;
    @BindView(R.id.user_viewpager)
    ViewPager userViewpager;

    UserTweetsFragmentPagerAdapter timelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_user_tweets);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        UserInfo userInfo = (UserInfo) bundle.getSerializable("USER");


        setViews();
    }

    private void setViews() {
        timelineAdapter = new UserTweetsFragmentPagerAdapter(getSupportFragmentManager(), this);
        userViewpager.setAdapter(timelineAdapter);
        userTablayout.setupWithViewPager(userViewpager);
        userViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.d(TAG, "position=" + position);

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "position=" + position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "state=" + state);


            }
        });
     }
}
