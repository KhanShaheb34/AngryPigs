package org.angrypigs.game.Sprites;

import com.badlogic.gdx.physics.box2d.*;

public class Ground {

    public Ground(World world, int width, int height) {

        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(width, height);

        Body ground = world.createBody(groundDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        ground.createFixture(fixtureDef);
    }
}
