package com.example.connmysql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class prueba extends AsyncTask<String, Void, String> {

    InputStream is = null;
    String result = "";
    String error_text="";
    JSONObject j = null;

    protected String  doInBackground(String... urls) {

    // http post
    try {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(urls[0]);
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        HttpParams myParams = null;
        HttpConnectionParams.setConnectionTimeout(myParams, 10000);
        HttpConnectionParams.setSoTimeout(myParams, 10000);

        is = entity.getContent();

    } catch (Exception e) {
        Log.e("log_tag", "Error in http connection " + e.toString());
    }
    return null ;
    }

    protected void onPostExecute(String result) {
   
        try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        result = sb.toString();
        //System.out.println("Result = " + result);

    } catch (Exception e) {
        Log.e("log_tag", "Error converting result " + e.toString());
    }

    // try parse the string to a JSON object
    try {
        //jArray = new JSONObject(result);
    	String response_status ;
        response_status = j.getString("response_status").toString().trim();

        if (response_status.equals("0")) {
            String getcustomer_id = j.getString("customer_id").toString().trim();
            String getPassword = j.getString("password").toString()
                    .trim();
            passData(getcustomer_id, getPassword);
        } else {
            String getcustomer_id = j.getString("response_status").toString()
                    .trim();
            String getPassword = j.getString("error_text").toString()
                    .trim();
            passData(getcustomer_id, getPassword);
        }

    } catch (JSONException e) {
        Log.e("log_tag", "Error parsing data " + e.toString());
    }
        super.onPostExecute(result);
    }
    private static void passData(String getcustomer_id, String getPassword) {
        String id = getcustomer_id;
        String password = getPassword;
    }
}