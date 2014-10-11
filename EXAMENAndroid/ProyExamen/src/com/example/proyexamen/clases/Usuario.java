package com.example.proyexamen.clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Usuario {
	private int idusuario = 0;
	private String nombcomp = "";
	private String loginusu = "";
	private String password = "";
	private URL url = null;
	HttpURLConnection conexion;
	JSONObject jObj = null;
	JSONArray node = null;
	private static final String TAG_MAIN = "results";
	private static final String TAG_ID = "idusuario";
	
	
	public Usuario(String url, String usuario, String password){
		try{
			this.url = new URL(url);
			this.loginusu = usuario;
			this.password = password;	
		}catch(MalformedURLException e){
			
		}
	}
	
	
	public String leer(){	
		String result ="";
		String json = sendData();	
		try {
			jObj = new JSONObject(json);			
			if(!jObj.get(TAG_MAIN).toString().equals("-1")){				
				node = jObj.getJSONArray(TAG_MAIN);				
				for(int x=0; x<node.length();x++){
					JSONObject item_node = node.getJSONObject(x);					
					result = item_node.getString("idusuario");					
				}				
				return result;
			}
			return "-1";			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("ERROR  leer : ", e.getMessage());
			return "-2";
		}
	}
	
	private String sendData(){		
		try {			
			StringBuilder construye = new StringBuilder();			
			conexion = (HttpURLConnection) url.openConnection();						
			conexion.setRequestMethod("POST");			
			conexion.setDoInput(true);			
			conexion.setDoOutput(true);	
			
			PrintWriter printer = new PrintWriter(conexion.getOutputStream());
			printer.print(parametros(loginusu, password));
			printer.close();
			
			InputStreamReader input = new InputStreamReader(conexion.getInputStream());
			BufferedReader buffer = new BufferedReader(input);
			
			String linea;
			while((linea=buffer.readLine()) !=null){
				construye.append(linea);
			}
			return construye.toString();		
		} catch (IOException e) {
			e.printStackTrace();
			Log.i("ERROR  SENDDATA : ", e.getMessage());
			return null;
		}		
	} 
	
	
	private String parametros(String usuario,String password){
		ArrayList<NameValuePair> values = new ArrayList<NameValuePair>();		 
        values.add(new BasicNameValuePair("txtUsuario",usuario));
        values.add(new BasicNameValuePair("txtContrasena",password));	         
        String param="";
        try {        	 
       	 for (NameValuePair nvp : values) {        		 
       		 if (param == "")
					param=nvp.getName() + "=" + URLEncoder.encode(nvp.getValue(),"UTF-8");								
       		  else 
       			param+= "&" + nvp.getName() + "=" + URLEncoder.encode(nvp.getValue(),"UTF-8");        		 
       	 }        	         	 
    	} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}        
        return param;
	}
	
	
}
