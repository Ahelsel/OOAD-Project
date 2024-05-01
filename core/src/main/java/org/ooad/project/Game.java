package org.ooad.project;

import com.badlogic.gdx.Screen;
import jdk.javadoc.internal.tool.Start;
import org.ooad.project.views.GameScreen;
import org.ooad.project.views.StartScreen;

public class Game extends com.badlogic.gdx.Game {
	private Screen startScreen;

	@Override
	public void create() {
		startScreen = new StartScreen(this);
		setScreen(startScreen);
	}

	@Override
	public void dispose() {
		startScreen.dispose();
	}

}

