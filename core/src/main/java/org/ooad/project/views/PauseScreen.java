package org.ooad.project.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseScreen implements Screen {
    private final GameScreen gameScreen;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private Texture exitButtonTexture;
    private Texture playButtonTexture;
    private Texture grassTexture;
    private Texture pathTexture;

    private final Rectangle resumeButtonBounds;
    private final Rectangle exitToMenuButtonBounds;

    public PauseScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        batch = new SpriteBatch();
        font = new BitmapFont();
        resumeButtonBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2, 200, 100);
        exitToMenuButtonBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 100, 200, 100);
        playButtonTexture = new Texture("buttons/PlayButton.png");
        exitButtonTexture = new Texture("buttons/QuitButton.png");
        grassTexture = new Texture("grass.png");
        pathTexture = new Texture("path.png");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    gameScreen.resume();
                    gameScreen.setIsPaused(false);
                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 touchPoint = new Vector2(screenX, screenY);
                touchPoint.set(touchPoint.x, Gdx.graphics.getHeight() - touchPoint.y);

                if (resumeButtonBounds.contains(touchPoint)) {
                    gameScreen.resume();
                    gameScreen.setIsPaused(false);
                } else if (exitToMenuButtonBounds.contains(touchPoint)) {
                    gameScreen.resetGame();
                    gameScreen.getGame().setScreen(new StartScreen(gameScreen.getGame()));
                    dispose();

                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0.5f);

        batch.begin();
        // iterate over level and draw grass or path depending on if tile is walkable or not
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gameScreen.getLevel().getTile(i, j).isWalkable()) {
                    batch.draw(pathTexture, i * 50, j * 50, 50, 50);
                } else {
                    batch.draw(grassTexture, i * 50, j * 50, 50, 50);
                }
            }
        }

        batch.draw(playButtonTexture, resumeButtonBounds.x, resumeButtonBounds.y, resumeButtonBounds.width, resumeButtonBounds.height);
        batch.draw(exitButtonTexture, exitToMenuButtonBounds.x, exitToMenuButtonBounds.y, exitToMenuButtonBounds.width, exitToMenuButtonBounds.height);
        batch.end();
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
        font.dispose();
    }
}
