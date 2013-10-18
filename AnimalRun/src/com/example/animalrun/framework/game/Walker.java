package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Pixmap;
import java.util.Random;

public class Walker extends Sprite {

	private final int LEFT = 0;
	private final int RIGHT = 400;
	private int vy;

	public Walker(int speed, Pixmap pixmap) {
		super(pixmap);
		width = 50;
		height = 50;
		Random rand = new Random();
		int run = rand.nextInt(2);
		switch (run) {
		case 0:
			x = RIGHT;
			y = -height;
			break;
		case 1:
			x = LEFT;
			y = -height;
			break;
		}
		speedY = speed;
	}

	public void Update() {
		vy = speedY;
		y += vy;
	}
}
