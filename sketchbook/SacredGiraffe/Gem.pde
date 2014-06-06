class Gem {

  int type;
  int xcor, ycor;
  int pxcor, pycor;
  int typeID;
  boolean highlight;
  boolean broken;

  Gem() {
    xcor = 0; 
    ycor = 0;
    pxcor = wx + side/2;
    pycor = wy + side/2;
    typeID = 0;
    highlight = false;
    broken = false;
    grid.getGemArray()[0][0] = this;
  }

  Gem(int type, int x, int y) {
    typeID = type;
    xcor = x;
    ycor = y;
    pxcor = wx + side* x + (side/4);
    pycor = wy + side* y + (side/4);
    highlight = false;
    broken = false;
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
  boolean isHighlighted() {
    return highlight;
  }  
  boolean isBroken() {
    return broken;
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
 void setHighlight(boolean b) {
    highlight = b;
  }

  color getColor () {
    color c = color(32 * typeID); 
    return c;
  }
  
  void breakGem() {
   broken = true; 
  }
  
  //testing move function... wip
  void move(int nx, int ny) {
    if (this.getTypeID() != 8) {
      Gem temp = new Gem(grid.getGem(nx, ny).getTypeID(), nx, ny);
      int newpxcor = wx + side* nx + side/4;
      int newpycor = wy + side* ny + side/4;      
      temp.setPXcor(pxcor);
      temp.setPYcor(pycor);
      pxcor = newpxcor;
      pycor = newpycor;
      grid.getGemArray()[nx][ny] = this;
      temp.setXcor(xcor);
      temp.setYcor(ycor);
      grid.getGemArray()[xcor][ycor] = temp;
      xcor = nx;
      ycor = ny;
    }
  }  
  void pmove(int px, int py) {    
    pxcor += ((px + side/2) - pxcor)/16;
    pycor += ((py + side/2) - pycor)/16;
  }
  void checkComboH () {
    if (xcor > 0 && xcor < 7 && typeID != 8 && !broken) {
      if ((grid.getGem(xcor-1, ycor).getTypeID() == typeID && grid.getGem(xcor+1, ycor).getTypeID() == typeID)) {
        breakGem();
        grid.getGem(xcor-1, ycor).checkComboH();
        grid.getGem(xcor-1, ycor).breakGem();
        grid.getGem(xcor+1, ycor).checkComboH();
        grid.getGem(xcor+1, ycor).breakGem();
      }
    }
  }
  void checkComboV () {
    if (ycor > 0 && ycor < 7 && typeID != 8 && !broken) {
      if ((grid.getGem(xcor, ycor+1).getTypeID() == typeID && grid.getGem(xcor, ycor-1).getTypeID() == typeID)) {
        breakGem();
        grid.getGem(xcor, ycor-1).checkComboV();
        grid.getGem(xcor, ycor-1).breakGem();
        grid.getGem(xcor, ycor+1).checkComboV();
        grid.getGem(xcor, ycor+1).breakGem();
      }
    }
  }

  void breakAction() {
    if (broken) {
      typeID = 19;
    }
  }
}

