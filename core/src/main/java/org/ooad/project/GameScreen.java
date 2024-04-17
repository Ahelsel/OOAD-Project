package org.ooad.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
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
    private Level level;
    private BitmapFont font;
    private SpriteBatch batch;

    private boolean gameOver;
    private float gameOverTime;

    private float animationTime = 0f;

    public GameScreen() {
        gameWidth = 9;
        gameHeight = 9;

        level = Level.getInstance(gameWidth, gameHeight, 2);

        levelRenderer = new LevelRenderer(level);
        towerRenderer = new TowerRenderer(level);
        enemyRenderer = new EnemyRenderer(level);

        font = new BitmapFont();
        batch = new SpriteBatch();

        gameOver = false;
        gameOverTime = 0;
    }


    @Override
    public void render(float v) {
        animationTime += v;
        ScreenUtils.clear(Color.BLACK);

        levelRenderer.render();
        towerRenderer.render();
        enemyRenderer.render(animationTime);
        // @TODO: render projectiles
        // @TODO: render GUI/health bar

        batch.begin();
        font.draw(batch, "Enemies remaining: " + level.getNumEnemies(), 10, level.getHeight() * 50 - 10);
        batch.end();

        if (level.getNumEnemies() == 0 && level.getEnemies().isEmpty() && !gameOver) {
            gameOver = true;
            gameOverTime = animationTime;
        }

        if (gameOver) {
            batch.begin();
            font.draw(batch, "Game Over", level.getWidth() * 25 - 50, level.getHeight() * 25);
            batch.end();

            if (animationTime - gameOverTime > 3) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void show() {
        // @TODO: show game
    }

    @Override
    public void resize(int i, int i1) {
        // @TODO: resize game
    }

    @Override
    public void pause() {
        // @TODO: pause game
    }

    @Override
    public void resume() {
        // @TODO: resume game
    }

    @Override
    public void hide() {
        // @TODO: hide game
    }

    @Override
    public void dispose() {
        levelRenderer.dispose();
        towerRenderer.dispose();
        enemyRenderer.dispose();
    }

}
