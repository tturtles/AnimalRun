package com.example.animalrun.framework.game;

import java.util.LinkedList;

import com.example.animalrun.framework.Graphics;

public class World {

	private static final float TICK_INITIAL = 1.0f;
	private static final double GRAVITY = 1.0; // 重力
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;
	private LinkedList sprites;
	
	public World() {
		sprites = new LinkedList();
		tickTime = 0;
		load();
	}
	
	public void update(float deltaTime) {
		tickTime += deltaTime;
		while(tickTime>tick) {
			tickTime -= tick;
		}
	}
	
	public void load() {
	}
	
	public void draw(Graphics g) {
		g.drawPixmap(Assets.way, 0, 0);
	}
	
	public LinkedList getSprites() {
		return sprites;
	}
	
}
