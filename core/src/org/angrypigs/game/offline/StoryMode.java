package org.angrypigs.game.offline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.Scenes.StoryHud;
import org.angrypigs.game.Sprites.Background;
import org.angrypigs.game.Util.Constants;

public class StoryMode implements Screen {

    private AngryPigs game;
    private StoryHud hud;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Background map;

    public StoryMode(AngryPigs g) {
        game = g;

        cam = new OrthographicCamera();
        cam.update();
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, cam);
        viewport.apply();
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        map = new Background("BG/Map1");
        hud = new StoryHud(g.batch);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);
        map.render(game.batch, cam);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void update(float dt) {
        handleInput(dt);
        cam.update();
        System.out.println(cam.position);
    }

    private void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) cam.position.x -= 2;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) cam.position.x += 2;
    }

    @Override
    public void resize(int width, int height) {
        hud.stage.getViewport().update(width, height);
        viewport.update(width, height);
    }

    @Override
    public void show() {

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

