package org.angrypigs.game.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.angrypigs.game.Sprites.Wizard;
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
                cam.position.x += 5;
                return true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            wizard.walk(2f);
            cam.position.x += 2;
            return true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)) {
            wizard.die();
            return true;
        }
        if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.F)) {
            wizard.attack();
            return true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.H)) {
            wizard.hurt();
            return true;
        }
        return false;
    }
}
