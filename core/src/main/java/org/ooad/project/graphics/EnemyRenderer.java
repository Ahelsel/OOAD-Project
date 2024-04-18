package org.ooad.project.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.ooad.project.entity.Enemy;
import org.ooad.project.items.Tower;
import org.ooad.project.level.Level;
import org.ooad.project.level.PivotPoint;

import java.util.Iterator;

public class EnemyRenderer {
    private Level level;
    private SpriteBatch batch;
    private Animation<TextureRegion> entityAnimation;

    public EnemyRenderer(Level level) {
        this.level = level;
        this.batch = new SpriteBatch();
        loadEntityAnimation();
    }

//    public void render(Float animationTime) {
//        batch.begin();
//        for (Enemy enemy : level.getEnemies()) {
//            updateEnemyPosition(enemy);
//            findDirection(enemy);
//            TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
//            TextureRegion rotatedFrame = new TextureRegion(currentFrame);
//            if (enemy.getDirection() == Enemy.Direction.UP) {
//                rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
//                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 25, 25, 50, 50, 1f, 1f, 90f);
//            } else if (enemy.getDirection() == Enemy.Direction.DOWN) {
//                rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
//                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 25, 25, 50, 50, 1f, 1f, 270f);
//            } else {
//                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 50, 50);
//            }
//            if (enemy.getX() > level.getWidth() * 50 || enemy.getY() > level.getHeight() * 50) {
//                level.removeEnemy(enemy);
//            }
//        }
//        batch.end();
//    }

    public void render(Float animationTime) {
        batch.begin();
        Iterator<Enemy> iterator = level.getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            updateEnemyPosition(enemy);
            findDirection(enemy);
            TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
            TextureRegion rotatedFrame = new TextureRegion(currentFrame);
            if (enemy.getDirection() == Enemy.Direction.UP) {
                rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 25, 25, 50, 50, 1f, 1f, 90f);
            } else if (enemy.getDirection() == Enemy.Direction.DOWN) {
                rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 25, 25, 50, 50, 1f, 1f, 270f);
            } else {
                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 50, 50);
            }
            updateTowerTargets(enemy);
            if (enemy.getX() > level.getWidth() * 50 || enemy.getY() > level.getHeight() * 50) {
                iterator.remove();
            }
        }
        batch.end();
    }

    private void updateTowerTargets(Enemy enemy) {
        for (Tower tower : level.getTowerManager().getTowers()) {
            if (isEnemyInTowerRadius(tower, enemy)) {
                if (tower.getTarget() == null) {
                    tower.setTarget(enemy);
                }
            } else if (tower.getTarget() == enemy) {
                tower.setTarget(null);
            }
        }
    }

    private boolean isEnemyInTowerRadius(Tower tower, Enemy enemy) {
        int towerX = tower.getTile().getXIndex();
        int towerY = tower.getTile().getYIndex();
        int enemyX = (int) (enemy.getX() / 50);
        int enemyY = (int) (enemy.getY() / 50);

        return Math.abs(towerX - enemyX) <= 1 && Math.abs(towerY - enemyY) <= 1;
    }

    public void findDirection(Enemy enemy) {
        Iterator<PivotPoint> iterator = enemy.getPathFinder().getPivotPoints().iterator();
        Float proximity = 5.0f;

        while (iterator.hasNext()) {
            PivotPoint pivot = iterator.next();
            if (Math.abs(enemy.getX() - pivot.getX()) <= proximity && Math.abs(enemy.getY() - pivot.getY()) <= proximity) {
                switch (pivot.getDirection()) {
                    case LEFT:
                        if (enemy.getDirection() == Enemy.Direction.DOWN) {
                            enemy.setDirection(Enemy.Direction.RIGHT);
                        } else {
                            enemy.setDirection(Enemy.Direction.UP);
                        }
                        break;
                    case RIGHT:
                        if (enemy.getDirection() == Enemy.Direction.UP) {
                            enemy.setDirection(Enemy.Direction.RIGHT);
                        } else {
                            enemy.setDirection(Enemy.Direction.DOWN);
                        }
                        break;
                    default:
                        break;
                }
                iterator.remove();
                break;
            }
        }
    }

    public void dispose() {
        batch.dispose();
        for (TextureRegion region : entityAnimation.getKeyFrames()) {
            region.getTexture().dispose();
        }
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

    public void updateEnemyPosition(Enemy enemy) {
        Float deltaX = 0.0f;
        Float deltaY = 0.0f;
        if (enemy.getDirection() == Enemy.Direction.DOWN) {
            deltaY = (-10.0f / 60.0f) * enemy.getSpeedMultiplier();
        } else if (enemy.getDirection() == Enemy.Direction.UP) {
            deltaY = (10.0f / 60.0f) * enemy.getSpeedMultiplier();
        } else if (enemy.getDirection() == Enemy.Direction.RIGHT) {
            deltaX = (10.0f / 60.0f) * enemy.getSpeedMultiplier();
        }
        enemy.move(deltaX, deltaY);
    }

}