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

    public RigidBody(Simulator p, float mass, PVector position) {
        super(p, position);
        this.mass = mass;
        this.velocity = new PVector(0, 0, 0);
        // By default, the acceleration is the gravity
        this.acceleration = new PVector(0, 9.8f, 0);
        this.cumulativeForce = new PVector(0, 0, 0);
    }

    public void applyForce(PVector force) {
        this.cumulativeForce.add(force);
    }

    @Override
    public void _update() {
        updatePhysics();
        update();
    }

    public void updatePhysics() {
        PVector acceleration = PVector.div(this.cumulativeForce, this.mass).add(this.acceleration);
        this.velocity.add(acceleration);
        this.position.add(PVector.mult(this.velocity, p.deltaTime*0.4f));
        this.cumulativeForce = new PVector(0, 0, 0);
        System.out.println("Position: "+this.position+"\nVelocity: "+this.velocity+"\nAcceleration: "+acceleration);
    }

    protected abstract void update();

}
