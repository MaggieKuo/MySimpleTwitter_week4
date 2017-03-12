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

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableEndpoint(name = User.NAME, contentProvider = TwitterDatabase.class)
@Table(database = TwitterDatabase.class, name = User.NAME)
@EqualsAndHashCode(callSuper = false)
public class User extends BaseSyncableProviderModel{

    public static final String NAME = "User";

    @ContentUri(
            path = NAME,
            type = ContentUri.ContentType.VND_MULTIPLE + NAME)
    public static final Uri CONTENT_URI = ContentUtils.buildUriWithAuthority(TwitterDatabase.AUTHORITY);

    @Column @PrimaryKey long uid;

    @Column String name;
    @Column String screenName;
    @Column String profileImageUrl;

    public static User fromJSON(JSONObject object){
        User user = new User();

        try {
            user.name = object.getString("name");
            user.uid = object.getLong("id");
            user.screenName = object.getString("screen_name");
            user.profileImageUrl = object.getString("profile_image_url");
//            user.save();
        } catch (JSONException e) {
            e.printStackTrace();
        }
         return user;
    }

    @Override
    public Uri getDeleteUri() {
        return User.CONTENT_URI;
    }

    @Override
    public Uri getInsertUri() {
        return User.CONTENT_URI;
    }

    @Override
    public Uri getUpdateUri() {
        return User.CONTENT_URI;
    }

    @Override
    public Uri getQueryUri() {
        return User.CONTENT_URI;
    }
}
