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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.Scenes.StoryHud;
import org.angrypigs.game.Sprites.Background;
import org.angrypigs.game.Sprites.Ground;
import org.angrypigs.game.Sprites.Wizard;
import org.angrypigs.game.Util.Constants;

public class StoryMode implements Screen {

    private AngryPigs game;
    private StoryHud hud;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Background map;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private Ground ground;
    private Wizard wizard;


    public StoryMode(AngryPigs g) {
        game = g;

        cam = new OrthographicCamera();
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        map = new Background("BG/Map1");
        hud = new StoryHud(g.batch);

        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        ground = new Ground(world, 1800, 20);
        wizard = new Wizard(world);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);
        map.render(game.batch, cam);

        debugRenderer.render(world, cam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void update(float dt) {
        handleInput(dt);
        cam.update();
        world.step(1/60f, 6, 2);
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

