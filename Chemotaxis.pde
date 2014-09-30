Bacteria[] colony;
Food mold; 

//setup variables
int scrnSz = 400;

void setup()
{
  size(scrnSz, scrnSz);
  frameRate(30);

  colony = new Bacteria[20];
  for (int i = 0; i < colony.length; i++)
  {
    colony[i] = new Bacteria ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz));
  }

  mold = new Food ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz));
}

int bactAlive, bactDead;
void draw()
{
  background(160, 100, 50);
  bactAlive = 0;
  bactDead = 0;
  for (int i = 0; i < colony.length; i++)
  {
    colony[i].move(mold.myX, mold.myY);
    colony[i].eating(mold.myX, mold.myY);
    colony[i].deadFromStarvation();
    if (colony[i].live == true)
    {
      bactAlive++;
    } else
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

  fill(255);
  text("Bacteria left: " + bactAlive, scrnSz-60, scrnSz-20);
  text("Bacteria dead: " + bactDead, 460-scrnSz, scrnSz-20);
}

void mousePressed()
{
  mold = new Food (mouseX, mouseY);
}

void keyPressed()
{
  //resets the bacteria
  if (key == 'r')
  {
    colony = new Bacteria[20];
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

  void move()
  {
    myX = myX + (int)(Math.random()*3)-1;
    myY = myY + (int)(Math.random()*3)-1;
  }

  void eaten(int x, int y) //checks if it has been eaten. if so, respawn. else, get smaller.
  {
    if (mySize == 0)
    {
      //respawn food if eaten
      myX = (int)(Math.random()*scrnSz);
      myY = (int)(Math.random()*scrnSz);
      mySize = 50;
    } else if (myX == x && myY == y)
    {
      mySize--;
    }
  }

  void show()
  {
    noStroke();
    fill(0, 100);
    ellipse(myX, myY, mySize, mySize);
    textAlign(CENTER, CENTER);
    fill(255, 100);
    text("food", myX, myY);
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
    myClr = color(clrVal);
    mySz = 5;

    //life cycle 
    live = true;
  }

  void eating(int x, int y) //if the bacteria has reached the food, start changing color
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

    void deadFromStarvation() //when dies, set live to false 
    {
      if (int(timeStarving/(400+(amtFood*10))) > 0) //sort of like they get 400 "seconds" to get food *last slighty longer if they have eaten before
      {
        live = false;
      }
    }

    void move(int x, int y)
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
        } else if (myX == x)
        {
          myX = myX + ((int)(Math.random()*7)-3);
        } else
        {
          myX = myX + ((int)(Math.random()*3)-3);
        }
        // in the y direction
        if (myY <= y)
        {
          myY = myY + ((int)(Math.random()*5)-1);
        } else if (myY == y)
        {
          myY = myY + ((int)(Math.random()*7)-3);
        } else 
        {
          myY = myY + ((int)(Math.random()*3)-3);
        }
      }
    }

    void show()
    {
      myClr = color(clrVal);
      noStroke();
      fill(myClr);
      ellipse(myX, myY, mySz, mySz);
    }
  }