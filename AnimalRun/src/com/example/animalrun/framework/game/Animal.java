package com.example.animalrun.framework.game;

import android.graphics.Rect;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Animal extends Sprite {

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

	private double vx;
	private double speed; // 移動速度

	private float tick_w = 0; // 無敵モード終了時画像切り替えでdeltaTimeを溜める変数
	private boolean state_switch = false; // 無敵モード終了時画像切り替えに使用するboolean(ボタン)
	private float tickTime = 0;

	Point point = Point.Center;
	Point request = Point.None;

	private boolean flag = false; // true = 無敵状態, false = 通常状態

	public Animal(double x, double y, Pixmap pixmap) {
		super(pixmap);
		this.x = x;
		this.y = y;
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

		// 無敵状態時の処理
		if (flag) {
			tickTime += deltaTime;

			if (tickTime > tick_lasttime) {
				if (tick_w > image_changeTime) {
					if (state_switch)
						image = Assets.animal;
					else
						image = Assets.animal_sp;
					tick_w = 0;
				}
				state_switch = !state_switch;
				tick_w += deltaTime;
			}
			while (tickTime > tick) { // 無敵状態終了処理
				tickTime -= tick;
				flag = false;
			}
		} else {
			image = Assets.animal;
			tick = 0;
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
		g.drawPixmap(Assets.animal, (int) x, (int) y);
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

	// 無敵状態にする
	public void setFlag() {
		flag = true;
		tickTime = 0;
		image = Assets.animal_sp; // 無敵状態は画像変えるよ！
		tick = TICK_INITIAL;
	}

	// 無敵状態かどうか返す true = 無敵状態, false = 通常状態
	public boolean getflag() {
		return flag;
	}

}
