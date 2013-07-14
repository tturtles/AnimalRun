package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class ScoreScreen extends Screen {

	public ScoreScreen(Game game) {
		super(game);
	}

//	private int select=0;
	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (isBounds(event, 70, 500, 210, 100)) {
					game.setScreen(new CharaSelectScreen(game));
//					select = 1;
				} else if (isBounds(event, 260, 500, 400, 100)) {
					game.setScreen(new LoadingScreen(game));
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
		g.drawPixmap(Assets.ScoreScreen_retry, 70, 500);
		g.drawPixmap(Assets.ScoreScreen_title, 260, 500);


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
