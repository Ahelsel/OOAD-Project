//package org.ooad.project.views;
//
//import com.badlogic.gdx.ApplicationListener;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.backends.headless.HeadlessApplication;
//import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
//import com.badlogic.gdx.graphics.GL20;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.ooad.project.Game;
//import org.ooad.project.TestRunner;
//
//import static org.mockito.Mockito.*;
//
////@RunWith(TestRunner.class)
//@RunWith(MockitoJUnitRunner.class)
//public class GameScreenTest {
//
//    @Mock
//    private GameScreen gameScreen;
//    @Mock
//    private Game game;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        HeadlessNativesLoader.load();
//        Gdx.gl = mock(GL20.class);
//        Gdx.gl20 = mock(GL20.class);
//        Gdx.app = new HeadlessApplication(new ApplicationListener() {
//            @Override
//            public void create() {}
//
//            @Override
//            public void resize(int width, int height) {}
//
//            @Override
//            public void render() {}
//
//            @Override
//            public void pause() {}
//
//            @Override
//            public void resume() {}
//
//            @Override
//            public void dispose() {}
//        });
//        game = mock(Game.class);
//        gameScreen = mock(GameScreen.class);
//        //gameScreen = new GameScreen(game);
//    }
//
//    @After
//    public void cleanup() {
//        Gdx.app.exit();
//    }
//
//    @Test
//    public void testShow() {
//        game.setScreen(gameScreen);
//        gameScreen.show();
//        verify(game, times(1)).setScreen(gameScreen);
//    }
//
//    @Test
//    public void testRender() {
//        game.setScreen(gameScreen);
//        gameScreen.render(0);
//        verify(gameScreen, times(1)).render(0);
//    }
//
//    @Test
//    public void testResize() {
//        game.setScreen(gameScreen);
//        gameScreen.resize(0, 0);
//        verify(gameScreen, times(1)).resize(0, 0);
//    }
//
//    @Test
//    public void testPause() {
//        game.setScreen(gameScreen);
//        gameScreen.pause();
//        verify(gameScreen, times(1)).pause();
//    }
//
//    @Test
//    public void testResume() {
//        game.setScreen(gameScreen);
//        gameScreen.resume();
//        verify(gameScreen, times(1)).resume();
//    }
//
//    @Test
//    public void testHide() {
//        game.setScreen(gameScreen);
//        gameScreen.hide();
//        verify(gameScreen, times(1)).hide();
//    }
//
//}