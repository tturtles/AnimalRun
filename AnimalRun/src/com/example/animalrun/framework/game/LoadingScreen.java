package com.example.animalrun.framework.game;

import com.example.animalrun.framework.game.Assets;
import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Graphics;
import com.example.animalrun.framework.Pixmap;
import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		// 背景
		Assets.title = g.newPixmap("title.png", PixmapFormat.ARGB4444);
		Assets.buck_StartScreen = g.newPixmap("StartScreenImage.png",
				PixmapFormat.ARGB4444);
		Assets.way0 = g.newPixmap("way0.png", PixmapFormat.ARGB4444);
		Assets.way1 = g.newPixmap("way1.png", PixmapFormat.ARGB4444);
		// 動物の餌
		Assets.kumaesa_true = g.newPixmap("kumaesa_true.png",
				PixmapFormat.ARGB4444);
		Assets.kumaesa_false = g.newPixmap("kumaesa_false.png",
				PixmapFormat.ARGB4444);
		Assets.lionesa_true = g.newPixmap("lionesa_true.png",
				PixmapFormat.ARGB4444);
		Assets.lionesa_false = g.newPixmap("lionesa_false.png",
		 PixmapFormat.ARGB4444);
		Assets.tanukiesa_true = g.newPixmap("tanukiesa_true.png",
				PixmapFormat.ARGB4444);
		Assets.tanukiesa_false = g.newPixmap("tanukiesa_false.png",
				PixmapFormat.ARGB4444);
		// 障害物
		Assets.car = g.newPixmap("car.png", PixmapFormat.ARGB4444);
		Assets.truk = g.newPixmap("truk.png", PixmapFormat.ARGB4444);
		Assets.walker = g.newPixmap("walker.png", PixmapFormat.ARGB4444);
		// ボタンイラスト配置
		Assets.kuma_bt = g.newPixmap("kuma_bt.png", PixmapFormat.ARGB4444);
		Assets.tanuki_bt = g.newPixmap("tanuki_bt.png", PixmapFormat.ARGB4444);
		Assets.lion_bt = g.newPixmap("lion_bt.png", PixmapFormat.ARGB4444);
		Assets.bt_back = g.newPixmap("back.png", PixmapFormat.ARGB4444);
		Assets.bt_retry = g.newPixmap("bt_retry.png", PixmapFormat.ARGB4444);
		Assets.bt_title = g.newPixmap("bt_title.png", PixmapFormat.ARGB4444);
		Assets.bt_touroku = g
				.newPixmap("bt_touroku.png", PixmapFormat.ARGB4444);
		Assets.bt_kanryou = g
				.newPixmap("bt_kanryou.png", PixmapFormat.ARGB4444);
		Assets.bt_start = g.newPixmap("bt_start.png", PixmapFormat.ARGB4444);
		Assets.bt_score = g.newPixmap("bt_score.png", PixmapFormat.ARGB4444);
		Assets.bt_close = g.newPixmap("bt_close.png", PixmapFormat.ARGB4444);
		// 動物(ノーマル)のイラスト配置
		Assets.kumanorone = g
				.newPixmap("kumanorone.png", PixmapFormat.ARGB4444);
		Assets.kumanortwo = g
				.newPixmap("kumanortwo.png", PixmapFormat.ARGB4444);
		Assets.lionnorone = g
				.newPixmap("lionnorone.png", PixmapFormat.ARGB4444);
		Assets.lionnortwo = g
				.newPixmap("lionnortwo.png", PixmapFormat.ARGB4444);
		Assets.tanukinorone = g.newPixmap("tanukinorone.png",
				PixmapFormat.ARGB4444);
		Assets.tanukinortwo = g.newPixmap("tanukinortwo.png",
				PixmapFormat.ARGB4444);
		// 動物(無敵状態)のイラスト配置
		Assets.kumainvone = g
				.newPixmap("kumainvone.png", PixmapFormat.ARGB4444);
		Assets.kumainvtwo = g
				.newPixmap("kumainvtwo.png", PixmapFormat.ARGB4444);
		Assets.lioninvone = g
				.newPixmap("lioninvone.png", PixmapFormat.ARGB4444);
		Assets.lioninvtwo = g
				.newPixmap("lioninvtwo.png", PixmapFormat.ARGB4444);
		Assets.tanukiinvone = g.newPixmap("tanukiinvone.png",
				PixmapFormat.ARGB4444);
		Assets.tanukiinvtwo = g.newPixmap("tanukiinvtwo.png",
				PixmapFormat.ARGB4444);
		// 動物(ゲームオーバー)のイラスト配置
		Assets.kumagameoverone = g.newPixmap("kumaGameOverone.png",
				PixmapFormat.ARGB4444);
		Assets.kumagameovertwo = g.newPixmap("kumaGameOvertwo.png",
				PixmapFormat.ARGB4444);
		Assets.liongameoverone = g.newPixmap("lionGameOverone.png",
				PixmapFormat.ARGB4444);
		Assets.liongameovertwo = g.newPixmap("lionGameOvertwo.png",
				PixmapFormat.ARGB4444);
		Assets.tanukigameoverone = g.newPixmap("tanukiGameOverone.png",
				PixmapFormat.ARGB4444);
		Assets.tanukigameovertwo = g.newPixmap("tanukiGameOvertwo.png",
				PixmapFormat.ARGB4444);
		
		Assets.table = g.newPixmap("table.png", PixmapFormat.ARGB4444);
		Assets.logo_gameover = g.newPixmap("Logo_GameOver.png", PixmapFormat.ARGB4444);
		Assets.logo_ready = g.newPixmap("Logo_Ready.png", PixmapFormat.ARGB4444);
		Assets.logo_score = g.newPixmap("logo_score.png", PixmapFormat.ARGB4444);
		
		Assets.image_ScoreScreen = g.newPixmap("ScoreScreen.png", PixmapFormat.ARGB4444);
		Assets.image_RunkingScreen = g.newPixmap("RunkingScreen.png", PixmapFormat.ARGB4444);
		Assets.bgm_playscreen = game.getAudio().newMusic("bgm_playscreen.ogg");
		Assets.bgm_muteki = game.getAudio().newMusic("bgm_muteki.ogg");
		Assets.bgm_syoutotu = game.getAudio().newSound("bgm_syoutotu.ogg");
		Assets.bgm_newrecode = game.getAudio().newSound("bgm_newrecode.ogg");
		Assets.bgm_norecode = game.getAudio().newSound("bgm_norecode.ogg");
		Assets.bgm_select = game.getAudio().newSound("bgm_select.ogg");
		Utils.load(game.getFileIO());
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
