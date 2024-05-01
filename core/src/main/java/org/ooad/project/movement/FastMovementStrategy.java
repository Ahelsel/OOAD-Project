package org.ooad.project.movement;

import org.ooad.project.entity.Enemy;

public class FastMovementStrategy implements MovementStrategy {

    @Override
    public void move(Enemy enemy, Float deltaX, Float deltaY) {
        Float newX = enemy.getX() + (deltaX * 2f);       // change 2f to any other value to increase or decrease the speed
        Float newY = enemy.getY() + (deltaY * 2f);
        enemy.setX(newX);
        enemy.setY(newY);
    }
}
