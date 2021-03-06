class Gem {

  int type;
  int xcor, ycor;
  int pxcor, pycor;
  int typeID;
  int mxcor, mycor;
  boolean highlight;
  boolean brokenH;
  boolean brokenV;
  boolean moved;

  Gem() {
    xcor = 0; 
    ycor = 0;
    pxcor = wx + side/4;
    pycor = wy + side/4;
    typeID = 0;
    highlight = false;
    brokenH = false;
    brokenV = false;
   // grid.getGemArray()[0][0] = this;
  }
  
  Gem(int type, boolean broken) {
    this();
    typeID = type;
    brokenH = broken;
    brokenV = broken;
    mxcor = pxcor;
    mycor = pycor;
    moved = false;
  }
  
  Gem(int type, int x, int y, boolean broken) {
    typeID = type;
    xcor = x;
    ycor = y;
    pxcor = wx + side* x + side/4;
    pycor = wy + side* y + side/4;
    highlight = false;
    brokenH = broken;
    brokenV = broken;
    mxcor = pxcor;
    mycor = pycor;
    grid.getGemArray()[x][y] = this;
    moved = false;
  }

  int getMXcor(){
   return mxcor; 
  }
  
  int getMYcor(){
   return mycor; 
  }
  
  void setMXcor(int val){
    mxcor = val;
  }
  
  void setMYcor(int val){
   mycor = val; 
  }

  boolean getStat(){
   return moved; 
  }
  
  void setStat(){
   moved = true; 
  }
  
  void statReset(){
   moved = false; 
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
  
  boolean checkMatch(){
   return mxcor == pxcor && mycor == pycor;
  }
  
  int mDir(int val, int val2){
    if (val - val2 < 0){
     return -1; 
    }
    else if(val - val2 > 0){
     return 1; 
    }
    else{return 0;}
  }
  
  void mMove(){
   mxcor += mDir(pxcor,mxcor)*speed;
   mycor += mDir(pycor,mycor)*speed; 
  }
  
  void breakGemH() {
   brokenH= true; 
  }
  void breakGemV() {
   brokenV = true; 
  }
  
  //testing move function... wip
  void move(int nx, int ny) {
    if (this.getTypeID() != 19) {
      Gem temp = new Gem(grid.getGem(nx, ny).getTypeID(), nx, ny, grid.getGem(nx, ny).isBroken());
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
      grid.getGemArray()[xcor][ycor].setStat();
      xcor = nx;
      ycor = ny;
      setStat();
    }
  }  
  void pmove(int px, int py) {    
    pxcor += ((px + side/2) - pxcor)/16;
    pycor += ((py + side/2) - pycor)/16;
  }
  boolean checkComboH () {
    if (xcor > 0 && xcor < 7 && typeID != 19 && !brokenH) {
      if ((grid.getGem(xcor-1, ycor).getTypeID() == typeID && grid.getGem(xcor+1, ycor).getTypeID() == typeID)) {
        breakGemH();
        grid.getGem(xcor-1, ycor).checkComboH();
        grid.getGem(xcor-1, ycor).breakGemH();
        grid.getGem(xcor+1, ycor).checkComboH();
        grid.getGem(xcor+1, ycor).breakGemH();
        addPoints();
        time += timeBonus;
        //timeBonus++;
        return true;
      }
    }return false;
  }
  boolean checkComboV () {
    if (ycor > 0 && ycor < 7 && typeID != 19 && !brokenV) {
      if ((grid.getGem(xcor, ycor+1).getTypeID() == typeID && grid.getGem(xcor, ycor-1).getTypeID() == typeID)) {
        breakGemV();
        grid.getGem(xcor, ycor-1).checkComboV();
        grid.getGem(xcor, ycor-1).breakGemV();
        grid.getGem(xcor, ycor+1).checkComboV();
        grid.getGem(xcor, ycor+1).breakGemV();
        addPoints();
        time +=timeBonus;
        //timeBonus++;
        return true;
      }
    }return false;
  }


  void breakAction() {
    if (isBroken()) {
      typeID = 19;
    }
  }
}

void addPoints(){
 breakPoints+=1; 
}

