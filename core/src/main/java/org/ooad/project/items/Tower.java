package org.ooad.project.items;

import org.ooad.project.entity.Enemy;
import org.ooad.project.level.Tile;
import org.ooad.project.observer.Observable;
import org.ooad.project.observer.Observer;

import java.util.ArrayList;

public class Tower implements Observer {
    Tile tile;
    private Enemy target;

    public Tower(Tile tile) {
        this.tile = tile;
        this.target = null;
    }

    public Tile getTile() {
        return tile;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    @Override
    public void update(Observable observable) {
        Enemy enemy = (Enemy) observable;
        if (isEnemyInTowerRange(enemy)) {
            if (target == null) {
                setTarget(enemy);
            }
        } else if (target == enemy) {
            setTarget(null);
        }
    }

    private boolean isEnemyInTowerRange(Enemy enemy) {
        float towerX = getTile().getXIndex();
        float towerY = getTile().getYIndex();
        float enemyX = enemy.getX() / 50f;
        float enemyY = enemy.getY() / 50f;

        return Math.abs(towerX - enemyX) <= 1.5f && Math.abs(towerY - enemyY) <= 1.5f;
    }
}
