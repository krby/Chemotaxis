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

//info: bacteria simulates bacteria by doing stuff. they chase food and eat it. the more they eat, the larger they grow. food respawns on its own
Bacteria[] colony;
Food mold;  

//setup variables
int scrnSz = 400;
int amtBact = 30;

public void setup()
{
  size(scrnSz, scrnSz);
  frameRate(30);

  colony = new Bacteria[amtBact];
  for (int i = 0; i < colony.length; i++)
  {
    colony[i] = new Bacteria ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz));
  }

  mold = new Food ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz));
}

int bactAlive, bactDead;
public void draw()
{
  background(160, 100, 50);
  bactAlive = 0;
  bactDead = 0;
  for (int i = 0; i < colony.length; i++)
  {
    colony[i].move(mold.myX, mold.myY);
    colony[i].eating(mold.myX, mold.myY);
    colony[i].dieBcNature();
    if (colony[i].live == true)
    {
      bactAlive++;
    } 
    else
    {
      bactDead++;
    }  
    colony[i].show();
    //System.out.println("time starving of " + i); //how long are they usually starving for?
    //System.out.println(colony[i].timeStarving);
    //System.out.println(colony[i].amtFood);
    mold.eaten(colony[i].myX, colony[i].myY); //checks if bacteria have eaten mold
    textAlign(CENTER, CENTER);
  }
  mold.show();

  fill(0);
  text("Bacteria left: " + bactAlive, scrnSz-60, scrnSz-20);
  text("Bacteria dead: " + bactDead, 460-scrnSz, scrnSz-20);

  if (bactDead == amtBact)
  {
    fill (0);
    text ("Press r to restart!", scrnSz/2, scrnSz/2);
  }
}

public void mousePressed()
{
  mold = new Food (mouseX, mouseY);
}

public void keyPressed()
{
  //resets the bacteria
  if (key == 'r')
  {
    colony = new Bacteria[amtBact];
    for (int i = 0; i < colony.length; i++)
    {
      colony[i] = new Bacteria ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz));
    }
  }
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

  public void eaten(int x, int y) //checks if it has been eaten. if so, respawn. else, get smaller.
  {
    if (mySize == 0)
    {
      //respawn food if eaten
      mySize = 50;
      myX = (int)(Math.random()*scrnSz);
      myY = (int)(Math.random()*scrnSz);
    } 
    else if (myX == x && myY == y)
    {
      mySize--;
    }
  }

  public void show()
  {
    noStroke();
    fill(0, 100);
    ellipse(myX, myY, mySize, mySize);
    textAlign(CENTER, CENTER);
    fill(255, 100);
    text("food", myX+1, myY-2);
  }
}

class Bacteria    
{     
  int myX, myY;
  int myClr, clrVal; //color
  int mySz;

  //related to life-cycle
  int timeStarving, amtFood;
  boolean live, eatingFood; //eatingFood used for debugging
  Bacteria(int x, int y) 
  {
    myX = x;
    myY = y;
    clrVal = 255;
    myClr = color(clrVal, sin(clrVal/2), clrVal+1);
    mySz = 5;

    //life cycle 
    live = true;
  }

  public void eating(int x, int y) //if the bacteria has reached the food, start changing color
  {
    if (live == true)
    {
      timeStarving++;
      {
        if (myX == x && myY == y)
        {
          clrVal-=10; //chnge color
          mySz++; //incr size
          amtFood++;
          timeStarving = 0;
        }
      }
    }
  }  
  public void dieBcNature() //when dies, set live to false 
  {
    if (PApplet.parseInt(timeStarving/(400+(amtFood*10))) > 0) //sort of like they get 400 "seconds" to get food *last slighty longer if they have eaten before
    {
      live = false;
    }
    if (amtFood >= 100)
    {
      live = false;
    }
  }

  public void move(int x, int y)
  {
    if (live == true)
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
        myX = myX + ((int)(Math.random()*5)-1);   //if mold is to right, go more towards right
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
  }

  public void show()
  {
    myClr = color(clrVal);
    stroke(100);
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
