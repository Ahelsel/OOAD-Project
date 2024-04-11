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
    //private Array<Enemy> enemyArray;
    private Enemy enemy;
    private float animationTime = 0f;

    public GameScreen() {
        gameWidth = 9;
        gameHeight = 9;

        level = new Level(gameWidth, gameHeight);
        batch = new SpriteBatch();
        //enemyArray = new Array<>();
        enemy = new Enemy();

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
        // clear the screen
        ScreenUtils.clear(Color.BLACK);

        // do all the updating here
        //animationTime += v;
        animationTime += Gdx.graphics.getDeltaTime();
        updateEnemyPosition();

        // do all the rendering here
        renderLevel();
        // @TODO: render towers
        renderEnemies();
        // @TODO: render projectiles
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
                    if (i == 0) {
                        // just for exaple: put enemy in the middle of the level
                        Enemy enemyToAdd = new Enemy();
                        enemyToAdd.setXCoordinate(i*50.0);
                        enemyToAdd.setYCoordinate(j*50.0);
                        //enemyArray.add(enemyToAdd);
                        enemy = enemyToAdd;
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

        TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
        batch.draw(currentFrame, enemy.getXCoordinate().floatValue(), enemy.getYCoordinate().floatValue(), 50, 50);

        batch.end();
    }

    public void updateEnemyPosition()  {
        // this will be called every frame before rendering. We need to move the enemies along the path
        // at a rate that makes sense. We can do 1 tiles per second for example.
        // render rate is 60fps, so we can move 1/60 tiles per frame

        float deltaTime = Gdx.graphics.getDeltaTime();

        Double movementSpeed = 100.0;
        Double movementThisFrame = movementSpeed * deltaTime;

        // this logic will need to be changed once corners are implemented
        // or if the path is not left to right (i.e. top to bottom)
        Double newX = enemy.getXCoordinate() + movementThisFrame;
        Double newY = enemy.getYCoordinate();

        enemy.setXCoordinate(newX);
        enemy.setYCoordinate(newY);

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
