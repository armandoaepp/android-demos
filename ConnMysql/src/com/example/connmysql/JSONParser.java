package com.example.connmysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class JSONParser extends AsyncTask<String, Void, String> {

	static InputStream is = null;

	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {
		 doInBackground() ;
	}

	@Override
	protected String doInBackground(String... params) {
		String posturl =  "http://planeatec.com/app/android_app/view/appAndoid.php";
		String text ;
		try {
			// creamos un cliente HTTP -> cliente para comunicar con el servidor web
			HttpClient httpclient = new DefaultHttpClient();
			// objeto httppost
			HttpPost httppost = new HttpPost(posturl);
			// respuesta que se obtiene desde el servidor web para guardarlo en  este objeto
			HttpResponse resp = httpclient.execute(httppost);
			// creamos un entidad formato de texto legible
			HttpEntity ent = resp.getEntity();
			// convertimos a string 
			 text = EntityUtils.toString(ent);
			Log.e("JSON data: ", text );
			return text;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			 text ="errot : UnsupportedEncodingException" ; 
			 Log.e("ERRO en Conexion : ",text);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			text ="errot : ClientProtocolException" ; 
			Log.e("ERRO en Conexion : ",text);
			
		} catch (IOException e) {
			Log.d("IO", e.getMessage().toString());
			e.printStackTrace();
			text ="errot : IOException" ; 
			Log.e("ERRO en Conexion : ",text);

		}
		return text;
		// Making HTTP request
		/*try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpPost = new HttpGet();

			HttpResponse getResponse = httpClient.execute(httpPost);
			final int statusCode = getResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;
			}

			HttpEntity getResponseEntity = getResponse.getEntity();

			// HttpResponse httpResponse = httpClient.execute(httpPost);
			// HttpEntity httpEntity = httpResponse.getEntity();
			is = getResponseEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("IO", e.getMessage().toString());
			e.printStackTrace();

		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			
			
			
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj.toString();
		
		*/

	}

	protected void onPostExecute(String page) {
		// onPostExecute
	}
}