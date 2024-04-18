package org.ooad.project.graphics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.ooad.project.items.Tower;
import org.ooad.project.level.Level;

public class TowerRenderer {
    private Level level;
    private SpriteBatch batch;
    private TextureRegion towerTexture;
    private TextureRegion baseTexture;

    public TowerRenderer(Level level) {
        this.level = level;
        this.batch = new SpriteBatch();
        this.towerTexture = new TextureRegion (new Texture(Gdx.files.internal("towers/Cannon.png")));
        this.baseTexture = new TextureRegion (new Texture(Gdx.files.internal("towers/Tower.png")));
    }

    public void render() {
        batch.begin();
        for (Tower tower : level.getTowerManager().getTowers()) {
            float towerX = tower.getTile().getXCoordinate().floatValue() + 5;
            float towerY = tower.getTile().getYCoordinate().floatValue() + 5;

            batch.draw(baseTexture, towerX, towerY, 20, 20, 40, 40, 1f, 1f, 0f);

            float towerRotation = 0;
            if (tower.getTarget() != null) {
                float enemyX = tower.getTarget().getX() + 25;
                float enemyY = tower.getTarget().getY() + 25;
                towerRotation = (float) Math.toDegrees(Math.atan2(enemyY - (towerY + 20), enemyX - (towerX + 20))) - 90;
            }

            batch.draw(towerTexture, towerX + 5, towerY + 5, 15, 15, 30, 30, 1f, 1f, towerRotation);
        }
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        towerTexture.getTexture().dispose();
        baseTexture.getTexture().dispose();
    }
}

