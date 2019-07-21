package org.angrypigs.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.angrypigs.game.offline.StoryMode;

public class Enemy extends Sprite {

    private Vector2 initPos;
    private boolean alive = true;
    private Bullet bullet;
    private StoryMode game;
    private float bulletTimer;

    public Enemy(Texture texture, StoryMode game) {
        super(texture);
        initPos = new Vector2(0, 0);
        this.game = game;
    }

    public void setPos(float x, float y) {
        initPos.x = x;
        initPos.y = y;
    }

    public void fire(float x, float y) {
        bulletTimer += Gdx.graphics.getDeltaTime();
        if (bulletTimer >= 1f) {
            game.shoot(new EnemyBullet(super.getX(), super.getY(), x, y));
            bulletTimer = 0;
        }
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void update() {
        if(bullet != null)
            bullet.update();
    }

    @Override
    public void draw(Batch batch) {
        batch.begin();
        super.draw(batch);
        if(bullet != null)
            bullet.draw(batch);
        batch.end();
    }
}
