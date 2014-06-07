import java.util.*;

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
  
  Gem getNext() {
    return queue.remove();
  }
  
}
