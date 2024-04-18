package org.ooad.project.items;

import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    private Level level;
    private List<Tower> towers;

    public TowerManager(Level level) {
        this.level = level;
        this.towers = new ArrayList<Tower>();
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public Tower getTowerAtTile(Tile tile) {
        for (Tower tower : towers) {
            if (tower.getTile().equals(tile)) {
                return tower;
            }
        }
        return null;
    }

}
