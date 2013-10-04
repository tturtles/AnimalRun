package com.example.animalrun.framework.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.R.anim;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Input.TouchEvent;
import com.example.animalrun.framework.Screen;

public class PlayScreen extends Screen {

	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	private int select = 0;
	private World world;
	private Animal animal;
	private int score = 0;

	public PlayScreen(Game game, int _select) {
		super(game);
		select = _select;
		int speed = 0;
		switch (select) {
		case 1: // カピパラ選択時
			speed = 4;
			animal = new Animal(190, 630, Assets.animal);
			break;
		case 2: // ライオン選択時
			speed = 7;
			animal = new Animal(190, 630, Assets.animal);
			break;
		case 3: // ダチョウ選択時
			speed = 10;
			animal = new Animal(190, 630, Assets.animal);
			break;
		}
		world = new World(speed, _select);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		if (state == GameState.Ready)
			updateReady(touchEvents, deltaTime);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.GameOver) {
			updateGameOver(touchEvents);
		}
	}

	private void updateReady(List<TouchEvent> touchEvents, float deltaTime) {
		// ゲーム準備時のタッチ処理書き込み
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case MotionEvent.ACTION_UP:
				state = GameState.Running;
			}
		}
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		// ゲーム中のタッチ処理書き込み
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case MotionEvent.ACTION_DOWN:
				if (isBounds(event, 0, 0, 160, 800)) {
					animal.setRequest(1);
				}
				if (isBounds(event, 161, 0, 160, 800)) {
					animal.setRequest(2);
				}
				if (isBounds(event, 321, 0, 160, 800)) {
					animal.setRequest(3);
				}
			}
		}
		animal.Update(deltaTime);
		world.update(deltaTime);
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		// ゲームオーバー時のタッチ処理書き込み
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case MotionEvent.ACTION_DOWN:
				game.setScreen(new ScoreScreen(game, world));
				break;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
	}

	private void drawReadyUI() {
		// ゲーム準備時のUI(描画系)
		Graphics g = game.getGraphics();
		world.draw(g);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(100);
		g.drawTextAlp("Ready?", 70, 300, paint);
	}

	private void drawRunningUI() {
		// ゲーム中のUI(描画系)
		Graphics g = game.getGraphics();
		world.draw(g);
		animal.draw(g);
		LinkedList sprites = world.getSprites();
		Iterator iterator = sprites.iterator(); // Iterator=コレクション内の要素を順番に取り出す方法
		while (iterator.hasNext()) { // iteratorの中で次の要素がある限りtrue
			Sprite sprite = (Sprite) iterator.next();
			sprite.Update();
			sprite.draw(g);
			if (animal.isCollision(sprite)) { // 衝突した場合
				if (sprite instanceof Esa) { // それがエサの場合
					Esa esa = (Esa) sprite;
					if (esa.getFlag()) {
						esa.Use(animal); // エサの効果発動！
						sprites.remove(esa);
					} else if (!animal.getflag())
						state = GameState.GameOver; // エサが偽物且つ動物が無敵状態じゃないときゲームオーバー
					else
						esa.crash();
					break;
				} else if (!animal.getflag())
					state = GameState.GameOver; // エサ以外に衝突且つ動物が無敵状態じゃない場合ゲームオーバー
				Sprite _sprite = (Sprite) sprite;
				_sprite.crash();
				_sprite.setFlag(true);
				break;
			}
			
			if (Judg_remove(sprite)) {
				Sprite _sprite = (Sprite) sprite;
				sprites.remove(_sprite);
				break;
			}
		}
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(50);
		score = world.getScore();
		int i;
		for (i = 0; score > 9; i++) {
			if (score / 10 < 10)
				break;
			else {
				i++;
				score /= 10;
			}
		}
		g.drawTextAlp("SCORE : " + world.getScore(), 200 - i * 15, 50, paint);
	}

	private boolean Judg_remove(Sprite sprite) {
		if (sprite.getX() < 0 - sprite.getWidth() || sprite.getX() > 480
				|| sprite.getY() < 0 - sprite.getHeight()
				|| sprite.getY() > 810)
			return true;
		else
			return false;
	}

	private void drawGameOverUI() {
		// ゲームオーバー時のUI(描画系)
		Graphics g = game.getGraphics();
		world.draw(g);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(100);
		g.drawTextAlp("GameOver", 0, 300, paint);
	}

	// タップ時の当たり判定 目標がタップされた場合true、違う場合false
	private boolean isBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
