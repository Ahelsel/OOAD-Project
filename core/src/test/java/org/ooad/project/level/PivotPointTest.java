package org.ooad.project.level;

import org.junit.Test;
import org.ooad.project.entity.Enemy;
import org.ooad.project.level.PivotPoint;

import static org.junit.Assert.assertEquals;

public class PivotPointTest {
    @Test
    public void testGetY() {
        PivotPoint pivotPoint = new PivotPoint(0.0f, 5.0f, Enemy.Direction.LEFT);
        assertEquals(5.0f, pivotPoint.getY(), 0.01);
    }
    @Test
    public void testGetX() {
        PivotPoint pivotPoint = new PivotPoint(5.0f, 0.0f, Enemy.Direction.LEFT);
        assertEquals(5.0f, pivotPoint.getX(), 0.01);
    }
    @Test
    public void testGetDirection() {
        PivotPoint pivotPoint = new PivotPoint(0.0f, 0.0f, Enemy.Direction.LEFT);
        assertEquals(Enemy.Direction.LEFT, pivotPoint.getDirection());
    }
}