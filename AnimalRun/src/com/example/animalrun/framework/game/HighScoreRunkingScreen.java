package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.game.Utils;
import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class HighScoreRunkingScreen extends Screen {
	private String[][] list;

	public HighScoreRunkingScreen(Game game) {
		super(game);
		list = Utils.readFile(game.getFileIO());
	}

	@Override
	public void update(float deltaTime) {
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
		if (list == null) {
			g.drawTextAlp("登録スコアがありません", 10, 100, Color.RED, 40);
		} else {
			for (int i = 0; i < list.length; i++) {
				for (int j = 0; j < list[0].length && list[i][j] != null; j++) {
					g.drawTextAlp(list[i][j], 20 + j * 150, (i + 1) * 100,
							Color.RED, 40);
				}
			}
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
