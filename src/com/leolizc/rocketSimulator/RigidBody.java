package com.leolizc.rocketSimulator;

import processing.core.PVector;

public abstract class RigidBody extends WorldEntity {

    private final PVector velocity;
    protected float mass;
    private PVector acceleration;
    private PVector cumulativeForce;

    public RigidBody(Simulator p, float mass, PVector position, PVector velocity, PVector acceleration) {
        super(p, position);
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.cumulativeForce = new PVector(0, 0, 0);
    }

    public void applyForce(PVector force) {
        this.cumulativeForce.add(force);
    }

    public void update() {
        updatePhysics();
        updateObject();
    }

    public void updatePhysics() {
        this.acceleration = PVector.div(this.cumulativeForce, this.mass);
        this.velocity.add(this.acceleration);
        this.position.add(this.velocity);
        this.cumulativeForce = new PVector(0, 0, 0);
    }

    public abstract void updateObject();

}
