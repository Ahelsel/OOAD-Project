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


}
