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

	public Esa(double x, double y, int speed, boolean flag,Pixmap pixmap) {
		super(x, y, pixmap);
		this.x = x;
		this.y = y;
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

	public boolean getFlag() {
		return flag;
	}
}
