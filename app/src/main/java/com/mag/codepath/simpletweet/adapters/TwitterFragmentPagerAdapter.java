package com.mag.codepath.simpletweet.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.mag.codepath.simpletweet.fragments.HomeTimelineFragment;
import com.mag.codepath.simpletweet.fragments.MentionsTimelineFragment;

import java.util.ArrayList;



public class TwitterFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = TwitterFragmentPagerAdapter.class.getSimpleName();
    private String tabTitle[] = new String[]{"Home Timeline", "Mentions"};
    private ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
    private Context context;

    public TwitterFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (fragmentArrayList.size()<position+1 || fragmentArrayList.get(position)==null) {
            Log.d(TAG, "fragment position = " +position);
            switch (position) {
                case 0:
                    fragment = HomeTimelineFragment.newInstance();
                    break;
                case 1:
                    fragment = MentionsTimelineFragment.newInstance();
                    break;
                case 2:
                    fragment = MentionsTimelineFragment.newInstance();
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
        return tabTitle.length;
    }
}
