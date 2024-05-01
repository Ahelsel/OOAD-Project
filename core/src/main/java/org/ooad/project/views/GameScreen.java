package org.ooad.project.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.ooad.project.Game;
import org.ooad.project.graphics.GuiRenderer;
import org.ooad.project.level.Level;
import org.ooad.project.graphics.LevelRenderer;
import org.ooad.project.graphics.TowerRenderer;
import org.ooad.project.graphics.EnemyRenderer;

public class GameScreen implements Screen {
    private Integer gameWidth = 9;
    private Integer gameHeight = 9;
    private LevelRenderer levelRenderer;
    private TowerRenderer towerRenderer;
    private EnemyRenderer enemyRenderer;
    private GuiRenderer guiRenderer;
    private Boolean isPaused;
    private Level level;
    private boolean gameOver;
    private float gameOverTime;
    private float animationTime = 0f;

    private final Game game;

    public GameScreen(Game game) {
        this.game = game;
        isPaused = false;

        gameOver = false;
        gameOverTime = 0;

        gameWidth = 9;
        gameHeight = 9;

        level = Level.getInstance(gameWidth, gameHeight, 5);

        levelRenderer = new LevelRenderer(level);
        towerRenderer = new TowerRenderer(level);
        enemyRenderer = new EnemyRenderer(level);
        guiRenderer   = new GuiRenderer(level);

        level.placeTower(level.getTile(2, (level.getHeight()/2) + 1));
        level.placeTower(level.getTile(6, (level.getHeight()/2) - 2));
    }

    @Override
    public void render(float v) {
        if (!isPaused) {
            animationTime += v;

            ScreenUtils.clear(Color.BLACK);

            levelRenderer.render();
            towerRenderer.render();
            enemyRenderer.render(animationTime);
            // @TODO: render projectiles
            guiRenderer.renderEnemiesRemaining();

            if (level.getNumEnemies() == 0 && level.getEnemies().isEmpty() && !gameOver) {
                gameOver = true;
                gameOverTime = animationTime;
            }

            if (gameOver) {
                float alpha = Math.min(1, (animationTime - gameOverTime) / 4);
                if (animationTime - gameOverTime > 3) {
                    resetGame();
                    getGame().setScreen(new StartScreen(game));
                    dispose();
                } else {
                    guiRenderer.renderGameOver(alpha);
                }
            }
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    if (!isPaused) {
                        pause();
                        setIsPaused(true);
                    }
                }
                return true;
            }
        });
    }

    public void setIsPaused(Boolean isPaused) {
        this.isPaused = isPaused;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void pause() {
        game.setScreen(new PauseScreen(this));
    }

    @Override
    public void resume() {
        game.setScreen(this);
    }

    @Override
    public void hide() {
        // @TODO: hide game
    }

    @Override
    public void resize(int i, int i1) {
        // @TODO: resize game
    }

    @Override
    public void dispose() {
        levelRenderer.dispose();
        towerRenderer.dispose();
        enemyRenderer.dispose();
        guiRenderer.dispose();
    }

    public Level getLevel() {
        return level;
    }

    public void resetGame() {
        level.resetLevel();
        level = Level.getInstance(gameWidth, gameHeight, 5);
        levelRenderer = new LevelRenderer(level);
        towerRenderer = new TowerRenderer(level);
        enemyRenderer = new EnemyRenderer(level);
        guiRenderer   = new GuiRenderer(level);
        isPaused = false;
        gameOver = false;
        gameOverTime = 0f;
        animationTime = 0f;
    }

}
