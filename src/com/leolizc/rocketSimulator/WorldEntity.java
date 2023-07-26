package com.leolizc.rocketSimulator;

import processing.core.PVector;

public abstract class WorldEntity {

    public PVector position;
    public PVector scale;

    public WorldEntity(PVector position){
        this.position = position;
        this.scale = new PVector(1,1,1);
    }

    public abstract void draw();
    public abstract void update();

    public void setScale(float x, float y, float z){
        this.scale = new PVector(x,y,z);
    }
}
