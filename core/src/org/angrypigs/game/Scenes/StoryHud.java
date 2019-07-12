package org.angrypigs.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import org.angrypigs.game.Util.Constants;

public class StoryHud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private int life;
    private int level;

    private Label lifeLabel;
    private Label levelLabel;

    public StoryHud(SpriteBatch sb) {
        life = 5;
        level = 1;

        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        lifeLabel = new Label(String.format("LIFE: %01d", life), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(String.format("LEVEL: %01d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(lifeLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
