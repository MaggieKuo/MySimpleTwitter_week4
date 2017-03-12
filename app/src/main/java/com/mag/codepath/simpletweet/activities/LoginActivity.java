package com.mag.codepath.simpletweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.TwitterClient;


public class LoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}


	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	private void getInfo(){

	}
//
//	public Token checkAccessToken() {
//		return this.prefs.contains("oauth_token") && this.prefs.contains("oauth_token_secret")?new Token(this.prefs.getString("oauth_token", ""), this.prefs.getString("oauth_token_secret", "")):null;
//	}

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
	@Override
	public void onLoginSuccess() {

//		Toast.makeText(this, "Successed!", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, TimelineActivity.class);
		startActivity(intent);
		finish();
	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		getClient().connect();
	}

}
