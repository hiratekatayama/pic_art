import processing.core.PApplet;
import processing.opengl.*;

public class Main extends PApplet {
    float xstart, xnoise, ystart, ynoise;

    public void settings() {
        size(500, 300, OPENGL);
    }

    public void setup() {
        sphereDetail(40);
        background(0);
        sphereDetail(8);
        noStroke();

        xstart = random(10);
        ystart = random(10);
    }

    public void draw() {
        background(0);

        xstart += 0.01;
        ystart += 0.01;

        xnoise = xstart;
        ynoise = ystart;

        for (int y = 0; y <= height; y += 5) {
            ynoise += 0.1;
            xnoise = xstart;
            for (int x = 0; x <= width; x += 5) {
                xnoise += 0.1;
                drawPoint(x, y, noise(xnoise, ynoise));
            }
        }

    }

    private void drawPoint(int x, int y, float noiseFactor) {
        pushMatrix();
        translate(x, 250 - y, -y);
        float sphereSize = noiseFactor * 35;
        float grey = 150 + (noiseFactor * 120);
        float alph = 150 + (noiseFactor * 120);
        fill(grey, alph);
        sphere(sphereSize);
        popMatrix();
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}