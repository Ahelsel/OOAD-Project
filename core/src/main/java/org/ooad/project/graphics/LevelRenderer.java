package org.ooad.project.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.ooad.project.level.Level;
import org.ooad.project.level.Tile;

public class LevelRenderer {
    private Level level;
    private SpriteBatch batch;
    private Texture grassTexture;
    private Texture pathTexture;

    public LevelRenderer(Level level ) {
        this.level = level;
        this.batch = new SpriteBatch();
        this.pathTexture = new Texture(Gdx.files.internal("path.png"));
        this.grassTexture = new Texture(Gdx.files.internal("grass.png"));
    }

    public void render() {
        batch.begin();
        for (Float i = 0f; i < level.getWidth(); i++) {
            for (Float j = 0f; j < level.getHeight(); j++) {
                Tile tile = level.getTile(i.intValue(), j.intValue());
                if (tile.isWalkable()) {
                    batch.draw(pathTexture, i*50, j*50, 50, 50);
                } else {
                    batch.draw(grassTexture, i*50, j*50, 50, 50);
                }
            }
        }
        batch.end();
    }

    public void dispose() {
        this.pathTexture.dispose();
        this.grassTexture.dispose();
    }


}
