package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Car extends Sprite {

	private World world;
	private int vy;
	private int vx;

	private static final float TICK_INITIAL = 0.5f;
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;

	public Car(double _x, double _y, int speed, Pixmap pixmap) {
		super(_x, _y, pixmap);
		x = _x;
		y = _y;
		this.world = world;
		speedY = speed;
		width = 100;
		height = 150;
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
