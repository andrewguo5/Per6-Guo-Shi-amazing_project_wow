class Gem {

  int type;
  int xcor, ycor;
  int pxcor, pycor;
  int typeID;

  Gem() {
    xcor = 0; 
    ycor = 0;
    pxcor = wx + side/2;
    pycor = wy + side/2;
    typeID = 0;
    grid.getGemArray()[0][0] = this;
  }

  Gem(int type, int x, int y) {
    typeID = type;
    xcor = x;
    ycor = y;
    pxcor = wx + side* x + side/2;
    pycor = wy + side* y + side/2;
    grid.getGemArray()[x][y] = this;
  }

  int getXcor() {
    return xcor;
  }
  int getYcor() {
    return ycor;
  }
  int getPXcor() {
    return pxcor;
  }
  int getPYcor() {
    return pycor;
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
  void setPXcor(int val) {
    pxcor = val;
  }
  void setPYcor(int val) {
    pycor = val;
  }

  color getColor () {
    color c = color(32 * typeID); 
    return c;
  }

  //testing move function... wip
  void move(int nx, int ny) {
    if (this.getTypeID() != 8) {
      int newpxcor = wx + side* nx + side/2;
      int newpycor = wy + side* ny + side/2;      
      grid.getGem(nx, ny).setPXcor(pxcor);
      grid.getGem(nx, ny).setPYcor(pycor);
      pxcor = newpxcor;
      pycor = newpycor;
      /*
    while (pxcor != newpxcor || pycor != newpycor) {
       pxcor += ((newpxcor + side/2) - pxcor)/16;
       pycor += ((newpxcor + side/2) - pycor)/16;
       }    
       */
      grid.getGemArray()[xcor][ycor] = grid.getGem(nx, ny);
      //grid.getGemArray()[xcor][ycor] = new Gem(8, xcor, ycor);
      xcor = nx;
      ycor = ny;
      grid.getGemArray()[xcor][ycor] = this;
    }
  }  
  void pmove(int px, int py) {    
    pxcor += ((px + side/2) - pxcor)/16;
    pycor += ((py + side/2) - pycor)/16;
  }
}

