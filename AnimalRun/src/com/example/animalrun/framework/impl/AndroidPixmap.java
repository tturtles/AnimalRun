package com.example.animalrun.framework.impl;

import com.example.animalrun.framework.Graphics.PixmapFormat;
import com.example.animalrun.framework.Pixmap;

import android.graphics.Bitmap;

public class AndroidPixmap implements Pixmap{
	Bitmap bitmap;
	PixmapFormat format;

	public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}
	
	public int getWidth() {
		return bitmap.getWidth();
	}

	public int getHeight() {
		return bitmap.getHeight();
	}

	public PixmapFormat getFormat() {
		return format;
	}

	public void dispose() {
		bitmap.recycle();		//bitmap・ｽ・ｽ・ｽJ・ｽ・ｽ
	}

}
