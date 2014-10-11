package com.example.proyexamen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.proyexamen.clases.Usuario;

public class MainActivity extends Activity implements OnClickListener {
	EditText txtusu;
	EditText txtpass;
	Button btnlogin;
	ProgressDialog progressDialog;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lytlogin);
		txtusu = (EditText) findViewById(R.id.txtUsuario);
		txtpass = (EditText) findViewById(R.id.txtContrasena);
		btnlogin = (Button) findViewById(R.id.btnLogin);
	}
	

	@Override
	protected void onResume() {
		super.onResume();		
		btnlogin.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {		
		switch(v.getId()){
			case R.id.btnLogin:
				VerificarLogin(getApplicationContext().getString(R.string.url_login), txtusu.getText().toString(), txtpass.getText().toString());				
		}
	}

	
	private void VerificarLogin(final String url, final String usuario, final String password){
		progressDialog = new ProgressDialog(this);
	    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    progressDialog.setTitle("Examen");
	    progressDialog.setMessage("Loading...");
	    progressDialog.setCancelable(false);
	    progressDialog.show();
	    
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				Usuario usu = new Usuario(url, usuario, password);
				String codigo = usu.leer();
				Message msg = new Message();
				msg.obj = codigo;
				handle.sendMessage(msg);
			}
		});
		th.start();		
	}
	
	
	private Handler handle = new Handler(){
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub			
			String valor = (String) msg.obj;						
			if(!valor.equals("-1") && !valor.equals("-2")){
				//progressDialog.dismiss();
				//Toast.makeText(MainActivity.this, valor, Toast.LENGTH_LONG).show();
				
				Intent i = new Intent(MainActivity.this, ListNoticiasActivity.class);
				startActivity(i);
				progressDialog.dismiss();
			}else if(valor.equals("-1")){
				progressDialog.dismiss();
				Toast.makeText(MainActivity.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();				
			}else if(valor.equals("-2")){
				progressDialog.dismiss();
				Toast.makeText(MainActivity.this, "Error de Lectura", Toast.LENGTH_LONG).show();
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
