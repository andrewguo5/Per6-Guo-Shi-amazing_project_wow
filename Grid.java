import java.awt.*;
import java.util.*;

public class Grid 
{

    public static int width = 10; //the size of the entire game layout
    public static int height = 10;
    public static int tileSize = 40; // size of each slot that holds a gem
    
    public Tile[][] grid;
    public Gem[][] gemGrid;
    
    public Grid(){
	grid = new Tile[height][width];
	for (int y = 0; y < grid.length; y++){
	    for(int x = 0; x < grid[y].length;x++){
		grid[y][x] = new Tile(/*(Screen.myWidth/2) - ((width*tileSize)/2)+*/(x*tileSize),
				      /*(Screen.myHeight/2)- ((height*tileSize)/2)+*/(y*tileSize),//Centers
				      tileSize,tileSize); //this places the Tiles in correct location on GUI
	    }
	}
	//initializes the gemGrid for testing purposes. does not insert into GUI
	fillGrid(12);
    }

    public Grid(int n) {
	this();
	fillGrid(n);
    }

    //fills the gemGrid with numcolors of different colors
    public void fillGrid(int numcolors) {
	gemGrid = new Gem[height][width];
	for (int y = 0; y < gemGrid.length; y++) {
	    for (int x = 0; x < gemGrid[y].length;x++) {
		gemGrid[y][x] = new Gem ((int)(Math.random() * numcolors), x , y);
	    }
	}
    }

    public void setNull(int x, int y) 
    {
	gemGrid[x][y] = null;
    }

    public Gem getGem (int x, int y)
    {
	return gemGrid[x][y];
    }

    public Tile getTile (int x, int y)
    {
	return grid[x][y];
    }


    //This places the gem in the grid at (x, y)
    //This returns the gem that was previously at (x, y)
    public Gem placeGem (Gem gem, int x, int y) {
	int type = gemGrid[x][y].getType();
	Gem oldGem = new Gem (type, x, y);
	gemGrid[x][y] = gem;
	return oldGem;
     }

    //Sort of complicated, built-to-be-versatile switch method. 
    //Preconds:
    //the length of the array is an integer multiple of step
    //Postconds:
    //the gems at grid(x[n], y[n]) are rotated at "step" number of gems at a time
    //ie. a normal swap between two gems would have a gem array with length 2, step at 2
    public void switchGems (int steps, Gem[] gemArray) {
	
	int x[] = new int[gemArray.length];
	for (int i = 0; i < x.length; i++) {
	    x[i] = gemArray[i].getXcor();
	}
	int y[] = new int[gemArray.length];
	for (int i = 0; i < y.length; i++) {
	    y[i] = gemArray[i].getYcor();
	}		
	
	//makes a list of gems to switch around
	//rotates the position of the gems in the list of gems
	for (int i = 0; i < gemArray.length; i += steps) {
	    switchHelper(gemArray, i, i+steps);
	}

	//puts the Gems in the list of gems in the 2d array
	for (int i = 0; i < gemArray.length; i ++ ) {
	    gemArray[i].setXcor(x[i]);
	    gemArray[i].setYcor(y[i]);
	    gemGrid[y[i]][x[i]] = gemArray[i];
	}

	
    }

    public void switchHelper (Gem[] gemArray, int start, int end) {
        LinkedList<Gem> gemList = new LinkedList<Gem>();
	for (int i = start; i < end; i++) {
	    gemList.add(gemArray[i]);
	}
	gemList.addFirst(gemList.pollLast());
	for (int i = start; i < end; i++) {
	    gemArray[i] = gemList.remove();
	}
	System.out.println("nice turtle" + Arrays.toString(gemArray));
       
    }

    public void draw(Graphics g){
	for (int y = 0; y < grid.length; y ++){
	    for (int x = 0; x< grid[y].length; x++){
		grid[y][x].draw(g);
	    }
	}
    }
	
    public String toString() {
	String spidey = "[";
	for (int x = 0; x < gemGrid.length; x++) {
	    for (int y = 0; y < gemGrid[x].length; y++) {
		spidey += gemGrid[y][x];
		spidey += ",\t";
	    }
	    spidey = spidey.substring(0, spidey.length() - 2);
	    spidey += "]\n";
	}
	spidey = spidey.substring(0, spidey.length() - 1);
	return spidey;
    }

}
