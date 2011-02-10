package com.mashup.bboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Posts extends Activity {
	/** Called when the activity is first created. */
	ArrayList <String> Questions;
	static final Integer questionIndex = 13;
	static final Integer idIndex = 2;
	Button postNew;
	//Button resolve;
	TextView title;
	TextView noquestions;
	ListView listview;
	String test;
	Integer courseId;
	TableLayout tblayout;
	HashMap<String, Integer> question_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);   
        courseId = getIntent().getIntExtra("com.mashup.bboard.classID", 0);
        title = (TextView)findViewById(R.id.posttv);
        title.setText("Select a question to view it!");
        postNew = (Button)findViewById(R.id.postbutton);
        postNew.setOnClickListener(postNewQuestion);
        tblayout = (TableLayout)findViewById(R.id.tblayout);
        listview = (ListView)findViewById(R.id.postlist);
        listview = (ListView)findViewById(R.id.postlist);
        updateHTML();
        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	Intent i = new Intent(Posts.this, Question.class);
            	i.putExtra("com.mashup.bboard.questionID", question_id.get(((TextView) view).getText()).intValue());
            	i.putExtra("com.mashup.bboard.title", ((TextView) view).getText());
            	startActivityForResult(i, 0);
            }
          });
      }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	updateHTML();
    }
    public void updateHTML(){
    	HttpClient httpClient = new DefaultHttpClient();
  		HttpGet getMethod = new HttpGet("http://cold-ice-629.heroku.com/questions/by_course_id/" + courseId.toString() + ".json");
      	HttpResponse response;
  		HttpEntity entity;
  		
  		try {
  			question_id = new HashMap<String, Integer>();
  			Questions = new ArrayList<String>();
  			String questions[];
  			response = httpClient.execute(getMethod);
  			entity = response.getEntity();
  			String contentString = convertStreamToString(entity.getContent());
  			JSONArray jarray = new JSONArray(contentString);
  			for (int i = 0; i < jarray.length(); i++) {
  				JSONObject jobject = jarray.getJSONObject(i);
  				String questionInfo = jobject.getString("question").toString();
  				questions = questionInfo.split("\"");
  				String question = questions[questionIndex];
  				question = question.replace(".", "");
  				String str_id = questions[idIndex].replaceAll(":", "");
  				str_id = str_id.replaceAll(",", "");
  				Integer id = Integer.parseInt(str_id);
  				question_id.put(question, id);
  				Questions.add(question); 
  			}
  		} catch (Throwable t) {
      		Log.e("Networking", "Exception in getStatus()", t);
  		}
      	
          if (Questions.size() == 0){
          	TextView noquestions = new TextView(Posts.this);
          	noquestions.setText("There are currently no questions for this Class. Click the button above to create one.");
          	noquestions.setTextColor(Color.GREEN);
          	noquestions.setTextSize(18);
          	tblayout.addView(noquestions);        	
          }
          Collections.sort(Questions);
          ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Questions);
          listview.setAdapter(classAdapter); 
      
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



private View.OnClickListener postNewQuestion = new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		//TODO: FINISH QUESTION POST
		/*try {   		
    		
     		HttpPost post = new HttpPost("https://cold-ice-629.heroku.com/questions/" + courseId + ".json");
    		//Question, title, course_id
    		//POST WITH COURSEID
    		
    		//post.addHeader("Authorization", "Basic "+encodedString);
    		
    		List<NameValuePair> form = new ArrayList<NameValuePair>(3);
    		//form.add(new BasicNameValuePair("text", s));
    		form.add(new BasicNameValuePair("question", question));
    		form.add(new BasicNameValuePair("title", title));
    		form.add(new BasicNameValuePair("course_id", course_id));
    		post.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));
    		
    		ResponseHandler<String> responseHandler = new BasicResponseHandler();
    		client.execute(post, responseHandler);
    		return 0;
 
    	} catch (Throwable t) {
    		Log.e("Networking", "Exception in updateStatus()", t);
    		goBlooey(t);
    	}
		*/
	}
};
/*
private View.OnClickListener resolveQuestion = new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		//TODO: FINISH QUESTION RESOLVE
		/*try {   		
    		
     		HttpPost post = new HttpPost("https://cold-ice-629.heroku.com/questions/" + courseId + ".json");
    		//Question, title, course_id
    		//POST WITH COURSEID
    		
    		//post.addHeader("Authorization", "Basic "+encodedString);
    		
    		List<NameValuePair> form = new ArrayList<NameValuePair>(3);
    		//form.add(new BasicNameValuePair("text", s));
    		form.add(new BasicNameValuePair("question", question));
    		form.add(new BasicNameValuePair("title", title));
    		form.add(new BasicNameValuePair("course_id", course_id));
    		post.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));
    		
    		ResponseHandler<String> responseHandler = new BasicResponseHandler();
    		client.execute(post, responseHandler);
    		return 0;
 
    	} catch (Throwable t) {
    		Log.e("Networking", "Exception in updateStatus()", t);
    		goBlooey(t);
    	}
	}
};
*/
}
