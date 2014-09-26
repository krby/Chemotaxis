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
	frameRate(30);

	colony = new Bacteria[100];
	for (int i = 0; i < colony.length; i++)
	{
		colony[i] = new Bacteria ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz));
	}

	mold = new Food ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz)); 
        
}

public void draw()
{    
	background(100);
	for (int i = 0; i < colony.length; i++)
	{
		colony[i].move(mold.myX, mold.myY);
		colony[i].fullOfFood(mold.myX, mold.myY);
		colony[i].show();
		mold.eaten(colony[i].myX, colony[i].myY); //checks if bacteria have gotten to mold
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
	int mySize;
	Food(int x, int y)
	{
		myX = x;
		myY = y;
		mySize = 50;
	}

	public void move()
	{
		myX = myX + (int)(Math.random()*3)-1;
		myY = myY + (int)(Math.random()*3)-1;
	}

	public void eaten(int x, int y) // checks if eaten, if it is, then respawn
	{
		if (mySize == 0)
		{
			//respawn
			myX = (int)(Math.random()*scrnSz);
			myY = (int)(Math.random()*scrnSz);
			mySize = 50;
		}
		else if (myX == x && myY == y)
		{
			mySize--;
		}
	}

	public void show()
	{
		noStroke();
		fill(0);
		ellipse(myX, myY, mySize, mySize); 
	}
}

class Bacteria    
{     
	int myX, myY;
	int myClr, clrVal;
	int mySz;
	Bacteria(int x, int y) 
	{
		myX = x;
		myY = y;
		clrVal = 255;
		myClr = color(clrVal);
		mySz = 5;
	}

	public void fullOfFood(int x, int y) //if the bacteria has reached the food, start changing color
	{
		if (myX == x && myY == y)
		{
			clrVal-=10;
			myClr = color(clrVal);
			mySz++;
		}
	}

	public void move(int x, int y)
	{
		//if it's at the mold, stay there
		if (myX == x && myY == y)
		{
			myX = x;
			myY = y;
		}
		//follows mold		
		if (myX <= x)
		{
			myX = myX + ((int)(Math.random()*5)-1);   //if mold is to right, go go right
		}
		else if (myX == x)
		{
			myX = myX + ((int)(Math.random()*7)-3);
		}
		else
		{
			myX = myX + ((int)(Math.random()*3)-3);	
		}
		// in the y direction
		if (myY <= y)
		{
			myY = myY + ((int)(Math.random()*5)-1);
		}
		else if (myY == y)
		{
			myY = myY + ((int)(Math.random()*7)-3);
		}	
		else 
		{
			myY = myY + ((int)(Math.random()*3)-3);
		}
	}

	public void show()
	{
		noStroke();
		fill(myClr);
		ellipse(myX, myY, mySz, mySz);
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
