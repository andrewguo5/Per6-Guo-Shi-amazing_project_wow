int x, y, nx, ny, dx, dy, ddx, ddy;

void setup() {
   size (400, 400);
   frameRate (30);
   x = width/2;
   y = height/2;
   //nx = x;
   //ny = y;
   dx = 0;
   dy = 0;
   ddx = 0;
   ddy = 2;
   strokeWeight (10);
    
}

void draw() {
   //x += (nx - x)/20;
   //y += (ny - y)/20;
   x += dx;
   y += dy;
   background (100);
   fill (184, 121, 0);
   stroke (255);
   ellipse ( x, y, 50, 50);
   if (y + 25 > height) {
      dy += ddy; 
      dy = -dy;
      //y = 0;
   }
   if (x >= width) {
      //dx += ddx; 
      //dx = -dx;  
      //ddx = -ddx;
      x = 0;
   }
   dx += ddx;
   dy += ddy;
   
}

void mousePressed () {

   //nx = mouseX;
   //ny = mouseY; 
   x = mouseX;
   y = mouseY;
}

void keyPressed() {
  if (key == 'd'){
  dx = (dx + 1)* 2; 
  }
  if (key == 'a') {
  dx = -(dx +1 )*2;  
  }
}
