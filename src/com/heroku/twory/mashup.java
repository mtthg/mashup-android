package com.heroku.twory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class mashup extends Activity {
	private DefaultHttpClient client=null;
	private EditText status=null;
	private TextView storyTitle=null;
	private TextView lastSentence=null;
	private String story_id=null;
	private Button receive=null;
	private Button send=null;

	private DefaultHttpClient client2 = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);   

		storyTitle = (TextView) findViewById(R.id.showStoryTitle);
		lastSentence = (TextView) findViewById(R.id.showSentence);
		status = (EditText) findViewById(R.id.addSentence);

		send=(Button)findViewById(R.id.submitSentence);
		send.setOnClickListener(onSend);
		send.setEnabled(false);
		
		status.setOnFocusChangeListener(clear_text);
		
		
		receive =(Button)findViewById(R.id.getSentence);
		receive.setOnClickListener(onReceive);

		client=new DefaultHttpClient();

		client2 = new DefaultHttpClient();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		client.getConnectionManager().shutdown();
		client2.getConnectionManager().shutdown();
	}

	private void updateStatus() {
		Thread t = new Thread(){
			public void run() {
				Looper.prepare(); //For Preparing Message Pool for the child Thread
				try {
					String s=status.getText().toString();

					HttpPost post=new HttpPost("http://twory.heroku.com/lines.js");
					post.addHeader("text","story_id");
					Log.d("Tworypost", post.toString());

					List<NameValuePair> line=new ArrayList<NameValuePair>();

					line.add(new BasicNameValuePair("line[text]", s));
					line.add(new BasicNameValuePair("line[story_id]", story_id));

					post.setEntity(new UrlEncodedFormEntity(line, HTTP.UTF_8));

					HttpResponse httpResponse = client.execute(post);
					if(httpResponse!=null){
						InputStream in = httpResponse.getEntity().getContent(); //Get the data in the entity
						in.close();
					}
//================================
//					HttpGet get = new HttpGet("http://twory.heroku.com/random.json");
//					ResponseHandler<String> responseHandler = new BasicResponseHandler();
//
//					Log.d("GETTING", "Getting get created");
//					try {			
//						String response = client2.execute(get, responseHandler);
//						JSONObject jObject = new JSONObject(response);
//						JSONObject jObjLine = jObject.getJSONObject("last_line").getJSONObject("line");
//						String message = jObjLine.getString("text");
//						story_id = jObjLine.getString("story_id");
//
//						JSONObject jObjStory = jObject.getJSONObject("current_story").getJSONObject("story");
//						String story_title = jObjStory.getString("title");
//
//						storyTitle.setText(story_title);
//						lastSentence.setText(message);
//					}
//
//					catch (Throwable t) {
//						t.printStackTrace();
//						Log.e("MashUp", "Exception in getStuff()", t);
//						errorHandler(t);
//					}

					
					
				}
				catch (Throwable t) {
					Log.e("MashUp", "Exception in updateStatus()", t);
//					errorHandler(t);
				}
				Looper.loop(); //Loop in the message queue
			}
		};
		t.start();      
	}

	private static String converStreamToString(InputStream is) {
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public void getStuff() {
		HttpGet get = new HttpGet("http://twory.heroku.com/random.json");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		try {			
			String response = client2.execute(get, responseHandler);
			JSONObject jObject = new JSONObject(response);
			JSONObject jObjLine = jObject.getJSONObject("last_line").getJSONObject("line");
			String message = jObjLine.getString("text");
			story_id = jObjLine.getString("story_id");

			JSONObject jObjStory = jObject.getJSONObject("current_story").getJSONObject("story");
			String story_title = jObjStory.getString("title");

			storyTitle.setText(story_title);
			lastSentence.setText(message);
		}

		catch (Throwable t) {
			Log.e("MashUp", "Exception in getStuff()", t);
			errorHandler(t);
		}
	}

	private void errorHandler(Throwable t) {
		Toast.makeText(getApplicationContext(), "NO INTERNET!", Toast.LENGTH_SHORT).show();
	}

	private View.OnClickListener onReceive=new View.OnClickListener() {
		public void onClick(View v) {
			getStuff();
			send.setEnabled(true);
			status.setText("Continue the story here");
			receive.requestFocus();
		}
	};

	private View.OnClickListener onSend=new View.OnClickListener() {
		public void onClick(View whomp) {
			updateStatus();
			status.setText("");
			getStuff();
		}
	};
	
	private View.OnFocusChangeListener clear_text=new View.OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View arg0, boolean this_focussed) {
			// TODO Auto-generated method stub
			if (this_focussed) {
				status.setText("");
			}
			
		}
	};
}