package org.ooad.project.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import org.ooad.project.Game;

public class StartScreen implements Screen {
    private SpriteBatch batch;
    private Texture startButtonTexture;
    private Texture exitButtonTexture;
    private Texture backgroundTexture;
    private Rectangle startButtonBounds;
    private Rectangle exitButtonBounds;
    private Texture logoTexture;
    private Rectangle logoBounds;
    private final Game game;
    public StartScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        logoTexture = new Texture("logo.png");
        startButtonTexture = new Texture("buttons/PlayButton.png");
        exitButtonTexture = new Texture("buttons/QuitButton.png");
        backgroundTexture = new Texture("grass.png");
        // start button should be in the center of the screen, but shifted down a bit
        logoBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 175, 400, 400);
        startButtonBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 150, 200, 100);
        exitButtonBounds  = new Rectangle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 150, 200, 100);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        batch.begin();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(backgroundTexture, i * 50, j * 50, 50, 50);
            }
        }

        batch.draw(logoTexture, logoBounds.x, logoBounds.y, logoBounds.width, logoBounds.height);
        batch.draw(startButtonTexture, startButtonBounds.x, startButtonBounds.y, startButtonBounds.width, startButtonBounds.height);
        batch.draw(exitButtonTexture, exitButtonBounds.x, exitButtonBounds.y, exitButtonBounds.width, exitButtonBounds.height);
        batch.end();

        if (Gdx.input.justTouched()) {
            Vector2 touchPoint = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            touchPoint.set(touchPoint.x, Gdx.graphics.getHeight() - touchPoint.y);

            if (startButtonBounds.contains(touchPoint)) {
                game.setScreen(new GameScreen(game));
                dispose();
            }

            if (exitButtonBounds.contains(touchPoint)) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        startButtonTexture.dispose();
    }
}
