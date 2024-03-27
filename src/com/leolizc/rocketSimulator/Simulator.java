package com.leolizc.rocketSimulator;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Simulator extends PApplet {
    ArrayList<WorldEntity> entities = new ArrayList<>();
    Camera camera;
    public float deltaTime=0;
    private float lastTime=0;

    public static void main(String[] args) {
        PApplet.main("com.leolizc.rocketSimulator.Simulator");
    }

    public void settings() {
        size(600, 600, P3D);
    }

    @Override
    public void setup() {
//        camera(0, 0, 1, 0, 0, -1, 0, 1, 0);
//        perspective(PI / 3.0f, (float) width / height, 0.1f, 10000.0f);
        lastTime=millis();

        camera = new Camera(this, null);
        camera.position.set(0, 0, -200);

//        entities.add(new Cylinder(this));
        entities.add(new Rocket(this));

        PerlinSea sea = new PerlinSea(this, 1000, 80);
        sea.position.set(0, 100, -300);
        sea.intensity = 30f;
//        sea.scaleP = sea.scaleP/2;
//        sea.setSegments(40);


        entities.add(sea);
        entities.add(new Platform(
                this,
                new PVector(0, 50, -200))
        );
    }

    private void render() {
        for (WorldEntity entity : entities) {
            entity.render();
        }
    }

    private void update() {
        deltaTime = (millis()-lastTime)/1000f;
        lastTime=millis();
        camera.updateCamera();
        for (WorldEntity entity : entities) {
            entity._update();
        }
    }

    @Override
    public void draw() {
//        background(255f);
        update();
        camera.processCamera();
        render();
    }

    public void mouseDragged() {
        camera.rotate();
    }

    public void mousePressed() {
        camera.startRotation();
    }

    public void keyPressed() {
        camera.startMove(key);
    }

    public void keyReleased() {
        camera.stopMove(key);
    }

    public void translate(PVector vector) {
        super.translate(vector.x, vector.y, vector.z);
    }

    public void scale(PVector vector) {
        super.scale(vector.x, vector.y, vector.z);
    }

    public void rotate(PVector vector) {
        super.rotateX(vector.x);
        super.rotateY(vector.y);
        super.rotateZ(vector.z);
    }

    public void rotate(float angle, PVector vector) {
        super.rotate(angle, vector.x, vector.y, vector.z);
    }
}
