package org.ooad.project.level;

import org.junit.Test;
import org.ooad.project.level.Tile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TileTest {
    @Test
    public void testWalkable() {
        Tile tile = new Tile(true);
        assertTrue(tile.isWalkable());

        tile.setWalkable(false);
        assertFalse(tile.isWalkable());
    }

    @Test
    public void testCoordinates() {
        Tile tile = new Tile(true);

        tile.setXCoordinate(10.0);
        tile.setYCoordinate(20.0);

        assertEquals(10.0, tile.getXCoordinate(), 0.01);
        assertEquals(20.0, tile.getYCoordinate(), 0.01);
    }
}