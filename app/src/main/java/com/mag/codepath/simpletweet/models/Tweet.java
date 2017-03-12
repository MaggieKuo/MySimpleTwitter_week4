package com.mag.codepath.simpletweet.models;


import android.net.Uri;

import com.mag.codepath.simpletweet.db.TwitterDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.provider.ContentUri;
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint;
import com.raizlabs.android.dbflow.structure.provider.BaseSyncableProviderModel;
import com.raizlabs.android.dbflow.structure.provider.ContentUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@TableEndpoint(name = Tweet.NAME, contentProvider = TwitterDatabase.class)
@Table(name = Tweet.NAME, database = TwitterDatabase.class)
@EqualsAndHashCode(callSuper = false)
public class Tweet extends BaseSyncableProviderModel{

    public static final String NAME = "Tweet";

    @ContentUri(path = NAME, type = ContentUri.ContentType.VND_MULTIPLE + NAME)
    public static final Uri CONTENT_URI = ContentUtils.buildUri(TwitterDatabase.BASE_CONTENT_URI, TwitterDatabase.AUTHORITY + "/" + NAME);

    @Column @PrimaryKey
    long uid;

    @Column String body;
    @Column String createAt;
    User user;

    public static Tweet fromJSON(JSONObject object){
        Tweet tweet = new Tweet();
        try {
            tweet.body = object.getString("text");
            tweet.uid = object.getLong("id");
            tweet.createAt = object.getString("created_at");
            tweet.user = User.fromJSON(object.getJSONObject("user"));
//            tweet.save();

        } catch (JSONException e) {
            e.printStackTrace();
        }
         return  tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<>();

        for (int i=0; i<jsonArray.length(); i++){
            try {
                Tweet tweet = Tweet.fromJSON(jsonArray.getJSONObject(i));
                if (tweet != null)
                    tweets.add(tweet);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweets;

    }

    @Override
    public Uri getDeleteUri() {
        return Tweet.CONTENT_URI;
    }

    @Override
    public Uri getInsertUri() {
        return Tweet.CONTENT_URI;
    }

    @Override
    public Uri getUpdateUri() {
        return Tweet.CONTENT_URI;
    }

    @Override
    public Uri getQueryUri() {
        return Tweet.CONTENT_URI;
    }
}
