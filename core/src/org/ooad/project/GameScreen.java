package org.ooad.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import org.ooad.project.entity.Enemy;
import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;

public class GameScreen implements Screen {
    private Integer gameWidth = 9;
    private Integer gameHeight = 9;
    private Double tileWidth = 50.0;


    private Level level;

    // texture for the path/grass
    private SpriteBatch batch;
    private Texture pathTexture;
    private Texture grassTexture;

    private Animation<TextureRegion> entityAnimation;
    private Enemy sampleEnemy;

    // temp
    Float enemyX = 0.0f;
    Float enemyY = (gameWidth/ 2) * 50.0f;
    // end temp

    private float animationTime = 0f;

    public GameScreen() {
        gameWidth = 9;
        gameHeight = 9;

        level = new Level(gameWidth, gameHeight);
        batch = new SpriteBatch();

        pathTexture = new Texture(Gdx.files.internal("path.png"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

        sampleEnemy = new Enemy(0.0f, (gameWidth/2)*50.0f);

        loadEntityAnimation();
    }

    private void loadEntityAnimation() {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 17; i++) {
            String filename = "entityMove/move" + i + ".png";
            TextureRegion frameTexture = new TextureRegion(new Texture(Gdx.files.internal(filename)));
            frames.add(new TextureRegion(frameTexture));
        }
        entityAnimation = new Animation<>(0.1f, frames);
    }

    @Override
    public void render(float v) {
        animationTime += v;
        //animationTime += Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(Color.BLACK);
        updateEnemyPosition(sampleEnemy);

        batch.begin();
        // do all the rendering here
        renderLevel();
        // @TODO: render towers
        renderEnemies();
        // @TODO: render projectiles
        // @TODO: render GUI/health bar

        batch.end();
    }

    private void renderLevel() {
        for (Float i = 0.0f; i < gameWidth; i+=1.0f) {
            for (Float j = 0.0f; j < gameHeight; j+=1.0f) {
                Tile tile = level.getTile(i.intValue(), j.intValue());
                // straight line of walkable tiles from left to right in middle
                if (j == gameWidth/2) {
                    tile.setWalkable(true);
                }
                if (tile.isWalkable()) {
                    batch.draw(pathTexture, i*50, j*50, 50, 50);
                } else {
                    batch.draw(grassTexture, i*50, j*50, 50, 50);
                }
            }
        }

    }

    private void renderEnemies() {
        TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
        batch.draw(currentFrame, sampleEnemy.getX(), sampleEnemy.getY(), 50, 50);
    }

    public void updateEnemyPosition(Enemy enemy)  {
        Float deltaX = 10.0f / 60.0f;
        Float deltaY = 0.0f;
        enemy.move(deltaX, deltaY);
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
