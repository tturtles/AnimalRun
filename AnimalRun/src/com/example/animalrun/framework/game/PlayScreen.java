package com.example.animalrun.framework.game;

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
		Ready, Running, Paused, GameOver, Clear
	}

	GameState state = GameState.Ready;
	private int select = 0;
	private World world;
	private Lion lion;
	
	public PlayScreen(Game game, int _select) {
		super(game);
		select = _select;
		world = new World();
		switch (select) {
		case 3:
			lion = new Lion(190, 630, Assets.animal, world);
			break;

		default:
			break;
		}
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
		if (state == GameState.Clear)
			updateClear(touchEvents);
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
			case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_DOWN:
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		// ゲームオーバー時のタッチ処理書き込み

	}

	private void updateClear(List<TouchEvent> touchEvents) {
		// ゲームクリア時のタッチ処理書き込み
	}

	@Override
	public void present(float deltaTime) {
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running) {
			drawRunningUI();
		}
		if (state == GameState.GameOver)
			drawGameOverUI();
		if (state == GameState.Clear)
			drawClearUI();
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
		lion.draw(g);
	}

	private void drawGameOverUI() {
		// ゲームオーバー時のUI(描画系)
	}

	private void drawClearUI() {
		// ゲームクリア時のUI(描画系)
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
