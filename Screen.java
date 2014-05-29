import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;


public class Screen extends JPanel implements Runnable{
    public static Grid grid;
    public static int myWidth, myHeight;
    private static boolean isFirst = true;
    public Thread thread = new Thread(this);
    public static Image[] back;


    public Screen(){
	thread.start();
    }

    public void define(){
	back = new Image[30];
	grid= new Grid();


	for (int i = 0; i < back.length; i++){
	    back[i] = new ImageIcon("Res/sprites_column.png").getImage();
	    back[i] = createImage(new FilteredImageSource(back[i].getSource(),new CropImageFilter(0,50*i,50,50)));
									    
	}
    }

    public void paintComponent(Graphics g){
	if (isFirst){
	    myWidth = getWidth();
	    myHeight = getHeight();
	    define();

	    isFirst = false;
	}
	
	g.setColor(new Color(60,60,60));
	g.fillRect(0,0,getWidth(),getHeight());
	g.setColor(new Color(0,0,0));
	grid.draw(g);
    }

    public void run(){
	while(true){//continuous running
	    if(!isFirst){
		
	    }
	    repaint();
	    try{
		Thread.sleep(1);//slows running.
	    }catch(Exception e){}
	}
    }




}
