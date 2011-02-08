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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Classes extends Activity implements OnItemClickListener {
    /** Called when the activity is first created. */
	ArrayList <String> Classes;
	static final Integer classIndex = 13;
	static final Integer idIndex = 6;
	TextView title;
	ListView listview;
	HashMap<String, Integer> course_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes);
        title = (TextView)findViewById(R.id.tv);
        title.setText("Please Choose a Course");
        listview = (ListView)findViewById(R.id.list);
        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	Intent i = new Intent(Classes.this, Posts.class);
            	i.putExtra("com.mashup.bboard.classID", course_id.get(((TextView) view).getText()).intValue());
            	startActivity(i);
            	//Toast.makeText(Classes.this, course_id.get(((TextView) view).getText()).toString(),
            		//	Toast.LENGTH_SHORT).show();
            }
          });
        
        HttpClient httpClient = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet("http://cold-ice-629.heroku.com/courses.json");
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
	}

}