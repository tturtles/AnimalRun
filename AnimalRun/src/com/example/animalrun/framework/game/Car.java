package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Car extends Sprite {

	public Car(double _x, double _y, Pixmap pixmap, World world) {
		super(_x, _y, pixmap, world);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	@Override
	public void Update() {
	}

	public void draw(Graphics g, float deltaTime) {
		g.drawPixmap(Assets.animal, (int) x, (int) y);
	}

	// 左移動
	public void accelerateLeft() {
	}

	// 右移動
	public void accelerateRight() {
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
