package org.angrypigs.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import org.angrypigs.game.AngryPigs;

public class GameOver implements Screen {
    private Texture texture;
    private AngryPigs game;

    public GameOver(AngryPigs game) {
        this.game = game;
        texture = new Texture("over.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(texture, 0, 0, 1200, 685);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
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
