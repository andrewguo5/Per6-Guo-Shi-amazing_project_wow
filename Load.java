import java.io.*;
import java.util.*;

public class Load{
    public void load(File loadPath){
	try{
	    Scanner loadScanner = new Scanner(loadPath);
	    while (loadScanner.hasNext()){
		for (int y = 0; y < Screen.grid.grid.length;y++){
		    for (int x = 0; x < Screen.grid.grid[y].length; x++){
			Screen.grid.grid[y][x] = loadScanner.nextInt();
		    }
		}
	    }
	    loadScanner.close();
	}catch(Exception e){}
    }
}
