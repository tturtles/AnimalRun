package com.example.animalrun.framework.game;

import java.util.Iterator;
import java.util.LinkedList;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.animalrun.framework.Graphics;

public class World {
	private final int MARGIN = 30; 
	private final int LEFT = 40;
	private final int CENTER = 195;
	private final int RIGHT = 345;

	private static final float TICK_INITIAL = 1.0f;
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;
	private LinkedList sprites;
	private boolean flag = false; // 試験用

	public World(int sc_speed) {
		sprites = new LinkedList();
		tickTime = 0;
		load();
	}

	public void update(float deltaTime) {
		tickTime += deltaTime;
		while (tickTime > tick) {
			tickTime -= tick;
			flag = !flag;
			if (sprites.size() > 0) {
				Sprite sprite = (Sprite) sprites.getLast();
				if (sprite.getY() >= MARGIN) {
					sprites.add(new Car(CENTER, -(sprite.getHeight()), Assets.car, this));
				}
			} else {
			}
		}
	}

	public LinkedList getSprite() {
		return sprites;
	}

	public void load() {
		sprites.add(new Car(RIGHT, -150, Assets.car, this));
	}

	public void draw(Graphics g) {
		g.drawPixmap(Assets.way, 0, 0);
		if (flag) {
			Paint paint = new Paint();
			paint.setColor(Color.WHITE);
			g.drawCircle(100, 100, 10, paint);
		}
	}

	public LinkedList getSprites() {
		return sprites;
	}

}
