Bacteria[] colony;   
void setup()   
{     
	size (400, 400);
	colony = new Bacteria[100];
	for (int i = 0; i < colony.length; i++)
	{
		colony[i] = new Bacteria (200, 200);
	}
}

void draw()
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

	void move()
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

	void show()
	{
		fill (myClr);
		ellipse (myX, myY, 10, 10);
	}
}
