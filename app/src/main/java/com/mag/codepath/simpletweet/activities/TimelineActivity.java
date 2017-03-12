package com.mag.codepath.simpletweet.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.TwitterApplication;
import com.mag.codepath.simpletweet.TwitterClient;
import com.mag.codepath.simpletweet.adapters.TwitterFragmentPagerAdapter;
import com.mag.codepath.simpletweet.models.UserInfo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;


public class TimelineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = TimelineActivity.class.getSimpleName();
    private static final int REQTWEET = 6;

    TwitterClient twitterClient;

//    @BindView(R.id.btnCompose)
//    Button btnCompose;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_tablayout)
    TabLayout idTablayout;
    @BindView(R.id.id_viewpager)
    ViewPager idViewpager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.img_usr)
    ImageView imgUsr;
    @BindView(R.id.nav_profile)
    NavigationView profile;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private TwitterFragmentPagerAdapter vpAdapter;

    UserInfo currentUser = new UserInfo();
    private ImageView profile_user_img;
    private TextView user_name;
    private TextView user_screen_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        twitterClient = TwitterApplication.getRestClient();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCompose();

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        setViews();
        setNavigationView();
    }

    private void setViews() {
        vpAdapter = new TwitterFragmentPagerAdapter(getSupportFragmentManager(), this);
        idViewpager.setAdapter(vpAdapter);
        idTablayout.setupWithViewPager(idViewpager);
        idViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        getCurrentProfile();


//        if (savedInstanceState == null){
//            HomeTimelineFragment fragment = HomeTimelineFragment.newInstance();
//        }
    }

//    @OnClick(R.id.btnCompose)
    void onClickCompose() {
//        update("this is a test tweet");
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivityForResult(intent, REQTWEET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQTWEET:
                if (resultCode == RESULT_OK) {
                    String responseBody = data.getStringExtra("responseBody");

                    Toast.makeText(TimelineActivity.this, "success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setAction("com.mag.codepath.new tweet");
                    intent.putExtra("tweet", responseBody);
                    sendBroadcast(intent);
                } else {

                }
                break;
        }

    }


    public void update(String data) {
        twitterClient.UpdateTimeline(data, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "statusCode = " + statusCode);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "statusCode = " + statusCode);
            }
        });

    }

    private void getCurrentProfile() {
        twitterClient.getProfile(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                currentUser = UserInfo.getFromJsonObject(response);
                Glide.with(TimelineActivity.this).load(currentUser.getProfile_image_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgUsr) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(TimelineActivity.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imgUsr.setImageDrawable(circularBitmapDrawable);
                        profile_user_img.setImageDrawable(circularBitmapDrawable);
                    }
                });

//                Glide.with(TimelineActivity.this).load(currentUser.getProfile_image_url()).into(imgUsr);
//                Glide.with(TimelineActivity.this).load(currentUser.getProfile_image_url()).into(profile_user_img);
                user_name.setText(currentUser.getName());
                user_screen_name.setText("@" + currentUser.getScreen_name());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }

    @OnClick(R.id.img_usr)
    void displayProfile() {
        drawerLayout.openDrawer(Gravity.START);
    }

    @Nullable
    @Override
    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
//        Glide.with(TimelineActivity.this).load(currentUser.getProfile_image_url()).into(profileUsrImg);
//        usrName.setText(currentUser.getName());
//        usrID.setText("@" + currentUser.getScreen_name());
        return super.getDrawerToggleDelegate();
    }

    private void setNavigationView() {
        View view = profile.getHeaderView(0);
//        ButterKnife.bind(this, view);
        profile_user_img = (ImageView) view.findViewById(R.id.profile_usr_img);

        profile_user_img.setOnClickListener(this);

        user_name = (TextView) view.findViewById(R.id.usrName);
        user_screen_name = (TextView) view.findViewById(R.id.usrScreenName);

        profile.setNavigationItemSelectedListener(this);

//        View header = profile.inflateHeaderView(R.layout.nav_header_profile);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_usr_img:
                Intent intent = new Intent(this, UserTweetsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("USER", currentUser);
                bundle.putLong("userId", currentUser.getId());
                bundle.putString("screen_name", currentUser.getScreen_name());
                intent.putExtras(bundle);
                drawerLayout.closeDrawers();
                startActivity(intent);
                break;
        }
    }
}
