package com.mag.codepath.simpletweet.util;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

import cz.msebera.android.httpclient.ParseException;

/**
 * Created by perfumekuo on 2017/3/5.
 */

public class Utils {
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();


//            String[] ds = relativeDate.split(" ");
//            relativeDate = ds[0] + ds[1].substring(0,1);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }


}
