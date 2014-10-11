package com.example.proyexamen.fragmentos;

import com.example.proyexamen.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class frgDetallesNoticias extends Fragment{

	TextView contenido;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.lytnotcontent, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);		
		contenido = (TextView) view.findViewById(R.id.txtContentNoticia); 
	}
	
	public void mostrarContenido(String texto){
		contenido.setText(texto);		
	}
	
	
}
