package org.angrypigs.game.offline;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class Map {

    public abstract void render(OrthographicCamera camera);
    public abstract void update(float delta);
    public abstract void dispose();
}
