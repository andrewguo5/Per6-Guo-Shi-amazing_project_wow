import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SacredGiraffe extends PApplet {



AudioPlayer player;
Minim minim;



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
int speed;
PImage src;
PImage frames[];
PImage bcksrc;
PImage back[];
PImage tilesrc;
PImage gamesrc;
PImage shopsrc;
PImage scoresrc;
int totalSprites;
int sCol;
int score;
int highScore;
Gemqueue queue;
PFont f, m;
int breakPoints;
int money;
int lives;
int maxTime;
int time;
int timeCounter;
int loadCount;
int loadingCount;
int timeBonus;
int state;
int types;
int timeCost;
int speedCost;


public void setup() {
  
  size (800, 800);  
  f = createFont("Arial", 24, true);
  m = createFont("Arial", 24, true);
  speed = 1;
  speedCost = 200;
  timeCost = 300;
  types = 5;
  state = 0;
  loadCount = 0;
  timeBonus = 1;
  loadingCount = 0;
  maxTime = 60;
  time = maxTime;
  timeCounter= 60;
  frameRate(60);
  money = 100000000;
  breakPoints = 0;
  score = 0;
  highScore = score;
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
  gamesrc = loadImage("battle.png");
  shopsrc = loadImage("shop.png");
  scoresrc = loadImage("score.png");
  frames = new PImage[totalSprites];
  back = new PImage[100];
  queue = new Gemqueue(42);
  grid = new Gemgrid();
  for (int i = 0; i <frames.length; i++) {
    frames[i] = src.get(0, 50*i, 50, 50);
  }
  /*for (int x = 0; x < 10;x++){
   for (int y = 0; y < 10; y++){
    back[loadCount] = bcksrc.get(80*x,80*y,80,80); 
    loadCount++;
   }
  }*/
  minim = new Minim(this);
  player = minim.loadFile("Music.mp3");
  player.play();
}

public void draw () {  
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
     fill(0,255,0);
     rect(0,0,80,80);
     textSize(20);
     text("Menu",15,45);
    }
  }
  else if(state == 2){
   shop(); 
  }
  else if(state == 3){
   highScore(); 
  }
  if (score > highScore){
   highScore = score; 
  }
}

public boolean checkMatch() {
  for (int x = 0; x < grid.getGemArray ().length; x++) {
    for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
      if (!grid.getGemArray()[x][y].checkMatch()) {
        return false;
      }
    }
  }
  return true;
}

