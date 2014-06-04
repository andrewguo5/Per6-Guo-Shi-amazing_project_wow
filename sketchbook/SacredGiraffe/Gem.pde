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
