package org.angrypigs.game.online;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.angrypigs.game.AngryPigs;
import org.angrypigs.game.online.sprites.Bullet;
import org.angrypigs.game.online.sprites.Spaceship;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class JoinGame implements Screen {

    private Batch batch;
    private float timer;
    private Socket socket;
    private Bullet bullet;              /* THIS HAVE TO BE FIXED */
    private AngryPigs game;
    private Spaceship player;
    private Texture playerShip;
    private Texture friendlyShip;
    private final float UPDATE_TIME = 1 / 60f;
    private HashMap <String, Spaceship> friendlyPlayers;

    public JoinGame(AngryPigs g) {

        playerShip = new Texture("ship/playerShip2.png");
        friendlyShip = new Texture("ship/playerShip.png");
        bullet = new Bullet(new Texture("ship/bullet.png"));
        friendlyPlayers = new HashMap<String, Spaceship>();
        batch = new SpriteBatch();
        game = g;
        connectSocket();
        configSocketEvent();
    }

    private void connectSocket() {

        try {
            socket = IO.socket("http://192.168.31.56:8080");
            socket.connect();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void configSocketEvent() {

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {

                Gdx.app.log("SocketIO", "Connected");
                player = new Spaceship(playerShip);
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
                    friendlyPlayers.put(playerId, new Spaceship(friendlyShip));

                } catch (JSONException e) {

                    Gdx.app.log("SocketIO", String.valueOf(e));
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

                    Gdx.app.log("SocketIO", String.valueOf(e));
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
                        Spaceship coopPlayer = new Spaceship(friendlyShip);
                        pos.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
                        pos.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
                        coopPlayer.setPosition(pos.x, pos.y);
                        coopPlayer.setFirePos(0,0);

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
        if(timer >= UPDATE_TIME && player != null &&
                (player.hasMoved() || player.isFiring())) {

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

        handleInput(Gdx.graphics.getDeltaTime());
        updetaServer(Gdx.graphics.getDeltaTime());

        batch.begin();
        if(player != null) {
            player.draw(batch);
        }
        for(HashMap.Entry<String, Spaceship> entry: friendlyPlayers.entrySet()) {
            entry.getValue().draw(batch);
            entry.getValue().bullet.draw(batch);
        }
        batch.end();
    }

    private void handleInput(float deltaTime) {

        if(player != null) {

            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

                player.setPosition(player.getX() + (-200 * deltaTime), player.getY());

            } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

                player.setPosition(player.getX() + (200 * deltaTime), player.getY());
            }

            if(Gdx.input.isTouched()) {

                player.fire(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            } else {

                player.noFire();
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

        playerShip.dispose();
        friendlyShip.dispose();
    }
}
