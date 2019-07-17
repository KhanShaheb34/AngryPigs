package org.angrypigs.game.online.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.angrypigs.game.Util.Constants;

public class Bullet extends Sprite {

    private Vector2 init, end;
    private Batch batch;

    public Bullet(Texture texture) {

        super(texture);
    }

    Bullet(Bullet bullet, Vector2 init, Vector2 end) {

        super(bullet.getTexture());
        this.init = init;
        this.end = end;
    }

    public void getVect(Vector2 init, Vector2 end) {

        this.init = init;
        this.end = end;
    }
}
