package myCustomPackage;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Robot {
	private static int health;

	private int centerX = 61;
	private int centerY = 382;
	static int speedX = 0, speedY = 0;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	static boolean moveBackG = false;
	private boolean ducked = false;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect3 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect4 = new Rectangle(0, 0, 0, 0);
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);

	public static Rectangle footleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle footright = new Rectangle(0, 0, 0, 0);
	public static Rectangle fullRobot = new Rectangle(0, 0, 0, 0);

	public Rectangle r = new Rectangle(0, 0, 0, 0);
	private boolean readyToFire = true;

	public void update() {
		
		if (speedX < 0) {
			centerX += speedX;

		} else if (speedX == 0) {

		} else {
			if (centerX <= 200) {
				centerX += speedX;
			} else {
				start.bg1.setSpeedX(-speedX / 5);
				start.bg2.setSpeedX(-speedX / 5);
			}
		}

		centerY += speedY;
		speedY = 5;
		if (jumped == true) {
			centerY -= 20;
		}

		if (centerX + speedX <= 61) {
			centerX = 61;
			speedX = 0;
			start.bg1.setSpeedX(0);
			start.bg2.setSpeedX(0);
		}

		if (centerY + speedY + 12 > 400) {
			speedX = 0;
			start.bg1.setSpeedX(0);
			start.bg2.setSpeedX(0);

		}
		rect.setRect(centerX - 34, centerY - 63, 68, 63);
		rect2.setRect(rect.getX(), rect.getY() + 63, 68, 63);
		rect3.setRect(rect.getX() - 26, rect.getY() + 32, 26, 20);
		rect4.setRect(rect.getX() + 68, rect.getY() + 32, 26, 20);
		yellowRed.setRect(centerX - 110, centerY - 110, 180, 180);
		fullRobot.setRect(centerX -60, centerY - 64, 70, 125);
		if(isDuck())
		{
			fullRobot.setRect(centerX-60,centerY-11, 70, 60);
			
		}
//
		
		footleft.setRect(centerX - 50, centerY + 20, 50, 15);
		footright.setRect(centerX, centerY + 20, 50, 15);

	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public int getCenterx() {
		return centerX;
	}

	public void setCenterx(int x) {
		centerX = x;
	}

	public void setCentery(int y) {
		centerY = y;
	}

	public int getCentery() {
		return centerY;
	}

	public void moveRight() {
		speedX = 5;
	}

	public void moveLeft() {
		speedX = -5;
		moveBackG = false;

	}

	public void stop() {
		speedX = 0;
	}

	public void jump() {

		speedY = -15;
	}

	public void shoot() {
		if (readyToFire) {
			
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public void setJump(Boolean b) {
		this.jumped = b;
	}

	public void setDuck(Boolean b) {
		this.ducked = b;
	}

	public void setMovingLeft(Boolean b) {
		this.movingLeft = b;
	}

	public void setMmovingRight(Boolean b) {
		this.movingRight = b;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public boolean isJumped() {
		return jumped;
	}

	public boolean isDuck() {
		return ducked;
	}

	public boolean isMoveBackg() {
		return moveBackG;
	}

	public void setSpeedX(int x) {
		speedX = x;
	}

	public void setSpeedY(int y) {
		speedY = y;
	}

	public void clear() {
		centerX = 61;
		centerY = 382;
		speedX = 0;
		speedY = 0;
		jumped = false;
		movingLeft = false;
		movingRight = false;
		moveBackG = false;
		ducked = false;
		projectiles = new ArrayList<Projectile>();
		rect = new Rectangle(0, 0, 0, 0);
		rect2 = new Rectangle(0, 0, 0, 0);
		rect3 = new Rectangle(0, 0, 0, 0);
		rect4 = new Rectangle(0, 0, 0, 0);
		yellowRed = new Rectangle(0, 0, 0, 0);

		footleft = new Rectangle(0, 0, 0, 0);
		footright = new Rectangle(0, 0, 0, 0);
		r = new Rectangle(0, 0, 0, 0);
		readyToFire = true;
	}

}
