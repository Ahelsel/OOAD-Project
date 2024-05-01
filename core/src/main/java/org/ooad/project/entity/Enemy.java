package org.ooad.project.entity;

import org.ooad.project.level.PathFinder;
import org.ooad.project.level.PivotPoint;
import org.ooad.project.movement.MovementStrategy;
import org.ooad.project.observer.Observable;
import org.ooad.project.observer.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Enemy implements Observable {
    private MovementStrategy movementStrategy;
    List<Observer> observers;

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }
    private Direction direction;
    private Float speedMultiplier = 10.0f;
    private Double health;
    private Boolean isDead;
    private PathFinder pathFinder;
    private Float x = 0.0f;
    private Float y = 0.0f;

    public Enemy(Float x, Float y, PathFinder pathFinder, MovementStrategy movementStrategy) {
        this.health = 100.0;
        this.isDead = false;
        this.pathFinder = pathFinder;
        this.x = x;
        this.y = y;

        this.observers = new ArrayList<>();
        this.movementStrategy = movementStrategy;
        this.direction = Direction.RIGHT;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void move() {
        Float deltaX = 0.0f;
        Float deltaY = 0.0f;
        if (getDirection() == Direction.DOWN) {
            deltaY = (-10.0f / 60.0f) * getSpeedMultiplier();
        } else if (getDirection() == Direction.UP) {
            deltaY = (10.0f / 60.0f) * getSpeedMultiplier();
        } else if (getDirection() == Direction.RIGHT) {
            deltaX = (10.0f / 60.0f) * getSpeedMultiplier();
        }
        movementStrategy.move(this, deltaX, deltaY);
        notifyObservers();
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

    public Float getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void findDirection() {
        Iterator<PivotPoint> iterator = getPathFinder().getPivotPoints().iterator();
        Float proximity = 5.0f;

        while (iterator.hasNext()) {
            PivotPoint pivot = iterator.next();
            if (Math.abs(getX() - pivot.getX()) <= proximity && Math.abs(getY() - pivot.getY()) <= proximity) {
                switch (pivot.getDirection()) {
                    case LEFT:
                        if (getDirection() == Enemy.Direction.DOWN) {
                            setDirection(Enemy.Direction.RIGHT);
                        } else {
                            setDirection(Enemy.Direction.UP);
                        }
                        break;
                    case RIGHT:
                        if (getDirection() == Enemy.Direction.UP) {
                            setDirection(Enemy.Direction.RIGHT);
                        } else {
                            setDirection(Enemy.Direction.DOWN);
                        }
                        break;
                    default:
                        break;
                }
                iterator.remove();
                break;
            }
        }
    }

}
