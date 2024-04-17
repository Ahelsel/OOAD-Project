package org.ooad.project;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

    private float animationTime = 0f;

    public GameScreen() {
        gameWidth = 9;
        gameHeight = 9;

        level = Level.getInstance(gameWidth, gameHeight, 1);

        levelRenderer = new LevelRenderer(level);
        towerRenderer = new TowerRenderer(level);
        enemyRenderer = new EnemyRenderer(level);

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
