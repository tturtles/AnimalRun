package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.game.Utils;
import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class HighScoreRunkingScreen extends Screen {
	private String[][] list_easy;
	private String[][] list_normal;
	private String[][] list_hard;

	public HighScoreRunkingScreen(Game game) {
		super(game);
		list_easy = Utils.readFile(game.getFileIO(), "easy");
		list_normal = Utils.readFile(game.getFileIO(), "normal");
		list_hard = Utils.readFile(game.getFileIO(), "hard");
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
		if (list_easy == null) {
			g.drawTextAlp("easyの登録スコアがありません", 10, 100, Color.RED, 25);
		} else {
			g.drawTextAlp("easyモードランキング", 10, 100, Color.RED, 25);
			for (int i = 0; i < list_easy.length; i++) {
				g.drawTextAlp((i+1)+"位", 10, 100+(i + 1) * 30, Color.RED, 25);
				for (int j = 0; j < list_easy[0].length && list_easy[i][j] != null; j++) {
					g.drawTextAlp(list_easy[i][j], 60 + j * 150, 100+(i + 1) * 30,
							Color.RED, 25);
				}
			}
		}
		if (list_normal == null) {
			g.drawTextAlp("normalの登録スコアがありません", 10, 300, Color.RED, 25);
		} else {
			g.drawTextAlp("easyモードランキング", 10, 300, Color.RED, 25);
			for (int i = 0; i < list_normal.length; i++) {
				g.drawTextAlp((i+1)+"位", 10, 300+(i + 1) * 30, Color.RED, 25);
				for (int j = 0; j < list_normal[0].length && list_normal[i][j] != null; j++) {
					g.drawTextAlp(list_normal[i][j], 60 + j * 150, 300+(i + 1) * 30,
							Color.RED, 25);
				}
			}
		}
		if (list_hard == null) {
			g.drawTextAlp("hardの登録スコアがありません", 10, 500, Color.RED, 25);
		} else {
			g.drawTextAlp("hardモードランキング", 10, 500, Color.RED, 25);
			for (int i = 0; i < list_hard.length; i++) {
				g.drawTextAlp((i+1)+"位", 10, 500+(i + 1) * 30, Color.RED, 25);
				for (int j = 0; j < list_hard[0].length && list_hard[i][j] != null; j++) {
					g.drawTextAlp(list_hard[i][j], 60 + j * 150, 500+(i + 1) * 30,
							Color.RED, 25);
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
