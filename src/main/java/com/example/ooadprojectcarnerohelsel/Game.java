package com.example.ooadprojectcarnerohelsel;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

    private Player player;
    private Display display = new Display(ROWS, COLS, TILE_SIZE);

    @Override
    public void start(Stage stage) {
        Rectangle2D viewport = new Rectangle2D(10, 10, 20, 20);
        //player = new Player(3.5, 3.5, new Text("Player"), "/images/playerSpriteSheet.png", viewport);
        player = new Player(2.0, 2.0, new Text("Player"), "/images/playerSpriteSheet.png", viewport);


        Pane root = new Pane();
        StackPane stackPane = new StackPane(root);
        Scene scene = new Scene(stackPane, WIDTH, HEIGHT);

        display.initializeGrid(root);
        root.getChildren().add(player.getModel());
        display.updatePlayerPosition(player);

        scene.setOnKeyPressed(this::handleKeyPress);

        stage.setScene(scene);
        stage.setTitle("GameTest");
        stage.show();
    }

    private void handleKeyPress(KeyEvent event) {
//        Rectangle2D northViewport = new Rectangle2D(0, 64, 30, 30);
//        Rectangle2D southViewport = new Rectangle2D(0, 64, 30, 30);
//        Rectangle2D westViewport = new Rectangle2D(0, 64, 30, 30);
//        Rectangle2D eastViewport = new Rectangle2D(0, 64, 30, 30);

        switch (event.getCode()) {
            case W:
            case UP:
                player.moveNorth();
//                player.getPlayerModel().setViewport(northViewport);
                break;
            case S:
            case DOWN:
                player.moveSouth();
//                player.getPlayerModel().setViewport(southViewport);
                break;
            case A:
            case LEFT:
                player.moveWest();
//                player.getPlayerModel().setViewport(westViewport);
                break;
            case D:
            case RIGHT:
                player.moveEast();
//                player.getPlayerModel().setViewport(eastViewport);
                break;
            case ESCAPE:
                System.exit(0);
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
