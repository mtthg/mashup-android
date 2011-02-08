package com.mashup.bboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Posts extends Activity {
	/** Called when the activity is first created. */
	ArrayList <String> Classes;
	static final Integer classIndex = 13;
	static final Integer idIndex = 6;
	Button postNew;
	Button resolve;
	TextView title;
	ListView listview;
	String test;
	HashMap<String, Integer> course_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);
        final Integer courseId = getIntent().getIntExtra("com.mashup.bboard.classID", 0);
        title = (TextView)findViewById(R.id.posttv);
        title.setText("Select a question to view it!");
        postNew = (Button)findViewById(R.id.postbutton);
        postNew.setOnClickListener(postNewQuestion);
        resolve = (Button)findViewById(R.id.resolvebutton);
        resolve.setOnClickListener(resolveQuestion);
        listview = (ListView)findViewById(R.id.postlist);
        listview = (ListView)findViewById(R.id.postlist);
        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	//Intent i = new Intent(Classes.this, Posts.class);
            	//i.putExtra("com.mashup.bboard.classID", course_id.get(((TextView) view).getText()).intValue());
            	//startActivity(i);
            	Toast.makeText(Posts.this, courseId.toString(),
            			Toast.LENGTH_SHORT).show();
            }
          });
       
        Classes = new ArrayList<String>();
        String fakeClasses[] = new String[5];
        fakeClasses[0] = "FBE";
        fakeClasses[1] = "Soft Design";
        fakeClasses[2] = "Soft Sys";
        fakeClasses[3] = "UOCD";
        fakeClasses[4] = "Eng in Java";
        
        for (int i = 0; i < fakeClasses.length; i++){
        	Classes.add(fakeClasses[i]);
        }
        
        Collections.sort(Classes);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Classes);
        listview.setAdapter(classAdapter); 
        //Toast.makeText(this, class_id.toString(), Toast.LENGTH_SHORT).show();
        /*
        
        
        HttpClient httpClient = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet("http://cold-ice-629.heroku.com/questions.json");
    	HttpResponse response;
		HttpEntity entity;
		
		try {
			course_id = new HashMap<String, Integer>();
			Classes = new ArrayList<String>();
			String courses[];
			response = httpClient.execute(getMethod);
			entity = response.getEntity();
			String contentString = convertStreamToString(entity.getContent());
			JSONArray jarray = new JSONArray(contentString);
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject jobject = jarray.getJSONObject(i);
				String courseInfo = jobject.getString("course").toString();
				courses = courseInfo.split("\"");
				String course = courses[classIndex];
				String str_id = courses[idIndex].replace(":", "");
				str_id = str_id.replace(",", "");
				Integer id = Integer.parseInt(str_id);
				course_id.put(course, id);
				Classes.add(course); 
			}
		} catch (Throwable t) {
    		Log.e("Networking", "Exception in getStatus()", t);
		}
    	
       
        Collections.sort(Classes);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Classes);
        listview.setAdapter(classAdapter); */
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
		
	}
};

private View.OnClickListener resolveQuestion = new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
	}
};

}