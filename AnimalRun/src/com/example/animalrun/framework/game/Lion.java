package com.example.animalrun.framework.game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.Toast;

import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;

public class Lion extends Sprite {

	enum Point {
		Left, Center, Right, None
	}

	private static final float TICK_INITIAL = 0.3f;
	private static float tick = TICK_INITIAL; // 更新速度

	private double vx;
	private double speed; // 移動速度

	private float tickTime = 0;

	Point point = Point.Center;
	Point request = Point.None;

	private World world;

	public Lion(double _x, double _y, Pixmap pixmap, World _world) {
		super(_x, _y, pixmap, _world);
		x = _x;
		y = _y;
		width = 100;
		height = 150;
		speed = 10;
		world = _world;
		vx = 0;
	}

	@Override
	public void Update() {
		switch (request) {
		case Left:	//左レーンをタップされた場合
			if (x <= 40) {
				request = Point.None;
				point = Point.Left;
			} else
				accelerateLeft();
			break;
		case Center:		//中央レーンをタップされた場合
			if(x<=195) {	//キャラが中央レーン定位置より左にいた場合
				accelerateRight();
			}
			if(x>=195&&x<=195+speed){
				request = Point.None;
				point = Point.Center;
				break;
			}
			
			if(x>=195) {//キャラが中央レーン定位置より右にいた場合
				accelerateLeft();
			}
			if(x<=195&&x>=195-speed){
				request = Point.None;
				point = Point.Center;
				break;
			}
			break;
		case Right:	//右レーンをタップされた場合
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

	/*
	 * 以下getter,setter群
	 */
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getPoint() {
		int _point;
		switch (point) {
		case Left:
			_point = 1;
			break;
		case Center:
			_point = 2;
			break;
		case Right:
			_point = 3;
			break;
		default:
			_point = 0;
		}
		return _point;
	}
	

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
		Rect playerRect = new Rect((int) x, (int) y, width+(int)x, height+(int)y);
		Rect spriteRect = new Rect((int) sprite.getX(), (int) sprite.getY(),
				(int) sprite.getWidth()+(int)sprite.getX(), (int) sprite.getHeight()+(int)sprite.getY());
		 if(playerRect.intersect(spriteRect)) {return true;}	// //Rect同士ぶつかり合っていたらtrue
			return false;
	}

}
