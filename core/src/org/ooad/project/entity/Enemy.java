package org.ooad.project.entity;

import org.ooad.project.level.Tile;

public class Enemy {
    Double health;
    Double speed;
    Boolean isDead;
    private Double xCoordinate;
    private Double yCoordinate;

    public Enemy() {
        this.health = 100.0;
        this.speed = 1.0;
        this.isDead = false;
        this.xCoordinate = 0.0;
        this.yCoordinate = 0.0;
    }

    public Boolean isDead() {
        return isDead;
    }

    public Double getHealth() {
        return health;
    }

    public void move() {

    }

    public Double getXCoordinate() {
        return xCoordinate;
    }

    public Double getYCoordinate() {
        return yCoordinate;
    }

    public void setXCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

}
