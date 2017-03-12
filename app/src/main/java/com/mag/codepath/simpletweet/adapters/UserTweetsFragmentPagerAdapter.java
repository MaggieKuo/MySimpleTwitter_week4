package com.mag.codepath.simpletweet.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.mag.codepath.simpletweet.fragments.HomeTimelineFragment;
import com.mag.codepath.simpletweet.fragments.MentionsTimelineFragment;
import com.mag.codepath.simpletweet.fragments.UserTimelineFragment;

import java.util.ArrayList;


public class UserTweetsFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = UserTweetsFragmentPagerAdapter.class.getSimpleName();
    Context context;
    private String tabTitle[] = new String[]{"Tweets", "Media", "Likes"};
    private ArrayList<Fragment> fragmentArrayList= new ArrayList<>();

    public UserTweetsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (fragmentArrayList.size()<position+1 || fragmentArrayList.get(position)!=null) {
            Log.d(TAG, "fragment position = " +position);
            switch (position) {
                case 0:
                    fragment = UserTimelineFragment.newInstance();
                    break;
                case 1:
                    fragment = UserTimelineFragment.newInstance();
                    break;
                case 2:
                    fragment = UserTimelineFragment.newInstance();
                    break;
            }
            fragmentArrayList.add(position, fragment);
        }else{
            fragment = fragmentArrayList.get(position);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
