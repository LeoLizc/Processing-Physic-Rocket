package com.leolizc.rocketSimulator;

import processing.core.PApplet;
import processing.core.PVector;

public class Simulator extends PApplet {
    public static void main(String[] args) {
        PApplet.main("com.leolizc.rocketSimulator.Main");
    }

    public void settings() {
        size(600, 600, P3D);
    }

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void draw() {
        super.draw();
    }

    public void translate(PVector vector) {
        super.translate(vector.x, vector.y, vector.z);
    }
}
