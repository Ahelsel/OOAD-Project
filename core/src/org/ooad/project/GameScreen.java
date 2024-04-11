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
        sampleEnemy = new Enemy();

        pathTexture = new Texture(Gdx.files.internal("path.png"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

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

        batch.begin();
        // do all the rendering here
        //renderLevel();
        // @TODO: render towers
        //renderEnemies();
        // @TODO: render projectiles
        // @TODO: render GUI/health bar
        for (Float i = 0.0f; i < gameWidth; i+=1.0f) {
            for (Float j = 0.0f; j < gameHeight; j+=1.0f) {
                Tile tile = level.getTile(i.intValue(), j.intValue());
                // straight line of walkable tiles from left to right in middle
                if (j == gameWidth/2) {
                    tile.setWalkable(true);
                    if (i == 0) {
                        // just for example: put enemy in the middle of the level
                        Enemy enemyToAdd = new Enemy();
                        enemyToAdd.setXCoordinate(i*50.0f);
                        enemyToAdd.setYCoordinate(j*50.0f);
                        sampleEnemy = enemyToAdd;
                    }
                }
                if (tile.isWalkable()) {
                    batch.draw(pathTexture, i*50, j*50, 50, 50);
                } else {
                    batch.draw(grassTexture, i*50, j*50, 50, 50);
                }
            }
        }

        //Float newX = sampleEnemy.getXCoordinate() + (1.0f / 60.0f);
        //Float newY = sampleEnemy.getYCoordinate();
        enemyX += 10.0f / 60.0f ;
        sampleEnemy.changePosition(enemyX, enemyY);
        TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
        System.out.println("new X: " + enemyX + " new Y: " + enemyY);
        batch.draw(currentFrame, enemyX, enemyY, 50, 50);

        batch.end();
    }

//    private void renderLevel() {
//        for (Float i = 0.0f; i < gameWidth; i+=1.0f) {
//            for (Float j = 0.0f; j < gameHeight; j+=1.0f) {
//                Tile tile = level.getTile(i.intValue(), j.intValue());
//                // straight line of walkable tiles from left to right in middle
//                if (j == gameWidth/2) {
//                    tile.setWalkable(true);
//                    if (i == 0) {
//                        // just for example: put enemy in the middle of the level
//                        Enemy enemyToAdd = new Enemy();
//                        enemyToAdd.setXCoordinate(i*50.0f);
//                        enemyToAdd.setYCoordinate(j*50.0f);
//                        sampleEnemy = enemyToAdd;
//                    }
//                }
//                if (tile.isWalkable()) {
//                    batch.draw(pathTexture, i*50, j*50, 50, 50);
//                } else {
//                    batch.draw(grassTexture, i*50, j*50, 50, 50);
//                }
//            }
//        }
//
//        Float[] newCoords = updateEnemyPosition(sampleEnemy);
//        sampleEnemy.changePosition(newCoords[0], newCoords[1]);
//        TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
//        System.out.println("new X: " + newCoords[0] + " new Y: " + newCoords[1]);
//        batch.draw(currentFrame, newCoords[0], newCoords[1], 50, 50);
//    }
//
//    private void renderEnemies() {
//        Float[] newCoords = updateEnemyPosition(sampleEnemy);
//        sampleEnemy.changePosition(newCoords[0], newCoords[1]);
//        TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
//        System.out.println("new X: " + newCoords[0] + " new Y: " + newCoords[1]);
//        batch.draw(currentFrame, newCoords[0], newCoords[1], 50, 50);
//    }

    public Float[] updateEnemyPosition(Enemy enemy)  {
        // this will be called every frame before rendering. We need to move the enemies along the path
        // at a rate that makes sense. We can do 1 tiles per second for example.
        // render rate is 60fps, so we can move 1/60 tiles per frame
        Float movementThisFrame = 1.0f / 60.0f;

        // this logic will need to be changed once corners are implemented
        // or if the path is not left to right (i.e. top to bottom)
        Float newX = enemy.getXCoordinate() + movementThisFrame;
        Float newY = enemy.getYCoordinate();

        System.out.println("new x: " + newX + " new y: " + newY);

        return new Float[]{newX, newY};
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
