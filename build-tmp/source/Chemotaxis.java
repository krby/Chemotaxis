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
Food mold;   
public void setup()   
{
	size(400, 400);
	colony = new Bacteria[100];
	for (int i = 0; i < colony.length; i++)
	{
		colony[i] = new Bacteria (200, 200, (int)(Math.random()*255));
	}
	mold = new Food (200, 200);
}

public void draw()
{    
	background(100);
	for (int i = 0; i < colony.length; i++)
	{
		colony[i].move();
		colony[i].show();
	}
	mold.show();
}

public void mousePressed()
{
	mold = new Food (mouseX, mouseY);
}

class Food 
{
	int myX, myY;
	Food(int x, int y)
	{
		myX = x;
		myY = y;
	}

	public void move()
	{
		myX = myX + (int)(Math.random()*3)-1;
		myY = myY + (int)(Math.random()*3)-1;
	}

	public void show()
	{
		noStroke();
		fill(0);
		ellipse(myX, myY, 20, 20); 
	}
}

class Bacteria    
{     
	int myX, myY, myClr;
	Bacteria(int x, int y, int colorVal) 
	{
		myX = x;
		myY = y;
		myClr = color(colorVal, colorVal/2, 50);
	}

	public void move()
	{
		// myX = myX + (((int)(Math.random()*3))-1);
		// myY = myY + (((int)(Math.random()*3))-1);

		//follows mold
		if (myX <= mold.myX)
		{
			myX = myX + ((int)(Math.random()*3));   //if mold is to right, go go right
		}
		else if (myX == mold.myX)
		{
			myX = myX + ((int)(Math.random()*5)-2);
		}
		else
		{
			myX = myX + ((int)(Math.random()*3)-2);	
		}
		// in the y direction
		if (myY <= mold.myY)
		{
			myY = myY + ((int)(Math.random()*3));
		}
		else if (myY == mold.myY)
		{
			myY = myY + ((int)(Math.random()*5)-2);
		}	
		else 
		{
			myY = myY + ((int)(Math.random()*3)-2);
		}
	}

	public void show()
	{
		noStroke();
		fill(myClr);
		ellipse(myX, myY, 5, 5);
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
