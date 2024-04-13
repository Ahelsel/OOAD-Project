package org.ooad.project.entity;

import org.ooad.project.level.PathFinder;

public class Enemy {
    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
    private Direction direction;
    private Double health;
    private Boolean isDead;
    private PathFinder pathFinder;
    private Float x = 0.0f;
    private Float y = 0.0f;

    public Enemy(Float x, Float y, PathFinder pathFinder) {
        this.health = 100.0;
        this.isDead = false;
        this.pathFinder = pathFinder;
        this.x = x;
        this.y = y;

        this.direction = Direction.RIGHT;
    }

    public Boolean isDead() {
        return isDead;
    }

    public Double getHealth() {
        return health;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void move(Float deltaX, Float deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
