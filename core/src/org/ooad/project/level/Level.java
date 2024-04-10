package org.ooad.project.level;

public class Level {
    private Tile[][] tiles;

    private Integer width;
    private Integer height;

    public Level(Integer width, Integer height) {
        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++) {
                    tiles[i][j] = new Tile(false);
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

}
