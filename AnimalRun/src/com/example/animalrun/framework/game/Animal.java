package com.example.animalrun.framework.game;

import android.graphics.Rect;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Animal extends Sprite {

	enum AnimalState {
		Normal, Unrivaled
	}

	enum Point {
		Left, Center, Right, None
	}

	// 無敵モード時間
	private static final float TICK_INITIAL = 5.0f;
	private static float tick = TICK_INITIAL;

	// 無敵モード終了合図開始時間
	private static final float TICK_INITIAL2 = 2.0f;
	private static float tick_lasttime = TICK_INITIAL - TICK_INITIAL2;

	// 無敵モード終了合図画像切り替え時間（x秒毎）
	private static final float TICK_INITIAL3 = 0.1f;
	private static float image_changeTime = TICK_INITIAL3;

	// 画像の切り替え
	private static final float TICK_INITIAL4 = 0.3f;
	private static float tick_changeSwx = TICK_INITIAL4;

	private double vx;
	private double speed; // 移動速度

	private float tick_w = 0; // 無敵モード終了時画像切り替えでdeltaTimeを溜める変数
	private float tick_swxChange = 0; //

	private boolean state_switch = false; // 無敵モード終了時画像切り替えに使用するboolean(ボタン)
	private float tickTime = 0;
	private Pixmap[][] image = new Pixmap[2][2]; // 動物イラストを格納する二次元配列
	private int swy = 0; // 動物をノーマルと無敵の間を遷移する変数 0がノーマル状態 1が無敵状態
	private int swx = 0; // 動物の状態を遷移する変数 0がone状態 1がtwo状態
	private int select = 0;

	private AnimalState state = AnimalState.Normal;

	Point point = Point.Center;
	Point request = Point.None;

	public Animal(int select) {
		switch (select) {
		case 1:
			image[0][0] = Assets.tanukinorone;
			image[0][1] = Assets.tanukinortwo;
			image[1][0] = Assets.tanukiinvone;
			image[1][1] = Assets.tanukiinvtwo;
			break;
		case 2:
			image[0][0] = Assets.kumanorone;
			image[0][1] = Assets.kumanortwo;
			image[1][0] = Assets.kumainvone;
			image[1][1] = Assets.kumainvtwo;
			break;
		case 3:
			image[0][0] = Assets.lionnorone;
			image[0][1] = Assets.lionnortwo;
			image[1][0] = Assets.lioninvone;
			image[1][1] = Assets.lioninvtwo;
			break;
		}
		this.select = select;
		this.x = 190;
		this.y = 630;
		width = 100;
		height = 150;
		speed = 10;
		vx = 0;
	}

	public float gettick() {
		return tick;
	}

	@Override
	public void Update() {
	}

	public void Update(float deltaTime) {
		tickTime += deltaTime;
		tick_swxChange += deltaTime;
		if (state == AnimalState.Unrivaled) {
			if (tickTime > tick_lasttime) {
				if (tick_w > image_changeTime) {
					swy = swy == 0 ? 1 : 0;
					tick_w = 0;
				}
				state_switch = !state_switch;
				tick_w += deltaTime;
			}
			if (tickTime > tick) { // 無敵状態終了処理
				tickTime -= tick;
				state = AnimalState.Normal;
			}
		}

		if (tick_swxChange > tick_changeSwx) {
			tick_swxChange -= tick_changeSwx;
			swx = swx == 0 ? 1 : 0;
		}

		switch (request) {
		case Left: // 左レーンをタップされた場合
			if (x <= 40) {
				request = Point.None;
				point = Point.Left;
			} else
				accelerateLeft();
			break;
		case Center: // 中央レーンをタップされた場合
			if (x <= 195) { // キャラが中央レーン定位置より左にいた場合
				accelerateRight();
			}
			if (x >= 195 && x <= 195 + speed) {
				request = Point.None;
				point = Point.Center;
				break;
			}

			if (x >= 195) {// キャラが中央レーン定位置より右にいた場合
				accelerateLeft();
			}
			if (x <= 195 && x >= 195 - speed) {
				request = Point.None;
				point = Point.Center;
				break;
			}
			break;
		case Right: // 右レーンをタップされた場合
			if (x >= 345) {
				request = Point.None;
				point = Point.Right;
			} else
				accelerateRight();
			break;
		case None:
			break;
		}
		x += vx;
		vx = 0;
	}

	public void draw(Graphics g, float deltaTime) {
		g.drawPixmap(image[swy][swx], (int) x, (int) y);
	}

	// 左移動
	public void accelerateLeft() {
		vx = -speed;
	}

	// 右移動
	public void accelerateRight() {
		vx = speed;
	}

	// タップされたレーンへ移動をリクエスト
	public void setRequest(int _point) {
		switch (_point) {
		case 1:
			this.request = Point.Left;
			break;
		case 2:
			this.request = Point.Center;
			break;
		case 3:
			this.request = Point.Right;
			break;
		}
	}

	/*
	 * 他のスプライトと接触しているか
	 */
	public boolean isCollision(Sprite sprite) {
		Rect playerRect = new Rect((int) x, (int) y, width + (int) x, height
				+ (int) y);
		Rect spriteRect = new Rect((int) sprite.getX(), (int) sprite.getY(),
				(int) sprite.getWidth() + (int) sprite.getX(),
				(int) sprite.getHeight() + (int) sprite.getY());
		if (playerRect.intersect(spriteRect)) {
			return true;
		} // //Rect同士ぶつかり合っていたらtrue
		return false;
	}

	// 無敵状態にする7
	public void setFlag() {
		state = AnimalState.Unrivaled;
		tickTime = 0;
		swy = 1;// 無敵状態は画像変えるよ！
		tick = TICK_INITIAL;
	}

	// 無敵状態かどうか返す true = 無敵状態, false = 通常状態
	public boolean getflag() {
		return state == AnimalState.Unrivaled ? true : false;
	}

	public int getSelect() {
		return select;
	}
}
