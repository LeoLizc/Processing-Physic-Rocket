package com.leolizc.rocketSimulator;

import processing.core.PApplet;
import processing.core.PVector;

public class Rocket extends RigidBody{
    public Rocket(Simulator p, float mass, PVector position, PVector velocity, PVector acceleration) {
        super(p, mass, position, velocity, acceleration);
    }

    public Rocket(Simulator p){
        super(p, 10, new PVector(0, -200, -200));
    }

    @Override
    protected void drawObject() {
        int numOfFaces=50;
        float h = 100, r = 8;

        p.stroke(100, 100, 100);
        p.fill(200f);

//        this.applyForce(new PVector(0, -9.8f/12, 0));

        // Top circle
        p.beginShape();
//        p.stroke(0 ,0, 255);
//        p.fill(200f);
        for (int i = 0; i < numOfFaces; i++) {
            float angle = PApplet.map(i, 0, numOfFaces, 0, p.TWO_PI);
            p.vertex(r * PApplet.cos(angle), -h / 2f, r * PApplet.sin(angle));
        }
        p.endShape(p.CLOSE);

        //Cylindrical body
        p.beginShape(p.QUAD_STRIP);
        for (int i = 0; i <= numOfFaces; i++) {
            float angle = PApplet.map(i, 0, numOfFaces, 0, p.TWO_PI);

            p.vertex(r * PApplet.cos(angle), -h / 2f, r * PApplet.sin(angle));
            p.vertex(r * PApplet.cos(angle), h / 2f, r * PApplet.sin(angle));
        }
        p.endShape();

        // Bottom circle
        p.beginShape(p.TRIANGLE_FAN);
        p.vertex(0, h / 2f, 0);
        for (int i = 0; i <= numOfFaces; i++) {
            float angle = PApplet.map(i, 0, numOfFaces, 0, p.TWO_PI);
            p.vertex(r * PApplet.cos(angle), h / 2f, r * PApplet.sin(angle));
        }
        p.endShape();
    }

    @Override
    protected void update() {

    }
}
