package com.example.animalrun.framework.game;

import java.util.List;

import android.graphics.Color;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;
import com.example.animalrun.framework.Screen;

public class GameOverScreen extends Screen {

	private static final float TICK_INITIAL = 2.0f;
	private static float tick = TICK_INITIAL;
	private float tickTime = 0;
	private Pixmap image;
	private World world;
	private boolean flag = false;

	public GameOverScreen(Game game, World world) {
		super(game);
		this.world = world;
		String value[] = new String[1];
		switch (world.getSelect()) {
		case 1:
			image = Assets.tanukigameoverone;
			value[0] = "easy";
			break;
		case 2:
			image = Assets.kumagameoverone;
			value[0] = "normal";
			break;
		case 3:
			image = Assets.liongameoverone;
			value[0] = "hard";
			break;
		}
		String[] columns = { "name", "score" };
		String order = "score desc";
		String where = "mode like ?";
		String[][] list = game.getFileIO().readFile(columns, where, value,
				order, 1);

		if (list == null || Integer.parseInt(list[0][1]) < world.getScore())
			flag = true;
		switch (world.getSelect()) {
		case 1:
			if (flag)
				image = Assets.tanukigameoverone;
			else
				image = Assets.tanukigameovertwo;
			break;
		case 2:
			if (flag)
				image = Assets.kumagameoverone;
			else
				image = Assets.kumagameovertwo;
			break;
		case 3:
			if (flag)
				image = Assets.liongameoverone;
			else
				image = Assets.liongameovertwo;
			break;
		}
		if(flag)
			Assets.bgm_newrecode.play(1);
		else 
			Assets.bgm_norecode.play(1);
	}

	@Override
	public void update(float deltaTime) {
		tickTime += deltaTime;
		if (tickTime > tick)
			game.setScreen(new ScoreScreen(game, world));
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 481, 801, Color.BLACK);
		g.drawPixmap(Assets.table, 40, 500);
		g.drawPixmap(image, 90, 370);
		g.drawPixmap(Assets.logo_gameover, 20, 100);
	}

	@Override
	public void pause() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void resume() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void dispose() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
