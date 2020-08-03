package main;

import processing.core.*;

import java.util.ArrayList;

public class Mosaic extends PApplet {
    FlowField flowfield;
    ArrayList<Particle> particles;

    @Override
    public void settings(){
        size(1000, 500);
    }

    @Override
    public void setup(){
        flowfield = new FlowField(10);

        background(255);
    }

    @Override
    public void draw(){

    }

    public void keyPressed(){
        if (keyCode == ENTER){
            saveFrame("picture_####.png");
        }
        if (keyCode == BACKSPACE){
            setup();
        }
    }

    public static void main(String[] args) {
        PApplet.main("main.Mosaic");
    }
}
