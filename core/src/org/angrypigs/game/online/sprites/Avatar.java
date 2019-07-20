package org.angrypigs.game.online.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.angrypigs.game.Sprites.Bullet;
import org.angrypigs.game.Util.Constants;

public class Avatar extends Sprite {

    public Vector2 firePos;
    public Vector2 previousPos;
    public Bullet bullet;

    public Avatar(Texture texture) {

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
        bullet = new Bullet(previousPos.x, previousPos.y, firePos.x, firePos.y);
        System.out.println(previousPos.x+" "+previousPos.y+" "+firePos.x+" "+firePos.y);
    }
}
