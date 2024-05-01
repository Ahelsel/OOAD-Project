package org.ooad.project;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.backends.headless.mock.input.MockInput;
import com.badlogic.gdx.backends.headless.mock.*;
import com.badlogic.gdx.backends.headless.HeadlessNet;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class TestRunner extends BlockJUnit4ClassRunner implements ApplicationListener {

    public TestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        HeadlessNativesLoader.load();
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        Gdx.app = new HeadlessApplication((ApplicationListener) this, conf);
        Gdx.graphics = new MockGraphics();
        Gdx.net = new HeadlessNet(conf);
        Gdx.input = new MockInput();
    }

    @Override
    public void create() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void render() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}