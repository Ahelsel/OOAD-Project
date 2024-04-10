package org.ooad.project.level;

public class Level {
    private Tile[][] tiles;

    public Level() {
        tiles = new Tile[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
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
