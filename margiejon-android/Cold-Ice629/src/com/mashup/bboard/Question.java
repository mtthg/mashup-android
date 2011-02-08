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
import android.os.Bundle;
//import android.util.Log;

public class Question extends Activity{
	Integer questionId;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        questionId = getIntent().getIntExtra("com.mashup.bboard.classID", 0);
        //TODO:Finish Question Processing and UI
        //HttpClient httpClient = new DefaultHttpClient();
		//HttpGet getMethod = new HttpGet("http://cold-ice-629.heroku.com/questions/" + questionId.toString() + ".json");
    	//HttpResponse response;
		//HttpEntity entity;
        /*
        try {
			response = httpClient.execute(getMethod);
			entity = response.getEntity();
			String contentString = convertStreamToString(entity.getContent());
			JSONArray jarray = new JSONArray(contentString);
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject jobject = jarray.getJSONObject(i);
				String questionInfo = jobject.getString("question").toString();
				questions = questionInfo.split("\"");
				String question = questions[questionIndex];
				String str_id = questions[idIndex];
				str_id.replaceAll(":", "");
				str_id = str_id.replaceAll(",", "");
				Integer id = Integer.parseInt(str_id);
				question_id.put(question, id);
				Questions.add(question); 
			}
		} catch (Throwable t) {
    		Log.e("Networking", "Exception in getStatus()", t);
		} */
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
}
