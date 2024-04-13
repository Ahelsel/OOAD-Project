package org.ooad.project.entity;

public class Enemy {

    public enum Direction {
        UP,
        DOWN,
        RIGHT
    }
    private Direction currentDirection;
    private Double health;
    private Boolean isDead;
    private Float x = 0.0f;
    private Float y = 0.0f;

    public Enemy() {
        this.health = 100.0;
        this.isDead = false;
        this.x = 0.0f;
        this.y = 0.0f;

        this.currentDirection = Direction.RIGHT;
    }

    public Enemy(Float x, Float y) {
        this.health = 100.0;
        this.isDead = false;
        this.x = x;
        this.y = y;

        this.currentDirection = Direction.RIGHT;
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

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

}
