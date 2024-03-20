package com.example.ooadprojectcarnerohelsel;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Display {
    private final Integer rows;
    private final Integer cols;
    private final Integer tileSize;

    public static Integer borderOffset = null;

    public Display(Integer rows, Integer cols, Integer tileSize) {
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;
        this.borderOffset = 2 * tileSize;
    }

    public void initializeGrid(Pane root) {
        for (int y = 0; y < rows ; y++) {
            for (int x = 0; x < cols; x++) {
                Rectangle tile = new Rectangle(tileSize, tileSize);
                tile.setStroke(Color.BLACK);
                tile.setFill(Color.WHITE);
                tile.setX(x * tileSize + borderOffset);
                tile.setY(y * tileSize + borderOffset);
                root.getChildren().add(tile);
            }
        }
    }

    public void addPlayerToGrid(Pane root, Player player) {
        root.getChildren().add(player.getName());
    }

    public void updatePlayerPosition(Player player) {
        player.centerTextInTile();
    }

}
