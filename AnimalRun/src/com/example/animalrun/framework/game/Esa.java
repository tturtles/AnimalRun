package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Pixmap;

public class Esa extends Sprite {

	private int vy;
	private int vx;
	private boolean flag;

	public Esa(double x,int speed, boolean flag, Pixmap pixmap) {
		speedY = speed;
		width = 100;
		height = 100;
		this.x = x;
		this.y = -height;
		this.flag = flag;
		this.speedX = 0;
		this.image = pixmap;
	}

	public void Update() {
		vy = speedY;
		y += vy;
		vx = speedX;
		x += vx;
	}

	public void Use(Animal animal) {
			animal.setFlag();
	}

	public boolean getFlag() {
		return flag;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
