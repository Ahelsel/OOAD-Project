package org.ooad.project.movement;

import org.ooad.project.entity.Enemy;

public class DefaultMovementStrategy implements MovementStrategy {
    @Override
    public void move(Enemy enemy, Float deltaX, Float deltaY) {
        enemy.setX(enemy.getX() + deltaX);
        enemy.setY(enemy.getY() + deltaY);
    }

}
