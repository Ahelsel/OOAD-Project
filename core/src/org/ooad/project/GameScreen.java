package org.ooad.project;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;

import java.awt.*;

public class GameScreen implements Screen {

    private Level level;
    private ShapeRenderer shapeRenderer;

    public GameScreen() {
        level = new Level();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float v) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Tile tile = level.getTile(i, j);
                // straight line from left to right
                if (j == 4) {
                    tile.setWalkable(true);
                    if (i == 4) {
                        tile.setContainsEnemy(true);
                    }
                }

                if (tile.isWalkable()) {
                    // render LibGDX yellow square 50x50 starting at top left (i*50, j*50)
                    shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.YELLOW);
                    shapeRenderer.rect(i*50, j*50, 50, 50);
                    if (tile.containsEnemy()) {
                        // render LibGDX red square 50x50 starting at top left (i*50, j*50)
                        shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
                        shapeRenderer.circle(i*50 + 25, j*50 + 25, 10);

                    }
                } else {
                    // render LibGDX green square 50x50 starting at top left (i*50, j*50)
                    shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.FOREST);
                    shapeRenderer.rect(i*50, j*50, 50, 50);
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
    }

}
