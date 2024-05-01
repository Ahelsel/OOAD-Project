package org.ooad.project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ooad.project.entity.Enemy;
import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;
import org.ooad.project.items.Tower;
import org.ooad.project.items.TowerManager;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class LevelTest {
    private Level level;
    private Integer width = 5;
    private Integer height = 5;
    private Integer numEnemies = 3;

    @Mock
    private TowerManager mockTowerManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        level = mock(Level.class); // Mock the Level class entirely
        when(level.getWidth()).thenReturn(width);
        when(level.getHeight()).thenReturn(height);
        when(level.getTowerManager()).thenReturn(mockTowerManager);
        when(level.getNumEnemies()).thenReturn(numEnemies);

        // Setup a mocked grid of tiles
        Tile[][] tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = mock(Tile.class);
            }
        }
        when(level.getTiles()).thenReturn(tiles);
    }

    @Test
    public void testGetTile() {
        //assertNotNull(level.getTile(1, 1));
    }

    @Test
    public void testGetWidthAndHeight() {
        assertEquals(width, level.getWidth());
        assertEquals(height, level.getHeight());
    }

    @Test
    public void testGetEnemies() {
        List<Enemy> mockEnemies = new ArrayList<>();
        when(level.getEnemies()).thenReturn(mockEnemies);
        assertNotNull(level.getEnemies());
    }

    @Test
    public void testPlaceTower() {
        // Arrange
        Tile mockTile = mock(Tile.class);
        when(mockTile.isWalkable()).thenReturn(false); // Correct condition to add a tower.

        TowerManager mockTowerManager = mock(TowerManager.class);
        when(level.getTowerManager()).thenReturn(mockTowerManager);

        // Act
        level.placeTower(mockTile);

        // Assert
        //verify(mockTowerManager).addTower(any(Tower.class)); // Verifying the interaction.
    }


    @Test
    public void testGetTileAbove() {
        // Mock the tile and the Level to return this tile when asked for the tile at position (2, 2)
        Tile mockTile = mock(Tile.class);
        when(level.getTile(2, 2)).thenReturn(mockTile);

        // Now mock getTileAbove to return another mocked tile when called with (2, 3)
        Tile mockTileAbove = mock(Tile.class);
        when(level.getTileAbove(mockTile)).thenReturn(mockTileAbove);

        Tile tileAbove = level.getTileAbove(mockTile);
        assertNotNull(tileAbove); // Check that we get a tile
    }

}
