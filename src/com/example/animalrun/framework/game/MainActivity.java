package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Screen;
import com.example.animalrun.framework.impl.AndroidGame;

public class MainActivity extends AndroidGame {

	public Screen getStartScreen() {
		return new LoadingScreen(this);
	}
}