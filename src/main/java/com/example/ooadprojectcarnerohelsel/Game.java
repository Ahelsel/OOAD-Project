package com.example.ooadprojectcarnerohelsel;

import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Game extends Application {
    public static final Integer ROWS = 10;
    public static final Integer COLS = 10;
    public static final Integer TILE_SIZE = 40;
    // (4 * TILE_SIZE) gives a 2-tile border around the grid (top, bottom, left, right)
    public static final Integer WIDTH = COLS * TILE_SIZE + (4 * TILE_SIZE);
    public static final Integer HEIGHT = ROWS * TILE_SIZE + (4 * TILE_SIZE);

    private Player player = new Player(0, 0, new Text("Player"));
    private Display display = new Display(ROWS, COLS, TILE_SIZE);

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        display.initializeGrid(root);
        display.addPlayerToGrid(root, player);

        scene.setOnKeyPressed(this::handleKeyPress);

        stage.setScene(scene);
        stage.setTitle("GameTest");
        stage.show();
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                player.moveNorth();
                break;
            case S:
                player.moveSouth();
                break;
            case A:
                player.moveWest();
                break;
            case D:
                player.moveEast();
                break;
            default:
                break;
        }
        display.updatePlayerPosition(player);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
