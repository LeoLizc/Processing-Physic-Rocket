package com.leolizc.rocketSimulator;

import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;

public class Rocket extends RigidBody{
    public Rocket(Simulator p, float mass, PVector position, PVector velocity, PVector acceleration) {
        super(p, mass, new PMatrix3D(
                (float)(1f/(0.25*mass*(Math.pow(100,2)/3 + Math.pow(8,2)))), 0, 0, 0,
                0, (float)(1/(0.5*mass*Math.pow(8, 2))), 0, 0,
                0, 0, (float)(1f/(0.25*mass*(Math.pow(100,2)/3f + Math.pow(8,2)))), 0,
                0, 0, 0, 1
        ), position, velocity, acceleration);
    }

    public Rocket(Simulator p){
        super(p, 10,
                new PMatrix3D(
                        (float)(1f/(0.25*10*(Math.pow(100,2)/3 + Math.pow(8,2)))), 0, 0, 0,
                        0, (float)(1/(0.5*10*Math.pow(8, 2))), 0, 0,
                        0, 0, (float)(1f/(0.25*10*(Math.pow(100,2)/3f + Math.pow(8,2)))), 0,
                        0, 0, 0, 1
                ),
                new PVector(0, -200, -200));
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
        keyInteract();
    }

    private void keyInteract(){
        if(p.keyPressed){
            if(p.pressedKey('x')){
                System.out.println("x pressed");
                this.applyRelativeForce(new PVector(0, -200, 0), new PVector(0,0, 0));
            }
            if(p.pressedKey('z')){
                System.out.println("z pressed");
                PVector fD = (new PVector(0, -1)).rotate(p.PI/10f);//Force direction
                System.out.println(fD);
                this.applyRelativeForce((new PVector(fD.x, fD.y, 0)).mult(50), new PVector(-4, -35, 0));
//                this.applyForce((new PVector(fD.x, fD.y, 0)).mult(-100));
            }
            if(p.pressedKey('c')) {
                System.out.println("c pressed");
                PVector fD = (new PVector(0, -1)).rotate(-p.PI / 10f);//Force direction
                System.out.println(fD);
                this.applyRelativeForce((new PVector(fD.x, fD.y, 0)).mult(50), new PVector(4, -35, 0));
            }
        }
    }
}
