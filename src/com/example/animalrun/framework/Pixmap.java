package com.example.animalrun.framework;

import com.example.animalrun.framework.Graphics.PixmapFormat;

public interface Pixmap {
	
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
