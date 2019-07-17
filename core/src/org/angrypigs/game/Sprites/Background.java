package org.angrypigs.game.Sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture sky, bg, md, fg, gr;
    private String location;

    public Background(String location) {
        this.location = location;

        sky = new Texture(location + "/sky.png");
        bg = new Texture(location + "/bg.png");
        md = new Texture(location + "/md.png");
        fg = new Texture(location + "/fg.png");
        gr = new Texture(location + "/gr.png");
    }

    public void render(SpriteBatch batch, OrthographicCamera cam) {
        batch.begin();
        batch.draw(sky, (int)((cam.position.x-600)*0.6), 0);
        batch.draw(bg, (int)((cam.position.x-600)*0.4),0 );
        batch.draw(md, (int)((cam.position.x-600)*0.2), 0);
        batch.draw(fg, (int)((cam.position.x-600)*0), 0);
        batch.draw(gr, (int)((cam.position.x-600)*(-0.1)), 0);
        batch.end();
    }

    public void renderGr(SpriteBatch batch, OrthographicCamera cam) {
        batch.begin();
        batch.draw(gr, (int)((cam.position.x-600)*(-0.1)), 0);
        batch.end();
    }
}
