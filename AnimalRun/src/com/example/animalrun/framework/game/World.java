package com.example.animalrun.framework.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Button;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class World {
	private final int MARGIN = 200;
	private final int LEFT = 40;
	private final int CENTER = 195;
	private final int RIGHT = 345;
	private final int HAIKEI = 10;

	private static final float TICK_INITIAL = 0.1f;
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;
	private LinkedList sprites;
	private ArrayList bucks = new ArrayList();
	private int count = HAIKEI - 1;
	private int speed;
	private int score = 0;
	private int select = 0;
	private Pixmap Image_esatrue;
	public int getSelect() {
		return select;
	}

	private Pixmap Image_esafalse;

	private ArrayList list;
	private int list_count = 0;

	public World(int speed, int select) {
		sprites = new LinkedList();
		tickTime = 0;
		this.speed = speed;
		this.select = select;
		load();
	}

	public void load() {
		for (int i = 0; i < 5; i++) {
			bucks.add(Assets.way0);
			bucks.add(Assets.way1);
		}

		switch (select) {
		case 1:
		case 3:
			Image_esatrue = Assets.esa1_true;
			Image_esafalse = Assets.esa1_false;
			break;
		case 2:
			Image_esatrue = Assets.esa2_true;
			Image_esafalse = Assets.esa2_false;
			break;
		}
		madeList();
		madeObject();
	}

	private void madeList() {
		list = new ArrayList();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
	}

	private void madeObject() {
		int ran;
		int x = LEFT;
		if (list_count > 2) {
			madeList();
			list_count = 0;
		}
		switch ((Integer) list.get(list_count)) {
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
		list_count++;

		Random rand = new Random();
		ran = rand.nextInt(100);
		if (ran < 90) {
			ran = rand.nextInt(2);
			if (ran == 0)
				sprites.add(new Car(x, -150, speed, Assets.car));
			else
				sprites.add(new Truk(x, -300, speed, Assets.truk));
		} else {
			ran = rand.nextInt(2);
			if (ran == 0)
				sprites.add(new Esa(x, -100, speed, true, Image_esatrue));
			else
				sprites.add(new Esa(x, -100, speed, false, Image_esafalse));
		}
	}

	public void update(float deltaTime) {
		tickTime += deltaTime;
		while (tickTime > tick) {
			tickTime -= tick;
			score++;
			Sprite sprite = (Sprite) sprites.getLast();
			if (sprites.size() > 0 && sprite.getY() >= MARGIN) {
				madeObject();
			}
			if (count > -1)
				count--;
			else
				count = HAIKEI - 1;
		}
	}

	public LinkedList getSprite() {
		return sprites;
	}

	public void draw(Graphics g) {
		int l = HAIKEI - 1;
		for (int i = count; l > -1; i--, l--) {
			if (i < 0)
				i = HAIKEI - 1;
			g.drawPixmap((Pixmap) bucks.get(i), 0, l * 80);
		}
		// if (flag) {
		// Paint paint = new Paint(); //更新速度の確認用（試験用）
		// paint.setColor(Color.WHITE);
		// g.drawCircle(100, 100, 10, paint);
		// }
	}

	public LinkedList getSprites() {
		return sprites;
	}

	public int getScore() {
		return score;
	}

}
