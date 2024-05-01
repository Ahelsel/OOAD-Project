package org.ooad.project.entity;

import org.ooad.project.level.PathFinder;
import org.ooad.project.movement.MovementStrategy;
import org.ooad.project.observer.Observable;
import org.ooad.project.observer.Observer;

import java.util.ArrayList;
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

    public void move(Float deltaX, Float deltaY) {
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

}
