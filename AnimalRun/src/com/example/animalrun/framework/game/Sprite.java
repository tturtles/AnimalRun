package com.example.animalrun.framework.game;

import android.graphics.Rect;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public abstract class Sprite {
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected Pixmap image;

	public Sprite(double _x, double _y, Pixmap pixmap, World world) {
		x = _x;
		y = _y;
		image = pixmap;
	}

	public abstract void Update();

	public void draw(Graphics g) {
		g.drawPixmap(image, (int) x, (int) y);
	}

	/*
	 * 他のスプライトとの当たり判定
	 */
	public boolean isCollision(Sprite sprite) {
		Rect playerRect = new Rect((int) x, (int) y, width + (int) x, height
				+ (int) y);
		Rect spriteRect = new Rect((int) sprite.getX(), (int) sprite.getY(),
				(int) sprite.getWidth() + (int) sprite.getX(),
				(int) sprite.getHeight() + (int) sprite.getY());
		if (playerRect.intersect(spriteRect)) {
			return true;
		} // //Rect同士ぶつかり合っていたらtrue
		return false;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
