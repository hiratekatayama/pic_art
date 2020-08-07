import processing.core.PApplet;

public class Main4 extends PApplet {
    float xstart, xnoise, ystart, ynoise;

    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        smooth();
        background(0);
        frameRate(30);

        xstart = random(10);
        ystart = random(10);

    }

    private void drawPoint3(int x, int y, float noiseFactor) {
        pushMatrix();
        translate(x, y);
        rotate(noiseFactor * radians(540));
        float edgeSize = noiseFactor * 35;
        float grey = 150 + (noiseFactor * 120);
        float alph = 150 + (noiseFactor * 120);
        noStroke();
        fill(grey, alph);
        ellipse(0, 0, edgeSize, edgeSize/2);
        popMatrix();
    }

    private void drawPoint2(int x, int y, float noiseFactor) {
        float len = 10 * noiseFactor;
        rect(x, y, len, len);
    }

    private void drawPoint(int x, int y, float noiseFactor) {
        pushMatrix();
        translate(x, y);
        rotate(noiseFactor * radians(360));
        stroke(1, 250);
        line(0, 0, 20, 0);
        popMatrix();
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
                drawPoint2(x, y, noise(xnoise, ynoise));
                drawPoint3(x, y, noise(xnoise, ynoise));
                drawPoint(x, y, noise(xnoise, ynoise));
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main4");
    }
}
