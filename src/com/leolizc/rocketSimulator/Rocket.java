package com.leolizc.rocketSimulator;

import processing.core.PVector;

public class Rocket extends RigidBody{
    public Rocket(Simulator p, float mass, PVector position, PVector velocity, PVector acceleration) {
        super(p, mass, position, velocity, acceleration);
    }

    @Override
    protected void drawObject() {

    }

    @Override
    protected void update() {

    }
}
