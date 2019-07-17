package org.angrypigs.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import org.angrypigs.game.Util.PVector;
import org.angrypigs.game.Util.WizardState;

import static org.angrypigs.game.Util.WizardState.*;

public class Wizard extends Sprite {
    private TextureAtlas atlas;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> runAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> attackAnimation;
    private Animation<TextureRegion> hurtAnimation;
    private Animation<TextureRegion> dieAnimation;
    private float elapsedTime, stateTime;
    private Sprite sprite;
    public WizardState state;

    private PVector loc, vel, acc;
    private PVector gravity;
    public boolean dying, jumping, hurting, attacking;

    public Wizard() {
        atlas = new TextureAtlas("Spritesheets/MagicWizard.atlas");

        idleAnimation = new Animation<TextureRegion>(1f/6f, atlas.findRegions("1_IDLE"));
        walkAnimation = new Animation<TextureRegion>(1f/6f, atlas.findRegions("2_WALK"));
        runAnimation = new Animation<TextureRegion>(1f/6f, atlas.findRegions("3_RUN"));
        jumpAnimation = new Animation<TextureRegion>(1f/6f, atlas.findRegions("4_JUMP"));
        attackAnimation = new Animation<TextureRegion>(1f/6f, atlas.findRegions("5_ATTACK"));
        hurtAnimation = new Animation<TextureRegion>(1f/6f, atlas.findRegions("6_HURT"));
        dieAnimation = new Animation<TextureRegion>(1f/6f, atlas.findRegions("7_DIE"));

        sprite = new Sprite(idleAnimation.getKeyFrame(elapsedTime, true));

        state = IDLE;

        loc = new PVector(50, 40);
        vel = new PVector(0, 0);
        acc = new PVector(0, 0);

        gravity = new PVector(0, -2);
    }

    public void draw(SpriteBatch batch) {
        updatePosition();
        updateAnimation();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    private void updateAnimation() {
        stateTime += Gdx.graphics.getDeltaTime();
        switch (this.state) {
            case IDLE:
                sprite.setRegion(idleAnimation.getKeyFrame(elapsedTime, true));
                break;
            case WALK:
                sprite.setRegion(walkAnimation.getKeyFrame(elapsedTime, true));
                break;
            case RUN:
                sprite.setRegion(runAnimation.getKeyFrame(elapsedTime, true));
                break;
            case DIE:
                sprite.setRegion(dieAnimation.getKeyFrame(stateTime, false));
                break;
            case JUMP:
                sprite.setRegion(jumpAnimation.getKeyFrame(stateTime, false));
                break;
        }

        elapsedTime += Gdx.graphics.getDeltaTime();
    }

    private void updatePosition() {
        vel.add(acc);
        loc.add(vel);
        acc.mult(0);
        if(loc.y <= 40) {
            loc.y = 40;
            jumping = false;
        } else {
            acc.add(gravity);
        }
        sprite.setPosition(loc.x, loc.y);
        if (dying && dieAnimation.isAnimationFinished(stateTime)) {
            this.dispose();
            sprite.setPosition(-500, -500);
        }
        vel = new PVector(0, vel.y);
    }

    public void walk(float x) {
        if((dieAnimation.isAnimationFinished(stateTime) && jumpAnimation.isAnimationFinished(stateTime) && hurtAnimation.isAnimationFinished(stateTime) && attackAnimation.isAnimationFinished(stateTime))) {
            this.state = WALK;
        }
        vel.add(x, 0);
    }

    public void idle() {
        if((dieAnimation.isAnimationFinished(stateTime) && jumpAnimation.isAnimationFinished(stateTime) && hurtAnimation.isAnimationFinished(stateTime) && attackAnimation.isAnimationFinished(stateTime))){
            this.state = IDLE;
            vel = new PVector(0, 0);
        }
    }

    public void run(float x) {
        if((dieAnimation.isAnimationFinished(stateTime) && jumpAnimation.isAnimationFinished(stateTime) && hurtAnimation.isAnimationFinished(stateTime) && attackAnimation.isAnimationFinished(stateTime))) {
            this.state = RUN;
        }
        vel.add(x, 0);
    }

    public void die() {
        stateTime = 0;
        this.state = DIE;
        vel = new PVector(0, 0);
        dying = true;
    }

    public void jump(float force) {
        acc = new PVector(0, force);
        stateTime = 0;
        this.state = JUMP;
        jumping = true;
    }

    public void dispose() {
        atlas.dispose();
    }
}
