package superEnemy;

import java.awt.Rectangle;
import java.util.ArrayList;

import myCustomPackage.Robot;
import myCustomPackage.start;

public class SuperEnemyClass {

	private static int centerX, centerY;
	private Robot robot = start.getRobot();
	Float angle = 0.0f;

	Rectangle r;
	public static SuperAnimation sanim;
	int x = 700;
	int y = 20;
	Boolean b = false;
	ArrayList<BulletManage> ar;
	int check=1000;

	int count=0;
	public SuperEnemyClass(int x, int y) {
		this.x = x;
		this.y = y;
		r = new Rectangle(x, y, 3, 4);
		sanim = new SuperAnimation();
		ar= new ArrayList<>();
		count=0;
	}

	public void Update() {
		if (x >= 700 || b == false) {
			b = false;
			if (x <= 0)
				b = true;
			x -= 5;
		}

		else if (x < 0 || b) {
			b = true;
			if (x >= 700)
				b = false;
			x += 5;

		}
		centerX = x + 20;
		centerY = 40;
		r.setBounds(x, y, 20, 20);
		calculateAngle();
		sanim.checkImgnum();
		 addBullet() ;
	}

	private void addBullet() {

		/*count+=20;
		if(count>check)
		{
			count=0;
		ar.add(new BulletManage(centerX, centerY));
		}*/
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public ArrayList<BulletManage> getBulletArray() {
		return ar;
	}

	public static int getCenterX() {
		return centerX;
	}

	public void calculateAngle() {
		try {

			angle = (float) Math.toDegrees(Math.atan2(robot.getCentery() - centerY, robot.getCenterx() - centerX));
			sanim.setAngle(angle);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
