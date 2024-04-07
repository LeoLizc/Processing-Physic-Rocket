package com.leolizc.rocketSimulator;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.HashMap;

public class Simulator extends PApplet {
    ArrayList<WorldEntity> entities = new ArrayList<>();
    Camera camera;
    public float deltaTime=0;
    private float lastTime=0;
    private HashMap<Character, Boolean> pressedKeys = new HashMap<>();

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
        Rocket rocket = new Rocket(this);
//        rocket.blockLinearMovement = true;
        entities.add(rocket);

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

        // Add a red Cube with blue edges
        /*PVector cubeRotationAxis = new PVector(.8f, 1, .8f);
        entities.add(new WorldEntity(
                this,
                new PVector(0, 0, -200)
        ) {

            boolean toggleAxis = false;
            @Override
            protected void drawObject() {
                p.stroke(0, 0, 255);
                p.strokeWeight(5);

                PVector lineDirection = cubeRotationAxis.copy().normalize();
                PVector lineStart = lineDirection.copy().mult(50);
                PVector lineEnd = lineDirection.copy().mult(-150);

                p.line(lineStart.x, lineStart.y, lineStart.z, lineEnd.x, lineEnd.y, lineEnd.z);
                p.strokeWeight(1);

                p.fill(255, 0, 0);
                p.stroke(0, 0, 255);
                p.box(50);
            }

            @Override
            protected void update() {
                if(keyPressed){
                    if(key == 't') {
                        this.toggleAxis = !this.toggleAxis;
                    }
                }

                if(this.toggleAxis) {
                    this.rotateByGlobalAxis(0.01f, cubeRotationAxis);
                } else {
                    this.rotateByAxis(0.01f, cubeRotationAxis);
                }
            }
        });

        entities.get(entities.size()-1).rotateByGlobalAxis((float)(QUARTER_PI/2), new PVector(1, 0, 0));

        // Add a green line in y-axis in the middle of the cube
        entities.add(new WorldEntity(
                this,
                new PVector(0, 0, -200)
        ) {
            @Override
            protected void drawObject() {
                p.stroke(0, 255, 0);
                p.strokeWeight(5);

                PVector lineDirection = cubeRotationAxis.copy().normalize();
                PVector lineStart = lineDirection.copy().mult(50);
                PVector lineEnd = lineDirection.copy().mult(-150);

                p.line(lineStart.x, lineStart.y, lineStart.z, lineEnd.x, lineEnd.y, lineEnd.z);
                p.strokeWeight(1);
            }

            @Override
            protected void update() {
            }
        });*/
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
        pressedKeys.put(key, true);
        camera.startMove(key);
    }

    public void keyReleased() {
        camera.stopMove(key);
        pressedKeys.put(key, false);
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

    public boolean pressedKey(char key){
        return (keyPressed && this.key == key) || pressedKeys.getOrDefault(key, false);
    }
}
