package com.example.animalrun.framework.game;

import android.graphics.Rect;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Animal extends Sprite {

	enum Point {
		Left, Center, Right, None
	}

	private static final float TICK_INITIAL = 5.0f;
	private static float tick = 0; // 更新速度

	private double vx;
	private double speed; // 移動速度

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
		if (flag) {
			tickTime += deltaTime;
			while (tickTime > tick) {
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
		image = Assets.animal_sp; // 無敵状態は画像変えるよ！
		tick += TICK_INITIAL;
	}

	// 無敵状態かどうか返す true = 無敵状態, false = 通常状態
	public boolean getflag() {
		return flag;
	}

}
