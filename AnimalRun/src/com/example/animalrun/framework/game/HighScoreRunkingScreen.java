package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class HighScoreRunkingScreen extends Screen {

	String lines[] = new String[5];

	public HighScoreRunkingScreen(Game game) {
		super(game);

		for (int i = 0; i < 5; i++) {
			lines[i] = "" + (i + 1) + ". " + Utils.highscores[i];
		}
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
				if (isBounds(event, 270, 680, 200, 100)) {
					game.setScreen(new StartScreen(game));
					return;
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
		g.drawRect(0, 0, 481, 801, Color.WHITE);
		g.drawPixmap(Assets.bt_title, 270, 680);

		for (int i = 0; i < 5; i++) {
			g.drawTextAlp(lines[i], 20, (i + 1) * 50, Color.RED, 20);
		}
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
