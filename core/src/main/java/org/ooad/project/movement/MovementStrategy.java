package org.ooad.project.movement;

import org.ooad.project.entity.Enemy;

public interface MovementStrategy {
    void move(Enemy enemy, Float deltaX, Float deltaY);
}
