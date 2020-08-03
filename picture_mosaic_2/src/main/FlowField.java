package main;

import processing.core.PVector;

import static java.lang.Math.floor;
import static processing.core.PApplet.radians;

public class FlowField {
    PVector[] vectors;
    int cols, rows;
    float inc = (float) 0.1;
    float zoff = 0;
    int scl;

    FlowField(int res) {
        scl = res;
        cols = floor(width / res) + 1;
        rows = floor(height / res) + 1;
        vectors = new PVector[cols * rows];
    }

    void update() {
        float xoff = 0;
        for (int y = 0; y < rows; y++) {
            float yoff = 0;
            for (int x = 0; x < cols; x++) {
                float angle = noise(xoff, yoff, zoff) * radians(180);

                PVector v = PVector.fromAngle(angle);
                v.setMag(1);
                int index = x + y * cols;
                vectors[index] = v;

                xoff += inc;
            }
            yoff += inc;
        }
        zoff += 0.004;
    }
}
