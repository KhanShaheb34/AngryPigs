package org.angrypigs.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.offline.StoryMode;
import org.angrypigs.game.online.Multiplayer;

public class MenuScreen implements Screen {
    private AngryPigs game;

    public MenuScreen(AngryPigs game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.setScreen(new StoryMode(game));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.setScreen(new Multiplayer(game));
        }
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

    }
}

