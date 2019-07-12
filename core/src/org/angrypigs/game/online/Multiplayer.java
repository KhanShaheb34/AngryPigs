package org.angrypigs.game.online;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import org.angrypigs.game.AngryPigs;

public class Multiplayer implements Screen {

    private AngryPigs game;
    private Stage stage;
    private Label outputLabel;
    private Button button1, button2;

    public Multiplayer(AngryPigs g) {
        game = g;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // Button
        button1 = new TextButton("Create Server", mySkin,"small");
        button1.setSize(col_width*4,row_height);
        button1.setPosition(col_width,Gdx.graphics.getHeight()-row_height*3);
        button1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Server Created.");
                return true;

            }
        });
        stage.addActor(button1);

        // Text Button
        button2 = new TextButton("Join a Game",mySkin,"small");
        button2.setSize(col_width*4,row_height);
        button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
        button2.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Searching for Server...");
                return true;
            }
        });
        stage.addActor(button2);

        outputLabel = new Label("",mySkin);
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(button2.isPressed())
            game.setScreen(new JoinGame(game));
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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

