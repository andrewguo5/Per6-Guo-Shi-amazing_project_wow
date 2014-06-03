int radius;
int x1, x2, y1, y2;
int dx1, dy1, dx2, dy2, ddx, ddy;

void setup() {
  
   size (200, 200);
   frameRate (30);
   x1 = width /3;
   x2 = x1 + x1;
   y1 = height/3;
   y2 = y1 + y1;   
   radius = 50;
   dx1 = 0; dy1 = 0; ddx = 0; ddy = 0;
   dx2 = 0; dy2 = 0;
}

void draw() {
   stroke (0);
   background(100);
   ellipse (x1, y1, radius, radius);
   ellipse (x2, y2, radius, radius);
   fill(168, 168, 50);
   x1 += sgn(dx1); x2 += sgn(dx2);
   y1 += sgn(dy1); y2 += sgn(dy2);
}

void mousePressed() {
  dx1 = mouseX - x1;
  dy1 = mouseY - y1;
  dx2 = mouseX - x2;
  dy2 = mouseY - y2;
  
}

int sgn(int val) {
  if (val > 0) 
   return 1;
  else if (val < 0)
   return -1;
  else 
   return 0; 
  
}


   
