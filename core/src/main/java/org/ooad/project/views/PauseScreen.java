package org.ooad.project.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseScreen implements Screen {
    private final GameScreen gameScreen;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Rectangle resumeButtonBounds;
    private final Rectangle exitToMenuButtonBounds;
    private final Rectangle exitToDesktopButtonBounds;

    public PauseScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        batch = new SpriteBatch();
        font = new BitmapFont();
        resumeButtonBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 50, 200, 50);
        exitToMenuButtonBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 25, 200, 50);
        exitToDesktopButtonBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 100, 200, 50);
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
                    gameScreen.getGame().setScreen(new StartScreen(gameScreen.getGame()));
                    gameScreen.dispose();
                } else if (exitToDesktopButtonBounds.contains(touchPoint)) {
                    Gdx.app.exit();
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0.5f);

        batch.begin();
        font.draw(batch, "Resume", resumeButtonBounds.x + 50, resumeButtonBounds.y + 30);
        font.draw(batch, "Exit to Menu", exitToMenuButtonBounds.x + 30, exitToMenuButtonBounds.y + 30);
        font.draw(batch, "Exit to Desktop", exitToDesktopButtonBounds.x + 10, exitToDesktopButtonBounds.y + 30);
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
