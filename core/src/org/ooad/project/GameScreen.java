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

import java.security.DigestOutputStream;

public class GameScreen implements Screen {
    private Integer gameWidth = 9;
    private Integer gameHeight = 9;
    private Double tileWidth = 50.0;
    private Level level;

    // texture for the path/grass
    private SpriteBatch batch;
    private Texture pathTexture;
    private Texture grassTexture;

    private Array<Float> xLeftPivots = new Array<>();
    private Array<Float> yLeftPivots = new Array<>();

    private Array<Float> xRightPivots = new Array<>();
    private Array<Float> yRightPivots = new Array<>();
    private Animation<TextureRegion> entityAnimation;
    private Enemy sampleEnemy;

    private Boolean pathFound = false;

    private float animationTime = 0f;

    public GameScreen() {
        gameWidth = 9;
        gameHeight = 9;

        level = new Level(gameWidth, gameHeight);
        batch = new SpriteBatch();

        pathTexture = new Texture(Gdx.files.internal("path.png"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

        // create enemies here
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
        findPath();
        renderTowers();
        renderEnemies();
        // @TODO: render projectiles
        // @TODO: render GUI/health bar

        batch.end();
    }

    public void renderTowers() {
        // tower is located in assets/tower/Cannon.png
        // the tile (gameHeight/2 + 1, 2) should have a tower
        // make the tower size 50x50

        TextureRegion towerTexture = new TextureRegion(new Texture(Gdx.files.internal("towers/Cannon.png")));
        TextureRegion baseTexture = new TextureRegion(new Texture(Gdx.files.internal("towers/Tower.png")));
        batch.draw(baseTexture, 2*50 + 5, (gameHeight/2 + 1)*50+5, 40, 40);
        batch.draw(towerTexture, 2*50 + 10, (gameHeight/2 + 1)*50+10, 30, 30);

    }

    public void findPath() {
        if (!pathFound) {
            Tile currentTile = level.getFirstTile();
            while (currentTile != null) {
                if (level.isLeftTurn(currentTile)) {
                    xLeftPivots.add(currentTile.getXCoordinate().floatValue());
                    yLeftPivots.add(currentTile.getYCoordinate().floatValue());
                } else if (level.isRightTurn(currentTile)) {
                    xRightPivots.add(currentTile.getXCoordinate().floatValue());
                    yRightPivots.add(currentTile.getYCoordinate().floatValue());
                }
                currentTile = currentTile.getNextTile();
            }
            pathFound = true;
        }

        Tile enemyTile = level.getTileAtCoordinates(sampleEnemy.getX(), sampleEnemy.getY());
        Float proximity = 5.0f;

        if (level.isLeftTurn(enemyTile)) {
            for (int i = 0; i < xLeftPivots.size; i++) {
                if (Math.abs(sampleEnemy.getX() - xLeftPivots.get(i)) <= proximity &&
                        Math.abs(sampleEnemy.getY() - yLeftPivots.get(i)) <= proximity) {
                    if (sampleEnemy.getCurrentDirection() == Enemy.Direction.DOWN) {
                        sampleEnemy.setCurrentDirection(Enemy.Direction.RIGHT);
                    } else {
                        sampleEnemy.setCurrentDirection(Enemy.Direction.UP);
                    }

                    xLeftPivots.removeIndex(i);
                    yLeftPivots.removeIndex(i);
                    break;
                }
            }
        } else if (level.isRightTurn(enemyTile)) {
            for (int i = 0; i < xRightPivots.size; i++) {
                if (Math.abs(sampleEnemy.getX() - xRightPivots.get(i)) <= proximity &&
                        Math.abs(sampleEnemy.getY() - yRightPivots.get(i)) <= proximity) {
                    if (sampleEnemy.getCurrentDirection() == Enemy.Direction.UP) {
                        sampleEnemy.setCurrentDirection(Enemy.Direction.RIGHT);
                    } else {
                        sampleEnemy.setCurrentDirection(Enemy.Direction.DOWN);
                    }
                    xRightPivots.removeIndex(i);
                    yRightPivots.removeIndex(i);
                    break;
                }
            }
        }
    }

    private void renderLevel() {
        Tile previousTile = null;
        for (Float i = 0.0f; i < gameWidth; i+=1.0f) {
            for (Float j = 0.0f; j < gameHeight; j+=1.0f) {
                Tile tile = level.getTile(i.intValue(), j.intValue());
                if (previousTile != null) {
                    previousTile.setNextTile(tile);
                }
                tile.setPreviousTile(previousTile);
                // for Path finding
                if (i == 0.0f && j == 0.0f) {
                    tile.setFirstTile(true);
                    level.setFirstTile(tile);
                } else if (i == gameWidth - 1 && j == gameHeight - 1) {
                    tile.setLastTile(true);
                    level.setLastTile(tile);
                }
                // straight line of walkable tiles from left to right in middle
                if (j == gameHeight/2) {
                    if (i <= 2) {
                        tile.setWalkable(true);
                    }
                }
                if (i == 3) {
                    if (j == gameHeight / 2 || j == gameHeight/2 + 1 || j == gameHeight/2 + 2 || j == gameHeight/2 + 3) {
                        tile.setWalkable(true);
                    }
                }
                if (i == 4 && j == gameHeight/2 + 3) {
                    tile.setWalkable(true);
                }
                if (i == 5) {
                    // if j is between gameHeight / 2 + 2 and gameHeight / 2 - 3), setWalkable(true)
                    Integer topBound = gameHeight/2 + 3;
                    Integer bottomBound = gameHeight/2 - 3;
                    if (j >= bottomBound && j <= topBound) {
                        tile.setWalkable(true);
                    }
                }
                if (i > 5 && j == gameHeight/2 - 3) {
                    tile.setWalkable(true);
                }

                if (tile.isWalkable()) {
                    batch.draw(pathTexture, i*50, j*50, 50, 50);
                } else {
                    batch.draw(grassTexture, i*50, j*50, 50, 50);
                }
                previousTile = tile;
            }
        }

    }

    private void renderEnemies() {
        TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
        TextureRegion rotatedFrame = new TextureRegion(currentFrame);
        if (sampleEnemy.getCurrentDirection() == Enemy.Direction.UP) {
            rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
            batch.draw(rotatedFrame, sampleEnemy.getX(), sampleEnemy.getY(), 25, 25, 50, 50, 1f, 1f, 90f);
        } else if (sampleEnemy.getCurrentDirection() == Enemy.Direction.DOWN) {
            rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
            batch.draw(rotatedFrame, sampleEnemy.getX(), sampleEnemy.getY(), 25, 25, 50, 50, 1f, 1f, 270f);
        } else {
            batch.draw(rotatedFrame, sampleEnemy.getX(), sampleEnemy.getY(), 50, 50);
        }
    }

    public void updateEnemyPosition(Enemy enemy) {
        Float deltaX = 0.0f;
        Float deltaY = 0.0f;
        if (enemy.getCurrentDirection() == Enemy.Direction.DOWN) {
            deltaY = -10.0f / 60.0f;
        } else if (enemy.getCurrentDirection() == Enemy.Direction.UP) {
            deltaY = 10.0f / 60.0f;
        } else if (enemy.getCurrentDirection() == Enemy.Direction.RIGHT) {
            deltaX = 10.0f / 60.0f;
        }
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
