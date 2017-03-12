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

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableEndpoint(name = UserInfo.NAME, contentProvider = TwitterDatabase.class)
@Table(name = UserInfo.NAME, database = TwitterDatabase.class)
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends BaseSyncableProviderModel implements Serializable {
    public static final String NAME = "UserInfo";

    @ContentUri(
            path = NAME,
            type = ContentUri.ContentType.VND_MULTIPLE + NAME)
    public static final Uri CONTENT_URI = ContentUtils.buildUriWithAuthority(TwitterDatabase.AUTHORITY);

    @Column
    @PrimaryKey
    long id;

    @Column String name;
    @Column String screen_name;
    @Column String description;
    @Column int listed_count;
    @Column int friends_count;
    @Column int followers_count;
    @Column String location;
    @Column String url;
    @Column String profile_image_url;


    public static UserInfo getFromJsonObject(JSONObject object){
        UserInfo usr_info = new UserInfo();
        try {
            usr_info.setId(object.getLong("id"));
            usr_info.setName(object.getString("name"));
            usr_info.setScreen_name(object.getString("screen_name"));
            usr_info.setDescription(object.getString("description"));
            usr_info.setFollowers_count(object.getInt("followers_count"));
            usr_info.setFriends_count(object.getInt("friends_count"));
            usr_info.setListed_count(object.getInt("listed_count"));
            usr_info.setLocation(object.getString("location"));
            usr_info.setUrl(object.getString("url"));
            usr_info.setProfile_image_url(object.getString("profile_image_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  usr_info;
    }

    @Override
    public Uri getDeleteUri() {
        return null;
    }

    @Override
    public Uri getInsertUri() {
        return null;
    }

    @Override
    public Uri getUpdateUri() {
        return null;
    }

    @Override
    public Uri getQueryUri() {
        return null;
    }
}
