package org.angrypigs.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.angrypigs.game.Scenes.MenuScreen;

public class AngryPigs extends Game {
	public SpriteBatch batch;
	public Stage stage;

	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
