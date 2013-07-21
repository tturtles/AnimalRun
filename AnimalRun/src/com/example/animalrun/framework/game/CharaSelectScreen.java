package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Input.TouchEvent;

public class CharaSelectScreen extends Screen {

	public CharaSelectScreen(Game game) {
		super(game);
	}

	private int select = 0;

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (isBounds(event, 0, 100, 480, 200)) {
					select = 1;
				} else if (isBounds(event, 0, 300, 480, 200)) {
					select = 2;
				} else if (isBounds(event, 0, 500, 480, 200)) {
					select = 3;
				}
				if(select>0) {
					game.setScreen(new PlayScreen(game, select));
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
		g.drawPixmap(Assets.bt_kapipara, 0, 100);
		g.drawPixmap(Assets.bt_lion, 0, 300);
		g.drawPixmap(Assets.bt_datyou, 0, 500);
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
