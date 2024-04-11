package org.ooad.project.entity;

public class Enemy {
    Double health;
    Boolean isDead;
    private Float xCoordinate;
    private Float yCoordinate;

    public Enemy() {
        this.health = 100.0;
        this.isDead = false;
        this.xCoordinate = 0.0f;
        this.yCoordinate = 0.0f;
    }

    public Boolean isDead() {
        return isDead;
    }

    public Double getHealth() {
        return health;
    }

    public void move() {

    }

    public Float getXCoordinate() {
        return xCoordinate;
    }

    public Float getYCoordinate() {
        return yCoordinate;
    }

    public void setXCoordinate(Float xCoord) {
        this.xCoordinate = xCoord;
    }

    public void setYCoordinate(Float yCoord) {
        this.yCoordinate = yCoord;
    }

    public void changePosition(Float x, Float y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

}
