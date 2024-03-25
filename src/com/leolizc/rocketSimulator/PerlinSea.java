package com.leolizc.rocketSimulator;

import processing.core.PVector;

public class PerlinSea extends WorldEntity {
    float scale, diff = 100;
    float intensity, velocity, rotation;
    private float size, segments, step;

    public PerlinSea(Simulator p) {
        super(p);
        init();
    }

    public PerlinSea(
            Simulator p,
            float size
    ) {
        super(p);
        init(size);
    }

    public PerlinSea(
            Simulator p,
            float size,
            float segments
    ) {
        super(p);
        init(size, segments);
    }

    public PerlinSea(
            Simulator p,
            float size,
            float segments,
            float scale,
            float intensity
    ) {
        super(p);
        init(size, segments, scale, intensity);
    }

    private void init() {
        init(450);
    }

    private void init(
            float size
    ) {
        init(size, 20);
    }

    private void init(
            float size,
            float segments
    ) {
        init(size, segments, 0.02f, 30);
    }

    private void init(
            float size,
            float segments,
            float scale,
            float intensity
    ) {
        this.size = size;
        this.segments = segments;
        this.scale = scale;
        this.intensity = intensity;
        velocity = Simulator.HALF_PI / 1440f;
        step = size / segments;
        rotation = 0;
    }

    public void drawObject() {

        p.rotateX(p.PI / 2f);// 90 degrees
        p.noFill();
        p.stroke(30, 30, 250);

        // 20x20 squares From x = -200 to 200, draw a line from y = -200 to 200
        float halfSize = size / 2f;

        p.beginShape();
        horizontalLine(halfSize);
        for (float y = halfSize; y > -halfSize; y -= step) {
            for (float x = halfSize; x >= -halfSize; x -= step) {
                vertex(x, y);
                vertex(x, y - step);
            }
            horizontalLine(y - step);
        }
        vertex(halfSize, -halfSize);
        p.endShape();

    }

    public void update() {
        rotation = (rotation + velocity) % Simulator.TWO_PI;
    }

    private void horizontalLine(float y) {
        float halfSize = size / 2f;
        for (float x = -halfSize; x < halfSize; x += step) {
            vertex(x, y);
        }
    }

    private float noise(float x, float y) {
        PVector perlinPoint = getPerlinPoint(x, y);
        return p.noise(perlinPoint.x * scale, perlinPoint.y * scale);
    }

    private PVector getPerlinPoint(float x, float y) {
        float xOff = diff + size / 2f;
        return (new PVector(x + xOff, y + xOff)).rotate(rotation);
    }

    private void vertex(float x, float y) {

        p.vertex(x, y, noise(x, y) * intensity);
    }

    public float getSize() {
        return size;
    }

    // GETTERS AND SETTERS

    public void setSize(float size) {
        this.size = size;
    }

    public float getSegments() {
        return segments;
    }

    public void setSegments(float segments) {
        this.segments = segments;
        step = size / segments;
    }


}

