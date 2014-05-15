package net.infobosccoma.projecte.afroditanuvies;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.infobosccoma.projecte.afroditanuvies.model.Teixit;
import net.infobosccoma.projecte.afroditanuvies.utils.AppConstant;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MostrarTeixitActivity extends Activity {
	ImageView imatge;
	Teixit aquestTeixit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_teixit);
		aquestTeixit = (Teixit) getIntent().getSerializableExtra("teixit");
		setTitle(aquestTeixit.getNom());
		imatge = (ImageView) findViewById(R.id.imatgeTeixit);
		TextView text = (TextView) findViewById(R.id.descripcioTeixit);
		new TascaDescarrega().execute(AppConstant.URL+aquestTeixit.getImatgeurl()+aquestTeixit.getImatge());
		text.setText(aquestTeixit.getDescripcio());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_coleccio, menu);
		return true;
	}	
	
	class TascaDescarrega extends AsyncTask<String, Void, Bitmap> {
		
		 @Override
		 protected Bitmap doInBackground(String... params) {
		 return loadImageFromNetwork(params[0]);
		 }
		
		 protected void onPostExecute(Bitmap result) {
		 imatge.setImageBitmap(result);
		 }
		
		 }
		
		 private Bitmap loadImageFromNetwork(String url) {
		
		 Bitmap bitmap = null;
		 try {
		 bitmap = BitmapFactory.decodeStream((InputStream) new URL(url)
		 .getContent());
		 } catch (MalformedURLException e) {
		 e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 return bitmap;		
		 }

}
