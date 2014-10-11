package com.example.proyexamen.fragmentos;


import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.proyexamen.R;
import com.example.proyexamen.clases.AdapterNoticia;
import com.example.proyexamen.clases.item;
import com.example.proyexamen.clases.leerXML;

public class frgListadoNoticias extends Fragment{
	List<item> lista = null;
	ProgressDialog progress =null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);			
		/*progress = new ProgressDialog(this.getActivity());
		progress.setTitle("Examen");
		progress.setMessage("leyendo datos");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.show();*/
		CargarXML();
	}
	
	private void CargarXML(){
		new Thread(new Runnable() {
			public void run() {
				try{
					leerXML lx = new leerXML("http://www.andina.com.pe/rss/Economia.xml");				
					lista = lx.LeerXML();
					AdapterNoticia adapter = new AdapterNoticia(frgListadoNoticias.this, lista);
					Message msg = new Message();
					msg.obj = adapter;
					handler.sendMessage(msg);					
				}
				catch(Exception ex){
					Log.i("error: ", ex.getMessage());
				}
			}
		}).start();		
	}
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {		
			/*
			if(msg.obj!=null){
				progress.dismiss();
			}
			*/
			lst = (ListView) getView().findViewById(R.id.lstnoticias);	
			AdapterNoticia adapter = (AdapterNoticia)  msg.obj;
			if(adapter != null){
				lst.setAdapter(adapter);
			}
			lst.setOnItemClickListener(new OnItemClickListener() {
	        	@Override
	        	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {        			        		
	        		if (listener!=null) {
	        			item e = (item) lst.getAdapter().getItem(pos);
	        			listener.onNoticiaSeleccionado(e);
					}        		
	        	}        	
			});
		};		
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.lytlistnoticias, container, false);		
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		try{
			
			/*
			lst = (ListView) getView().findViewById(R.id.lstnoticias);		         
			lst.setAdapter(new AdapterNoticia(this, lista));
	       */
	        /*lst.setOnItemClickListener(new OnItemClickListener() {
	        	@Override
	        	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {        			        		
	        		if (listener!=null) {
	        			item e = (item) lst.getAdapter().getItem(pos);
	        			listener.onNoticiaSeleccionado(e);
					}        		
	        	}        	
			});*/
	        //Log.i("****** onActivityCreated **********", "****");
		}
		catch(Exception ex){
			Log.i("error", ex.getMessage());
		}
	}
	
	
	ListView lst;	
	private NoticiaListener listener;
	
	public interface NoticiaListener {
		void onNoticiaSeleccionado(item i);
	}
	
	public void setNoticiaListener(NoticiaListener listener) {
		this.listener = listener;
	}
	
}

