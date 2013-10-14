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
	protected boolean flag_crash = false;
	protected int speedX;
	protected int speedY;

	protected boolean flag = false;
	protected int angle = 0;


	public Sprite(Pixmap pixmap) {
		this.image = pixmap;
	}
	
	public abstract void Update();

	public void draw(Graphics g) {
		if (flag) {
			g.drawPixmap(image, (int) x, (int) y, angle);
			angle += 20;
		} else {
			g.drawPixmap(image, (int) x, (int) y);
			angle = 0;
		}
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
	
	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public void crash() {
		this.speedY = -30;
	}

}
