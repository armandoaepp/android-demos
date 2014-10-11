package com.example.connmysql;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
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

public class MainActivity extends ActionBarActivity {
	private static final String TAG = "Debug";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn_submit = (Button) findViewById(R.id.btn_data);
		btn_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// mensaje en consola
				Log.e(TAG, "Hizo Click");
				// Mensaje en pantalla
				Toast.makeText(getApplicationContext(), "Hizo Click",
						Toast.LENGTH_SHORT).show();
				JSONParser json = new JSONParser() ;
				
				

			}

		});
	
		
		
		/*
		httpHandler handler = new httpHandler();
		String URL_connect = "http://planeatec.com/app/android_app/view/appAndoid.php";
		String txt = "holas";
		//String txt = handler.post("http://planeatec.com/app/android_app/view/appAndoid.php");
		
	
		TextView txt_data = (TextView) findViewById(R.id.txt_data);
		txt_data.setText("planeatec :"+txt);

		// Toast.makeText(getApplicationContext(),"holas->"+txt, 1);
		

		
		ArrayList<NameValuePair> postparameters2send = new ArrayList<NameValuePair>();

		postparameters2send.add(new BasicNameValuePair("usuario", "Armando"));
		postparameters2send.add(new BasicNameValuePair("password", "pisfil"));

		// realizamos una peticion y como respuesta obtenes un array JSON
		JSONArray jdata = handler.getserverdata(postparameters2send, URL_connect);
		
		Log.e(TAG, txt.toString());
		txt_data.setText(txt + " - "+jdata.toString());
		
		if (jdata != null && jdata.length() > 0) {

			for (int i = 0; i < jdata.length(); i++) {
				try {
					JSONObject jsonObject = jdata.getJSONObject(i);
					int var1 = jsonObject.getInt("nParCodigo");
					String var2 = jsonObject.getString("cParNombre");
					String var3 = jsonObject.getString("cParDescripcion");
					Log.e("Num : " + i, "var1: " + var1 + " var2: " + var2
							+ " var3: " + var3);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else { // json obtenido invalido verificar parte WEB.
			Log.e("JSON  ", "ERROR");
		}
		*/

		
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
