package com.example.connmysql;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class httpHandler {

	private static final String TAG = "Debug";
	String result = "";
	HttpEntity entity = null;

	// DEVOLVER LA CADENA DIRECTAMENTE
	public String post(String posturl) {
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
			String text = EntityUtils.toString(ent);
			return text;
		} catch (Exception e) {
			return "Error: " + e.getMessage().toString();
		}

	}

	// ENVIAR PARAMETROR MAS URL DEL SERVIDOR
	public JSONArray getserverdata(ArrayList<NameValuePair> parameters,
			String urlwebserver) {

		// conecta via http y envia un post.
		httppostconnect(parameters, urlwebserver);

		if (entity != null) {// si obtuvo una respuesta
			getpostresponse();
			return getjsonarray();

		} else {
			return null;
		}
	}

	// peticion HTTP
	private void httppostconnect(ArrayList<NameValuePair> parametros,
			String urlwebserver) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(urlwebserver);
			httppost.setEntity(new UrlEncodedFormEntity(parametros));
			// ejecuto peticion enviando datos por POST
			HttpResponse response = httpclient.execute(httppost);
			entity = response.getEntity();

		} catch (Exception e) {
			Log.e(TAG, "Error in http connection " + e.toString());
		}

	}

	// Convierte respuesta a String
	public void getpostresponse() {
		try {
			String text = EntityUtils.toString(entity);
			result = text.toString();
			Log.e("getpostresponse", " result= " + text.toString());
		} catch (Exception e) {
			Log.e(TAG, "Error converting result " + e.toString());
		}
	}

	public JSONArray getjsonarray() {
		// parse json data
		try {
			JSONArray jArray = new JSONArray(result);
			return jArray;
		} catch (JSONException e) {
			Log.e(TAG, "Error parsing data " + e.toString());
			return null;
		}

	}

}
