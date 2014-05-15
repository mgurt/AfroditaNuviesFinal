package net.infobosccoma.projecte.afroditanuvies;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.Vestit;
import net.infobosccoma.projecte.afroditanuvies.utils.AppConstant;
import net.infobosccoma.projecte.afroditanuvies.utils.Utils;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class VestitIndividual extends Activity{
	
	public static final String VESTIT_SELECCIONAT = "Vestit Seleccionat";
	public static final String POSICIO = "posicio";
	private Utils utils;
	private VestitIndividualAdapter adapter;
	private ArrayList<Vestit> vestits;
	private Bitmap bitmapVestit;
	private int posicio;
	private ViewPager viewPager; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen_view);

		viewPager = (ViewPager) findViewById(R.id.pager);

		utils = new Utils(getApplicationContext());
		
		// Obtenir el vestit i la posició
		Intent it = getIntent();
		vestits = (ArrayList<Vestit>) it.getExtras().getSerializable(VESTIT_SELECCIONAT);
		posicio = it.getExtras().getInt(POSICIO);
		
		for(int i = 0; i < vestits.size(); i++){
			new DescarregaImatges().execute(AppConstant.URL+File.separator+"/img/vestits/"+vestits.get(i).getImatge());
		}
		
	}
	
	// TODO OBTENIR LA IMATGES DE LA URL
	/**
	 * Classe encarregada de crear bitmaps a través de direccions URL, afegint-les a una llista
	 * @author marc
	 *
	 */
	class DescarregaImatges extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... urls) {
			for (String url : urls) {
				bitmapVestit = loadImageFromNetwork(url);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO MODIFICAR L'ADAPTADOR
			adapter = new VestitIndividualAdapter(VestitIndividual.this, vestits, posicio);
			
			viewPager.setAdapter(adapter);

			// displaying selected image first
			viewPager.setCurrentItem(posicio);
		}
		

		/**
		 * Crear un bitmap carregant una imatges des d'una URL
		 * 
		 * @param url
		 * @return
		 */
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
}
