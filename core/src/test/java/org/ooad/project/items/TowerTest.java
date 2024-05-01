package org.ooad.project.items;

import org.junit.Test;
import org.ooad.project.entity.Enemy;
import org.ooad.project.items.Tower;
import org.ooad.project.level.Tile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TowerTest {
    @Test
    public void testTowerCreation() {
        Tile mockTile = mock(Tile.class);
        Tower tower = new Tower(mockTile);

        assertEquals(mockTile, tower.getTile());
        assertNull(tower.getTarget());
    }

    @Test
    public void testSetTarget() {
        Tile mockTile = mock(Tile.class);
        Enemy mockEnemy = mock(Enemy.class);
        Tower tower = new Tower(mockTile);

        tower.setTarget(mockEnemy);

        assertEquals(mockEnemy, tower.getTarget());
    }

    @Test
    public void testUpdate() {
        Tile mockTile = mock(Tile.class);
        Enemy mockEnemy = mock(Enemy.class);
        Tower tower = new Tower(mockTile);

        tower.update(mockEnemy);

        assertEquals(mockEnemy, tower.getTarget());
    }

    @Test
    public void testIsEnemyInTowerRange() {
        Tile mockTile = mock(Tile.class);
        Enemy mockEnemy = mock(Enemy.class);
        Tower tower = new Tower(mockTile);

        when(mockTile.getXIndex()).thenReturn(0);
        when(mockTile.getYIndex()).thenReturn(0);
        when(mockEnemy.getX()).thenReturn(0.0f);
        when(mockEnemy.getY()).thenReturn(0.0f);

        assertTrue(tower.isEnemyInTowerRange(mockEnemy));
    }
}
