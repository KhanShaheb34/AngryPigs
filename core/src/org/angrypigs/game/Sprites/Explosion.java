package org.angrypigs.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;

public class Explosion extends Sprite {
    private final TextureAtlas atlas;
    private Animation<TextureRegion> animation;
    private Sprite sprite;
    private float elapsedTime;
    public boolean exploded;

    public Explosion(float x, float y) {
        atlas = new TextureAtlas("Bullets/Explosion.atlas");
        animation = new Animation<TextureRegion>(1f/30f, atlas.findRegions("explosion"));
        sprite = new Sprite(animation.getKeyFrame(elapsedTime, false));
        sprite.setPosition(x - 128, y - 128);
    }

    public void draw(SpriteBatch sb) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        sb.begin();
        sprite.draw(sb);
        sb.end();
        sprite.setRegion(animation.getKeyFrame(elapsedTime, false));
        if (animation.isAnimationFinished(elapsedTime)) {
            sprite.setPosition(-500, -500);
            exploded = true;
            atlas.dispose();
        }
    }


}
