package org.ooad.project.level;
import com.badlogic.gdx.utils.Array;
import org.ooad.project.entity.Enemy;

public class PathFinder {
    private Boolean pathFound;
    Level level;
    private Array<PivotPoint> pivotPoints;

    public PathFinder(Level level) {
        this.level = level;
        this.pathFound = false;
        this.pivotPoints = new Array<PivotPoint>();

        findPath();
    }

    public void findPath() {
        Tile currentTile = level.getFirstTile();
        while (currentTile != null) {
            if (level.isLeftTurn(currentTile)) {
                pivotPoints.add(new PivotPoint(currentTile.getXCoordinate().floatValue(),
                        currentTile.getYCoordinate().floatValue(), Enemy.Direction.LEFT));
            } else if (level.isRightTurn(currentTile)) {
                pivotPoints.add(new PivotPoint(currentTile.getXCoordinate().floatValue(),
                        currentTile.getYCoordinate().floatValue(), Enemy.Direction.RIGHT));
            }
            currentTile = currentTile.getNextTile();
        }
    }

    public Array<PivotPoint> getPivotPoints() {
        return pivotPoints;
    }

}
