package org.ooad.project.items;

import org.junit.Test;
import org.ooad.project.entity.Enemy;
import org.ooad.project.items.Tower;
import org.ooad.project.level.Tile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TowerTest {

    /* * *

    BDD for Tower Rendering
    Scenario: Properly render a Tower to the screen
    Given: A Level where the bounds of the tower are within the level bounds and not on a walking path
    When: The game is played and all entities/items/background is rendered to the screen
    Then: The tower should be placed over the background texture, with the base of the tower being below the cannon

    BDD for Tower Tracking
    Scenario: An enemy enters the tower's range and should track it
    Given: An enemy walking through a level and a Tower placed and rendered on the Level
    When: An enemy enters the tower's range
    Then: The tower should set the Enemy to be its target, and visually track the enemy as it moves (rotate the cannon)

    * * */

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
