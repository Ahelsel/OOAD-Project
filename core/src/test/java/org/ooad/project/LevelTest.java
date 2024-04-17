package org.ooad.project;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;

import org.ooad.project.level.Level;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {
    private static Lwjgl3Application app;
    private static Level level;
    private static Integer width = 10;
    private static Integer height = 10;
    private Integer numEnemies = 1;

    @BeforeClass
    public static void setUpClass() {
        //Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //config.setForegroundFPS(60);
        //config.setTitle("OOAD-Project");

        // the tiles are 50px in size, and the level is 9x9, so we do this
        // so that there is no filler/black space around the level when the window is rendered
        //config.setWindowedMode(50*9, 50*9);
        //config.setResizable(false);

        level = new Level(width, height, 1);


        //new Lwjgl3Application(new Game(), config);

    }


    @Before
    public void setUp() {
        level = Level.getInstance(width, height, numEnemies);
    }

    @Test
    public void testGetInstance() {
        Level instance1 = Level.getInstance(width, height, numEnemies);
        Level instance2 = Level.getInstance(width, height, numEnemies);
        assertSame(instance1, instance2);
    }

    @Test
    public void testLevel() {
        Integer levelWidth = level.getWidth();
        Integer levelHeight = level.getHeight();
        assertEquals(width, levelWidth);
        assertEquals(height, levelHeight);

    }

}
