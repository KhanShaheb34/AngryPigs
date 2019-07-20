package org.angrypigs.game.Scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.angrypigs.game.AngryPigs;

public class Settings implements Screen {

    private AngryPigs game;
    private Sprite bg;

    public Settings(AngryPigs game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        bg.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        game.dispose();
    }
}
