package com.example.proyexamen;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class DetNoticiaActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
					 
		setContentView(R.layout.lytdetalle_fragmento);
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
			String s = extras.getString("texto");
			TextView view = (TextView) findViewById(R.id.txtContentNoticia);
			view.setText(s);
		}				
					
	}
}
