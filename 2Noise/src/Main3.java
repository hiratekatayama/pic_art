import processing.core.PApplet;

public class Main3 extends PApplet {
    float xstart, xnoise, ystart, ynoise;

    public void settings() {
        size(300, 300);
    }

    public void setup() {
        smooth();
        background(0);
        frameRate(24);

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
        translate(x, y);
        rotate(noiseFactor * radians(540));
        noStroke();
        float edgeSize = noiseFactor * 35;
        float grey = 150 + (noiseFactor * 120);
        float alph = 150 + (noiseFactor * 120);
        fill(grey, alph);
        ellipse(0, 0, edgeSize, edgeSize/2);
        popMatrix();
    }

    public static void main(String[] args) {
        PApplet.main("Main3");
    }
}
