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
    private Rectangle startButtonBounds;
    private final Game game;

    public StartScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        startButtonTexture = new Texture("grass.png");
        startButtonBounds = new Rectangle(100, 100, 200, 100);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        batch.begin();
        batch.draw(startButtonTexture, startButtonBounds.x, startButtonBounds.y, startButtonBounds.width, startButtonBounds.height);
        batch.end();

        if (Gdx.input.justTouched()) {
            Vector2 touchPoint = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            touchPoint.set(touchPoint.x, Gdx.graphics.getHeight() - touchPoint.y);

            if (startButtonBounds.contains(touchPoint)) {
                game.setScreen(new GameScreen(game));
                dispose();
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
