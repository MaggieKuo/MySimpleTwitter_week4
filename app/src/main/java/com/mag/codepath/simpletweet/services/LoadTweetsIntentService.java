package com.mag.codepath.simpletweet.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;


public class LoadTweetsIntentService extends IntentService {
    private static final String ACTION_QUERY_TWEETS = "com.codepath.codepath.mysimpletweets.services.action.LOAD_TWEETS";

    private static final String QUERY_COUNT = "com.codepath.codepath.mysimpletweets.services.extra.COUNT";
    private static final String QUERY_SINCE_ID = "com.codepath.codepath.mysimpletweets.services.extra.SINCE_ID";

    public LoadTweetsIntentService() {
        super("LoadTweetsIntentService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, LoadTweetsIntentService.class);
        intent.setAction(ACTION_QUERY_TWEETS);
        intent.putExtra(QUERY_COUNT, param1);
        intent.putExtra(QUERY_SINCE_ID, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_QUERY_TWEETS.equals(action)) {
                final String queryCount = intent.getStringExtra(QUERY_COUNT);
                final String querySinceId = intent.getStringExtra(QUERY_SINCE_ID);
                handleActionFoo(queryCount, querySinceId);
            }
        }
    }

    private void handleActionFoo(String count, String since_id) {






//        throw new UnsupportedOperationException("Not yet implemented");
    }


}
