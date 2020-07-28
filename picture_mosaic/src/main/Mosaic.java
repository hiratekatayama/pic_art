package main;

import processing.core.*;

public class Mosaic extends PApplet {
    PImage _img;
    float _noiseScale;

    @Override
    public void settings(){
        size(1000, 500);
    }

    @Override
    public void setup(){
        _img = loadImage("kobe.jpeg");
        _noiseScale =  (float) 0.00005;

        background(255);
    }

    @Override
    public void draw(){
        for (int i = 0; i < 100; i++) {
            float x = random(_img.width);
            float y = random(_img.height);
            int col = _img.get((int) x, (int) y);
            float noise_factor = noise(x * _noiseScale, y * _noiseScale);
            float lineLength = noise_factor * 4;

            pushMatrix();
            translate(x, y);
            rotate(noise_factor * radians(180));
            stroke(col);
            strokeWeight(8);
            line(0, 0, lineLength, lineLength);
            popMatrix();
        }
    }

    public void keyPressed(){
        if (keyCode == ENTER){
            saveFrame("picture_mosaic_####.png");
        }
        if (keyCode == BACKSPACE){
            setup();
        }
    }

    public static void main(String[] args) {
        PApplet.main("main.Mosaic");
    }
}
