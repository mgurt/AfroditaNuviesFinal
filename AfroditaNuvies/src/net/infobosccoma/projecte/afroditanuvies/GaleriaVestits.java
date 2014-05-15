package net.infobosccoma.projecte.afroditanuvies;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.Vestit;
import net.infobosccoma.projecte.afroditanuvies.utils.AppConstant;
import net.infobosccoma.projecte.afroditanuvies.utils.JSonResponse;
import net.infobosccoma.projecte.afroditanuvies.utils.Utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.GridView;

public class GaleriaVestits extends Activity {

	private Utils utils;
	private ArrayList<Vestit> vestits;
	private ArrayList<Bitmap> bitmaps;
	private ProgressDialog dialog;
	private GaleriaVestitsAdapter adapter;
	private GridView gridView;
	private int columnWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		gridView = (GridView) findViewById(R.id.grid_view);
		
		utils = new Utils(getApplicationContext());
		
		// Inicialitzar les llistes
		bitmaps = new ArrayList<Bitmap>();
		vestits = new ArrayList<Vestit>();
		
		// Crear el dialeg
		dialog = new ProgressDialog(GaleriaVestits.this);
		
		// Inicialitzar Grid View
		InitilizeGridLayout();	
		
		// Gridview adapter
		adapter = new GaleriaVestitsAdapter(this, vestits, bitmaps, columnWidth);

		// Obtenir els vestits de la col·lecció
		new HttpPost().execute("http://afroditanuvies.bugs3.com/json/vestits.php");
		
	}

	private void InitilizeGridLayout() {
		Resources r = getResources();
		float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				AppConstant.GRID_PADDING, r.getDisplayMetrics());

		columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);

		gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
		gridView.setColumnWidth(columnWidth);
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setPadding((int) padding, (int) padding, (int) padding,
				(int) padding);
		gridView.setHorizontalSpacing((int) padding);
		gridView.setVerticalSpacing((int) padding);
	}
	
	/**
	 * Classe encarregada de obtenir la informació dels vestits d'una col·lecció determinada
	 * @author marc
	 *
	 */
	class HttpPost extends AsyncTask<String, Void, JSONArray> {
		
		private JSonResponse jSonResponse = new JSonResponse();

		/**
		 * S'executa aquest mètode abans del mètode doInBackground
		 */
		protected void onPreExecute() {
			dialog.setMessage("Obtenint els vestits...");
			dialog.show();
		}

		/**
		 * Executa en segon pla
		 * Aquí dins no s'interactua amb el GUI
		 */
		protected JSONArray doInBackground(String... url) {
			// Assignar la URL
			jSonResponse.setURL(url[0]);
			// Crear un POST amb els paràmetres corresponents
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("idColeccio", "51"));
			// Executar la consulta
			return jSonResponse.ejectuarConsultaPost(postParameters);
		}

		/**
		 * S'executa després del mètode doInBackground
		 */
		protected void onPostExecute(JSONArray jArray) {
			// Comprovar que hi hagi valors
			if (jArray != null) {
				try {
					JSONObject jSonObject = new JSONObject();
					for (int i = 0; i < jArray.length(); i++) {

						jSonObject = jArray.getJSONObject(i);
						
						String imatge = jSonObject.getString("imatge");
						
						vestits.add(new Vestit(
								jSonObject.getInt("id"),
								jSonObject.getString("nom"), 
								jSonObject.getString("descripcio"), 
								imatge
						));
						new DescarregaImatges().execute(AppConstant.URL+File.separator+"/img/vestits/"+imatge);
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				dialog.setMessage("CONSULTA SENSE RESULTATS");
			}

		}
	}
	
	/**
	 * Classe encarregada de crear bitmaps a través de direccions URL, afegint-les a una llista
	 * @author marc
	 *
	 */
	class DescarregaImatges extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... urls) {
			for (String url : urls) {
				bitmaps.add(loadImageFromNetwork(url));
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// setting grid view adapter
			gridView.setAdapter(adapter);
			
			if(dialog.isShowing()){
				dialog.dismiss();
			}
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
