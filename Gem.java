import java.awt.*;

public class Gem
{
    private int type;
    private int xcor;
    private int ycor;
    private int curX;
    private int curY;


    public Gem () {
	type = 0;
	xcor = 0;
	ycor = 0;
    }

    public Gem (int val) {
	this();
	type = val;

    }
    public Gem (int val, int x, int y) {
	this(val);
	xcor = x;
	ycor = y;
	curX = xcor;
	curY = ycor;
    }
    
    public int getType() {
	return type;
    }

    public int getXcor() {return xcor;}
    public int getYcor() {return ycor;}
    public int getCurX() {return curX;}
    public int getCurY() {return curY;}

    public int setXcor(int val) {
	int oldXcor = xcor;
	xcor = val;
	return oldXcor;
    }    
    public int setYcor(int val) {
	int oldYcor = ycor;
	ycor = val;
	return oldYcor;
    }
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

    public void setCors(){
	curX += sgn(xcor - curX, 10);
	curY += sgn(ycor - curY, 10);
    }

    public int sgn(int val, int mag) {
	if (val > 0) {
	    return mag;
	}
	else if (val < 0) {
	    return 0-mag;
	}
	else {return 0;}	 
    }

    public void draw(Graphics g){
	g.drawImage(Screen.food[0],curX,curY,50,50,null);
    }

    public String toString() {
	return "" + type;
    }

    
}
