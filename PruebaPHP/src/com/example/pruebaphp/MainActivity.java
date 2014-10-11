package com.example.pruebaphp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // Loading empleados in Background Thread
     		new LoadAllempleados().execute();
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
    
    
 // Progress Dialog
 	private ProgressDialog pDialog;

 	// Creating JSON Parser object
 	JSONParser jParser = new JSONParser();

 	ArrayList<HashMap<String, String>> empleadosList;

 	// url to get all empleados list Reemplaza la IP de tu equipo o la direccion de tu servidor 
 	// Si tu servidor es tu PC colocar IP Ej: "http://127.97.99.200/taller06oct/..", no colocar "http://localhost/taller06oct/.."
 	private static String url_all_empleados = "http://192.168.1.37/android/taller06oct/empleados.php";

 	// JSON Node names
 	private static final String TAG_SUCCESS = "success";
 	private static final String TAG_empleados = "empleados";
 	private static final String TAG_CEDULA = "cedula";
 	private static final String TAG_NOMBRE = "nombre";
 	private static final String TAG_APELLIDO = "apellido";
 	

 	// empleados JSONArray
 	JSONArray empleados = null;
 	
	class LoadAllempleados extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Cargando Empleados. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			
		}

		/**
		 * getting All empleados from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_empleados, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All empleados: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// empleados found
					// Getting Array of empleados
					empleados = json.getJSONArray(TAG_empleados);
					
					TextView txt_msg = (TextView) findViewById(R.id.txt_data);
					txt_msg.setText("DATA:" + empleados);
					 
					

					// looping through All empleados
					/* for (int i = 0; i < empleados.length(); i++) {
						JSONObject c = empleados.getJSONObject(i);

						// Storing each json item in variable
						String cedula = c.getString(TAG_CEDULA);
						String nombre = c.getString(TAG_NOMBRE)+ " " +c.getString(TAG_APELLIDO);
						

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_CEDULA, cedula);
						map.put(TAG_NOMBRE, nombre);
						

						// adding HashList to ArrayList
						empleadosList.add(map);
					}*/
				} else {
					// no empleados found
					// Launch Add New Empleado Activity
					/*Intent i = new Intent(getApplicationContext(),
							NewEmpladoActivity.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);*/
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all empleados
			/*pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					
					ListAdapter adapter = new SimpleAdapter(
							EmpleadosActivity.this, empleadosList,
							R.layout.list_item, new String[] { TAG_CEDULA,
									TAG_NOMBRE },
							new int[] { R.id.cedula, R.id.nombre });
					// updating listview
					setListAdapter(adapter);
				}
			});*/
		}

	}
}

