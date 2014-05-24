import javax.swing.*;
import java.awt.*;

public class Driver extends JFrame{

    public static String title = "Gems are truly outrageous";
    public static Dimension size = new Dimension(800,800);

    public Driver(){
	setTitle(title);
	setSize(size);
	setResizable(false);
	setLocationRelevativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	init();
    }


    public void init(){
	setLayout(new GridLayout(1,1,0,0));
	Screen scr = new Screen();
	add(scr);
	setVisible(true);


    }

    public static void main(String[]args){
	Driver border = new Driver();
    }


}
