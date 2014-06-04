int wx, wy; //starting x and y for player window
int rows, cols; //number of rows or columns
int side; //side length of grid boxes
Gem[][] gemArray; //array of gems


void setup() {
  size (1000, 1000);  
  wx = 100;
  wy = 100;
  rows = 8;
  cols = 8;
  side = 100;
  gemArray = new Gem[8][8];  
  for (int x = 0; x < gemArray.length; x++) {
    for (int y = 0; y < gemArray[x].length; y++) {
      gemArray[x][y] = new Gem((int)random(8), x, y);
    }
  }
}

class Gem {

  int type;
  int xcor, ycor;
  int typeID;

  Gem() {
    xcor = 0; 
    ycor = 0;
    typeID = 0;
    gemArray[0][0] = this;
  }

  Gem(int type, int x, int y) {
    typeID = type;
    xcor = x;
    ycor = y;
    gemArray[x][y] = this;
  }

  int getXcor() {
    return xcor;
  }
  int getYcor() {
    return ycor;
  }
  int getTypeID() {
    return typeID;
  }

  void setXcor (int val) {
    xcor = val;
  }
  void setYcor (int val) {
    ycor = val;
  }

  color getColor () {
    color c = color(32 * typeID); 
    return c;
  }
}

void draw () {  
  for (int x = 0; x < cols; x++) {
    for (int y = 0; y < rows; y++) {
      fill (100, 168, 255);
      rect(wx + side * x, wy + side * y, side, side);
    }
  }  
  for (int x = 0; x < gemArray.length; x++) {
    for (int y = 0; y < gemArray[x].length; y++) {
      fill (gemArray[x][y].getColor());
      ellipse(wx + side/2 + gemArray[x][y].getXcor() * side, wy + side /2 +gemArray[x][y].getYcor() * side, side/2, side/2);
    }
  }
}

