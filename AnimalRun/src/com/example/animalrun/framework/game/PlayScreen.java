package com.example.animalrun.framework.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Input.TouchEvent;
import com.example.animalrun.framework.Screen;

public class PlayScreen extends Screen {

	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	private World world;
	private Animal animal;
	
	public PlayScreen(Game game, int select) {
		super(game);
		Assets.bgm_playscreen.play();
		switch (select) {
		case 1: // タヌキ選択時
			animal = new Animal(1);
			break;
		case 2: // クマ選択時
			animal = new Animal(2);
			break;
		case 3: // ライオン選択時
			animal = new Animal(3);
			break;
		}
		world = new World(select);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		if (state == GameState.Ready)
			updateReady(touchEvents, deltaTime);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents, float deltaTime) {
		// ゲーム準備時のタッチ処理書き込み
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case MotionEvent.ACTION_UP:
				state = GameState.Running;
				Assets.bgm_select.play(1);
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
			case MotionEvent.ACTION_UP:
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
			drawRunningUI(deltaTime);
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
		g.drawPixmap(Assets.logo_ready, 20, 300);
	}

	private void drawRunningUI(float deltaTime) {
		// ゲーム中のUI(描画系)
		Graphics g = game.getGraphics();
		world.draw(g);
		animal.draw(g, deltaTime);
		LinkedList sprites = world.getSprites();
		Iterator iterator = sprites.iterator(); // Iterator=コレクション内の要素を順番に取り出す方法
		while (iterator.hasNext()) { // iteratorの中で次の要素がある限りtrue
			Sprite sprite = (Sprite) iterator.next();
			sprite.Update();
			if (animal.isCollision(sprite)) { // 衝突した場合
				if (animal.getflag()) {
					// 無敵状態の時
					sprite.crush(true);
					Assets.bgm_syoutotu.play(1);
				}

				if (sprite instanceof Esa) { // エサの場合
					Esa esa = (Esa) sprite;
					if (esa.getFlag()) { // 餌が本物の場合
						esa.Use(animal);
						sprites.remove(esa);
					}
				}

				if (!animal.getflag()) // 通常状態で障害物に衝突
					game.setScreen(new GameOverScreen(game, world));


				break;
			}

			if (Judg_remove(sprite)) { // 画面外に障害物が出た場合リストから削除
				sprites.remove(sprite);
				break;
			}
		}

		iterator = sprites.iterator();
		while (iterator.hasNext()) {
			Sprite sprite = (Sprite) iterator.next();
			sprite.draw(g);
		}

		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(50);
		int score = world.getScore();
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
		// 試験用(リストサイズを描画)
		// g.drawTextAlp("LIST : " + sprites.size(), 200 - i * 15, 50, paint);
	}

	private boolean Judg_remove(Sprite sprite) {
		if (sprite.getX() < 0 - sprite.getWidth() || sprite.getX() > 480
				|| sprite.getY() < 0 - sprite.getHeight()
				|| sprite.getY() > 810)
			return true;
		else
			return false;
	}

	public boolean isBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
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
	}

	@Override
	public void pause() {
		if (state == GameState.GameOver) {
			// addScore(world.getScore());
			// save(game.getFileIO());
		}
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Assets.bgm_muteki.stop();
		Assets.bgm_playscreen.stop();
		Assets.bgm_syoutotu.dispose();
	}

}
