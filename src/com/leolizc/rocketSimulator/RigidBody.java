package com.leolizc.rocketSimulator;

import processing.core.PVector;

public abstract class RigidBody extends WorldEntity{

    protected float mass;
    private PVector velocity;
    private PVector acceleration;
    private PVector cumulativeForce;

    public RigidBody(float mass, PVector position, PVector velocity, PVector acceleration){
        super(position);
        this.mass = mass;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.cumulativeForce = new PVector(0,0,0);
    }

    public void applyForce(PVector force){
        this.cumulativeForce.add(force);
    }

    public void updatePhysics(){
        this.acceleration = PVector.div(this.cumulativeForce, this.mass);
        this.velocity.add(this.acceleration);
        this.position.add(this.velocity);
        this.cumulativeForce = new PVector(0,0,0);
    }



}
