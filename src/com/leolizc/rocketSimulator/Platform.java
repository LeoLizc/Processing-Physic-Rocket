package com.leolizc.rocketSimulator;

import processing.core.PVector;

public class Platform extends WorldEntity {
    int color;

    public Platform(Simulator p) {
        super(p);
        color = p.color(255, 0, 0);
    }

    public Platform(Simulator p, PVector position) {
        super(p, position);
        color = p.color(255, 0, 0);
    }

    @Override
    protected void update() {
    }

    @Override
    protected void drawObject() {
        p.noStroke();
        p.fill(color);
        p.box(100, 10, 100);
    }
}