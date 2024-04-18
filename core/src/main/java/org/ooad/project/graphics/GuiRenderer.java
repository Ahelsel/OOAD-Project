package org.ooad.project.graphics;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.ooad.project.level.Level;

public class GuiRenderer {
    private BitmapFont font;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Level level;

    public GuiRenderer(Level level) {
        font = new BitmapFont();
        batch = new SpriteBatch();
        this.level = level;
        shapeRenderer = new ShapeRenderer();
    }

    public void renderEnemiesRemaining() {
        batch.begin();
        font.draw(batch, "Enemies remaining: " + level.getNumEnemies(), 10, level.getHeight() * 50 - 10);
        batch.end();
    }

    public void renderGameOver(float alpha) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, alpha);
        shapeRenderer.rect(0, 0, level.getWidth() * 50, level.getHeight() * 50);
        shapeRenderer.end();

        batch.begin();
        font.setColor(1, 1, 1, alpha);
        font.draw(batch, "Game Over", level.getWidth() * 25 - 50, level.getHeight() * 25);
        batch.end();
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }

}
