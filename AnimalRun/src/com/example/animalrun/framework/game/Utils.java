package com.example.animalrun.framework.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import com.example.animalrun.framework.Pixmap;
import com.example.animalrun.framework.Input.TouchEvent;

public class Utils {
	private final int LEFT = 40;
	private final int CENTER = 195;
	private final int RIGHT = 345;
	private final int PERC_CAR = 70;
	private final int PERC_WALKER = 20;
	private final int PERC_ESA = 10;
	private int speed;
	private ArrayList list;
	private int list_count = 0;
	private Pixmap Image_esatrue;
	private Pixmap Image_esafalse;

	public Utils() {
	}

	public void setSelect(int select) {
		switch (select) {
		case 1:
			this.speed = 4;
		case 3:
			Image_esatrue = Assets.esa1_true;
			Image_esafalse = Assets.esa1_false;
			if (select == 3)
				this.speed = 10;
			break;
		case 2:
			Image_esatrue = Assets.esa2_true;
			Image_esafalse = Assets.esa2_false;
			this.speed = 7;
			break;
		}
	}

	// タップ時の当たり判定 目標がタップされた場合true、違う場合false
	public boolean isBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	public void madeList() {
		list = new ArrayList();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
	}

	public LinkedList madeObject(LinkedList sprites) {
		Random rand = new Random();
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

		int ran = rand.nextInt(100);
		if (ran > 100 - PERC_CAR) {
			if (ran % 2 == 0)
				sprites.add(new Car(x, speed, Assets.car));
			else
				sprites.add(new Truk(x, speed, Assets.truk));
		} else if (ran > (100-PERC_CAR) - PERC_WALKER) {
			sprites.add(new Walker(speed, Assets.walker));
		} else if (ran <= PERC_ESA) {
			if (ran % 2 == 0)
				sprites.add(new Esa(x, speed, true, Image_esatrue));
			else
				sprites.add(new Esa(x, speed, false, Image_esafalse));
		}
		return sprites;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
