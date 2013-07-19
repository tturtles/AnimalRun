package com.example.animalrun.framework.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class World {
	private final int MARGIN = 160;
	private final int LEFT = 40;
	private final int CENTER = 195;
	private final int RIGHT = 345;
	private final int HAIKEI = 10;

	private static final float TICK_INITIAL = 0.1f;
	private static float tick = TICK_INITIAL; // 更新速度
	private float tickTime;
	private LinkedList sprites;
//	private boolean flag = false; // 試験用
	private ArrayList bucks = new ArrayList();
	private int count=HAIKEI-1;
	private int speed;
	private int score = 0;

	public World(int sc_speed) {
		sprites = new LinkedList();
		tickTime = 0;
		speed = sc_speed;
		load();
	}

	public void update(float deltaTime) {
		int ran;
		boolean flag = false;
		tickTime += deltaTime;
		while (tickTime > tick) {
			tickTime -= tick;
			score++;
//			score+= 10;	//スコア表示テスト用
			if (sprites.size() > 0) {
				Sprite sprite = (Sprite) sprites.getLast();
				if (sprite.getY() >= MARGIN) {
					Random rand = new Random();
					
					for(int i=0; true; i++) {	//前の障害物と同じレーンにならないようにする処理
						ran = rand.nextInt(3);
						switch (ran) {
						case 0:
							if(sprite.getX() == LEFT)	continue;
							else flag = true;
							break;
						case 1:
							if(sprite.getX() == CENTER)	continue;
							else flag = true;
							break;
						case 2:
							if(sprite.getX() == RIGHT)	continue;
							else flag = true;
							break;
						}
						if(flag) break;
					}
					
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
					ran = rand.nextInt(3);
					switch (ran) {
					case 0:
						break;
					case 1:
						sprites.add(new Car(x, -150, speed,Assets.car, this));
						break;
					case 2:
						sprites.add(new Truk(x, -300, speed,Assets.truk, this));
						break;
					}
				}
			}
			if(count>-1) count--;
			else count = HAIKEI-1;
		}
	}

	public LinkedList getSprite() {
		return sprites;
	}

	public void load() {
		sprites.add(new Car(RIGHT, -150, speed,Assets.car, this));
		bucks.add(Assets.way0);
		bucks.add(Assets.way1);
		bucks.add(Assets.way2);
		bucks.add(Assets.way3);
		bucks.add(Assets.way4);
		bucks.add(Assets.way5);
		bucks.add(Assets.way6);
		bucks.add(Assets.way7);
		bucks.add(Assets.way8);
		bucks.add(Assets.way9);
	}

	public void draw(Graphics g) {
//		g.drawPixmap(Assets.way, 0, 0);

		int l = HAIKEI-1;
		for(int i=count; l>-1; i--, l--) {
			if(i<0) i = HAIKEI-1;
			g.drawPixmap((Pixmap) bucks.get(i), 0, l*80);
		}
//		if (flag) {
//			Paint paint = new Paint();			//更新速度の確認用（試験用）
//			paint.setColor(Color.WHITE);
//			g.drawCircle(100, 100, 10, paint);
//		}
	}

	public LinkedList getSprites() {
		return sprites;
	}

	public int getScore() {
		return score;
	}

}
