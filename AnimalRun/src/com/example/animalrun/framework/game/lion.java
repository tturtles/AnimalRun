package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class lion extends Sprite {

	enum Point {
		Left, Center, Right
	}

	private static final float TICK_INITIAL = 0.3f;
	private static float tick = TICK_INITIAL; // 更新速度

	private double vx;
	private double speed; // 移動速度

	private float tickTime = 0;

	Point point = Point.Center;

	private World world;

	public lion(double _x, double _y, Pixmap pixmap, World _world) {
		super(_x, _y, pixmap, _world);
		x = _x;
		y = _y;
		width = 100;
		height = 150;
		speed = 7;
		world = _world;
		vx = 0;
	}

	@Override
	public void Update() {

	}
	
	public void draw(Graphics g, float deltaTime) {
		
	}

	// 左移動
	public void accelerateLeft() {
		vx = -speed;
	}

	// 右移動
	public void accelerateRight() {
		vx = speed;
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
