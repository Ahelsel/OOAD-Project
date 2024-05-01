package org.ooad.project.level;

import org.junit.Test;
import org.ooad.project.level.Level;
import org.ooad.project.level.PathFinder;
import org.ooad.project.level.Tile;

import static org.mockito.Mockito.*;

public class PathFinderTest {

    /* * *

    BDD for PathFinding
    Scenario: Find a valid path through the level
    Given: A level filled completely with tiles, where there is a valid path (entry and exit point connected)
    When: The Level is created
    Then: The PathFinder should be invoked and return an Array of coordinates called PivotPoints where the Enemy will turn

    * * */
    @Test
    public void testFindPath() {
        Level mockLevel = mock(Level.class);
        PathFinder pathFinder = new PathFinder(mockLevel);

        Tile[][] tiles = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j] = mock(Tile.class);
            }
        }

        when(mockLevel.getTiles()).thenReturn(tiles);
        when(mockLevel.getWidth()).thenReturn(3);
        when(mockLevel.getHeight()).thenReturn(3);

        pathFinder.findPath();

        verify(mockLevel, times(2)).getTiles();
        verify(mockLevel, times(9)).isLeftTurn(any(Tile.class));
        verify(mockLevel, times(9)).isRightTurn(any(Tile.class));
    }

    @Test
    public void testGetPivotPoints() {
        Level mockLevel = mock(Level.class);
        PathFinder pathFinder = new PathFinder(mockLevel);

        pathFinder.getPivotPoints();
    }
}