package org.angrypigs.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.angrypigs.game.AngryPigs;

public class MenuScreen implements Screen {
    private AngryPigs game;
    private Texture texture;
    private Sprite sprite;
    private SpriteBatch batch;
    int x, y;

    public MenuScreen(AngryPigs game) {
        this.game = game;
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        sprite = new Sprite(texture);
        this.batch = game.batch;
        x = 0;
        y = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        update();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    private void update() {
        sprite.setPosition(x, y);
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x+=5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x-=5;
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
