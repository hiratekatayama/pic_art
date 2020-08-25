import processing.core.PApplet;

public class main extends PApplet {
    float x, y;
    float bar_x;

    public void setting() {
        size(900, 900);
    }

    public void setup() {
    }

    public void draw() {
        background(0);
        x = width/2;
        y = height/2;
        function_1();
        bar_function();
    }

    public void bar_function() {
        //mouseを押すと動く
        if (mousePressed) {
            bar_x = mouseX;
            stroke(255, 10);
            fill(255, 50);
            rect(bar_x-50, 880, 100, 10);
            fill(255);
            textSize(20);
            text("mouseX_location", 720, 30);//xの位置
            text(mouseX, 720, 50);
        }
    }

    public void function_1() {
        pushMatrix();
        translate(x, y);
        float count_n = 1000;
        for (int i = 0; i < count_n; i = i + 3) {
            rotate(PI/bar_x*3);
            strokeWeight(2);
            stroke(map(mouseY, 0, height, 0, 255), map(i, 0, count_n, 0, 25), map(mouseX, 0, width, 255, 0));
            float y1 = (float) (i * 0.56);
            float x2 = (float) (i * 0.1);
            float y2 = (float) (i * 0.1);
            line(sin(radians(i)) + cos(radians(i)) / width, y1, x2, y2);
            strokeWeight(2);
            stroke(map(mouseX, 0, width, 255, 0), 200, map(i, 0, count_n, 200, 100));
            fill(map(mouseX, 0, width, 255, 0), 200, map(i, 0, count_n, 200, 100));
            ellipse((sin(radians(i)) + cos(radians(i)))/width, y1, 5, 5);
        }
        popMatrix();
    }

    public void keyPressed() {
        if (key==ENTER) {
            saveFrame("a1.png");
        }
    }

    public static void main(String[] args) {
        PApplet.main("main");
    }
}
