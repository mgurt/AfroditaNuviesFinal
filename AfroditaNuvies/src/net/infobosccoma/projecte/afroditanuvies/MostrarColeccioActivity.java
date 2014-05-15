package net.infobosccoma.projecte.afroditanuvies;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MostrarColeccioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_coleccio);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_coleccio, menu);
		return true;
	}

}
