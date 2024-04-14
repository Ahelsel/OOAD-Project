package org.ooad.project;
//import org.junit.jupiter.api.Test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import main.java.org.ooad.project.project.Game;

import main.java.org.ooad.project.project.level.Level;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {
    private static Lwjgl3Application app;
    private Level level;
    private int width = 10;
    private int height = 10;
    private int numEnemies = 1;

    @BeforeClass
    public static void setUpClass() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setInitialVisible(false);
        app = new Lwjgl3Application(new Game(), config);
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

    @AfterClass
    public static void tearDownClass() {
        app.exit();
    }

}
