package org.ooad;
import javafx.scene.text.Text;
import org.ooad.Game;

public class Player {
    private Integer x;
    private Integer y;
    private final Text name;

    public Player(Integer x, Integer y, Text name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void moveNorth() {
        if (y > 0) {
            y--;
        }
    }

    public void moveSouth() {
        if (y < Game.ROWS ) {
            y++;
        }
    }

    public void moveWest() {
        if (x > 0) {
            x--;
        }
    }

    public void moveEast() {
        if (x < Game.COLS ) {
            x++;
        }
    }

    public Text getName() {
        return name;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void centerTextInTile() {
        name.setTranslateX(x * Game.TILE_SIZE + Game.TILE_SIZE / 2 - name.getLayoutBounds().getWidth() / 2);
        name.setTranslateY(y * Game.TILE_SIZE + Game.TILE_SIZE / 2 + name.getLayoutBounds().getHeight() / 4);
    }

}
