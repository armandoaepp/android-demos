package com.example.proyexamen.clases;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class leerXML {

	private URL urlrss;
	InputStream input; 
	HttpURLConnection conexion;
	
	public leerXML(String url){		
		try {
            this.urlrss = new URL(url);
        } 
		catch (MalformedURLException e) {
            throw new RuntimeException(e);           
        }
	}
		
	private InputStream conseguirEntradaDatos() {
        try {
        	conexion = (HttpURLConnection) urlrss.openConnection();
        	input = conexion.getInputStream();	
            return input;
        } 
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	public List<item> LeerXML(){
		List<item> lista = null;
		XmlPullParser analizar = Xml.newPullParser();
		try{
			analizar.setInput(conseguirEntradaDatos(), null);
			int evento = analizar.getEventType();
			item noticia = null;
			
			while(evento != XmlPullParser.END_DOCUMENT){
				String nombreTag = null;
				
				switch(evento){
					case XmlPullParser.START_DOCUMENT :
						lista = new ArrayList<item>();
						break;					
					case XmlPullParser.START_TAG : 
						nombreTag = analizar.getName();
						
						if(nombreTag.equals("item")){
							noticia = new item();
						}
						if(nombreTag.equals("title") && noticia != null){
							noticia.setTitulo(analizar.nextText());
						}						
						if(nombreTag.equals("pubDate") && noticia != null){
							noticia.setPubdate(analizar.nextText());
						}
						if(nombreTag.equals("description") && noticia != null){
							noticia.setDescripcion(analizar.nextText());
						}
						
						break;						
					case XmlPullParser.END_TAG : 
						nombreTag = analizar.getName();
						if(nombreTag.equals("item") && noticia!=null){
							lista.add(noticia);
						}
						break;
				}
				evento = analizar.next();
			}
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
		return lista;
	}
	
	
	
	

	
}
