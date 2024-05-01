package org.ooad.project.items;

import org.junit.Test;
import org.ooad.project.items.Tower;
import org.ooad.project.items.TowerManager;
import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;

import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TowerManagerTest {
    @Test
    public void testAddAndRemoveTower() {
        Level mockLevel = mock(Level.class);
        TowerManager towerManager = new TowerManager(mockLevel);
        Tower mockTower = mock(Tower.class);

        towerManager.addTower(mockTower);

        List<Tower> towers = towerManager.getTowers();
        assertEquals(1, towers.size());
        assertTrue(towers.contains(mockTower));

        towerManager.removeTower(mockTower);

        towers = towerManager.getTowers();
        assertEquals(0, towers.size());
        assertFalse(towers.contains(mockTower));
    }

    @Test
    public void testGetTowerAtTile() {
        Level mockLevel = mock(Level.class);
        TowerManager towerManager = new TowerManager(mockLevel);
        Tile mockTile1 = mock(Tile.class);
        Tile mockTile2 = mock(Tile.class);
        Tower mockTower1 = mock(Tower.class);
        Tower mockTower2 = mock(Tower.class);

        when(mockTower1.getTile()).thenReturn(mockTile1);
        when(mockTower2.getTile()).thenReturn(mockTile2);

        towerManager.addTower(mockTower1);
        towerManager.addTower(mockTower2);

        Tower resultTower = towerManager.getTowerAtTile(mockTile1);
        assertEquals(mockTower1, resultTower);

        resultTower = towerManager.getTowerAtTile(mockTile2);
        assertEquals(mockTower2, resultTower);
    }
}