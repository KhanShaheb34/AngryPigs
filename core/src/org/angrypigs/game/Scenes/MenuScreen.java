package org.angrypigs.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.offline.StoryMode;
import org.angrypigs.game.online.JoinGame;

public class MenuScreen implements Screen {

    private AngryPigs game;
    private SpriteBatch batch;
    private boolean snd = true;
    private Sprite bgSprite, play, online, playHover, onlineHover, settings,
            music, about, help, settings_hover, music_hover,
            about_hover, help_hover, music_off, music_off_hover;
    private Music music_buff;
    private StoryMode story;
    private JoinGame multiplayer;

    public MenuScreen(AngryPigs game) {

        this.game = game;
        batch = game.batch;

        Texture faq = new Texture(Gdx.files.internal("BG/menu/faq.png"));
        Texture pTex = new Texture(Gdx.files.internal("BG/menu/play.png"));
        Texture abt = new Texture(Gdx.files.internal("BG/menu/about.png"));
        Texture oTex = new Texture(Gdx.files.internal("BG/menu/online.png"));
        Texture bgTexture = new Texture(Gdx.files.internal("BG/menu/bg.png"));
        Texture musicTex = new Texture(Gdx.files.internal("BG/menu/sound.png"));
        Texture mot = new Texture(Gdx.files.internal("BG/menu/sound_off.png"));
        Texture moth = new Texture(Gdx.files.internal("BG/menu/sound_off_hover.png"));
        Texture pHtex = new Texture(Gdx.files.internal("BG/menu/play_hover.png"));
        Texture oHtex = new Texture(Gdx.files.internal("BG/menu/online_hover.png"));
        Texture settingsTex = new Texture(Gdx.files.internal("BG/menu/settings.png"));
        Texture sht = new Texture(Gdx.files.internal("BG/menu/settings_hover.png"));
        Texture hht = new Texture(Gdx.files.internal("BG/menu/faq_hover.png"));
        Texture aht = new Texture(Gdx.files.internal("BG/menu/about_hover.png"));
        Texture mht = new Texture(Gdx.files.internal("BG/menu/sound_hover.png"));
        music_buff = Gdx.audio.newMusic(Gdx.files.internal("sound/bg.mp3"));

        music_buff.setLooping(true);
        if(!music_buff.isPlaying())
            music_buff.play();

        music_off_hover = new Sprite(moth);
        music_off = new Sprite(mot);
        help = new Sprite(faq);
        about = new Sprite(abt);
        play = new Sprite(pTex);
        online = new Sprite(oTex);
        music = new Sprite(musicTex);
        playHover = new Sprite(pHtex);
        onlineHover = new Sprite(oHtex);
        bgSprite = new Sprite(bgTexture);
        settings = new Sprite(settingsTex);
        settings_hover = new Sprite(sht);
        help_hover = new Sprite(hht);
        about_hover = new Sprite(aht);
        music_hover = new Sprite(mht);
        help.setScale(0.5f);
        play.setScale(0.5f);
        music.setScale(0.5f);
        about.setScale(0.5f);
        online.setScale(0.5f);
        settings.setScale(0.5f);
        playHover.setScale(0.5f);
        onlineHover.setScale(0.5f);
        help_hover.setScale(0.5f);
        about_hover.setScale(0.5f);
        music_hover.setScale(0.5f);
        music_off.setScale(0.5f);
        music_off_hover.setScale(0.5f);
        settings_hover.setScale(0.5f);
        help.setPosition(1020, 500);
        play.setPosition(350, 200);
        bgSprite.setPosition(0, 0);
        music.setPosition(-30, -40);
        music_off.setPosition(-30, -40);
        music_off_hover.setPosition(-30, -40);
        about.setPosition(-30, 500);
        online.setPosition(650, 200);
        settings.setPosition(1020, -40);
        music_hover.setPosition(-30, -40);
        about_hover.setPosition(-30, 500);
        help_hover.setPosition(1020, 500);
        playHover.setPosition(350, 200);
        onlineHover.setPosition(650, 200);
        settings_hover.setPosition(1020, -40);
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
        settings.draw(batch);
        if(snd)
            music.draw(batch);
        else
            music_off.draw(batch);
        about.draw(batch);
        help.draw(batch);

        if(Gdx.input.getX() >= 400 && Gdx.input.getX() <= (400 + 110) &&
                Gdx.input.getY() >= 320 && Gdx.input.getY() <= 320 + 110) {

            playHover.draw(batch);
            if(Gdx.input.isTouched()) {
                story = new StoryMode(this.game, this);
                game.setScreen(story);
            }
        }

        if(Gdx.input.getX() >= 700 && Gdx.input.getX() <= (700 + 110) &&
                Gdx.input.getY() >= 320 && Gdx.input.getY() <= 320 + 110) {

            onlineHover.draw(batch);
            if(Gdx.input.isTouched()) {
                multiplayer = new JoinGame(game, this);
                game.setScreen(multiplayer);
            }
        }

        if(Gdx.input.getX() >= 1070 && Gdx.input.getX() <= (1070 + 110) &&
                Gdx.input.getY() >= 560 && Gdx.input.getY() <= 560 + 110) {

            settings_hover.draw(batch);
            if(Gdx.input.isTouched())
                game.setScreen(new Settings(game));
        }

        if(Gdx.input.getX() >= 1070 && Gdx.input.getX() <= (1070 + 110) &&
                Gdx.input.getY() >= 20 && Gdx.input.getY() <= 20 + 110) {

            help_hover.draw(batch);
            if(Gdx.input.isTouched())
                game.setScreen(new Help(game));
        }

        if(Gdx.input.getX() >= 20 && Gdx.input.getX() <= 20 + 110 &&
                Gdx.input.getY() >= 20 && Gdx.input.getY() <= 20 + 110) {

            about_hover.draw(batch);
            if(Gdx.input.isTouched())
                game.setScreen(new About(game));
        }

        if(Gdx.input.getX() >= 20 && Gdx.input.getX() <= 20 + 110 &&
                Gdx.input.getY() >= 560 && Gdx.input.getY() <= 560 + 110) {
            if(snd) {
                music_hover.draw(batch);
                if(Gdx.input.justTouched()) {
                    snd = false;
                    music_buff.pause();
                }
            } else {
                music_off_hover.draw(batch);
                if(Gdx.input.justTouched()) {
                    snd = true;
                    music_buff.play();
                }
            }
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

        game.dispose();
        batch.dispose();
        music_buff.dispose();
        multiplayer.dispose();
        story.dispose();
    }
}

