int wx, wy; //starting x and y for player window
int rows, cols; //number of rows or columns
int side; //side length of grid boxes
//Gem[][] gemArray; //array of gems
Gemgrid grid;
boolean pickup;
//Stores selected gem
int sxcor;
int sycor;
PImage src;
PImage frames[];
int totalSprites;
int sCol;
int score;
Gemqueue queue;
PFont f,m;
int breakPoints;
int money;

void setup() {
  size (800, 800);  
  f = createFont("Arial",24,true);
  m = createFont("Arial",24,true);
  money = 0;
  breakPoints = 0;
  score = 0;
  wx = 80;
  wy = 80;
  rows = 8;
  cols = 8;
  side = 80;
  totalSprites = 20;
  pickup = true;
  //gemArray = new Gem[8][8];
  src = loadImage("sprites_column_transparent.png");
  frames = new PImage[totalSprites];
  queue = new Gemqueue(42);
  grid = new Gemgrid();
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
      //gemArray[x][y] = new Gem((int)random(8), x, y);
      grid.getGemArray()[x][y] = new Gem((int)random(8), x, y, false);
    }
  }
  for (int i = 0; i <frames.length;i++){
      frames[i] = src.get(0,50*i,50,50);
  }
}

void draw () {  
  for (int x = 0; x < cols; x++) {
    for (int y = 0; y < rows; y++) {
      fill (100, 168, 255);
      rect(wx + side * x, wy + side * y, side, side);
    }
  }  
  //draws the gems
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
      grid.getGemArray()[x][y].mMove();
      //fill (grid.getGemArray()[x][y].getColor());
      stroke(100);
     /* ellipse(grid.getGemArray()[x][y].getPXcor(), //wx + side/2 + grid.getGemArray()[x][y].getXcor() * side, 
              grid.getGemArray()[x][y].getPYcor(), //wy + side /2 +grid.getGemArray()[x][y].getYcor() * side, 
              side/2, side/2);
      */
      image(frames[grid.getGemArray()[x][y].getTypeID()],
            grid.getGemArray()[x][y].getMXcor(),
            grid.getGemArray()[x][y].getMYcor(),
            side/2,side/2);
      if (checkMatch()){
        grid.getGemArray()[x][y].checkComboH();
        grid.getGemArray()[x][y].checkComboV();
      }
    }
  }
  //Breaks Gems
  for (int x = 0; x < grid.getGemArray().length;x++){
    for (int y = 0; y < grid.getGemArray()[x].length;y++){
       grid.getGemArray()[x][y].breakAction();
    } 
  }
  //Gets gems to fall
  for (int x = 0; x < grid.getGemArray().length;x++){
    for (int y = 0; y < grid.getGemArray()[x].length;y++){
       if (y < 7 && grid.getGemArray()[x][y+1].isBroken() ) {
         grid.getGemArray()[x][y].move(x, y+1);                   
       }
    } 
  }
  for (int x = 0; x < grid.getGemArray().length; x++) {
      if (grid.getGemArray()[x][1].checkMatch()&&grid.getGemArray()[x][0].getTypeID()== 19) {
        Gem next = queue.getNext();
        grid.getGemArray()[x][0] = new Gem(next.getTypeID(), x, 0, false);
        grid.getGemArray()[x][0].setMYcor(grid.getGemArray()[x][0].getMYcor()-side/2);
        
      }
    }
  score+= breakPoints*100;
  money += breakPoints*10;
  breakPoints = 0;
  
  fill (200, 150, 200);
  rect (0,0,side*5,side);
  rect (side*5,0,side*5,side);
  textFont(f,36);
  fill(100,255,255);
  textSize(90);
  text(""+score,20,70);
  text("$: " + money,side*5+20,70);
}

boolean checkMatch(){
 for (int x = 0; x < grid.getGemArray().length; x++){
  for (int y = 0; y < grid.getGemArray()[x].length; y++){
   if (!grid.getGemArray()[x][y].checkMatch()){
    return false;
   } 
  }
 }return true;  
}

void mousePressed() {
  //print("desu");
  // if (pickup) {
  sxcor = grid.processMX(mouseX);
  sycor = grid.processMY(mouseY);
  //print(sxcor);
  //print(sycor);
  //pickup = !pickup;
  /*
  } else {      
   Gem selected = grid.getGem(sxcor, sycor);
   int xcor = grid.processMX(mouseX);
   int ycor = grid.processMY(mouseY); 
   selected.move(xcor, ycor);
   pickup = !pickup;
   }
   */
}

void mouseReleased() {    
  Gem selected = grid.getGem(sxcor, sycor);
  int xcor = grid.processMX(mouseX);
  int ycor = grid.processMY(mouseY); 
  selected.move(xcor, ycor);
  //print ("xcor:" + xcor);
  //print ("ycor: " + ycor);
  //pickup = !pickup;
}

void keyPressed() {
  int xcor = grid.processMX(mouseX);
  int ycor = grid.processMY(mouseY); 
  /*
  println(grid.getGem(xcor, ycor).getTypeID());
  println(grid.getGem(xcor, ycor).getPXcor());
  println(grid.getGem(xcor, ycor).getPYcor());
  println(grid.getGem(xcor, ycor).getXcor());
  println(grid.getGem(xcor, ycor).getYcor());
  */
  //println(grid.getGem(xcor, ycor).isBroken());
  println(grid.getGem(xcor, ycor).getTypeID());
}

int getScore(){
 return score; 
}

void addScore(int val){
 score += val; 
}


