Bacteria[] colony;   
ArrayList<Food> molds; 

//setup variables
int scrnSz = 400;

void setup()   
{
	size(scrnSz, scrnSz);
	colony = new Bacteria[100];
	for (int i = 0; i < colony.length; i++)
	{
		colony[i] = new Bacteria ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz), (int)(Math.random()*255));
	}

	molds = new ArrayList<Food>(); 
        molds.add(new Food((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz)));
}

void draw()
{    
	background(100);

	for (int i = 0; i < colony.length; i++)
	{
		colony[i].move(molds.get(0).myX, molds.get(0).myY);
		colony[i].show();
	}
        for (int i = 0; i < molds.size(); i++)
        {
	        Food aMold = molds.get(i);
                aMold.show();
        }
          System.out.println (molds.get(0).myX);
}

void mousePressed()
{
	molds.add(new Food(mouseX, mouseY)); 
}

class Food 
{
	int myX, myY;
	Food(int x, int y)
	{
		myX = x;
		myY = y;
	}

	void move()
	{
		myX = myX + (int)(Math.random()*3)-1;
		myY = myY + (int)(Math.random()*3)-1;
	}

	void show()
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

	void move(int x, int y)
	{
		// myX = myX + (((int)(Math.random()*3))-1);
		// myY = myY + (((int)(Math.random()*3))-1);

		//follows mold
		if (myX <= x)
		{
			myX = myX + ((int)(Math.random()*3));   //if mold is to right, go go right
		}
		else if (myX == x)
		{
			myX = myX + ((int)(Math.random()*5)-2);
		}
		else
		{
			myX = myX + ((int)(Math.random()*3)-2);	
		}
		// in the y direction
		if (myY <= y)
		{
			myY = myY + ((int)(Math.random()*3));
		}
		else if (myY == y)
		{
			myY = myY + ((int)(Math.random()*5)-2);
		}	
		else 
		{
			myY = myY + ((int)(Math.random()*3)-2);
		}
	}

	void show()
	{
		noStroke();
		fill(myClr);
		ellipse(myX, myY, 5, 5);
	}
}
