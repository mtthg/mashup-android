package com.mashup.bboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Intro extends Activity{
	ImageView logo;
	Button	enter;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        logo = (ImageView)findViewById(R.id.logo_image);
        enter = (Button)findViewById(R.id.enter);
        enter.setOnClickListener(enterClick);
	}
	
	private View.OnClickListener enterClick = new View.OnClickListener() {		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(Intro.this, Classes.class);
			startActivity(i);
		}
	};
}
