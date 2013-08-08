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
	public ScoreScreen(Game game, World world) {
		super(game);
		this.score = world.getScore();
		this.select = world.getSelect();
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
				if (isBounds(event, 20, 650, 140, 100)) {
					game.setScreen(new PlayScreen(game, select));
				}
				if (isBounds(event, 200, 650, 200, 100)) {
					game.setScreen(new StartScreen(game));
				}
			}
		}
	}


	private boolean isBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 480, 800, Color.BLACK);
		g.drawPixmap(Assets.bt_retry, 20, 650);
		g.drawPixmap(Assets.bt_title, 200, 650);
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
