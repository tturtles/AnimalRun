package com.example.animalrun.framework.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class World {

	private static final float TICK_INITIAL = 0.1f;
	private static float tick = TICK_INITIAL; // 更新速度
	private final int MARGIN = 200; // 車間距離(単位:pixcel)
	private final int HAIKEI = 10; // 背景リストの数
	private final int LEFT = 40;
	private final int CENTER = 195;
	private final int RIGHT = 345;
	private int PERC_CAR, PERC_WALKER, PERC_ESA;
	private int speed;
	private int score = 0;
	private int select = 0;
	private int count_list = 0;
	private float tickTime = 0;
	private Pixmap[] image_esa; // 0=trueエサ 1=falseエサ
	private ArrayList list;
	private ArrayList bucks;
	private LinkedList sprites;
	private int count_buck = HAIKEI - 1;
	public boolean gameOver = false;;

	public World(int select) {
		this.select = select;
		load();
	}

	public void load() {
		// 背景作成
		bucks = new ArrayList();
		for (int i = 0; i < 5; i++) {
			bucks.add(Assets.way0);
			bucks.add(Assets.way1);
		}

		// エサ画像イラストと障害物の移動速度の設定
		image_esa = new Pixmap[2];
		switch (select) {
		case 1:// タヌキ
			image_esa[0] = Assets.tanukiesa_true;
			image_esa[1] = Assets.tanukiesa_false;
			speed = 3;
			PERC_CAR = 60;
			PERC_WALKER = 10;
			PERC_ESA = 30;
			break;
		case 3:// ライオン
			image_esa[0] = Assets.lionesa_true;
			image_esa[1] = Assets.lionesa_false;
			if (select == 3) {
				this.speed = 11;
				PERC_CAR = 70;
				PERC_WALKER = 30;
				PERC_ESA = 0;
			}
			break;
		case 2:// くま
			image_esa[0] = Assets.kumaesa_true;
			image_esa[1] = Assets.kumaesa_false;
			speed = 7;
			PERC_CAR = 60;
			PERC_WALKER = 30;
			PERC_ESA = 10;
			break;
		}

		// 障害物作成
		madeList(); // リスト作成の準備
		sprites = new LinkedList();
		madeObject(); // spritesに障害物を入れる
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
			if (count_buck > -1)
				count_buck--;
			else
				count_buck = HAIKEI - 1;
		}
	}

	public void draw(Graphics g) {
		int l = HAIKEI - 1;
		for (int i = count_buck; l > -1; i--, l--) {
			if (i < 0)
				i = HAIKEI - 1;
			g.drawPixmap((Pixmap) bucks.get(i), 0, l * 80);
		}
	}

	// リストの初期化
	public void madeList() {
		list = new ArrayList();
		for (int i = 0; i < 3; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
	}

	// 障害物の作成
	public void madeObject() {
		Random rand = new Random();
		int x = LEFT;
		if (count_list > 2) {
			madeList();
			count_list = 0;
		}
		switch ((Integer) list.get(count_list)) {
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
		count_list++;

		int ran = rand.nextInt(100);
		if (ran > 100 - PERC_CAR) {
			if (ran % 2 == 0)
				sprites.add(new Car(x, speed, Assets.car));
			else
				sprites.add(new Truk(x, speed, Assets.truk));
		} else if (ran > (100 - PERC_CAR) - PERC_WALKER) {
			sprites.add(new Walker(speed, Assets.walker));
		} else if (ran <= PERC_ESA) {
			if (ran % 2 == 0)
				sprites.add(new Esa(x, speed, true, image_esa[0]));
			else
				sprites.add(new Esa(x, speed, false, image_esa[1]));
		}
	}

	/*
	 * ○仕組み 1.3つ分の配列を作成 2.配列の中に障害物の出現場所(左、真ん中、右)を1つずつ入れ配列をシャッフル
	 * 3.配列から出現位置を取り出し、それを元に障害物をnewする 4.newした障害物をspritesに入れる 5.配列が空になるまで3～4を繰り返す
	 * 6.配列が空になったら2まで戻る
	 */

	// getter群
	public LinkedList getSprites() {
		return sprites;
	}

	public int getScore() {
		return score;
	}

	public int getSelect() {
		return select;
	}

}
