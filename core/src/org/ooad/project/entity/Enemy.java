package org.ooad.project.entity;

public class Enemy {
    Double health;
    Boolean isDead;
    private Float x = 0.0f;
    private Float y = 0.0f;

    public Enemy() {
        this.health = 100.0;
        this.isDead = false;
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Enemy(Float x, Float y) {
        this.health = 100.0;
        this.isDead = false;
        this.x = x;
        this.y = y;
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

}
