class Gem {

  int type;
  int xcor, ycor;
  int pxcor, pycor;
  int typeID;
  boolean highlight;
  boolean brokenH;
  boolean brokenV;

  Gem() {
    xcor = 0; 
    ycor = 0;
    pxcor = wx + side/2;
    pycor = wy + side/2;
    typeID = 0;
    highlight = false;
    brokenH = false;
    brokenV = false;
    grid.getGemArray()[0][0] = this;
  }

  Gem(int type, int x, int y) {
    typeID = type;
    xcor = x;
    ycor = y;
    pxcor = wx + side* x + side/2;
    pycor = wy + side* y + side/2;
    highlight = false;
    brokenH = false;
    brokenV = false;
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
    return brokenH || brokenV;
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
  
  void breakGemH() {
   brokenH= true; 
  }
  void breakGemV() {
   brokenV = true; 
  }
  
  //testing move function... wip
  void move(int nx, int ny) {
    if (this.getTypeID() != 8) {
      Gem temp = new Gem(grid.getGem(nx, ny).getTypeID(), nx, ny);
      int newpxcor = wx + side* nx + side/2;
      int newpycor = wy + side* ny + side/2;      
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
    if (xcor > 0 && xcor < 7 && typeID != 8 && !brokenH) {
      if ((grid.getGem(xcor-1, ycor).getTypeID() == typeID && grid.getGem(xcor+1, ycor).getTypeID() == typeID)) {
        breakGemH();
        grid.getGem(xcor-1, ycor).checkComboH();
        grid.getGem(xcor-1, ycor).breakGemH();
        grid.getGem(xcor+1, ycor).checkComboH();
        grid.getGem(xcor+1, ycor).breakGemH();
      }
    }
  }
  void checkComboV () {
    if (ycor > 0 && ycor < 7 && typeID != 8 && !brokenV) {
      if ((grid.getGem(xcor, ycor+1).getTypeID() == typeID && grid.getGem(xcor, ycor-1).getTypeID() == typeID)) {
        breakGemV();
        grid.getGem(xcor, ycor-1).checkComboV();
        grid.getGem(xcor, ycor-1).breakGemV();
        grid.getGem(xcor, ycor+1).checkComboV();
        grid.getGem(xcor, ycor+1).breakGemV();
      }
    }
  }

  void breakAction() {
    if (isBroken()) {
      typeID = 8;
    }
  }
}

