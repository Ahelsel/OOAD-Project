package org.ooad.project;

import com.badlogic.gdx.Screen;
import org.ooad.project.views.GameScreen;
import org.ooad.project.views.StartScreen;

public class Game extends com.badlogic.gdx.Game {
	private Screen gameScreen;

	@Override
	public void create() {
		// create the screen
		//gameScreen = new GameScreen(this);
		setScreen(new StartScreen(this));
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
	}

}

