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
		Assets.way = g.newPixmap("way.png", PixmapFormat.ARGB4444);
		Assets.car = g.newPixmap("car.png", PixmapFormat.ARGB4444);
		Assets.truk = g.newPixmap("trukbule.png", PixmapFormat.ARGB4444);
		Assets.way0 = g.newPixmap("way0.png", PixmapFormat.ARGB4444);
		Assets.way1 = g.newPixmap("way1.png", PixmapFormat.ARGB4444);
		Assets.way2 = g.newPixmap("way2.png", PixmapFormat.ARGB4444);
		Assets.way3 = g.newPixmap("way3.png", PixmapFormat.ARGB4444);
		Assets.way4 = g.newPixmap("way4.png", PixmapFormat.ARGB4444);
		Assets.way5 = g.newPixmap("way5.png", PixmapFormat.ARGB4444);
		Assets.way6 = g.newPixmap("way6.png", PixmapFormat.ARGB4444);
		Assets.way7 = g.newPixmap("way7.png", PixmapFormat.ARGB4444);
		Assets.way8 = g.newPixmap("way8.png", PixmapFormat.ARGB4444);
		Assets.way9 = g.newPixmap("way9.png", PixmapFormat.ARGB4444);
		Assets.esa_true = g.newPixmap("esa_true.png", PixmapFormat.ARGB4444);
		Assets.esa_false = g.newPixmap("esa_false.png", PixmapFormat.ARGB4444);
		Assets.animal_sp = g.newPixmap("animal_sp.png", PixmapFormat.ARGB4444);
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
