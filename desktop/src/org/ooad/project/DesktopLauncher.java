package org.ooad.project;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.ooad.project.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("OOAD-Project");

		// the tiles are 50px in size, and the level is 9x9, so we do this
		// so that there is no filler/black space around the level when the window is rendered
		config.setWindowedMode(50*9, 50*9);
		config.setResizable(false);

		Game gameInstance = new Game();
		new Lwjgl3Application(gameInstance, config);
		gameInstance.setScreen(new GameScreen(gameInstance)); // Pass the Game instance to GameScreen
	}
}
