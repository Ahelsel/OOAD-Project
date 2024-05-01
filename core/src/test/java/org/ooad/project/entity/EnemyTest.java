package org.ooad.project.entity;

import com.badlogic.gdx.utils.Array;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.ooad.project.level.Level;
import org.ooad.project.entity.Enemy;
import org.ooad.project.level.PathFinder;
import org.ooad.project.level.PivotPoint;
import org.ooad.project.movement.DefaultMovementStrategy;
import org.ooad.project.movement.MovementStrategy;
import org.ooad.project.observer.Observer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EnemyTest {
    @Mock
    MovementStrategy mockMovementStrategy = mock(DefaultMovementStrategy.class);

    @Test
    public void testMovement() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        enemy.move();
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

    @Test
    public void testSetMovementStrategy() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        MovementStrategy newMovementStrategy = mock(DefaultMovementStrategy.class);
        enemy.setMovementStrategy(newMovementStrategy);
        assertEquals(newMovementStrategy, enemy.getMovementStrategy());
    }

    @Test
    public void testGetMovementStrategy() {
        PathFinder mockPathFinder = mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        assertEquals(mockMovementStrategy, enemy.getMovementStrategy());
    }

    @Test
    public void testFindDirection() {
        // mock Level
        PathFinder mockPathFinder = Mockito.mock(PathFinder.class);
        Level level = Mockito.mock(Level.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);

        // Define the behavior of getPivotPoints() method
        when(mockPathFinder.getPivotPoints()).thenReturn(new Array<PivotPoint>());

        enemy.findDirection();

        // Add assertions based on the expected behavior of the findDirection method
        // For example, if you expect the direction to be RIGHT after calling findDirection:
        Assert.assertEquals(Enemy.Direction.RIGHT, enemy.getDirection());
    }

    @Test
    public void testNotifyObservers() {
        PathFinder mockPathFinder = Mockito.mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);
        Observer mockObserver = Mockito.mock(Observer.class);
        enemy.addObserver(mockObserver);

        enemy.notifyObservers();
        Mockito.verify(mockObserver, Mockito.times(1)).update(enemy);
    }

    @Test
    public void testAddObserver() {
        PathFinder mockPathFinder = Mockito.mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);
        Observer mockObserver = Mockito.mock(Observer.class);

        enemy.addObserver(mockObserver);
        // Add assertions based on the expected behavior of the addObserver method
        // For example, if you expect the observer to be added to the observers list:
        Assert.assertTrue(enemy.observers.contains(mockObserver));
    }

    @Test
    public void testRemoveObserver() {
        PathFinder mockPathFinder = Mockito.mock(PathFinder.class);
        Enemy enemy = new Enemy(0.0f, 0.0f, mockPathFinder, mockMovementStrategy);
        Observer mockObserver = Mockito.mock(Observer.class);
        enemy.addObserver(mockObserver);

        enemy.removeObserver(mockObserver);
        // Add assertions based on the expected behavior of the removeObserver method
        // For example, if you expect the observer to be removed from the observers list:
        Assert.assertFalse(enemy.observers.contains(mockObserver));
    }

}
