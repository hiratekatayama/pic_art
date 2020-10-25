//あるの群れと群集アルゴリズム

//リアルな群れのシミュレーションをコードで書くには，3つのルールで作成できる
// 1. 分離 - 近くの個体を避けて進む
// 2. 整列 - 近くの個体の平均の進行方向に向かって進む
// 3. 統合 - 近くの個体の平均位置に向かって進む

import processing.core.PApplet;

public class GOL extends PApplet{
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
        boolean state;
        boolean nextState;
        Cell[] neighbours;

        Cell(float ex, float why) {
            x = ex * _cellSize;
            y = why * _cellSize;
            if (random(2) > 1) {
                nextState = true;
            } else {
                nextState = false;
            }
            state = nextState;
            neighbours = new Cell[0];
        }

        void addNeighbour(Cell cell) {
            neighbours = (Cell[])append(neighbours, cell);
        }

        void calcNextState() {
            int liveCount = 0;
            for (int i = 0; i < neighbours.length; i++) {
                if (neighbours[i].state == true) {
                    liveCount++;
                }
            }

            if (state == true) {
                if ((liveCount == 2) || (liveCount == 3)) {
                    nextState = true;
                } else {
                    nextState = false;
                }
            } else {
                if (liveCount == 3) {
                    nextState = true;
                } else {
                    nextState = false;
                }
            }
        }

        void drawMe() {
            state = nextState;
            stroke(0);
            if (state == true) {
                fill(0);
            } else {
                fill(255);
            }
            ellipse(x, y, _cellSize, _cellSize);
        }
    }

    public static void main(String[] args) {
        PApplet.main("GOL");
    }
}
