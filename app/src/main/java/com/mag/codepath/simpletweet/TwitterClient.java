package com.mag.codepath.simpletweet;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
	public static final String REST_URL = "https://api.twitter.com/1.1";
	public static final String REST_CONSUMER_KEY = "nqxckQ01v5W7tl1VyUaNN9y97";
	public static final String REST_CONSUMER_SECRET = "sSOpZPK5WAwwOdpYuXurzsy0QDOxX06iHFqn2orhL0ZMKhJrxC";
	public static final String REST_CALLBACK_URL = "oauth://cpmgtweetsapp";
	private static final String TAG = TwitterClient.class.getSimpleName();
	private static final String MAX_ID = "max_id";
	private static final String PAGE_COUNT = "count";
	private static final String PAGE_SINCE_ID = "since_id";
	private static final int NUM_PAGE_COUNT = 25;

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here
	public void getInterestingnessList(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}


	public void getHomeTimeline(long since_id, AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/home_timeline.json");
//		Log.d(TAG, " HomeTimeline since_id = " + since_id);
		RequestParams params = new RequestParams();

		params.put(PAGE_COUNT, NUM_PAGE_COUNT);
		params.put(PAGE_SINCE_ID, since_id);
		getClient().get(apiUrl, params, handler);

	}

	public void getMentionsTimeline(long since_id, AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		RequestParams params = new RequestParams();
//		Log.d(TAG, " Mentions since_id = " + since_id);
		params.put(PAGE_COUNT, NUM_PAGE_COUNT);
		params.put(PAGE_SINCE_ID, since_id);

		getClient().get(apiUrl, params, handler);

	}

	public void getUserTimeline(long since_id, AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		RequestParams params = new RequestParams();
//		Log.d(TAG, " Mentions since_id = " + since_id);
		params.put(PAGE_COUNT, NUM_PAGE_COUNT);
		params.put(PAGE_SINCE_ID, since_id);

		getClient().get(apiUrl, params, handler);

	}

	public void UpdateTimeline(String data, AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/update.json");
		RequestParams params = new RequestParams();
		params.put("status", data);
		params.put("include_entities", true);

		getClient().post(apiUrl, params, handler);
	}

	public void getProfile(AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("account/update_profile.json");
		getClient().post(apiUrl, null, handler);
	}

}
