package com.example.animalrun.framework.game;

import java.util.LinkedList;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.animalrun.framework.Graphics;

public class World {
	private final int MARGIN = 30;
	private final int LEFT = 40;
	private final int CENTER = 195;
	private final int RIGHT = 345;

	private static final float TICK_INITIAL = 0.5f;
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
					Random rand = new Random();
					int ran = rand.nextInt(3);
					int x = LEFT;
					switch (ran) {
					case 0:
						x = LEFT;
						break;
					case 1:
						x = CENTER;
						break;
					case 2:
						x = RIGHT;
						break;
					}
					ran = rand.nextInt(2);
					switch (ran) {
					case 0:
						sprites.add(new Car(x, -(sprite.getHeight()),
								Assets.car, this));
						break;
					case 1:
						sprites.add(new Truk(x, -(sprite.getHeight()),
								Assets.truk, this));
						break;
					}
				}
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
