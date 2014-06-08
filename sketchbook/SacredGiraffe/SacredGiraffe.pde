int wx, wy; //starting x and y for player window
int rows, cols; //number of rows or columns
int side; //side length of grid boxes
//Gem[][] gemArray; //array of gems
Gemgrid grid;
boolean pickup;
//Stores selected gem
int sxcor;
int sycor;
int  dxcor;
int dycor;
PImage src;
PImage frames[];
PImage bcksrc;
PImage back[];
PImage tilesrc;
int totalSprites;
int sCol;
int score;
Gemqueue queue;
PFont f, m;
int breakPoints;
int money;
int lives;
int time;
int timeCounter;
int loadCount;
int loadingCount;
int timeBonus;
int state;


void setup() {
  size (800, 800);  
  f = createFont("Arial", 24, true);
  m = createFont("Arial", 24, true);
  state = 0;
  loadCount = 0;
  timeBonus = 1;
  loadingCount = 0;
  time = 60;
  timeCounter= 60;
  frameRate(60);
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
  bcksrc = loadImage("back.png");
  tilesrc = loadImage("chest.png");
  frames = new PImage[totalSprites];
  back = new PImage[100];
  queue = new Gemqueue(42);
  grid = new Gemgrid();
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
      //gemArray[x][y] = new Gem((int)random(8), x, y);
      grid.getGemArray()[x][y] = new Gem((int)random(8), x, y, false);
    }
  }
  for (int i = 0; i <frames.length; i++) {
    frames[i] = src.get(0, 50*i, 50, 50);
  }
  for (int x = 0; x < 10;x++){
   for (int y = 0; y < 10; y++){
    back[loadCount] = bcksrc.get(80*x,80*y,80,80); 
    loadCount++;
   }
  }
}

void draw () {  
  if (state == 0){
    menu();
  }
  else if(state == 1){ 
    if (time >0 ){
     gameplay();
    }else{
     fill(255,0,0);
     rect(0,0,800,800);
     fill(0,0,0);
     textFont(f,36);
     textSize(300);
     text("GG",200,500); 
    }
  }
}

boolean checkMatch() {
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
      if (!grid.getGemArray()[x][y].checkMatch()) {
        return false;
      }
    }
  }
  return true;
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
  if(state == 0){
   if (mouseX > 100 && mouseX < 700 && mouseY > 50 && mouseY < 200){
    state = 1;
   } 
  }
  else if (state == 1){
    Gem selected = grid.getGem(sxcor, sycor);
    dxcor = grid.direction(sxcor, grid.processMX(mouseX));
    dycor = grid.direction(sycor, grid.processMY(mouseY)); 
  /*
  if (abs(mouseX - sxcor) > abs(mouseY - sycor) ) {
    selected.move(sxcor + xcor, sycor);
  }
  if (abs(mouseX - sxcor) < abs(mouseY - sycor) ) {
    selected.move(sxcor, sycor + ycor);
  }*/
    selected.move(sxcor + dxcor, sycor + dycor);
 // grid.getGem(sxcor+dxcor,sycor+dycor).changeStat();
 // grid.getGem(sxcor,sycor).changeStat();
  //print ("xcor:" + xcor);
  //print ("ycor: " + ycor);
  //pickup = !pickup;
    time -= 1;
  }
}

void keyPressed() {
  /*
  println(grid.getGem(xcor, ycor).getTypeID());
   println(grid.getGem(xcor, ycor).getPXcor());
   println(grid.getGem(xcor, ycor).getPYcor());
   println(grid.getGem(xcor, ycor).getXcor());
   println(grid.getGem(xcor, ycor).getYcor());
   */
  //println(grid.getGem(xcor, ycor).isBroken());
}

int getScore() {
  return score;
}

void addScore(int val) {
  score += val;
}


