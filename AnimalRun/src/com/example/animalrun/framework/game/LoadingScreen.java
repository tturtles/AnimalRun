package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.title = g.newPixmap("title.png", PixmapFormat.ARGB4444);
		Assets.bt_start = g.newPixmap("bt_start.png", PixmapFormat.ARGB4444);
		Assets.bt_score = g.newPixmap("bt_score.png", PixmapFormat.ARGB4444);
		Assets.bt_close = g.newPixmap("bt_close.png", PixmapFormat.ARGB4444);
		Assets.bt_kapipara = g.newPixmap("bt_kapipara.png", PixmapFormat.ARGB4444);
		Assets.bt_lion = g.newPixmap("bt_lion.png", PixmapFormat.ARGB4444);
		Assets.bt_datyou = g.newPixmap("bt_datyou.png", PixmapFormat.ARGB4444);
		Assets.animal = g.newPixmap("animal.png", PixmapFormat.ARGB4444);
		Assets.car = g.newPixmap("car.png", PixmapFormat.ARGB4444);
		Assets.truk = g.newPixmap("trukbule.png", PixmapFormat.ARGB4444);
		Assets.way0 = g.newPixmap("way0.png", PixmapFormat.ARGB4444);
		Assets.way1 = g.newPixmap("way1.png", PixmapFormat.ARGB4444);
		Assets.esa1_true = g.newPixmap("esa1_true.png", PixmapFormat.ARGB4444);
		Assets.esa1_false = g.newPixmap("esa1_false.png", PixmapFormat.ARGB4444);
		Assets.esa2_true = g.newPixmap("esa2_true.png", PixmapFormat.ARGB4444);
		Assets.esa2_false = g.newPixmap("esa2_false.png", PixmapFormat.ARGB4444);
		Assets.animal_sp = g.newPixmap("animal_sp.png", PixmapFormat.ARGB4444);
		Assets.bt_back = g.newPixmap("back.png", PixmapFormat.ARGB4444);
		Assets.bt_retry = g.newPixmap("retry.png", PixmapFormat.ARGB4444);
		game.setScreen(new StartScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO 自動生成されたメソッド・スタブ

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
