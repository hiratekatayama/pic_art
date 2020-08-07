import processing.core.PApplet;

public class Main2 extends PApplet {
    float xstart, xnoise, ynoise;

    public void settings() {
        size(1000, 500);
    }

    public void setup() {
        smooth();
        background(255);
        xstart = xstart;
        ynoise = random(10);
        for (int y = 0; y <= height; y += 5) {
            ynoise += 0.1;
            xnoise = xstart;
            for (int x = 0; x <= width; x += 5) {
                xnoise += 0.1;
                drawPoint(x, y, noise(xnoise, ynoise));
                //drawPoint2(x, y, noise(xnoise, ynoise));
                drawPoint3(x, y, noise(xnoise, ynoise));
            }
        }
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
        stroke(0, 150);
        line(0, 0, 20, 0);
        popMatrix();
    }

    public void draw() {

    }

    public static void main(String[] args) {
        PApplet.main("Main2");
    }
}
