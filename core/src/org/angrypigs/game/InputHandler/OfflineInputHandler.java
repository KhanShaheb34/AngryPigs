package org.angrypigs.game.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import org.angrypigs.game.Sprites.Wizard;
import org.angrypigs.game.Util.Constants;
import org.angrypigs.game.Util.PVector;
import org.angrypigs.game.offline.StoryMode;

public class OfflineInputHandler {
    private StoryMode game;
    private Wizard wizard;
    private OrthographicCamera cam;

    public OfflineInputHandler(StoryMode game) {
        this.game = game;
        this.wizard = game.getWizard();
        this.cam = game.getCam();
    }

    public boolean processInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !wizard.jumping) {
            wizard.jump(25);
            return true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                wizard.run(5f);
                if (cam.position.x < 2700) cam.position.x += 5;
                return true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            wizard.walk(2f);
            if (cam.position.x < 2700) cam.position.x += 2;
            return true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)) {
            wizard.die();
            return true;
        }
        if(Gdx.input.justTouched()) {
            wizard.attack();
            game.shoot(wizard.getPosition().x + 140, wizard.getPosition().y + 120, getMouse().x - 16, getMouse().y - 16);
            return true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.H)) {
            wizard.hurt();
            return true;
        }

        return false;
    }

    private PVector getMouse() {
        return new PVector(cam.position.x + Gdx.input.getX() - Constants.WIDTH/2f,
                            cam.position.y - Gdx.input.getY() + Constants.HEIGHT/2f);
    }
}
