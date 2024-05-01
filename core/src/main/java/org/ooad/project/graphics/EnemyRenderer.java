package org.ooad.project.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import org.ooad.project.entity.Enemy;
import org.ooad.project.items.Tower;
import org.ooad.project.level.Level;
import org.ooad.project.level.PivotPoint;

import java.util.Iterator;

public class EnemyRenderer {
    private Level level;
    private SpriteBatch batch;
    private Animation<TextureRegion> entityAnimation;
    private Array<Texture> textures;

    public EnemyRenderer(Level level) {
        textures = new Array<>();
        this.level = level;
        this.batch = new SpriteBatch();
        loadEntityAnimation();
    }

    public void render(Float animationTime) {
        batch.begin();
        Iterator<Enemy> iterator = level.getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.move();
            enemy.findDirection();
            TextureRegion currentFrame = entityAnimation.getKeyFrame(animationTime, true);
            TextureRegion rotatedFrame = new TextureRegion(currentFrame);
            if (enemy.getDirection() == Enemy.Direction.UP) {
                rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 25, 25, 50, 50, 1f, 1f, 90f);
            } else if (enemy.getDirection() == Enemy.Direction.DOWN) {
                rotatedFrame.setRegion(currentFrame.getRegionX(), currentFrame.getRegionY(), currentFrame.getRegionHeight(), currentFrame.getRegionWidth());
                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 25, 25, 50, 50, 1f, 1f, 270f);
            } else {
                batch.draw(rotatedFrame, enemy.getX(), enemy.getY(), 50, 50);
            }
            if (enemy.getX() > level.getWidth() * 50 || enemy.getY() > level.getHeight() * 50) {
                iterator.remove();
            }
        }
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        for (Texture texture : textures) {
            texture.dispose();
        }
    }

    private void loadEntityAnimation() {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 17; i++) {
            String filename = "entityMove/move" + i + ".png";
            Texture texture = new Texture(Gdx.files.internal(filename));
            textures.add(texture);
            TextureRegion frameTexture = new TextureRegion(texture);
            frames.add(new TextureRegion(frameTexture));
        }
        entityAnimation = new Animation<>(0.1f, frames);
    }

}