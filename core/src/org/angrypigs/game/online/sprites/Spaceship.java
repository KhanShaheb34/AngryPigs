package org.angrypigs.game.online.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.angrypigs.game.Util.Constants;

public class Spaceship extends Sprite {

    private Vector2 firePos;
    private Vector2 previousPos;
    public Bullet bullet;

    public Spaceship(Texture texture) {

        super(texture);
        firePos = new Vector2(0,0);
        previousPos = new Vector2(getX(), getY());
    }

    public boolean hasMoved() {

        if(previousPos.x != getX() || previousPos.y != getY()) {

            previousPos.x = getX();
            previousPos.y = getY();
            return true;
        }

        return false;
    }

    public float getFireposX() {
        return firePos.x;
    }

    public float getFireposY() {
        return firePos.y;
    }

    public void setFirePos(float x, float y) {

        firePos = new Vector2(x, y);
        fire(firePos);
    }

    private void fire() {

                /* THIS HAVE TO BE FIXED */
        if(firePos.x != 0 && firePos.y != 0) {
            bullet.setPosition(Gdx.input.getX(), Gdx.input.getY());
        }
    }

    public void fire(Vector2 pos) {

                /* THIS HAVE TO BE FIXED */
        bullet = new Bullet(new Texture(Gdx.files.internal("ship/bullet.png")));
        firePos = pos;
        if(pos.x != 0 && pos.y != 0)
            fire();
    }

    public void noFire() {
        firePos = new Vector2(0,0);
    }

    public boolean isFiring() {
        return Gdx.input.isTouched();
    }
}
