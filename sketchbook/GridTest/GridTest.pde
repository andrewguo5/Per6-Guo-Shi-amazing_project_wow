Grid g;

void setup() {
 size(800, 800); 
 g = new Grid();
}

class Grid {
 
 int maxX, maxY;
 int w, h;
 
 void Grid() {
   maxX = 10;
   maxY = 10;
   w = width/maxX;
   h = height/maxY;
 } 
 
 void Grid(int x, int y) {
  maxX = x;
  maxY = y; 
  w = width/maxX;
  h = height/maxY;
 }
 
 void display() {
   print(maxX);
   print(w);
   for (int x = 0; x < maxX; x++) {
     for (int y = 0; y < maxY; y++) {
       fill (255, 168, 0);
       rect(w * x, h * y, w, h);
       print("desu");
     }
   }
 }
}

void draw() {
  background(100);
  g.display(); 
}

