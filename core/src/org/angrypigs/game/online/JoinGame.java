package org.angrypigs.game.online;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.Scenes.MenuScreen;
import org.angrypigs.game.Sprites.Bullet;
import org.angrypigs.game.Util.Constants;
import org.angrypigs.game.online.sprites.Avatar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.util.HashMap;

public class JoinGame implements Screen {

    private int it = 0;
    private Batch batch;
    private float timer;
    private Socket socket;
    private Avatar player;
    private Bullet bullet;
    private Sprite sprite;
    private AngryPigs game;
    private Texture playerTex;
    private Texture enemyTex;
    private float elapsedTime;
    private Animation<TextureRegion> connectAnimation;
    private HashMap <String, Avatar> friendlyPlayers;

    public JoinGame(AngryPigs g) {

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Spritesheets/wait.atlas"));
        connectAnimation = new Animation<TextureRegion>(1 / 20f, atlas.findRegions("wait"));
        sprite = new Sprite(connectAnimation.getKeyFrame(elapsedTime, true));
        sprite.setPosition(Constants.WIDTH / 2 - 110, Constants.HEIGHT / 2 - 150);
        playerTex = new Texture("ship/playerShip2.png");
        enemyTex = new Texture("ship/playerShip.png");
        friendlyPlayers = new HashMap<String, Avatar>();
        bullet = new Bullet(0, 0, 0, 0);
        sprite.setScale(0.7f);
        batch = g.batch;
        game = g;
        connectSocket();
    }

    private void connectSocket() {

        try {
            socket = IO.socket("http://127.0.0.1:8080");
            socket.connect();
            configSocketEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configSocketEvent() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
                player = new Avatar(playerTex);
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: "+ id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", String.valueOf(e));
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player ID: "+ playerId);
                    friendlyPlayers.put(playerId, new Avatar(enemyTex));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    friendlyPlayers.remove(id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    Double x = data.getDouble("x");
                    Double y = data.getDouble("y");
                    String playerId = data.getString("id");
                    Double touchX = data.getDouble("touchX");
                    Double touchY = data.getDouble("touchY");
                    if(friendlyPlayers.get(playerId) != null) {
                        friendlyPlayers.get(playerId).setPosition(x.floatValue(), y.floatValue());
                        friendlyPlayers.get(playerId).setFirePos(touchX.floatValue(), touchY.floatValue());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray objects = (JSONArray) args[0];
                try {
                    for(int i = 0; i < objects.length(); i++) {
                        Vector2 pos = new Vector2();
                        Avatar coopPlayer = new Avatar(enemyTex);
                        pos.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
                        pos.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
                        coopPlayer.setPosition(pos.x, pos.y);
                        friendlyPlayers.put(objects.getJSONObject(i).getString("id"), coopPlayer);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updetaServer(float dt) {

        timer += dt;
        float UPDATE_TIME = 1 / 60f;
        if(timer >= UPDATE_TIME && player != null && (player.hasMoved())) {
            JSONObject data = new JSONObject();
            try {
                data.put("x", player.getX());
                data.put("y", player.getY());
                data.put("touchX", player.getFireposX());
                data.put("touchY", player.getFireposY());
                socket.emit("playerMoved", data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        elapsedTime += delta;
        handleInput(Gdx.graphics.getDeltaTime());
        updetaServer(Gdx.graphics.getDeltaTime());
        bullet.update();

        batch.begin();

        if(player != null) {
            player.draw(batch);
        } else {
            sprite.setRegion(connectAnimation.getKeyFrame(elapsedTime, true));
            sprite.draw(batch);
        }

        for(HashMap.Entry<String, Avatar> entry: friendlyPlayers.entrySet()) {
            entry.getValue().draw(batch);
        }

        batch.end();
    }

    private void handleInput(float deltaTime) {

        if(player != null) {
            if(Gdx.input.isTouched()) {
               bullet = new Bullet(player.getX(), player.getY(), Gdx.input.getX(), Gdx.input.getY());
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player.setPosition(player.getX() + (-200 * deltaTime), player.getY());
            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player.setPosition(player.getX() + (200 * deltaTime), player.getY());
            }
            if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
                game.setScreen(new MenuScreen(game));
            }
        }
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

        playerTex.dispose();
        enemyTex.dispose();
    }
}
