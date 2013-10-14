package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Pixmap;

public class Truk extends Sprite {

	private int vy;
	private int vx;

	public Truk(double _x, int speed, Pixmap pixmap) {
		super(pixmap);
		speedY = speed;
		width = 100;
		height = 300;
		this.x = _x;
		this.y = -height;
	}

	public void Update() {
		vy = speedY;
		y += vy;
		vx = speedX;
		x += vx;
	}

	/*
	 * 以下getter,setter群
	 */
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
