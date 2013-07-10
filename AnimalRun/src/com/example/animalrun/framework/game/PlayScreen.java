package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Game;
import com.example.animalrun.framework.Screen;

public class PlayScreen extends Screen {

	enum GameState {
		Ready, Running, Paused, GameOver, Clear
	}

	GameState state = GameState.Ready;
	private int select = 0;
	
	public PlayScreen(Game game, int _select) {
		super(game);
		select = _select;
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void present(float deltaTime) {
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
