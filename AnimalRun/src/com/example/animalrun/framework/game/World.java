package com.example.animalrun.framework.game;

import java.util.ArrayList;
import java.util.LinkedList;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class World {
	private final int MARGIN = 200;
	private final int HAIKEI = 10;

	private static final float TICK_INITIAL = 0.1f;
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;
	private LinkedList sprites;
	private ArrayList bucks = new ArrayList();
	private int count = HAIKEI - 1;
	private int score = 0;
	private int select = 0;
	private Utils utils;


	public int getSelect() {
		return select;
	}


	public World(int select) {
		utils = new Utils();
		sprites = new LinkedList();
		tickTime = 0;
		this.select = select;
		load();
	}

	public void load() {
		utils.setSelect(select);
		for (int i = 0; i < 5; i++) {
			bucks.add(Assets.way0);
			bucks.add(Assets.way1);
		}
		utils.madeList();
		sprites = utils.madeObject(sprites);
	}

	public void update(float deltaTime) {
		tickTime += deltaTime;
		while (tickTime > tick) {
			tickTime -= tick;
			score++;
			Sprite sprite = (Sprite) sprites.getLast();
			if (sprites.size() > 0 && sprite.getY() >= MARGIN) {
				sprites = utils.madeObject(sprites);
			}
			if (count > -1)
				count--;
			else
				count = HAIKEI - 1;
		}
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
