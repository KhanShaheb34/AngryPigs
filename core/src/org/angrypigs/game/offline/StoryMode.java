package org.angrypigs.game.offline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.InputHandler.OfflineInputHandler;
import org.angrypigs.game.Scenes.StoryHud;
import org.angrypigs.game.Sprites.Background;
import org.angrypigs.game.Sprites.Bullet;
import org.angrypigs.game.Sprites.Ground;
import org.angrypigs.game.Sprites.Wizard;
import org.angrypigs.game.Util.Constants;

public class StoryMode implements Screen {

    private AngryPigs game;
    private StoryHud hud;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Background map;

    private Wizard wizard;
    public Bullet bullet;
    private OfflineInputHandler handler;


    public StoryMode(AngryPigs g) {
        game = g;

        cam = new OrthographicCamera();
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        map = new Background("BG/Map1");
        hud = new StoryHud(g.batch);

        bullet = new Bullet(0, 0, 0, 0);

        wizard = new Wizard();
        handler = new OfflineInputHandler(this);
    }

    @Override
    public void render(float delta) {
        if (!handler.processInput(delta)) {
            wizard.idle();
        }
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);
        map.render(game.batch, cam);

        wizard.draw(game.batch);
        bullet.draw(game.batch);

        map.renderGr(game.batch, cam);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    private void update(float dt) {
        cam.update();
        bullet.update();
    }

    public void shoot(float sx, float sy, float ex, float ey) {
        bullet = new Bullet(sx, sy, ex, ey);
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
        wizard.dispose();
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public Wizard getWizard() {
        return wizard;
    }
}

