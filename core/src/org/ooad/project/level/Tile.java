package org.ooad.project.level;

public class Tile {

    private Boolean isWalkable;
    private Boolean containsEnemy;

    public Tile(boolean isWalkable) {
        this.isWalkable = isWalkable;
        this.containsEnemy = false;
    }

    public Boolean isWalkable() {
        return isWalkable;
    }

    public Boolean containsEnemy() {
        return containsEnemy;
    }

    public void setContainsEnemy(Boolean containsEnemy) {
        this.containsEnemy = containsEnemy;
    }

    public void setWalkable(Boolean isWalkable) {
        this.isWalkable = isWalkable;
    }

}
