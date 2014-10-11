package com.planeatec.ejemplo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private final static String TAG = "Debug" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_submit = (Button)findViewById(R.id.btn_submit);
       btn_submit.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// mensaje en consola
			Log.e(TAG, "Hizo Click");
			// Mensaje en pantalla 
			Toast.makeText(getApplicationContext(), "Hizo Click", Toast.LENGTH_SHORT).show();
			
			EditText edit_name = (EditText)findViewById(R.id.edit_name) ;
			TextView txt_msg = (TextView)findViewById(R.id.txt_msg);
			txt_msg.setText("Hola"+edit_name.getText());
			
			
		}
        	
        }) ;
 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
