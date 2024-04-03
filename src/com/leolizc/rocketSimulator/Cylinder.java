package com.leolizc.rocketSimulator;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;

public class Cylinder extends RigidBody {

    int numOfFaces = 50;
    float h = 100, r = 60;

    public Cylinder(Simulator p, PVector position) {
        super(p, 10, new PMatrix3D(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        ), position);
    }

    public Cylinder(Simulator p) {
        super(p, 10, new PMatrix3D(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1

        ), new PVector(0, 0, -200));
    }

    public Cylinder(Simulator p, float mass, PVector position) {
        super(p, mass, new PMatrix3D(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1

        ), position);
    }

    public Cylinder(Simulator p, float mass, PVector position, float h, float r) {
        super(p, mass, new PMatrix3D(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        ), position);
        this.h = h;
        this.r = r;
    }

    public void update() {
    }

    public void drawObject() {

        p.stroke(100, 100, 100);
        p.fill(200f);

        // Top circle
        p.beginShape();
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
}
