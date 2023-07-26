package com.leolizc.rocketSimulator;

import processing.core.PVector;

public abstract class WorldEntity {

    public Simulator p;
    public PVector position;
    public PVector scale;

    public WorldEntity(Simulator p, PVector position) {
        this.p = p;

        this.position = position;
        this.scale = new PVector(1, 1, 1);
    }

    public WorldEntity(Simulator p) {
        this.p = p;

        this.position = new PVector(0, 0, 0);
        this.scale = new PVector(1, 1, 1);
    }

    public abstract void render();

    public abstract void update();

    public void setScale(float x, float y, float z) {
        this.scale = new PVector(x, y, z);
    }
}
