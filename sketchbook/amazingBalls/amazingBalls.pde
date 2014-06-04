//Ball b;
//Ball c;
ArrayList<Ball> ballArray;

void setup() {
 size(800,800); 
 //b = new Ball (50); 
 //c = new Ball (60);
 ballArray = new ArrayList<Ball>();
}

class Ball {
   int radius;
   int x, y;
   
   Ball (int val) {
     radius = val;
     x = width/2;
     y = height/2;
   }  
   
   Ball (int val, int xcor, int ycor) {
     this(val);
     x = xcor;
     y = ycor; 
   }
   
   void display() {
     fill (0, 168, 255);
     ellipse (x, y, radius, radius);   
   }
   
    void jiggle () {
        x += sin(frameCount/((int)random(4)+1));
        y += (int)random(9) +1;
      
    }
    
   void jiggle (boolean edison) {
      if (edison) {
        x += sin(frameCount/((int)random(4)+1));
        y += (int)random(9) +1;
      }
      else {
        x += (int)random(9) + 1;
        y -= cos(frameCount/(int)random(4)+1);         
      }
   }
}

void draw () {
   background(255);
   for (Ball b : ballArray) {
      b.display();
      b.jiggle(); 
   }
   //b.display();
   //b.jiggle(true);
   //c.display();
   //c.jiggle(false);
}

void mouseDragged() {
   ballArray.add(new Ball(50, mouseX, mouseY) );
}
