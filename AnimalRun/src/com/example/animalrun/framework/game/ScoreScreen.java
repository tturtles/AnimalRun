package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.game.Utils;
import com.example.animalrun.framework.game.Assets;
import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class ScoreScreen extends Screen {

	private World world;
	private boolean flag = false;

	public ScoreScreen(Game game, World world) {
		super(game);
		this.world = world;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		String mode = null;
		switch (world.getSelect()) {
		case 1:
			mode = "easy";
			break;
		case 2:
			mode = "normal";
			break;
		case 3:
			mode = "hard";
			break;
		}
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (isBounds(event, 20, 650, 140, 100)) {
					game.setScreen(new PlayScreen(game, world.getSelect()));
					game.chengeEditText(false);
					return;
				}
				if (isBounds(event, 140, 400, 200, 100)) {
					flag = Utils.addScore(game.getFileIO(), game.getEText(),
							world.getScore(), mode);
					return;
				}
				if (isBounds(event, 200, 650, 200, 100)) {
					game.setScreen(new StartScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 481, 801, Color.WHITE);
		g.drawTextAlp("スコア", 30, 100, Color.RED, 50);
		g.drawTextAlp("" + world.getScore(), 200, 200, Color.BLACK, 100);
		g.drawPixmap(Assets.bt_retry, 20, 650);
		g.drawPixmap(Assets.bt_title, 200, 650);
		g.drawTextAlp("name", 30, 250, Color.RED, 50);
		g.drawPixmap(Assets.bt_touroku, 140, 400);
		if (flag)
			g.drawTextAlp("登録完了", 110, 470, Color.RED, 70);
		game.chengeEditText(true);
	}

	public boolean isBounds(TouchEvent event, int x, int y, int width,
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
