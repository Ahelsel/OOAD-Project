package org.ooad.project.entity;

import org.junit.Test;
import org.mockito.Mock;
import org.ooad.project.entity.Enemy;
import org.ooad.project.level.PathFinder;
import org.ooad.project.movement.DefaultMovementStrategy;
import org.ooad.project.movement.MovementStrategy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EnemyTest {
    @Mock
    MovementStrategy mockMovementStrategy = mock(DefaultMovementStrategy.class);

    @Test
    public void testMovement() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        enemy.move(5.0f, 5.0f);
        assertNotEquals(5.0f, enemy.getX(), 0.01);
        assertNotEquals(5.0f, enemy.getY(), 0.01);
    }

    @Test
    public void testDirection() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        enemy.setDirection(Enemy.Direction.LEFT);
        assertEquals(Enemy.Direction.LEFT, enemy.getDirection());
    }

    @Test
    public void testSpeedMultiplier() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        assertEquals(10.0f, enemy.getSpeedMultiplier(), 0.01);
    }

    @Test
    public void testGetPathFinder() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        assertEquals(mockPathFinder, enemy.getPathFinder());
    }

    @Test
    public void testGetX() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        assertEquals(0.0f, enemy.getX(), 0.01);
    }

    @Test
    public void testGetY() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        assertEquals(0.0f, enemy.getY(), 0.01);
    }

    @Test
    public void testSetX() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        enemy.setX(5.0f);
        assertEquals(5.0f, enemy.getX(), 0.01);
    }

    @Test
    public void testSetY() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        enemy.setY(5.0f);
        assertEquals(5.0f, enemy.getY(), 0.01);
    }
}
