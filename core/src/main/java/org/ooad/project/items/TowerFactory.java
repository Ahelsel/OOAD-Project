package org.ooad.project.items;

import org.ooad.project.level.Tile;

public class TowerFactory {
    public Tower createTower(String type, Tile tile) {
        if (type.equals("Basic")) {
            return new Tower(tile);
        } else {
            return null;
        }
    }
}
