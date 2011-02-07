package com.mann.questionmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Class1Screen extends Activity{
	/** Called when the activity is first created. */
	DBAdapter db = new DBAdapter(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class3screenmain);
        
        Button addNewQ = (Button) findViewById(R.id.AddQuestionButton);
        addNewQ.setOnClickListener(qListener);
        
        
    
    final Button daButton = (Button) findViewById(R.id.BackButton);
    daButton.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            // Perform action on clicks
            Toast.makeText(Class1Screen.this, "Back", Toast.LENGTH_SHORT).show();
            Intent theIntent = new Intent();
    		Bundle bundle = new Bundle();
    		bundle.putString("data", "This is theoretically data.");
    		theIntent.putExtras(bundle);
    		setResult(RESULT_OK, theIntent);
    		finish();
        }
    });
    }
    
    private OnClickListener qListener = new OnClickListener()
    {
    	public void onClick(View v){
        // create a database and need to get the stuff via httpclient to fill it and then 
    	// to add a new question, make an extra entry in this database
    		}
    	};
    }

    
