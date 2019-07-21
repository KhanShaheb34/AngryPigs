package org.angrypigs.game.offline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.InputHandler.OfflineInputHandler;
import org.angrypigs.game.Scenes.MenuScreen;
import org.angrypigs.game.Scenes.StoryHud;
import org.angrypigs.game.Sprites.*;
import org.angrypigs.game.Util.Constants;

import java.util.ArrayList;
import java.util.Random;

public class StoryMode implements Screen {

    private AngryPigs game;
    private StoryHud hud;
    private boolean pause = false;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Background map;
    private ArrayList<Enemy> onkShoytan, moraShoytan;
    private Wizard wizard;
    private ArrayList<Bullet> bullets, toRemoveBul;
    private ArrayList<Explosion> explosions, toRemoveExp;
    private Sprite bg, header, table;
    private MenuScreen menu;
    private OfflineInputHandler handler;


    public StoryMode(AngryPigs g, MenuScreen menu) {
        game = g;
        this.menu = menu;
        cam = new OrthographicCamera();
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);

        Texture bgT = new Texture(Gdx.files.internal("BG/pause/bg.png"));
        Texture headerT = new Texture(Gdx.files.internal("BG/pause/header.png"));
        Texture tableT = new Texture(Gdx.files.internal("BG/pause/table.png"));

        bg = new Sprite(bgT);
        header = new Sprite(headerT);
        table = new Sprite(tableT);

        bg.setPosition(150,-100);
        bg.setScale(1.5f);
        table.setPosition(190, 65);
        table.setScale(1.1f);
        header.setScale(0.5f);
        header.setPosition(100, 250);

        map = new Background("BG/Map1");
        hud = new StoryHud(g.batch);

        bullets = new ArrayList<Bullet>();
        toRemoveBul = new ArrayList<Bullet>();
        bullets.add(new Bullet(0,0,0,0));

        explosions = new ArrayList<Explosion>();
        toRemoveExp = new ArrayList<Explosion>();

        wizard = new Wizard();
        handler = new OfflineInputHandler(this);

        onkShoytan = new ArrayList<Enemy>();
        moraShoytan = new ArrayList<Enemy>();

        Texture bird = new Texture("Bird/bird2.png");

        for(int i = 0; i < 10; i++) {
            onkShoytan.add(new Enemy(bird, this));
        }

        Random random = new Random();

        for (Enemy shoytan : onkShoytan) {
            shoytan.setPosition(random.nextInt(2700) + 500, random.nextInt(300) + 300);
            shoytan.setScale(0.9f);
        }
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE))
            game.setScreen(menu);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            pause = !pause;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!pause) {
            update(delta);

            if (!handler.processInput(delta)) {
                wizard.idle();
            }

            game.batch.setProjectionMatrix(cam.combined);
            map.render(game.batch, cam);

            wizard.draw(game.batch);

            for(Enemy shoytan : onkShoytan) {
                if(!shoytan.isAlive()) {
                    moraShoytan.add(shoytan);
                }
                if(shoytan.getX() - wizard.getPosition().x <= 1100) {
                    shoytan.draw(game.batch);
                    shoytan.fire(wizard.getPosition().x + 100, wizard.getPosition().y + 100);
                }
                for(Bullet bullet: bullets) {
                    if(bullet.getX() >= shoytan.getX() - 10 && bullet.getX() <= shoytan.getX() + 50 && !bullet.shoytan &&
                            bullet.getY() >= shoytan.getY() - 10 && bullet.getY() <= shoytan.getY() + 70) {
                        shoytan.life--;
                        bullet.removed = true;
                        explosions.add(new Explosion(bullet.getLoc().x, bullet.getLoc().y));
                    }
                    if(bullet.getX() >= wizard.getPosition().x - 100 && bullet.getX() <= wizard.getPosition().x + 100 &&
                            bullet.shoytan && bullet.getY() >= wizard.getPosition().y && bullet.getY() <= wizard.getPosition().y + 200) {
                        hud.life--;
                        if(hud.life <= 0)
                            wizard.die();
                    }
                    bullet.draw(game.batch);
                    if (bullet.removed) {
                        explosions.add(new Explosion(bullet.getLoc().x, bullet.getLoc().y));
                        toRemoveBul.add(bullet);
                    }
                }
                bullets.removeAll(toRemoveBul);
            }
            onkShoytan.removeAll(moraShoytan);

            for(Explosion explosion: explosions) {
                explosion.draw(game.batch);
                if (explosion.exploded) {
                    toRemoveExp.add(explosion);
                }
            }

            explosions.removeAll(toRemoveExp);

            map.renderGr(game.batch, cam);

            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw();
        } else {
            game.batch.begin();
            bg.draw(game.batch);
            table.draw(game.batch);
            header.draw(game.batch);
            game.batch.end();
        }
    }

    private void update(float dt) {
        cam.update();
        hud.update();
        for (Bullet bullet: bullets) {
            bullet.update();
        }
        for(Enemy shoytan : onkShoytan) {
            shoytan.update();
        }
    }

    public void shoot(Bullet bullet) {
        bullets.add(bullet);
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
        hud.dispose();
        game.dispose();
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public Wizard getWizard() {
        return wizard;
    }
}

