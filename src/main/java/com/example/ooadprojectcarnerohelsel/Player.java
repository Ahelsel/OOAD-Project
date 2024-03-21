package com.example.ooadprojectcarnerohelsel;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Player {
    private Double x;
    private Double y;
    private Double initialX;
    private Double initialY;
    private final Text name;
    private Image spriteSheet;
    private ImageView playerModel;

    public Player(Double x, Double y, Text name, String spritePath, Rectangle2D viewport) {
        this.initialY = y;
        this.initialX = x;
        this.x = x;
        this.y = y;
        this.name = name;
        spriteSheet = new Image(spritePath);
        this.playerModel = new ImageView(spriteSheet);
        this.playerModel.setViewport(viewport);
    }

    public void moveNorth() {
        if (y > 0 + initialY) {
            y--;
        }
    }

    public void moveSouth() {
        if (y < Game.ROWS - 1 + initialY) {
            y++;
        }
    }

    public void moveWest() {
        if (x > 0 + initialX) {
            x--;
        }
    }

    public void moveEast() {
        if (x < Game.COLS - 1 + initialX) {
            x++;
        }
    }

    public Text getName() {
        return name;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void centerPlayerModelInTile() {
        System.out.println("Player Location (units of Tiles) (x,y): ( " + x + ", " + y + " )");
        // Calculate the new x and y coordinates for the location
        double newX = x * Game.TILE_SIZE + Game.TILE_SIZE / 2;
        double newY = y * Game.TILE_SIZE + Game.TILE_SIZE / 2;
        // Center the player model in the tile
        newX -= playerModel.getImage().getWidth() / 2;
        newY -= playerModel.getImage().getHeight() / 2;
        // Set the new x and y coordinates
        playerModel.setX(newX);
        playerModel.setY(newY);
    }

    public ImageView getModel() {
        return playerModel;
    }

}
