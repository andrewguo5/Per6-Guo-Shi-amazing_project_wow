import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;


public class Screen extends JPanel implements Runnable{
    public static Grid grid;
    public static int myWidth, myHeight;
    private static boolean isFirst = true;
    public Thread thread = new Thread(this);


    public Screen(){
	thread.start();
    }

    public void paintComponent(Graphics g){
	if (isFirst){
	    myWidth = getWidth();
	    myHeight = getHeight();

	    isFirst = false;
	}
	
	g.setColor(new Color(0,0,0));
	g.fillRect(0,0,getWidth(),getHeight());

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
