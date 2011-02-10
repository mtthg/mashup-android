package com.mann.questionmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuestionMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        final Button button1 = (Button) findViewById(R.id.Class1Button);
        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(QuestionMain.this, "Class 1 Works", Toast.LENGTH_SHORT).show();
                Intent button1Pushed = new Intent(v.getContext(), Class1Screen.class);
                startActivity(button1Pushed);
            }
        });
        
        final Button button2 = (Button)findViewById(R.id.Class2Button);
        button2.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v){
        		Toast.makeText(QuestionMain.this, "Class 2 Works", Toast.LENGTH_SHORT).show();
        		Intent button2Pushed = new Intent(v.getContext(), Class2Screen.class);
        		startActivity(button2Pushed);
        	}
        });
        
        final Button button3 = (Button)findViewById(R.id.Class3Button);
        button3.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v){
        		Toast.makeText(QuestionMain.this, "Class 3 Works", Toast.LENGTH_SHORT).show();
        		Intent myIntent = new Intent(v.getContext(), Class3Screen.class);
                myIntent.putExtra("Data Transferred!", "Aaaaaand we're moving on");
                startActivity(myIntent);
        	}
        });
    }
    
 
}
    