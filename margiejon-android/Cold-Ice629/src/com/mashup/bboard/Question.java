package com.mashup.bboard;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        questionId = getIntent().getIntExtra("com.mashup.bboard.classID", 0);
        title = (TextView)findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("com.mashup.bboard.title"));
        question = (TextView)findViewById(R.id.questionview);
        question.setText("Is this a question?");
        respond = (Button)findViewById(R.id.respond);
        respond.setOnClickListener(respondClick);
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
				startActivity(i);
			}
	 };
}
