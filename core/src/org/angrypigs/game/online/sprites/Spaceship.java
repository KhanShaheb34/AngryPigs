package org.angrypigs.game.online.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Spaceship extends Sprite {

    private Vector2 firePos;
    private Vector2 previousPos;

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
        System.out.println(x+", "+ y);
    }

    public void fire(Vector2 pos) {
        firePos = pos;
    }

    public void noFire() {
        firePos = new Vector2(0,0);
    }

    public boolean isFiring() {
        return Gdx.input.isTouched();
    }
}
