package org.ooad.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;

import java.awt.*;

public class GameScreen implements Screen {
    private Integer gameWidth = 9;
    private Integer gameHeight = 9;


    private Level level;

    // texture for the path/grass
    private SpriteBatch batch;
    private Texture pathTexture;
    private Texture grassTexture;

    private Animation<TextureRegion> entityAnimation;
    private float animationTime = 0f;

    public GameScreen() {
        gameWidth = 18;
        gameHeight = 18;

        level = new Level(gameWidth, gameHeight);
        batch = new SpriteBatch();

        pathTexture = new Texture(Gdx.files.internal("path.png"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

        loadEntityAnimation();
    }

    private void loadEntityAnimation() {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 17; i++) {
            // get frame i from the assets/entityMove folder
            String filename = "entityMove/move" + i + ".png";
            // actual path example: "assets/entityMove/skeleton-move_0.png"
            TextureRegion frameTexture = new TextureRegion(new Texture(Gdx.files.internal(filename)));
            frames.add(new TextureRegion(frameTexture));
        }
        entityAnimation = new Animation<>(0.1f, frames);
    }

    @Override
    public void render(float v) {
        animationTime += v;
        renderLevel();

        // @TODO: render towers
        // @TODO: render projectiles

        renderEnemies();

        // @TODO: render GUI/health bar
    }

    private void renderLevel() {
        batch.begin();
        for (int i = 0; i < gameWidth; i++) {
            for (int j = 0; j < gameHeight; j++) {
                Tile tile = level.getTile(i, j);
                // straight line of walkable tiles from left to right in middle
                if (j == gameWidth/2) {
                    tile.setWalkable(true);
                    if (i == gameWidth/2) {
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
    }

    private void renderEnemies() {
        batch.begin();

        for (int i = 0; i < gameWidth; i++) {
            for (int j = 0; j < gameHeight; j++) {
                Tile tile = level.getTile(i, j);
                if (tile.containsEnemy()) {
                    TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
                    batch.draw(currentFrame, i*50, j*50, 50, 50);
                }
            }
        }
        batch.end();
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
        pathTexture.dispose();
        grassTexture.dispose();
        for (TextureRegion region : entityAnimation.getKeyFrames()) {
            region.getTexture().dispose();
        }
    }

    public Integer getGameWidth() {
        return gameWidth;
    }

    public Integer getGameHeight() {
        return gameHeight;
    }

}
