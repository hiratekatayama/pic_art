import processing.core.PApplet;

public class Main extends PApplet {
    float xstart, xnoise, ynoise;

    public void settings() {
        size(300, 300);
    }

    public void setup() {
        smooth();
        background(255);
        xstart = random(10);
        xnoise = xstart;
        ynoise = random(10);
        for (int y = 0; y <= height; y += 5) {
            ynoise += 0.1;
            xnoise = xstart;
            for (int x = 0; x <= width; x += 5) {
                xnoise += 0.1;
                drawPoint(x, y, noise(xnoise, ynoise));
            }
        }
    }

    private void drawPoint(float x, float y, float noiseFactor) {
        float len = 10 * noiseFactor;
        rect(x, y, len, len);
    }

    public void draw() {
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
