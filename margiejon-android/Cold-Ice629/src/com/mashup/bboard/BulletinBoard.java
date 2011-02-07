package com.mashup.bboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class BulletinBoard extends Activity implements OnItemSelectedListener {
    /** Called when the activity is first created. */
	ArrayList <String> Classes;
	static final Integer classIndex = 13;
	TextView title;
	ListView listview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        title = (TextView)findViewById(R.id.tv);
        title.setText("Please Choose a Course");
        listview = (ListView)findViewById(R.id.list);
        
        
        HttpClient httpClient = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet("http://cold-ice-629.heroku.com/courses.json");
    	HttpResponse response;
		HttpEntity entity;
		
		try {
			Classes = new ArrayList<String>();
			String courses[];
			response = httpClient.execute(getMethod);
			entity = response.getEntity();
			String contentString = convertStreamToString(entity.getContent());
			JSONArray jarray = new JSONArray(contentString);
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject jobject = jarray.getJSONObject(i);
				String course = jobject.getString("course").toString();
				courses = course.split("\"");
				Classes.add(courses[classIndex]);
			}
		} catch (Throwable t) {
    		Log.e("Networking", "Exception in getStatus()", t);
		}
    	
       
        Collections.sort(Classes);
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Classes);
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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}