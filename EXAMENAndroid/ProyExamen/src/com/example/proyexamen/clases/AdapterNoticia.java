package com.example.proyexamen.clases;

import java.util.List;

import com.example.proyexamen.R;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterNoticia extends ArrayAdapter<item>{

	Activity activityPadre = null;
	List<item> lista = null;
		
	public AdapterNoticia (Fragment fragmento, List<item> lista){
		super(fragmento.getActivity(), R.layout.lytlistnoticias, lista);
		this.lista = lista;
		this.activityPadre = fragmento.getActivity();
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {		
		LayoutInflater inflater = activityPadre.getLayoutInflater();
        View view = inflater.inflate(R.layout.lytfilanoticia, null);
		
        TextView emisor = (TextView) view.findViewById(R.id.txtTitulo);
        emisor.setText(lista.get(position).getTitulo());

        TextView asunto = (TextView) view.findViewById(R.id.txtDescripcion);
        /*String desc = lista.get(position).getDescripcion();
        if(desc.length() > 50){
        	desc = desc.substring(0, 47) + "...";
        }*/
        asunto.setText(lista.get(position).getPubdate());
		
		return view;
	}
	
	
}
