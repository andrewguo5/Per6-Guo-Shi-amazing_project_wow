import java.awt.*;

public class Tile extends Rectangle
{
    private int xcor;
    private int ycor;
    private int ID;
    
    public Tile(int xCor, int yCor, int width, int height){
	setBounds(xCor,yCor,width, height);//sets x and y coordinbates on GUI
	xcor = 0;
	ycor = 0;
    }

    public void draw(Graphics g){
	//	g.drawImage(Screen.back[0],x,y,width,height,null);
    }
    

    public int getXcor(){
	return xcor;
    }

    public int getYcor(){
	return ycor;
    }
    
    public void setID(int x){
	ID = x;
    }

    public int getID(){
	return ID;
    }

}
