package myCustomPackage;

import java.awt.Rectangle;

public class enemyCheck {

	int x, y;
	Rectangle r = new Rectangle(0, 0, 0, 0);

	private Boolean visible;

	public enemyCheck(int x, int y) {
		this.x = x;
		this.y = y;
		r.setBounds(x - 10, y + 5, 2, 2);
		visible = true;

	}

	public void update() {
		x -= 10;
		r.setBounds(x, y+5, 2, 2);
		if (x < -40) {
			visible = false;
		} else {
			checkCollision();
		}
	}

	private void checkCollision() {

			
		if(r.intersects(start.getRobot().fullRobot))
		{
			visible = false;
			x=-100;
		start.getRobot().setRootDecrease(-1);
			
		}
		
	}

	public Boolean isVisible()
	{
		return visible;
	}
	public Rectangle getRect() {
		return r;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

}
