package org.angrypigs.game.Scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.angrypigs.game.AngryPigs;

public class PlayScreen implements Screen {
    private AngryPigs game;
    private SpriteBatch batch;

    public PlayScreen(AngryPigs game) {
        this.game = game;
        this.batch = game.batch;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        /// Render Elements Here

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
