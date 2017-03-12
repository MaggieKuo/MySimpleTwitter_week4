package com.mag.codepath.simpletweet.activities;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mag.codepath.simpletweet.R;
import com.mag.codepath.simpletweet.TwitterApplication;
import com.mag.codepath.simpletweet.TwitterClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Optional;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    private static final String TAG = ComposeActivity.class.getSimpleName();
    @BindView(R.id.btnClose)
    ImageButton btnClose;
    @BindView(R.id.button2)
    Button btnTweet;
    @BindView(R.id.editText2)
    EditText editCompose;
    @BindView(R.id.txtLen)
    TextView txtLen;
    private TwitterClient twitterClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        ButterKnife.bind(this);

        twitterClient = TwitterApplication.getRestClient();
    }

    @OnClick(R.id.btnClose)
    void onClickClose() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.button2)
    void OnClickTweet(){
        if (editCompose.length()==0) return;

        Log.d(TAG, "data = " + editCompose.getText().toString());

        twitterClient.UpdateTimeline(editCompose.getText().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "statusCode = " + statusCode);

                getIntent().putExtra("responseBody", new String(responseBody));
                setResult(RESULT_OK, getIntent());
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                setResult(RESULT_FAILURE, );
                Log.d(TAG, "statusCode = " + statusCode );
            }
        });

    }

    @OnTextChanged(R.id.editText2)
    void OnTextChanged() {
//        String data = editCompose.getText().toString();
        if (editCompose.length()>140)
            editCompose.getText().delete(140, editCompose.length());
        txtLen.setText("" + (140-editCompose.length()));
    }
}
