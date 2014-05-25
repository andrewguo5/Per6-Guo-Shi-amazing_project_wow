import java.awt.*;

public class Grid 
{

    private int width = 10; //the size of the entire game layout
    private int height = 10;
    private int tileSize = 40; // size of each slot that holds a gem
    
    private Tile[][] grid;
    private Gem[][] gemGrid;
    

    public Grid(){
	grid = new Tile[height][width];
	for (int y = 0; y < grid.length; y++){
	    for(int x = 0; x < grid[y].length;x++){
		grid[y][x] = new Tile((x*tileSize),(y*tileSize),tileSize,tileSize); //this places the Tiles in correct location on GUI
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


}
