package com.leolizc.rocketSimulator;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Simulator extends PApplet {
    ArrayList<WorldEntity> entities = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("com.leolizc.rocketSimulator.Simulator");
    }

    public void settings() {
        size(600, 600, P3D);
    }

    @Override
    public void setup() {
        camera(0, 0, 1, 0, 0, -1, 0, 1, 0);
        perspective(PI / 3.0f, (float) width / height, 0.1f, 10000.0f);

        entities.add(new Cylinder(this));
        PerlinSea sea = new PerlinSea(this);
        sea.position.set(0, 100, -300);
        sea.setSegments(40);
        entities.add(sea);
    }

    private void render() {
        for (WorldEntity entity : entities) {
            entity.render();
        }
    }

    private void update() {
        for (WorldEntity entity : entities) {
            entity._update();
        }
    }

    @Override
    public void draw() {
        background(255f);
        update();
        render();
    }

    public void translate(PVector vector) {
        super.translate(vector.x, vector.y, vector.z);
    }
}
