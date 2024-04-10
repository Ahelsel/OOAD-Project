package org.ooad.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;

import java.awt.*;

public class GameScreen implements Screen {

    private Level level;
    private ShapeRenderer shapeRenderer;

    private Integer gameWidth = 9;
    private Integer gameHeight = 9;

    // texture for the path/grass
    private SpriteBatch batch;
    private Texture pathTexture;
    private Texture grassTexture;

    public GameScreen() {
        gameWidth = 9;
        gameHeight = 9;

        level = new Level(gameWidth, gameHeight);
        batch = new SpriteBatch();

        pathTexture = new Texture(Gdx.files.internal("path.png"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float v) {
        batch.begin();

        for (int i = 0; i < gameWidth; i++) {
            for (int j = 0; j < gameHeight; j++) {
                Tile tile = level.getTile(i, j);
                // straight line of walkable tiles from left to right in middle
                if (j == 4) {
                    tile.setWalkable(true);
                    if (i == 4) {
                        tile.setContainsEnemy(true);
                    }
                }

                if (tile.isWalkable()) {
                    batch.draw(pathTexture, i*50, j*50, 50, 50);
                } else {
                    batch.draw(grassTexture, i*50, j*50, 50, 50);
                }

            }
        }

        batch.end();
        renderEnemies();
    }

    private void renderEnemies() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
        for (int i = 0; i < gameWidth; i++) {
            for (int j = 0; j < gameHeight; j++) {
                Tile tile = level.getTile(i, j);
                if (tile.containsEnemy()) {
                    shapeRenderer.circle(i*50 + 25, j*50 + 25, 10);
                }
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void show() {

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
        shapeRenderer.dispose();
        pathTexture.dispose();
        grassTexture.dispose();
    }

    public Integer getGameWidth() {
        return gameWidth;
    }

    public Integer getGameHeight() {
        return gameHeight;
    }

}
