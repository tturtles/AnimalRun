package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.game.Utils;
import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class HighScoreRunkingScreen extends Screen {
	private String[][][] list;

	public HighScoreRunkingScreen(Game game) {
		super(game);
		list = new String[3][][];
		String[] mode_list = { "easy", "normal", "hard" };
		for (int i = 0; i < list.length; i++)
			list[i] = Utils.readFile(game.getFileIO(), mode_list[i]);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (isBounds(event, 280, 700, 200, 100)) {
					game.setScreen(new StartScreen(game));
					Assets.bgm_select.play(1);
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.image_RunkingScreen, 0, 0);
		g.drawPixmap(Assets.bt_title, 280, 700);
		for (int i = 0; i < list.length; i++)
			if (list[i] == null) {
				// g.drawTextAlp(mode_list[i] + "の登録スコアがありません", 10,
				// (100 + i * 200), Color.RED, 25);
			} else {
				for (int j = 0; j < list[i].length; j++) {
					g.drawTextAlp((j + 1) + "位", 10, (190 + i * 220) + (j + 1)
							* 30, Color.RED, 25);
					for (int k = 0; k < list[i][j].length
							&& list[i][j][k] != null; k++) {
						g.drawTextAlp(list[i][j][k], 60 + k * 150,
								(190 + i * 220) + (j + 1) * 30, Color.RED, 25);
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
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
