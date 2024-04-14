package main.java.org.ooad.project.project.graphics;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import main.java.org.ooad.project.project.level.Level;

public class TowerRenderer {
    private Level level;
    private SpriteBatch batch;
    private Texture towerTexture;
    private Texture baseTexture;

    public TowerRenderer(Level level) {
        this.level = level;
        this.batch = new SpriteBatch();
        this.towerTexture = new Texture(Gdx.files.internal("towers/Cannon.png"));
        this.baseTexture = new Texture(Gdx.files.internal("towers/Tower.png"));
    }

    public void render() {
        batch.begin();
        TextureRegion towerTexture = new TextureRegion(new Texture(Gdx.files.internal("towers/Cannon.png")));
        TextureRegion baseTexture = new TextureRegion(new Texture(Gdx.files.internal("towers/Tower.png")));
        batch.draw(baseTexture, 2*50 + 5, (level.getHeight()/2 + 1)*50+5, 40, 40);
        batch.draw(towerTexture, 2*50 + 10, (level.getHeight()/2 + 1)*50+10, 30, 30);
        batch.end();
    }

    public void dispose() {
        this.towerTexture.dispose();
        this.baseTexture.dispose();
    }


}

