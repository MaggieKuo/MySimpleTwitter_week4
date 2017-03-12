package com.mag.codepath.simpletweet.db;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider;

@ContentProvider(authority = TwitterDatabase.AUTHORITY,
                    database = TwitterDatabase.class,
                    baseContentUri = TwitterDatabase.BASE_CONTENT_URI)
@Database(name = TwitterDatabase.NAME,
        version = TwitterDatabase.VERSION)
public class TwitterDatabase {

    public static final String NAME = "TwitterDatabase";
    public static final int VERSION = 1;

    public static final String AUTHORITY = "com.codepath.mysimpletweets.tweetcontentprovider";
    public static final String BASE_CONTENT_URI = "content://";
}
