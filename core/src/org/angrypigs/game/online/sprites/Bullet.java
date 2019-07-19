package org.angrypigs.game.online.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite {

    private Vector2 init, end;
    private Batch batch;
    private float rad, dx, dy, x, y;
    private float speed = 400;

    public Bullet(Texture texture) {

        super(texture);
    }

    public Bullet(Bullet bullet, Vector2 init, Vector2 end) {

        super(bullet.getTexture());
        this.init = init;
        this.end = end;
        x = init.x;
        y = init.y;
        rad = MathUtils.atan2(end.y - init.y, end.x - init.x);
        dx = MathUtils.cos(rad) * speed;
        dy = MathUtils.sin(rad) * speed;
    }

    public void update(float dt) {

        x += dx * dt;
        y += dy * dt;

        setPosition(x, y);
    }
}
