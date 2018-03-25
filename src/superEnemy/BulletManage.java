package superEnemy;

import java.awt.Image;
import java.awt.Rectangle;

import myCustomPackage.Robot;
import myCustomPackage.start;

public class BulletManage {

	int bulletX, bulletY;
	Rectangle r;
	Robot rbt = start.getRobot();
	int rbtX, rbtY;
	Float deltaX, deltaY, angle;
	Image i;

	public BulletManage(int x, int y) {

		this.bulletX = x+15;
		this.bulletY = y+15;
		rbtX = rbt.getCenterx();
		rbtY = rbt.getCentery();
		deltaX = (float) (rbtX - bulletX);
		deltaY = (float) (rbtY - bulletY);
		angle = (float) Math.atan2(deltaY, deltaX);
		
	}

	public void update() {
		if(bulletX>=-50 || bulletY>800)
		{
		
		bulletX += 20 * Math.cos(angle);
		bulletY += 20 * Math.sin(angle);
		}
			
	}

	public int getCenterX() {
		return bulletX;
	}

	public int getcenterY() {
		return bulletY;
	}


}
