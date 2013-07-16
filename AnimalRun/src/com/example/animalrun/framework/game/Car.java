package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Car extends Sprite {
	
	private World world;
	private int speedY;
	private int vy;

	public Car(double _x, double _y, Pixmap pixmap, World world) {
		super(_x, _y, pixmap, world);
		x = _x;
		y = _y;
		this.world = world;
		speedY = 10;
	}


	@Override
	public void Update() {
		vy = speedY;
		y += vy;
	}

	public void draw(Graphics g, float deltaTime) {
		g.drawPixmap(Assets.animal, (int) x, (int) y);
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
