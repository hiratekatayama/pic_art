//ブライアンの脳は，3状態のセル・オートマトンです．
//セルにオン・オフだけでなく，もう一つの第3条の条件があります．
//ブライアンの脳のCAは，「発火」「休息」「オフ」の3種類です．
//セルは，脳のニューロンの動きのように，再び発火する前に休息します．

//もし状態が発火なら，次の状態は休息
//もし状態が休息なら，次の状態はオフ
//もし状態がオフで，2個の隣接セルが今まさに発火していれば，そのセルは発火する

import processing.core.PApplet;

public class BriantheBrain extends PApplet{
    Cell[][] _cellArray;
    int _cellSize = 10;
    int _numX, _numY;

    @Override
    public void settings() {
        size(2000, 2000);
    }

    @Override
    public void setup() {
        _numX = floor(width/_cellSize);
        _numY = floor(height/_cellSize);
        restart();
    }


    @Override
    public void draw() {
        background(200);

        //まず次の状態を計算
        for (int x = 0; x < _numX; x++) {
            for (int y = 0; y < _numY; y++) {
                _cellArray[x][y].calcNextState();
            }
        }

        translate(_cellSize/2, _cellSize/2);

        //すべえのセルを描く
        for (int x = 0; x < _numX; x++) {
            for (int y = 0; y < _numY; y++) {
                _cellArray[x][y].drawMe();
            }
        }
    }

    @Override
    public void mousePressed() {
        restart();
    }

    void restart() {
        //セルのグリッドを作る
        _cellArray = new Cell[_numX][_numY];
        for (int x = 0; x < _numX; x++) {
            for (int y = 0; y < _numY; y++) {
                Cell newCell = new Cell(x, y);
                _cellArray[x][y] = newCell;
            }
        }

        //オブジェクトごとにその隣接セルを教えるループ
        for (int x = 0; x < _numX; x++) {
            for (int y = 0; y < _numY; y++) {
                //上下左右の位置を設定
                int above = y - 1;
                int below = y + 1;
                int left = x - 1;
                int right = x + 1;

                //画面の端の位置をつなげる
                if (above < 0) {
                    above = _numY - 1;
                }
                if (below == _numY) {
                    below = 0;
                }
                if (left < 0) {
                    left = _numX - 1;
                }
                if (right == _numX) {
                    right = 0;
                }

                //参照したものを周囲に渡す
                _cellArray[x][y].addNeighbour(_cellArray[left][above]);
                _cellArray[x][y].addNeighbour(_cellArray[left][y]);
                _cellArray[x][y].addNeighbour(_cellArray[left][below]);
                _cellArray[x][y].addNeighbour(_cellArray[x][below]);
                _cellArray[x][y].addNeighbour(_cellArray[right][below]);
                _cellArray[x][y].addNeighbour(_cellArray[right][y]);
                _cellArray[x][y].addNeighbour(_cellArray[right][above]);
                _cellArray[x][y].addNeighbour(_cellArray[x][above]);
            }
        }
    }

    class Cell {
        float x, y;
        int state;
        int nextState;
        Cell[] neighbours;

        Cell(float ex, float why) {
            x = ex * _cellSize;
            y = why * _cellSize;
            nextState = (int) random(2);
            state = nextState;
            neighbours = new Cell[0];
        }

        void addNeighbour(Cell cell) {
            neighbours = (Cell[])append(neighbours, cell);
        }

        void calcNextState() {
            if (state == 0) {
                int firingCount = 0;
                for (int i = 0; i < neighbours.length; i++) {
                    if (neighbours[i].state == 1) {
                        firingCount++;
                    }
                }
                if (firingCount == 2) {
                    nextState = 1;
                } else {
                    nextState = state;
                }
            } else if (state == 1) {
                nextState = 2;
            } else if (state == 2) {
                nextState = 0;
            }
        }

        void drawMe() {
            state = nextState;
            stroke(0);
            if (state == 1) {
                fill(0);
            } else if (state == 2) {
                fill(150);
            } else {
                fill(255);
            }
            ellipse(x, y, _cellSize, _cellSize);
        }
    }

    public static void main(String[] args) {
        PApplet.main("BriantheBrain");
    }
}
