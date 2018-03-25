package RobotPackage;

public class bulletClas {

	int x, y;
	int centerX, centerY;

	public bulletClas(int x, int y) {
		this.x = x;
		this.y = y;
		centerX=x+20;
		centerY=y+40;
	}
	public void update() {
		centerX+=100;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY()

	{
		return centerY;
	}
	
}
