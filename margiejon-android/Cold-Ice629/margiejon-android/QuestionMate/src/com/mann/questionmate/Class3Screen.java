package com.mann.questionmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Class3Screen extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class3screenmain);
    
    final Button daButton = (Button) findViewById(R.id.BackButton);
    daButton.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            // Perform action on clicks
            Toast.makeText(Class3Screen.this, "Welcome to the Next Activity!", Toast.LENGTH_SHORT).show();
            Intent theIntent = new Intent();
    		Bundle bundle = new Bundle();
    		bundle.putString("data", "This is theoretically data.");
    		theIntent.putExtras(bundle);
    		setResult(RESULT_OK, theIntent);
    		finish();
        }
    });
    
    final Button newQButton = (Button) findViewById(R.id.AddQuestionButton);
    newQButton.setOnClickListener(new OnClickListener() {
    	public void onClick(View v){
    		Toast.makeText(Class3Screen.this, "Tester tester alskdjf", Toast.LENGTH_SHORT).show();
    	}
    });
    
    
    }
}

