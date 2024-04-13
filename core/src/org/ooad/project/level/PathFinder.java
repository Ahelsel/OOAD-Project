package org.ooad.project.level;
import com.badlogic.gdx.utils.Array;
import org.ooad.project.entity.Enemy;

public class PathFinder {
    Level level;
    private Array<PivotPoint> pivotPoints;

    public PathFinder(Level level) {
        this.level = level;
        this.pivotPoints = new Array<PivotPoint>();
        findPath();
    }

    public void findPath() {
        Tile[][] tiles = level.getTiles();
        for (int i = 0; i < level.getWidth(); i++) {
            for (int j = 0; j < level.getHeight(); j++) {
                Tile currentTile = tiles[i][j];
                if (level.isLeftTurn(currentTile)) {
                    pivotPoints.add(new PivotPoint(currentTile.getXCoordinate().floatValue(),
                            currentTile.getYCoordinate().floatValue(), Enemy.Direction.LEFT));
                } else if (level.isRightTurn(currentTile)) {
                    pivotPoints.add(new PivotPoint(currentTile.getXCoordinate().floatValue(),
                            currentTile.getYCoordinate().floatValue(), Enemy.Direction.RIGHT));
                }
            }
        }
    }

    public Array<PivotPoint> getPivotPoints() {
        return pivotPoints;
    }

    public Integer getNumPivotPoints() {
        return pivotPoints.size;
    }

}
