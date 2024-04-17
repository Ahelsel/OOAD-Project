package org.ooad.project;

import com.badlogic.gdx.Screen;

public class Game extends com.badlogic.gdx.Game {
	private Screen gameScreen;

	@Override
	public void create() {
		// create the screen
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
	}

}

