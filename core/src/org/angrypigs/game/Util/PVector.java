package org.angrypigs.game.Util;

import com.badlogic.gdx.math.Vector2;

public class PVector {
    public float x;
    public float y;
    public Vector2 vector;

    public PVector(float x, float y) {
        this.x = x;
        this.y = y;
        setVector(x, y);
    }

    public PVector(Vector2 vector) {
        this.vector = vector;
        setXY(vector);
    }

    public void add(PVector v) {
        this.x += v.x;
        this.y += v.y;
        setVector(this.x, this.y);
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
        setVector(this.x, this.y);
    }

    public void sub(PVector v) {
        this.x -= v.x;
        this.y -= v.y;
        setVector(this.x, this.y);
    }

    public void sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        setVector(this.x, this.y);
    }

    public void mult(float n) {
        this.x *= n;
        this.y *= n;
        setVector(this.x, this.y);
    }

    public void div(float n) {
        this.x /= n;
        this.y /= n;
        setVector(this.x, this.y);
    }

    public float mag() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public void norm() {
        float m = mag();
        if(m!=0) div(m);
    }

    private void setVector(float x, float y) {
        this.vector = new Vector2(x, y);
    }

    private void setXY(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void limit(float topspeed){
        if(mag() > topspeed){
            norm();
            mult(topspeed);
        }

    }

    @Override
    public String toString() {
        return "PVector {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
