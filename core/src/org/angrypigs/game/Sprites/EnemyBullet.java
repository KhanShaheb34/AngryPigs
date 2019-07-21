package org.angrypigs.game.Sprites;

public class EnemyBullet extends Bullet {

    public EnemyBullet(float sx, float sy, float ex, float ey) {
        super(sx, sy, ex, ey);
        super.shoytan = true;
    }

    @Override
    public void checkRemove() {
        if(loc.x <= this.ex || loc.y <= this.ey)
            removed = true;
    }
}
