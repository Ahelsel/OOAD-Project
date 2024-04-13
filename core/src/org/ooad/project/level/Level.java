package org.ooad.project.level;

public class Level {
    private Tile[][] tiles;
    private Tile firstTile;
    private Tile lastTile;

    private Integer width;
    private Integer height;

    public Level(Integer width, Integer height) {
        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++) {
                    Tile temp = new Tile(false);
                    if (i == 0 && j == 0) {
                        temp.setFirstTile(true);
                        firstTile = temp;
                    } else if (i == width - 1 && j == height - 1) {
                        temp.setLastTile(true);
                        lastTile = temp;
                    }

                    temp.setXIndex(i);
                    temp.setYIndex(j);
                    temp.setXCoordinate(i * 50.0);
                    temp.setYCoordinate(j * 50.0);

                    tiles[i][j] = temp;
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }


    public Tile getFirstTile() {
        return firstTile;
    }

    public Tile getLastTile() {
        return lastTile;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setFirstTile(Tile firstTile) {
        this.firstTile = firstTile;
    }

    public void setLastTile(Tile lastTile) {
        this.lastTile = lastTile;
    }

    public Tile getTileAtCoordinates(Float x, Float y) {
        return tiles[(int)(x/50)][(int)(y/50)];
    }

    public Tile getTileAbove(Tile tile) {
        int x = tile.getXIndex();
        int y = tile.getYIndex() + 1; // Tile above
        if (y >= height) return null; // Check boundary
        return tiles[x][y];
    }

    public Tile getTileBelow(Tile tile) {
        int x = tile.getXIndex();
        int y = tile.getYIndex() - 1; // Tile below
        if (y < 0) return null; // Check boundary
        return tiles[x][y];
    }

    public Tile getTileLeft(Tile tile) {
        int x = tile.getXIndex() - 1; // Tile to the left
        int y = tile.getYIndex();
        if (x < 0) return null; // Check boundary
        return tiles[x][y];
    }

    public Tile getTileRight(Tile tile) {
        int x = tile.getXIndex() + 1; // Tile to the right
        int y = tile.getYIndex();
        if (x >= width) return null; // Check boundary
        return tiles[x][y];
    }

    public Boolean isLeftTurn(Tile tile) {
        Tile tileAbove = getTileAbove(tile);
        Tile tileLeft = getTileLeft(tile);
        Tile tileRight = getTileRight(tile);

        // Only proceed if all required tiles are non-null and walkable
        if (tileAbove != null && ((tileLeft != null && tileLeft.isWalkable() && tileAbove.isWalkable()) ||
                (tileRight != null && tileRight.isWalkable() && tileAbove.isWalkable()))) {
            return true;
        }
        return false;
    }

    public Boolean isRightTurn(Tile tile) {
        Tile tileBelow = getTileBelow(tile);
        Tile tileLeft = getTileLeft(tile);
        Tile tileRight = getTileRight(tile);

        // Only proceed if all required tiles are non-null and walkable
        if (tileBelow != null && ((tileLeft != null && tileLeft.isWalkable() && tileBelow.isWalkable()) ||
                (tileRight != null && tileRight.isWalkable() && tileBelow.isWalkable()))) {
            return true;
        }
        return false;
    }


}
