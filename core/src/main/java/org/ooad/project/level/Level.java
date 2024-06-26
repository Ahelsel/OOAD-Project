package org.ooad.project.level;

import com.badlogic.gdx.utils.Timer;
import jdk.internal.icu.util.CodePointTrie;
import org.ooad.project.entity.Enemy;
import org.ooad.project.items.Tower;
import org.ooad.project.items.TowerFactory;
import org.ooad.project.items.TowerManager;
import org.ooad.project.movement.DefaultMovementStrategy;
import org.ooad.project.movement.FastMovementStrategy;
import org.ooad.project.movement.MovementStrategy;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Tile[][] tiles;
    private Tile firstTile;
    private Tile lastTile;
    private Boolean generated = false;

    private static Level instance = null;

    private Integer width;
    private Integer height;
    PathFinder pathFinder;
    private Integer numEnemies;
    private List<Enemy> enemies;

    private TowerManager towerManager;

    public static Level getInstance(Integer width, Integer height, Integer numEnemies) {
        if (instance == null) {
            instance = new Level(width, height, numEnemies);
        }
        if (!instance.generated) {
            instance.generateLevel();
            instance.generated = true;
        }
        return instance;
    }

    public Level(Integer width, Integer height, Integer numEnemies) {
        this.width = width;
        this.height = height;
        this.enemies = new ArrayList<>();

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

        towerManager = new TowerManager(this);

        this.numEnemies = numEnemies;
        buildLevel();
    }

    public void generateLevel() {
        pathFinder = new PathFinder(this);
        spawnEnemies();
    }

    public void spawnEnemies() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Float startX = 0.0f;
                Float startY = (width/2)*50.0f;
                if (numEnemies > 0) {
                    Enemy enemy;
                    if (numEnemies % 2 == 0) {
                        enemy = new Enemy(startX, startY, copyPathFinder(), new FastMovementStrategy());
                    } else {
                        enemy = new Enemy(startX, startY, copyPathFinder(), new DefaultMovementStrategy());
                    }
                    for (Tower tower : towerManager.getTowers()) {
                        enemy.addObserver(tower);
                    }
                    enemies.add(enemy);
                    numEnemies--;
                } else {
                    cancel();
                }
            }
        }, 0, 2);
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    public Integer getWidth() {
        return width;
    }

    public List<Enemy> getEnemies() {
        return enemies;
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

    private void buildLevel() {
        Tile previousTile = null;
        for (Float i = 0.0f; i < width; i+=1.0f) {
            for (Float j = 0.0f; j < height; j+=1.0f) {
                Tile tile = this.getTile(i.intValue(), j.intValue());
                if (previousTile != null) {
                    previousTile.setNextTile(tile);
                }
                tile.setPreviousTile(previousTile);
                assignTiles(tile, i.intValue(), j.intValue());
                previousTile = tile;
            }
        }
    }

    public void assignTiles(Tile tile, Integer i, Integer j) {
        if (i == 0.0f && j == 0.0f) {
            tile.setFirstTile(true);
            this.setFirstTile(tile);
        } else if (i == width - 1 && j == height - 1) {
            tile.setLastTile(true);
            this.setLastTile(tile);
        }
        // straight line of walkable tiles from left to right in middle
        if (j == height/2) {
            if (i <= 2) {
                tile.setWalkable(true);
            }
        }
        if (i == 3) {
            if (j == height / 2 || j == height/2 + 1 || j == height/2 + 2 || j == height/2 + 3) {
                tile.setWalkable(true);
            }
        }
        if (i == 4 && j == height/2 + 3) {
            tile.setWalkable(true);
        }
        if (i == 5) {
            // if j is between gameHeight / 2 + 2 and gameHeight / 2 - 3), setWalkable(true)
            Integer topBound = height/2 + 3;
            Integer bottomBound = height/2 - 3;
            if (j >= bottomBound && j <= topBound) {
                tile.setWalkable(true);
            }
        }
        if (i > 5 && j == height/2 - 3) {
            tile.setWalkable(true);
        }
    }

    PathFinder copyPathFinder() {
        return new PathFinder(this);
    }

    public Integer getNumEnemies() {
        return numEnemies;
    }

    public void placeTower(Tile tile, String TowerType) {
        if (!tile.isWalkable() && towerManager.getTowerAtTile(tile) == null) {
            TowerFactory towerFactory = new TowerFactory();
            Tower tower = towerFactory.createTower(TowerType, tile);
            towerManager.addTower(tower);
        }
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public void resetLevel() {
        instance = null;
    }

}
