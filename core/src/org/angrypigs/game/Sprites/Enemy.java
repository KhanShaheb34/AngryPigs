package org.angrypigs.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.angrypigs.game.offline.StoryMode;

public class Enemy extends Sprite {

    public int life;
    private StoryMode game;
    private float bulletTimer;

    public Enemy(Texture texture, StoryMode game) {
        super(texture);
        this.game = game;
        life = 1;
    }

    public void fire(float x, float y) {
        bulletTimer += Gdx.graphics.getDeltaTime();
        if (bulletTimer >= 1f && !game.getWizard().dying) {
            game.shoot(new EnemyBullet(super.getX(), super.getY(), x, y));
            bulletTimer = 0;
        }
    }

    public boolean isAlive() {
        return life >= 0;
    }

    public void update() {

    }

    @Override
    public void draw(Batch batch) {
        batch.begin();
        super.draw(batch);
        batch.end();
    }
}
