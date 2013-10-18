package com.example.animalrun.framework.game;

import com.example.animalrun.framework.Input.TouchEvent;

public class Utils {
	public Utils() {
	}

	// タップ時の当たり判定 目標がタップされた場合true、違う場合false
	public boolean isBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}
	
}
