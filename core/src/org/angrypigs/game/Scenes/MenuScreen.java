package org.angrypigs.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.angrypigs.game.AngryPigs;

public class MenuScreen implements Screen {
    private AngryPigs game;
    private Texture texture;
    private SpriteBatch batch;

    public MenuScreen(AngryPigs game) {
        this.game = game;
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        this.batch = game.batch;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
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
