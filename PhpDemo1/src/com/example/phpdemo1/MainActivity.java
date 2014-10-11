package com.example.phpdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView text_1, text_2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new task().execute();
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

	class task extends AsyncTask<String, String, Void> {
		private ProgressDialog progressDialog = new ProgressDialog(
				MainActivity.this);
		InputStream is = null;
		String result = "";

		protected void onPreExecute() {
			progressDialog.setMessage("Download data...");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface arg0) {
					task.this.cancel(true);
				}
			});
		}

		@Override
		protected Void doInBackground(String... params) {
			String url_select = "http://planeatec.com/app/android_app/view/appAndoid.php";

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url_select);

			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

			try {
				httpPost.setEntity(new UrlEncodedFormEntity(param));

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();

				// read content
				is = httpEntity.getContent();

			} catch (Exception e) {

				Log.e("log_tag", "Error in http connection " + e.toString());
			}
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "Error converting result " + e.toString());
			}

			return null;

		}

		protected void onPostExecute(Void v) {

			// ambil data dari Json database
			try {
				JSONArray Jarray = new JSONArray(result);
				for (int i = 0; i < Jarray.length(); i++) {
					JSONObject Jasonobject = null;
					text_1 = (TextView) findViewById(R.id.txt1);
					Jasonobject = Jarray.getJSONObject(i);

					// get an output on the screen
					String id = Jasonobject.getString("nParCodigo");
					String name = Jasonobject.getString("cParNombre");
					String password = Jasonobject.getString("cParDescripcion");
					text_1.append(id + "\t\t" + name + "\t\t" + password
							+ "\t\t" + "\n");

				}
				this.progressDialog.dismiss();

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "Error parsing data " + e.toString());
			}
		}
	}
}
