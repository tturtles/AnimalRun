package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Esa extends Sprite {

	private World world;
	private int speedY;
	private int vy;
	private boolean flag;

	private static final float TICK_INITIAL = 0.5f;
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;

	public Esa(double _x, double _y, int speed, boolean flag,Pixmap pixmap, World world) {
		super(_x, _y, pixmap, world);
		x = _x;
		y = _y;
		this.world = world;
		this.flag = flag;
		speedY = speed;
		width = 100;
		height = 100;
	}

	public void Update() {
		vy = speedY;
		y += vy;
	}

	public void draw(Graphics g, float deltaTime) {
		g.drawPixmap(Assets.animal, (int) x, (int) y);
	}

	public void Use(Animal animal) {
		if(flag) {	//本物のエサの場合
			animal.setFlag();
		} else {	//偽物のエサの場合
		}
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


	public boolean getFlag() {
		return flag;
	}
}
