package net.infobosccoma.projecte.afroditanuvies;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.Vestit;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GaleriaVestitsAdapter extends BaseAdapter {

	private Activity _activity;
	private ArrayList<Vestit> _vestits;
	private ArrayList<Bitmap> _bitmaps;
	private ImageView imageView;
	private int imageWidth;

	public GaleriaVestitsAdapter(Activity activity, ArrayList<Vestit> vestits, ArrayList<Bitmap> bitmaps,
			int imageWidth) {
		this._activity = activity;
		this._vestits = vestits;
		this.imageWidth = imageWidth;
		this._bitmaps = bitmaps;
	}

	@Override
	public int getCount() {
		return this._bitmaps.size();
	}

	@Override
	public Object getItem(int position) {
		return this._bitmaps.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			imageView = new ImageView(_activity);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(imageWidth,
				imageWidth));
		
		imageView.setImageBitmap(_bitmaps.get(position));

		// image view click listener
		imageView.setOnClickListener(new OnImageClickListener(position));

		return imageView;
	}

	class OnImageClickListener implements OnClickListener {

		int _postion;

		// constructor
		public OnImageClickListener(int position) {
			this._postion = position;
		}

		@Override
		public void onClick(View v) {
			// on selecting grid view image
			// launch full screen activity
			Intent i = new Intent(_activity, VestitIndividual.class);

			Bundle b = new Bundle();
			b.putSerializable(VestitIndividual.VESTIT_SELECCIONAT, _vestits);
			b.putInt(VestitIndividual.POSICIO, _postion);
			
			_activity.startActivity(i);
		}

	}

	/*
	 * Resizing image size
	 */
	public static Bitmap decodeFile(String filePath, int WIDTH, int HIGHT) {
		try {

			File f = new File(filePath);

			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			final int REQUIRED_WIDTH = WIDTH;
			final int REQUIRED_HIGHT = HIGHT;
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_WIDTH
					&& o.outHeight / scale / 2 >= REQUIRED_HIGHT)
				scale *= 2;

			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
