package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;
import com.example.animalrun.framework.game.Animal.Point;
import java.util.Random;

public class Walker extends Sprite {

	private int vy;
	private int vx;

    int run;
	public Walker(int speed, Pixmap pixmap) {
		super(pixmap);
		Random rand = new Random();
		run = rand.nextInt(2);
		switch(run){
	       case 0:x=0;
	              y=-30;
	              break;
	       case 1:x=400;
	              y=-30;
	              break;
	       }
		speedY = speed;
		width = 50;
		height = 50;
	}

	public void Update() {
		vy = speedY;
		y += vy;
		vx = speedX;
		x += vx;
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
