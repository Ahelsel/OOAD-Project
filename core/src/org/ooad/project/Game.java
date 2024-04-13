package org.ooad.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
	private Screen gameScreen;
	private int playerLife = 1; // Health of the player
	private BitmapFont font;
	private SpriteBatch batch;

	@Override
	public void create() {
		// create the screen
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);

		// Initialize font and batch
		font = new BitmapFont();
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		super.render();

		// Draw current health
		batch.begin();
		font.draw(batch, "Health: " + playerLife, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50);
		batch.end();

		// Check if player life is zero
		if (playerLife <= 0) {
			// End the game
			Gdx.app.exit();
		}
	}

	public void decreaseLife(int damage) {
		playerLife -= damage;
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
		font.dispose();
		batch.dispose();
	}

}

