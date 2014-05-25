
public class Gem
{
    private int type;
    private int xcor;
    private int ycor;
    
    public Gem () {
	type = 0;
    }

    public Gem (int val) {
	type = val;
    }
    
    public int getType() {
	return type;
    }

    public int getXcor() {return xcor;}
    public int getYcor() {return ycor;}

    public Gem getGem(Grid gemGrid, int dx, int dy) {
	return gemGrid.getGem(xcor + dx, ycor + dy);
    }

    public Gem getLeft(Grid gemGrid) {
	return gemGrid.getGem(xcor - 1, ycor);
    }
    public Gem getRight(Grid gemGrid) {
	return gemGrid.getGem(xcor + 1, ycor);
    }
    public Gem getUp(Grid gemGrid) {
	return gemGrid.getGem(xcor, ycor - 1);
    }
    public Gem getDown(Grid gemGrid) {
	return gemGrid.getGem(xcor, ycor + 1);
    }
    

    public void removeSelf (Grid gemGrid) {
	gemGrid.setNull(xcor, ycor);
    }

    public boolean equals(Gem obj) {
	return type == obj.getType();
    }

    public boolean isCombo(Grid g) {
	if (this.equals(getLeft(g) ) ) {
	    if (getLeft(g).equals(getLeft(g).getLeft(g))) {
		return true;
	    }
	    else if (this.equals(getRight(g) ) ) {
		return true;
	    }
	}
	else if ( (this.equals(getUp(g) ) ) ){
	    if (getUp(g).equals(getUp(g).getUp(g) ) ) {
		return true;
	    }
	    else if (this.equals(getDown(g) ) ) {
		return true;
	    }
	}
	return false;
    }

    
}
