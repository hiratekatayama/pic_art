package test;


import processing.core.PApplet;

public class main extends PApplet {
    float _angnoise, _radiusnoise;
    float _xnoise, _ynoise;
    float _angle = -PI/2;
    float _radius;
    float _strokeCol = 254;
    int _strokeChange = -1;

    public void settings() {
        size(2000, 1000);
    }

    public void setup() {
        smooth();
        frameRate(30);
        background(255);
        noFill();

        _angnoise = random(10);
        _radiusnoise = random(10);
        _xnoise = random(10);
    }

    public void draw() {
        _radiusnoise += 0.005;
        _radius = (noise(_radiusnoise) * 550) + 1;

        _angnoise += 0.005;
        _angle += (noise(_radiusnoise) * 550) + 1;

        _angnoise += 0.005;
        _angle += (noise(_radiusnoise) * 6) - 3;
        if (_angle > 360)
            _angle -= 360;
        if (_angle < 0)
            _angle += 360;

        _xnoise += 0.01;
        _ynoise += 0.01;
        float centerx = width/2 + (noise(_xnoise) * 100) - 50;
        float centery = height/2 + (noise(_ynoise) * 100) -50;

        float rad = radians(_angle);
        float x1 = centerx + (_radius * cos(rad));
        float y1 = centery + (_radius * sin(rad));

        float opprad = rad + PI;
        float x2 = centerx + (_radius * cos(opprad));
        float y2 = centery + (_radius * sin(opprad));

        _strokeCol += _strokeChange;
        if (_strokeCol > 254)
            _strokeChange = -1;
        if (_strokeCol < 0)
            _strokeChange = 1;
        stroke(_strokeCol,60);
        strokeWeight(1);
        line(x1, y1, x2, y2);
    }

    public static void main(String[] args) {
        PApplet.main("test.main");
    }
}
