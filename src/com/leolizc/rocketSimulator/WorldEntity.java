package com.leolizc.rocketSimulator;

import com.jogamp.opengl.math.Quaternion;
import processing.core.PVector;

public abstract class WorldEntity {

    public Simulator p;
    public PVector position;
    public PVector scale;
    public Quaternion rotation;

    public WorldEntity(Simulator p, PVector position) {
        this.p = p;
        this.position = position;
        this.scale = new PVector(1, 1, 1);
        this.rotation = new Quaternion();
    }

    public WorldEntity(Simulator p) {
        this.p = p;

        this.position = new PVector(0, 0, 0);
        this.scale = new PVector(1, 1, 1);
        this.rotation = new Quaternion();
    }

    public void render() {
        p.pushMatrix();
        p.translate(position);
        p.scale(scale.x, scale.y, scale.z);
        // Rotate the object
        float[] axis = new float[3]; // axis of rotation
        float angle = rotation.toAngleAxis(axis); // angle of rotation
        p.rotate(angle, axis[0], axis[1], axis[2]);

        drawObject();
        p.popMatrix();
    }

    protected abstract void drawObject();

    protected abstract void update();

    public void _update() {
        update();
    }

    public void setScale(float x, float y, float z) {
        this.scale = new PVector(x, y, z);
    }

    public void translate(PVector vector) {
        this.position.add(vector);
    }

    public void scale(PVector vector) {
        this.scale.add(vector);
    }

    public void rotate(float angle, PVector vector) {
        vector = vector.normalize();
        this.rotation.rotateByAngleNormalAxis(angle, vector.x, vector.y, vector.z);
    }
}
