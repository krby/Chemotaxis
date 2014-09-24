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

Bacteria[] colony;   
Food mold; 

//setup variables
int scrnSz = 400;

public void setup()   
{
	size(scrnSz, scrnSz);
	colony = new Bacteria[100];
	for (int i = 0; i < colony.length; i++)
	{
		colony[i] = new Bacteria ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz), (int)(Math.random()*255));
	}

	mold = new Food ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz)); 
        
}

public void draw()
{    
	background(100);
	for (int i = 0; i < colony.length; i++)
	{
		colony[i].move(mold.myX, mold.myY);
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
	int myX, myY;
	int myClr;
	Bacteria(int x, int y, int colorVal) 
	{
		myX = x;
		myY = y;
		myClr = color(colorVal, colorVal/2, 50);
	}

	public void move(int x, int y)
	{
		// myX = myX + (((int)(Math.random()*3))-1);
		// myY = myY + (((int)(Math.random()*3))-1);

		//follows mold
		if (myX <= x)
		{
			myX = myX + ((int)(Math.random()*5)-1)*(1/5);   //if mold is to right, go go right
		}
		else if (myX == x)
		{
			myX = myX + ((int)(Math.random()*5)-2)*(1/5);
		}
		else
		{
			myX = myX + ((int)(Math.random()*3)-3)*(1/5);	
		}
		// in the y direction
		if (myY <= y)
		{
			myY = myY + ((int)(Math.random()*5)-1)*(1/5);
		}
		else if (myY == y)
		{
			myY = myY + ((int)(Math.random()*5)-2)*(1/5);
		}	
		else 
		{
			myY = myY + ((int)(Math.random()*3)-3)*(1/5);
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
