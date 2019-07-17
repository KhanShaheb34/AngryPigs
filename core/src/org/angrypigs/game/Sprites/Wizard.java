package org.angrypigs.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;

public class Wizard extends Sprite {
    private TextureAtlas atlas;
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private Sprite sprite;

    public Wizard() {
        atlas = new TextureAtlas("Spritesheets/MagicWizard.atlas");
        animation = new Animation<TextureRegion>(1f/8f, atlas.findRegions("5_ATTACK"));
        sprite = new Sprite(animation.getKeyFrame(elapsedTime, true));
        sprite.setScale(0.6f);
        sprite.setPosition(50, -50);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void walk(int x) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        sprite.setRegion(animation.getKeyFrame(elapsedTime, true));
        sprite.setPosition(sprite.getX() + x, sprite.getY());
    }

    public void move(int x) {
    }

    public void dispose() {
        atlas.dispose();
    }
}
