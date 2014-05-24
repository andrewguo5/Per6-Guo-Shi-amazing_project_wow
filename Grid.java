

public class Grid 
{

    private int width = 10; //the size of the entire game layout
    private int height = 10;
    private int tileSize = 40; // size of each slot that holds a gem
    
    private Tile[][] grid;
    

    public Grid(){
	grid = new Tile[height][width];
	for (int y = 0; y < grid.length; y++){
	    for(int x = 0; x < grid[y].length;x++){
		grid[y][x] = new Tile((x*tileSize),(y*tileSize),tileSize,tileSize); //this places the Tiles in correct location on GUI
	    }
	}
    }


}
