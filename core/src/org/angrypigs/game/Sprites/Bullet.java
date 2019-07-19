package org.angrypigs.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.angrypigs.game.Util.PVector;

public class Bullet extends Sprite {
    private Sprite sprite;
    private PVector loc, vel, acc, dir;
    private float topSpeed, sx, sy, ex, ey;
    private boolean removed;

    public Bullet(float sx, float sy, float ex, float ey) {
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        sprite = new Sprite(new Texture("Bullets/blue2.png"));

        loc = new PVector(sx, sy);
        vel = new PVector(0,0);
        acc = new PVector(0, 0);
        dir = new PVector(ex, ey);

        dir.sub(loc);
        dir.norm();
        dir.mult(1f);
        acc = dir;

        topSpeed = 20.0f;

        sprite.setPosition(loc.x, loc.y);
    }

    public void update (){
        vel.add(acc);
        vel.limit(topSpeed);
        loc.add(vel);

        if(!removed) sprite.setPosition(loc.x, loc.y);

        if(loc.x > ex || loc.y > ey || loc.y < 0)
            removed = true;

    }

    public void draw(SpriteBatch sb) {
        sb.begin();
        sprite.draw(sb);
        sb.end();
    }
}
