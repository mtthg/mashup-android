package com.mashup.bboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Response extends Activity{
	private Integer questionId;
	Button submit;
	private EditText response;
	private static final String TAG = "RESPONSE POST";
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response);
		submit = (Button)findViewById(R.id.submit);
		response = (EditText)findViewById(R.id.response);
		submit.setOnClickListener(submitClick);
		questionId = getIntent().getIntExtra("com.mashup.bboard.questionId", -1);
       
    }
	private View.OnClickListener submitClick = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			postResponse();
			finish();
		}
	};

public HttpResponse postResponse() {
	HttpClient client = new DefaultHttpClient();
		String toPost = response.getText().toString();
		String strQId = questionId.toString();
    HttpPost post = new HttpPost("http://cold-ice-629.heroku.com/responses.json");

    Log.i(TAG, "Posting " + toPost + " to http://cold-ice-629.heroku.com/responses.json");
    try {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("body", toPost));
		pairs.add(new BasicNameValuePair("question_id", strQId));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse httpResponse = client.execute(post);

        Log.i(TAG, httpResponse.getStatusLine().toString());
        return httpResponse;
    } catch( Throwable t ) {
        Log.e(TAG, "Post exception",t );
        Toast.makeText(this, "Post failed " + t.toString(), Toast.LENGTH_LONG);
        return null;
    }
}
}