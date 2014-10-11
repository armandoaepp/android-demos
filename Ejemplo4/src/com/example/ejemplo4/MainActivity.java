package com.example.ejemplo4;

import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends Activity {
	private static final ArrayList<String> names = new ArrayList<String>();
	
	public static final String NAME_TAG ="Debug";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        final EditText input_name = (EditText)findViewById(R.id.edit_name);
        ListView list = (ListView)findViewById(R.id.list_of_names);
        Button btn_submit = (Button)findViewById(R.id.btn_submit);
        
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, names); ;
        list.setAdapter(adapter);
        
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String name = new adapter.getItem(position) ; 
				
			}
        	
		});
        
        
        names.add("Dato Incial ") ;
        btn_submit.setOnClickListener(new OnClickListener() {        	
			@Override
			public void onClick(View v) {
				String name = input_name.getText().toString();
				// Log.e("TAG","fueeeraaa al IF "+name);
				if(!names.contains(name))
				{
					//Log.e("TAG","Entra al IF ");
					names.add(name) ;
					adapter.notifyDataSetChanged();
					input_name.setText("");
					input_name.requestFocus();
				}
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
