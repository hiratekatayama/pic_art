package main;

import processing.core.PVector;

import static processing.core.PApplet.floor;

//画面に格子状に配置されるベクターを定義する
public class Particle {
    PVector pos;
    PVector vel;
    PVector acc;
    PVector previousPos;
    float maxSpeed;

    Particle(PVector start, float maxspeed) {
        maxSpeed = maxspeed;
        pos = start;
        vel = new PVector(0, 0);
        acc = new PVector(0, 0);
        previousPos = pos.copy();
    }

    public void run() {
        update();
        edges();
        show();
    }

    public void update() {
        pos.add(vel);
        vel.limit(maxSpeed);
        vel.add(acc);
        acc.mult(0);
    }

    public void applyForce(PVector force) {
        acc.add(force);
    }

    private void show() {
        stroke(10, 2);
        strokeWeight(1);
        line(pos.x, pos.y, previousPos.x, previousPos.y);
        updatePreviousPos();
    }

    private void edges() {
        float height;

        if (pos.x > width) {
            pos.x = 0;
            updatePreviousPos();
        }
        if (pos.x < 0) {
            pos.x = width;
            updatePreviousPos();
        }
        if (pos.y > height) {
            pos.y = 0;
            updatePreviousPos();
        }
        if (pos.y < 0) {
            pos.y = height;
            updatePreviousPos();
        }
    }

    private void updatePreviousPos() {
        this.previousPos.x = pos.x;
        this.previousPos.y = pos.y;
    }

    private void follow(FlowField flowfield) {
        int x = floor(pos.x / flowfield.scl);
        int y = floor(pos.y / flowfield.scl);
        int index = x + y * flowfield.cols;

        PVector force = flowfield.vectors[index];
        applyForce(force);
    }
}
