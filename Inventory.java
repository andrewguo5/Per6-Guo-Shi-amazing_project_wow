
public class Inventory {
    
    private int[] _inv;

    public Inventory () {
	_inv = new int[] {0,0,0,0,0,0,0,0,0,0};
    }

    public int[] getInventory () {
	return _inv;
    }
    public int setInventory (int n, int val) {
	try {
	    _inv[n] = val;
	    return val;
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    return -1;
	}
    }

    //Level is affected by the first slot in the inventory
    //The first three items will be different types of swaps
    //issue:
    //unoptimized lineswap needs to account for going off the edge on one side
    //g: calls the grid for grid methods
    //start: the start of the line being rotated (left or top)
    //vertical: whether the swap occurs in the up down direction or the left right direction
    //n: the total number of gems being swapped
    public void lineSwap (Grid g, Gem start, boolean vertical) {
	int cx = start.getXcor();
	int cy = start.getYcor();
	int n = _inv[0];
	Gem[] gemArray = new Gem[n];
	for (int i = 0; i < n; i++) {
	    if (vertical) {
		gemArray[i] = g.getGem(cx, cy+i);
	    }
	    else {
		gemArray[i] = g.getGem(cx+1, cy);
	    }
	}
	g.switchGems(n, gemArray);
	System.out.println("success!");
	//!!!function has not yet been tested.
    }

}
