package org.angrypigs.game;

import com.badlogic.gdx.Game;
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
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
