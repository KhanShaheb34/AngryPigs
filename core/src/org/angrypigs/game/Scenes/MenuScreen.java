package org.angrypigs.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.offline.StoryMode;
import org.angrypigs.game.online.Multiplayer;

public class MenuScreen implements Screen {

    private AngryPigs game;
    private Sprite bgSprite, play, online, playHover, onlineHover;
    private SpriteBatch batch;
    private boolean playTouched = false, onlineTouched = false;

    public MenuScreen(AngryPigs game) {
        this.game = game;
        batch = game.batch;
        Texture bgTexture = new Texture(Gdx.files.internal("BG/menu/bg.png"));
        Texture pTex = new Texture(Gdx.files.internal("BG/menu/play.png"));
        Texture pHtex = new Texture(Gdx.files.internal("BG/menu/play_hover.png"));
        Texture oTex = new Texture(Gdx.files.internal("BG/menu/online.png"));
        Texture oHtex = new Texture(Gdx.files.internal("BG/menu/online_hover.png"));
        play = new Sprite(pTex);
        playHover = new Sprite(pHtex);
        play.setScale(.5f);
        play.setPosition(350, 200);
        playHover.setScale(0.5f);
        playHover.setPosition(350, 200);
        online = new Sprite(oTex);
        onlineHover = new Sprite(oHtex);
        online.setScale(0.5f);
        online.setPosition(650, 200);
        onlineHover.setScale(0.5f);
        onlineHover.setPosition(650, 200);
        bgSprite = new Sprite(bgTexture);
        bgSprite.setPosition(0, 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(bgSprite,0, 0);
        play.draw(batch);
        online.draw(batch);
        if(Gdx.input.getX() >= 400 && Gdx.input.getX() <= (400 + 110) && Gdx.input.getY() >= 320 && Gdx.input.getY() <= 320 + 110) {
            playHover.draw(batch);
            if(Gdx.input.isTouched())
                game.setScreen(new StoryMode(this.game));
        }
        if(Gdx.input.getX() >= 700 && Gdx.input.getX() <= (700 + 110) && Gdx.input.getY() >= 320 && Gdx.input.getY() <= 320 + 110) {
            onlineHover.draw(batch);
            if(Gdx.input.isTouched())
                game.setScreen(new Multiplayer(this.game));
        }
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

