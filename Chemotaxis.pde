Bacteria[] colony;   
Food mold; 

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

	mold = new Food ((int)(Math.random()*scrnSz), (int)(Math.random()*scrnSz)); 
        
}

void draw()
{    
	background(100);
	for (int i = 0; i < colony.length; i++)
	{
		colony[i].move(mold.myX, mold.myY);
		colony[i].show();
	}
        mold.show();
}

void mousePressed()
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
	int myX, myY;
	int myClr;
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
			myX = myX + ((int)(Math.random()*5)-1);   //if mold is to right, go go right
		}
		else if (myX == x)
		{
			myX = myX + ((int)(Math.random()*5)-2);
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
			myY = myY + ((int)(Math.random()*5)-2);
		}	
		else 
		{
			myY = myY + ((int)(Math.random()*3)-3);
		}
	}

	void show()
	{
		noStroke();
		fill(myClr);
		ellipse(myX, myY, 5, 5);
	}
}