void gameplay(){
  background(bcksrc);
  //background(0,0,255);
  /*for (int x = 0; x < 10; x++) {
    for (int y = 0; y < 10; y++) {
      if(x > 0 && x < 9&&y>0&&y<9){ 
       fill (100, 168, 255);
       rect(side * x,side * y, side, side);
      }else{
        fill(0,0,255);
        rect(side*x,side*y,side,side);
       //image(back[loadingCount],80*x,80*y); 
      }
      //loadingCount++;
    }
  } */
  for(int x = 0 ; x <cols;x++){
   for (int y = 0; y < rows;y++){
    fill(100,168,255,63);
    rect(wx+side*x,wy+side*y,side,side);
   } 
  }
  loadingCount = 0;
  //draws the gems
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
      grid.getGemArray()[x][y].mMove();
      //fill (grid.getGemArray()[x][y].getColor());
      stroke(0);
      /* ellipse(grid.getGemArray()[x][y].getPXcor(), //wx + side/2 + grid.getGemArray()[x][y].getXcor() * side, 
       grid.getGemArray()[x][y].getPYcor(), //wy + side /2 +grid.getGemArray()[x][y].getYcor() * side, 
       side/2, side/2);
       */
      image(frames[grid.getGemArray()[x][y].getTypeID()], 
      grid.getGemArray()[x][y].getMXcor(), 
      grid.getGemArray()[x][y].getMYcor(), 
      side/2, side/2);
      if(checkMatch()){
        grid.getGemArray()[x][y].checkComboH();
        grid.getGemArray()[x][y].checkComboV();
      }
    }
  }
  
  for (int x = 0; x < grid.getGemArray ().length; x++) {
     for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
       grid.getGemArray()[x][y].breakAction();
     }
   }
  
  if(checkMatch()){
   // for(int x = 0; x < grid.getGemArray().length;x++){
     //for(int y = 0; y < grid.getGemArray()[x].length;y++){
      if (grid.getGem(sxcor,sycor).getStat()&&(!grid.getGem(sxcor,sycor).isBroken())){
        if(grid.getGem(sxcor+dxcor,sycor+dycor).getStat()&&(!grid.getGem(sxcor+dxcor,sycor+dycor).isBroken())){
          grid.getGem(sxcor,sycor).move(sxcor+dxcor,sycor+dycor);
          grid.getGem(sxcor,sycor).statReset();
          grid.getGem(sxcor+dxcor,sycor+dycor).statReset();
        }
      }
     } 
    //}
  //}
  //Breaks Gems
   /*for (int x = 0; x < grid.getGemArray ().length; x++) {
     for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
       grid.getGemArray()[x][y].breakAction();
     }
   }*/
  
  //Gets gems to fall
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
      if (y < 7 && grid.getGemArray()[x][y+1].isBroken() ) {
        grid.getGemArray()[x][y].move(x, y+1);
        grid.getGemArray()[x][y+1].statReset();
      }
    }
  }
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    if (grid.getGemArray()[x][1].checkMatch()&&grid.getGemArray()[x][0].getTypeID()== 19) {
      Gem next = queue.getNext();
      grid.getGemArray()[x][0] = new Gem(next.getTypeID(), x, 0, false);
      grid.getGemArray()[x][0].setMYcor(grid.getGemArray()[x][0].getMYcor()-side/2);
    }
  }
  score+= breakPoints*100;
  money += breakPoints*10;
  breakPoints = 0;

  textFont(f, 36);
  fill(100, 255, 255);
  textSize(90);
  text(""+score, 20, 70);
  text("$" + money, side*6+20, 70);
  timeCounter -= 1;
  if (timeCounter == 0){
   time -= 1;
   timeCounter = 60; 
  }
  text("" + time,side*5-50,70); 
}


void menu(){
 background(bcksrc);
 fill(255,0,0,63);
 rect(100,50,600,150);
 rect(100,250,600,150);
 rect(100,450,600,150);
 textFont(f,36);
 fill(100,255,255);
 textSize(120);
 text("Play Game",100,160);
 text("Shop" , 250,365);
 text("HighScore",100, 570);
 
  
}


