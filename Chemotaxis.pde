Bacteria[] colony;   
Food mold;   
void setup()   
{
	size(400, 400);
	colony = new Bacteria[100];
	for (int i = 0; i < colony.length; i++)
	{
		colony[i] = new Bacteria (200, 200, (int)(Math.random()*255));
	}
	mold = new Food (200, 200);
}

void draw()
{    
	background(100);
	for (int i = 0; i < colony.length; i++)
	{
		colony[i].move();
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
	int myX, myY, myClr;
	Bacteria(int x, int y, int colorVal) 
	{
		myX = x;
		myY = y;
		myClr = color(colorVal, colorVal/2, 50);
	}

	void move()
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

	void show()
	{
		noStroke();
		fill(myClr);
		ellipse(myX, myY, 5, 5);
	}
}
