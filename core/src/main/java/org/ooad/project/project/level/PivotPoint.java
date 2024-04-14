package main.java.org.ooad.project.project.level;

import main.java.org.ooad.project.project.entity.Enemy;

public class PivotPoint {
    private Float x;
    private Float y;
    private Enemy.Direction direction;

    public PivotPoint(Float x, Float y, Enemy.Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Enemy.Direction getDirection() {
        return direction;
    }

}
