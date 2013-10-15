package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class ScoreScreen extends Screen {

	private int score = 0;
	private int select = 0;
	private Utils utils;
	public ScoreScreen(Game game, World world) {
		super(game);
		this.score = world.getScore();
		this.select = world.getSelect();
		utils = new Utils();
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (utils.isBounds(event, 20, 650, 140, 100)) {
					game.chengeEditText(false);
					game.setScreen(new PlayScreen(game, select));
				}
				if (utils.isBounds(event, 200, 650, 200, 100)) {
					game.setScreen(new StartScreen(game));
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 481, 801, Color.WHITE);
		g.drawTextAlp("スコア", 30, 100, Color.RED, 50);
		g.drawTextAlp(""+this.score, 200, 200, Color.BLACK, 100);
		g.drawPixmap(Assets.bt_retry, 20, 650);
		g.drawPixmap(Assets.bt_title, 200, 650);
		g.drawTextAlp("name", 30, 250, Color.RED, 50);
		game.chengeEditText(true);
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
