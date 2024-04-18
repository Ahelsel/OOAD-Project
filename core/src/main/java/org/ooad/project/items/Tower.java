package org.ooad.project.items;

import org.ooad.project.entity.Enemy;
import org.ooad.project.level.Tile;

public class Tower {
    Tile tile;
    private Enemy target;

    public Tower(Tile tile) {
        this.tile = tile;
        this.target = null;
    }

    public Tower() {
        this.tile = null;
        this.target = null;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
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
}
