package org.ooad.project;

import org.junit.Test;
import org.ooad.project.entity.Enemy;
import org.ooad.project.level.PathFinder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EnemyTest {
    @Test
    public void testMovement() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder);

        enemy.move(5.0f, 5.0f);
        assertEquals(5.0f, enemy.getX(), 0.01);
        assertEquals(5.0f, enemy.getY(), 0.01);
    }

    @Test
    public void testDirection() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder);

        enemy.setDirection(Enemy.Direction.LEFT);
        assertEquals(Enemy.Direction.LEFT, enemy.getDirection());
    }
}