package org.angrypigs.game.online.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Spaceship extends Sprite {

    private Vector2 previousPos;

    public Spaceship(Texture texture) {
        super(texture);
        previousPos = new Vector2(getX(), getY());
    }

    public boolean hasMoved() {
        if(previousPos.x != getX() || previousPos.y != getY()) {
            previousPos.x = getX();
            previousPos.y =getY();
            return true;
        }
        return false;
    }
}