public void mousePressed() {
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

public void mouseReleased() {    
  if(state == 0){
   if (mouseX > 100 && mouseX < 700 && mouseY > 50 && mouseY < 200){
    state = 1;
    time = maxTime;
    score = 0;
    for (int x = 0; x < grid.getGemArray ().length; x++) {
      for (int y = 0; y < grid.getGemArray ()[x].length; y++) {
        //gemArray[x][y] = new Gem((int)random(8), x, y);
        grid.getGemArray()[x][y] = new Gem((int)random(types), x, y, false);
    }
  }
   } 
   if (mouseX > 100 && mouseX < 700 && mouseY > 250 && mouseY < 400){
    state = 2; 
   }
   if(mouseX> 100 && mouseX < 700 && mouseY > 450 && mouseY < 600){
    state = 3; 
   }
  }
  else if(state == 2){
   if (mouseX>0 && mouseX < 80 && mouseY > 0 && mouseY <80){
    state = 0;
   } 
   if(mouseX>50 && mouseX < 350 && mouseY > 125 && mouseY < 275&&money > timeCost&&maxTime <= 400){
    maxTime += 5;
    money -= timeCost;
    timeCost *=2; 
   }
   if (mouseX>50 && mouseX <350 && mouseY > 325 && mouseY < 475&&money>speedCost&&speed < 8){
    speed *=2;
    money -= speedCost;
    speedCost *= 4; 
   }
  }
  
  else if(state == 3){
   if (mouseX>0&&mouseX<80&&mouseY>0&&mouseY<80){
    state = 0;
   } 
  }
  
  else if (state == 1&&time>0){
    if(mouseX > 0 && mouseX < side && mouseY > 400 && mouseY < 400+side){
      state = 0;
    }
    if(mouseX > wx && mouseX < wx+ side*8 && mouseY>wy && mouseY < wy + side*8){
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
    if (!selected.getStat() && !grid.getGem(sxcor + dxcor, sycor + dycor).getStat() )
      selected.move(sxcor + dxcor, sycor + dycor);
 // grid.getGem(sxcor+dxcor,sycor+dycor).changeStat();
 // grid.getGem(sxcor,sycor).changeStat();
  //print ("xcor:" + xcor);
  //print ("ycor: " + ycor);
  //pickup = !pickup;
    }
    time -= 1;
  }else if(state == 1 && time <= 0){
   if(mouseX >0 &&mouseX < 80 && mouseY > 0 && mouseY < 80){
    state = 0;
   } 
  }
}

public void keyPressed() {
  /*
  println(grid.getGem(xcor, ycor).getTypeID());
   println(grid.getGem(xcor, ycor).getPXcor());
   println(grid.getGem(xcor, ycor).getPYcor());
   println(grid.getGem(xcor, ycor).getXcor());
   println(grid.getGem(xcor, ycor).getYcor());
   */
  //println(grid.getGem(xcor, ycor).isBroken());
}

public int getScore() {
  return score;
}

public void addScore(int val) {
  score += val;
}


public void gameplay(){
  background(gamesrc);
  fill(0,255,0,63);
  rect(0,400,side,side);
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
  
  if(checkMatch()&&timeCounter == 30){
   // for(int x = 0; x < grid.getGemArray().length;x++){
     //for(int y = 0; y < grid.getGemArray()[x].length;y++){
      if (grid.getGem(sxcor,sycor).getStat()&&(!grid.getGem(sxcor,sycor).isBroken())){
        if(grid.getGem(sxcor+dxcor,sycor+dycor).getStat()&&(!grid.getGem(sxcor+dxcor,sycor+dycor).isBroken())){
          grid.getGem(sxcor,sycor).move(sxcor+dxcor,sycor+dycor);
        }
      }          
      grid.getGem(sxcor,sycor).statReset();
      grid.getGem(sxcor+dxcor,sycor+dycor).statReset();
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
  textSize(20);
  text("Menu", 10, 450);
}


public void menu(){
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

public void shop(){
  background(shopsrc);
  fill(0,255,0);
  rect(0,0,80,80);
  fill(255,0,0);
  textFont(f,36);
  textSize(20);
  text("Menu",15,45);
  textSize(100);
  text("Shop; $"+ money,90,100);
  fill(134,148,232,63);
  rect(50,125,300,150);
  rect(450,125,300,150);
  rect(50,325,300,150);
  rect(450,325,300,150);
  rect(50,525,300,150);
  rect(450,525,300,150);
  textSize(35);
  fill(0,0,255);
  text("Time Increase",80, 170); 
  if(maxTime < 400){
    text("Cost:$" + timeCost, 80 ,250); 
  }else{
    text("Maxed Out!",80,250);
  }
  text("Speed Increase", 80, 375);
  if(speed < 8){
    text("Cost:$" + speedCost,80,455);
  }else{
    text("Maxed Out!",80,455);
  }
}


public void highScore(){
 background(scoresrc);
 fill(255,235,200);
 textFont(f,36);
 textSize(300);
 text("" + highScore,100,500);
 fill(255,0,0);
 rect(0,0,80,80);
 fill(0,0,255);
 textSize(20);
 text("Menu",15,45);
}

public void stop(){
 player.close();
 minim.stop();
 super.stop(); 
}
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

  public int getMXcor(){
   return mxcor; 
  }
  
  public int getMYcor(){
   return mycor; 
  }
  
  public void setMXcor(int val){
    mxcor = val;
  }
  
  public void setMYcor(int val){
   mycor = val; 
  }

  public boolean getStat(){
   return moved; 
  }
  
  public void setStat(){
   moved = true; 
  }
  
  public void statReset(){
   moved = false; 
  }

  public int getXcor() {
    return xcor;
  }
  public int getYcor() {
    return ycor;
  }
  public int getPXcor() {
    return pxcor;
  }
  public int getPYcor() {
    return pycor;
  }
  public int getTypeID() {
    return typeID;
  }
  public boolean isHighlighted() {
    return highlight;
  }  
  public boolean isBroken() {
    return brokenH || brokenV;
  }


  public void setXcor (int val) {
    xcor = val;
  }
  public void setYcor (int val) {
    ycor = val;
  }
  public void setPXcor(int val) {
    pxcor = val;
  }
  public void setPYcor(int val) {
    pycor = val;
  }  
 public void setHighlight(boolean b) {
    highlight = b;
  }

  public int getColor () {
    int c = color(32 * typeID); 
    return c;
  }
  
  public boolean checkMatch(){
   return mxcor == pxcor && mycor == pycor;
  }
  
  public int mDir(int val, int val2){
    if (val - val2 < 0){
     return -1; 
    }
    else if(val - val2 > 0){
     return 1; 
    }
    else{return 0;}
  }
  
  public void mMove(){
   mxcor += mDir(pxcor,mxcor)*speed;
   mycor += mDir(pycor,mycor)*speed; 
  }
  
  public void breakGemH() {
   brokenH= true; 
  }
  public void breakGemV() {
   brokenV = true; 
  }
  
  //testing move function... wip
  public void move(int nx, int ny) {
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
  public void pmove(int px, int py) {    
    pxcor += ((px + side/2) - pxcor)/16;
    pycor += ((py + side/2) - pycor)/16;
  }
  public boolean checkComboH () {
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
  public boolean checkComboV () {
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


  public void breakAction() {
    if (isBroken()) {
      typeID = 19;
    }
  }
}

public void addPoints(){
 breakPoints+=1; 
}

class Gemgrid {
  Gem[][] gemArray;

  Gemgrid() {
    gemArray = new Gem[8][8];
  }

  public Gem[][] getGemArray() {
    return gemArray;
  }

  public Gem getGem(int x, int y) {
    return gemArray[x][y];
  }

  public void moveGem(int x, int y, int dx, int dy) {
    gemArray[x][y].move(dx, dy);
  }

  public void highlight(int x, int y, int dx, int dy) {
  }

  //these pairs of functions convert mouseX/Y to int coords of the grid
  public int processMX(int mx) {
    return (mouseX - wx) / 80;
  }
  public int processMY(int my) {
    return (mouseY - wy) / 80;
  }
  public int direction(int sx, int mx) {
    int ans = 0;
    if (mx - sx > 0 && sx < 7) {
      ans = 1;
    } else if (mx - sx < 0 && sx > 0) {
      ans = -1;
    }
    return ans;
  }
}



class Gemqueue {
  LinkedList<Gem> queue;
  int seed;
  
  Gemqueue (int s) {
   queue = new LinkedList<Gem>();
   seed = s; 
   for (int i = 0; i < 100; i++) {
    queue.add(new Gem((int)random(8), false));
   }
  }
  
  public Gem getNext() {
    queue.add(new Gem((int)random(8),false));
    return queue.remove();
  }
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SacredGiraffe" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
