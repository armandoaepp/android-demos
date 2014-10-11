package com.example.proyexamen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.example.proyexamen.clases.item;
import com.example.proyexamen.fragmentos.frgDetallesNoticias;
import com.example.proyexamen.fragmentos.frgListadoNoticias;
import com.example.proyexamen.fragmentos.frgListadoNoticias.NoticiaListener;

public class ListNoticiasActivity extends FragmentActivity implements NoticiaListener {

	frgListadoNoticias frgListado = null;
	frgDetallesNoticias frgDetalless = null;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.lytcont_fragmento);		
		frgListado = (frgListadoNoticias) getSupportFragmentManager().findFragmentById(R.id.listaFragment);	
		frgListado.setNoticiaListener(this);
	}
	
	
	@Override
	public void onNoticiaSeleccionado(item e) {
		
		boolean Detalle = (getSupportFragmentManager().findFragmentById(R.id.detallesFragment) != null);
		
		if(Detalle) {			
			frgDetalless = (frgDetallesNoticias) getSupportFragmentManager().findFragmentById(R.id.detallesFragment);
			frgDetalless.mostrarContenido(e.getDescripcion());
		}else {
			Intent i = new Intent(this, DetNoticiaActivity.class);
			i.putExtra("texto", e.getDescripcion());
			startActivity(i);
		}
	}
	
}




