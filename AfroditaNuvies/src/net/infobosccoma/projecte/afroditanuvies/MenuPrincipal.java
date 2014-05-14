package net.infobosccoma.projecte.afroditanuvies;

import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.Element;
import net.infobosccoma.projecte.afroditanuvies.model.Temporada;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MenuPrincipal extends Activity implements OnItemClickListener {
	ListView llista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_llista_opcions);
		llista = (ListView) findViewById(R.id.listView1);
		String[] values = { getResources().getString(R.string.vestitsnuvia),
				getResources().getString(R.string.prendremides),
				getResources().getString(R.string.quisom),
				getResources().getString(R.string.dadescontacte) };
		String[] values2 = { "ic_launcher", "ic_launcher", "ic_launcher",
				"ic_launcher" };		
		
		ElementsmenuAdapter adapter = new ElementsmenuAdapter(this, values,
				values2);
		llista.setAdapter(adapter);
		llista.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		switch (position){
		case 0:
			Intent i = new Intent(getApplicationContext(),LlistarTemporades.class);
			startActivity(i);
			break;
		case 1:
			//TODO implementar opci�
			break;
		case 2:
			//TODO implementar opci�
			break;
		case 3:
			//TODO implementar opci�
			break;
		}
	}
}
