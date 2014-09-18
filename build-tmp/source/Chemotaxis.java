import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Chemotaxis extends PApplet {

Bacteria [] colony;   
public void setup()   
{     
	size (400, 400);
	colony = new Bacteria[100];
	for (int i = 0; i < colony.length; i++)
	{
		colony[i] = new Bacteria (200, 200);
	}
}

public void draw()
{    
	background(100);
	for (int i = 0; i < colony.length; i++)
	{
		colony[i].move();
		colony[i].show();
	}

	ellipse (mouseX, mouseY, 50, 50);
}



class Bacteria    
{     
	int myX, myY, myClr;
	Bacteria(int x, int y) 
	{
		myX = x;
		myY = y;
		myClr = color (155);
	}

	public void move()
	{
		// myX = myX + (((int)(Math.random()*3))-1);
		// myY = myY + (((int)(Math.random()*3))-1);

		//follow mouse
		if (myX <= mouseX)
		{
			myX = myX + (((int)(Math.random()*3)));	
		}
		else
		{
			myX = myX + (((int)(Math.random()*3))-2);	
		}

		if (myY <= mouseY)
		{
			myY = myY + (((int)(Math.random()*3)));
		}
		else 
		{
			myY = myY + (((int)(Math.random()*3))-2);
		}


	}

	public void show()
	{
		fill (myClr);
		ellipse (myX, myY, 10, 10);
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Chemotaxis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
