package org.angrypigs.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Sprite {

    private Vector2 initPos;
    private boolean alive = true;
    private Bullet bullet;

    public Enemy(Texture texture) {
        super(texture);
        initPos = new Vector2(0, 0);
    }

    public void setPos(float x, float y) {
        initPos.x = x;
        initPos.y = y;
    }

    public void fire(float x, float y) {
        bullet = new Bullet(initPos.x, initPos.y, x, y);
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
