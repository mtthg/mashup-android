package com.mashup.bboard;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

//import java.util.ArrayList;
//import java.util.HashMap;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONArray;
//import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
//import android.util.Log;

public class Question extends Activity{
	private Integer questionId;
	private TextView title;
	private TextView question;
	private Button respond;
	private Button remove;
	private HttpClient client;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        questionId = getIntent().getIntExtra("com.mashup.bboard.questionID", 0);
        title = (TextView)findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("com.mashup.bboard.title"));
        question = (TextView)findViewById(R.id.questionview);
        question.setText("Is this a question?");
        respond = (Button)findViewById(R.id.respond);
        respond.setOnClickListener(respondClick);
        remove = (Button)findViewById(R.id.delete);
        remove.setOnClickListener(deleteClick);
        client = new DefaultHttpClient();
    }
	 public static String convertStreamToString(InputStream is) {

	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        StringBuilder sb = new StringBuilder();
	        String line = null;

	        try {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }
	 
	 private View.OnClickListener respondClick = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Question.this, Response.class);
				i.putExtra("com.mashup.bboard.questionId", questionId.intValue());
				startActivityForResult(i, 0);
			}
	 };
	 
	 private View.OnClickListener deleteClick = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
		     		HttpDelete delete = new HttpDelete("http://cold-ice-629.heroku.com/questions/" + questionId.toString());
		    		client.execute(delete);
		    		Intent i = new Intent();
					i.putExtra("com.example.helloandroid.sucess", 1);
					Question.this.setResult(RESULT_OK, i);
					Question.this.finish();
		    	} catch (Throwable t) {
		    		Log.e("Networking", "Exception in updateStatus()", t);
		    		Intent i = new Intent();
					i.putExtra("com.example.helloandroid.sucess", 0);
					Question.this.setResult(RESULT_CANCELED, i);
					Question.this.finish();
		    	}
				
			}
	 };
}
