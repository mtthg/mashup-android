package com.mashup.bboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Response extends Activity{
	private Integer questionId;
	Button submit;
	private EditText response;
	private HttpClient client;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response);
		submit = (Button)findViewById(R.id.submit);
		response = (EditText)findViewById(R.id.respond);
		submit.setOnClickListener(submitClick);
		questionId = getIntent().getIntExtra("com.mashup.bboard.questionId", -1);
        client = new DefaultHttpClient();
       
    }
	private View.OnClickListener submitClick = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			try {
	     		HttpPost post = new HttpPost("https://cold-ice-629.heroku.com/questions/mobile/responses.json");
	    		//Question, title, course_id
	    		//POST WITH COURSEID
	     		String toPost = response.getText().toString();
	     		String strQId = questionId.toString();
	    		
	    		List<NameValuePair> form = new ArrayList<NameValuePair>(2);
	    		form.add(new BasicNameValuePair("content", toPost));
	    		form.add(new BasicNameValuePair("id", strQId));
	    		post.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));
	    		
	    		ResponseHandler<String> responseHandler = new BasicResponseHandler();
	    		client.execute(post, responseHandler); 
	    	} catch (Throwable t) {
	    		Log.e("Networking", "Exception in updateStatus()", t);
	    	}
	}
};
}