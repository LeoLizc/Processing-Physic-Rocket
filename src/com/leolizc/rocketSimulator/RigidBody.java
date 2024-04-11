package com.leolizc.rocketSimulator;

import com.jogamp.opengl.math.Quaternion;
import processing.core.PMatrix3D;
import processing.core.PVector;

public abstract class RigidBody extends WorldEntity {

    private final PVector velocity;
    private final PVector acceleration;
    private final PVector cumulativeTorque;
    public PVector angularVelocity;
    public PMatrix3D iInertiaTensor;
    public boolean blockLinearMovement = false;
    public boolean blockRotation = false;
    protected float mass;
    private PVector cumulativeForce;

    public RigidBody(
            Simulator p, float mass, PMatrix3D iInertiaTensor,
            PVector position, PVector velocity, PVector acceleration
    ) {
        super(p, position);
        this.mass = mass;
        this.velocity = velocity;
        this.angularVelocity = new PVector(0, 0, 0);
        this.acceleration = acceleration;
        this.cumulativeForce = new PVector(0, 0, 0);
        this.cumulativeTorque = new PVector(0, 0, 0);
        this.iInertiaTensor = iInertiaTensor;
    }

    public RigidBody(Simulator p, float mass, PMatrix3D iInertiaTensor, PVector position) {
        super(p, position);
        this.mass = mass;
        this.velocity = new PVector(0, 0, 0);
        this.angularVelocity = new PVector(0, 0, 0);
        // By default, the acceleration is the gravity
        this.acceleration = new PVector(0, 9.8f, 0);
        this.cumulativeForce = new PVector(0, 0, 0);
        this.cumulativeTorque = new PVector(0, 0, 0);
        this.iInertiaTensor = iInertiaTensor;
    }

    public void applyTorque(PVector torque) {
        this.cumulativeTorque.add(torque);
    }

    public void applyForce(PVector force) {
        this.cumulativeForce.add(force);
    }

    public void applyRelativeForce(PVector force, PVector position) {

//        Convert position into quaternion
        Quaternion p = new Quaternion(
                force.x,
                force.y,
                force.z,
                0
        );

//        Rotate q by the current rotation
        Quaternion q = new Quaternion(this.rotation), qn = new Quaternion(this.rotation);
        qn.conjugate();
        p = q.mult(p).mult(qn);

        this.cumulativeForce.add(new PVector(p.getX(), p.getY(), p.getZ()));
        this.cumulativeTorque.add(position.copy().cross(force));
    }

    @Override
    public void _update() {
        updatePhysics();
        update();
    }

    public void updatePhysics() {


        PVector acceleration = PVector.div(this.cumulativeForce, this.mass).add(this.acceleration);
        this.velocity.add(acceleration);
        if (!this.blockLinearMovement)
            this.position.add(PVector.mult(this.velocity, p.deltaTime * 0.4f));
        this.cumulativeForce = new PVector(0, 0, 0);
//        System.out.println("LINEAR MOVEMENT:");
//        System.out.println("Position: "+this.position+"\nVelocity: "+this.velocity+"\nAcceleration: "+acceleration);

//        Angular Acceleration = I^-1 * T
        PVector angAcc = new PVector(0, 0, 0);
        angAcc = this.iInertiaTensor.mult(this.cumulativeTorque, angAcc);
        this.angularVelocity.add(angAcc);
        if (!this.blockRotation)
            this.rotateByAxis(
                    this.angularVelocity.mag() * p.deltaTime,
                    this.angularVelocity
            );
        System.out.println("ANGULAR MOVEMENT:");
        System.out.println("Torque: " + this.cumulativeTorque);
        System.out.println("Angular Velocity: " + this.angularVelocity + "\nAngular Acceleration: " + angAcc + "\nRotation: " + this.rotation);
        this.cumulativeTorque.set(0, 0, 0);

    }

    protected abstract void update();

}
