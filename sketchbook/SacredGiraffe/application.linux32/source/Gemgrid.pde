class Gemgrid {
  Gem[][] gemArray;

  Gemgrid() {
    gemArray = new Gem[8][8];
  }

  Gem[][] getGemArray() {
    return gemArray;
  }

  Gem getGem(int x, int y) {
    return gemArray[x][y];
  }

  void moveGem(int x, int y, int dx, int dy) {
    gemArray[x][y].move(dx, dy);
  }

  void highlight(int x, int y, int dx, int dy) {
  }

  //these pairs of functions convert mouseX/Y to int coords of the grid
  int processMX(int mx) {
    return (mouseX - wx) / 80;
  }
  int processMY(int my) {
    return (mouseY - wy) / 80;
  }
  int direction(int sx, int mx) {
    int ans = 0;
    if (mx - sx > 0) {
      ans = 1;
    } else if (mx - sx < 0) {
      ans = -1;
    }
    return ans;
  }
}

