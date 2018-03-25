package myCustomPackage;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Robot {
	private static int health = 100;
	public static int GoldCoin = 0;
	Boolean checkSwordBoolean = false;

	private int centerX = 25;
	private int centerY = 400;
	static int speedX = 0, speedY = 0;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	static boolean moveBackG = false;
	private boolean ducked = false;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public static Rectangle rectTop = new Rectangle(0, 0, 0, 0);
	public static Rectangle rectBot = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect3 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect4 = new Rectangle(0, 0, 0, 0);
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);

	public static Rectangle footleft = new Rectangle(0, 0, 0, 0);
	public static Rectangle footright = new Rectangle(0, 0, 0, 0);
	public static Rectangle fullRobot = new Rectangle(0, 0, 0, 0);

	public Rectangle r = new Rectangle(0, 0, 0, 0);
	private boolean readyToFire = true;

	ArrayList<Image> imageArray;
	int currentFrame = 0;
	int check, xPos;
	Boolean move, fireCheck = false;
	Image currentImg;
	int x = 0, y, count, chkIntSword = 0, xSword = 0, countSword = 0;;
	private int chkInt;
	private int Swordint = 0;

	public Robot() {
		fireCheck = false;
		move = false;
		chkInt = 0;
		imageArray = new ArrayList<>();
	}

	public void addFrame(Image img) {

		imageArray.add(img);

	}

	public void update() {
		setImage();
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

		// start.sanim.giveAngle(centerX-61);
		if (centerX + speedX <= myStaticClass.robotCenterXValue) {
			centerX = myStaticClass.robotCenterXValue;
			speedX = 0;
			start.bg1.setSpeedX(0);
			start.bg2.setSpeedX(0);
		}

		if (centerY > 440) {
			speedX = 0;
			start.bg1.setSpeedX(0);
			start.bg2.setSpeedX(0);

		}
		rectTop.setRect(centerX - myStaticClass.robotCenterXValue, centerY - myStaticClass.robotCenterYValue,
				myStaticClass.tileWidth, myStaticClass.tileHeight);
		rectBot.setRect(rectTop.getX(), centerY + myStaticClass.robotCenterYValue, myStaticClass.tileWidth,
				myStaticClass.tileHeight);
		rect3.setRect(centerX - myStaticClass.robotCenterXValue, centerY - myStaticClass.robotCenterYValue, 2, 20);
		rect4.setRect(rectTop.getX() + myStaticClass.robotWidth - 4, rectTop.getY(), 4, myStaticClass.robotHeight - 20);
		yellowRed.setRect(centerX - myStaticClass.robotCenterXValue - 2, centerY - myStaticClass.robotCenterYValue - 2,
				myStaticClass.robotWidth + 2, myStaticClass.robotHeight + 2);
		fullRobot.setRect(centerX - myStaticClass.robotCenterXValue, centerY - myStaticClass.robotCenterYValue,
				myStaticClass.robotWidth - 20, myStaticClass.robotHeight);
		if (isDuck()) {
			fullRobot.setRect(centerX - 60, centerY - 11, 70, 60);

		}
		//

		footleft.setRect(centerX - myStaticClass.robotCenterXValue - 1, centerY, myStaticClass.robotCenterXValue,
				myStaticClass.robotCenterYValue - 10);
		footright.setRect(centerX, myStaticClass.robotCenterYValue, myStaticClass.robotCenterXValue,
				myStaticClass.robotCenterYValue);

	}

	public void setImage() {

		if (isReadyToFire() == true) {
			currentImg = imageArray.get(5);
		} else if (isDuck()) {
			currentImg = imageArray.get(6);
		} else {
			currentImg = imageArray.get(4);
		}
		if (isMovingRight() || chkInt > 0) {

			x += 1;
			if (x < 10) {
				{
					count += 1;
					if (count > 3) {
						xPos += 1;
						count = 0;
					}
				}
				currentImg = imageArray.get(0);

			} else if (x >= 10 && x < 20) {
				x += 1;

				count += 1;
				if (count > 5) {
					xPos += 2;
					count = 0;
				}

				currentImg = imageArray.get(1);
			} else if (x >= 20 && x < 30) {
				x += 1;

				count += 1;
				if (count > 5) {
					xPos += 2;
					count = 0;
				}
				currentImg = imageArray.get(2);

			} else if (x >= 30 && x < 40) {

				x += 1;

				count += 1;
				if (count > 5) {
					xPos += 2;
					count = 0;
				}
				currentImg = imageArray.get(3);

			} else if (x >= 40 && x < 50) {

				x += 1;

				count += 1;
				if (count > 5) {
					xPos += 1;
					count = 0;
				}
				currentImg = imageArray.get(4);

				chkInt = 0;
				setX(0);
			}
		}
		if (checkSwordBoolean || chkIntSword > 0) {

			xSword += 1;
			if (xSword < 10) {
				{
					countSword += 1;
					if (countSword > 3) {
						xPos += 1;
						countSword = 0;
					}
				}
				currentImg = imageArray.get(8);

			} else if (xSword >= 10 && xSword < 20) {
				xSword += 1;

				countSword += 1;
				if (countSword > 5) {
					xPos += 2;
					countSword = 0;
				}

				currentImg = imageArray.get(9);
			} else if (xSword >= 20 && xSword < 30) {
				xSword += 1;

				countSword += 1;
				if (countSword > 5) {
					xPos += 2;
					countSword = 0;
				}
				currentImg = imageArray.get(8);

			} else if (xSword >= 30 && xSword < 40) {

				xSword += 1;

				countSword += 1;
				if (countSword > 5) {
					xPos += 2;
					countSword = 0;
				}
				currentImg = imageArray.get(10);
				chkIntSword = 0;
				xSword = 0;
			}

		}

	}

	public void setX(int x) {
		this.x = x;
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
		{
			int y = centerY -4;
			if (isDuck()) {
				y = y + 15;
			}
			Projectile p = new Projectile(centerX + 19, y);
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

	public void setMovingRight(Boolean b, int checkInt) {
		this.movingRight = b;
		this.chkInt = checkInt;
		if (b == true)
			fireCheck = false;

	}

	public int chkInt() {
		return chkInt;
	}

	public int getSwordint() {
		return Swordint;
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

	public void setGoldCoin(int x) {
		GoldCoin = x;
	}

	public int getGoldCoin() {
		return GoldCoin;
	}

	public void setSpeedY(int y) {
		speedY = y;
	}

	public void clear() {
		health=100;
		centerX = myStaticClass.robotCenterXValue;
		centerY = myStaticClass.robotCenterYValue + 360;
		speedX = 0;
		speedY = 0;
		jumped = false;
		movingLeft = false;
		movingRight = false;
		moveBackG = false;
		ducked = false;
		setGoldCoin(0);
		projectiles = new ArrayList<Projectile>();
		rectTop = new Rectangle(0, 0, 0, 0);
		rectBot = new Rectangle(0, 0, 0, 0);
		rect3 = new Rectangle(0, 0, 0, 0);
		rect4 = new Rectangle(0, 0, 0, 0);
		yellowRed = new Rectangle(0, 0, 0, 0);

		footleft = new Rectangle(0, 0, 0, 0);
		footright = new Rectangle(0, 0, 0, 0);
		r = new Rectangle(0, 0, 0, 0);
		readyToFire = true;
	}

	public static int getHealth() {
		return health;
	}

	public void setRootDecrease(int i) {
		health += i;
		if (health > 100)
			health = 100;
		else if (health < 0)
			health = 0;
	}

	
	public Image getImage() {
		return currentImg;
	}

	public void setToSword(boolean b, int i) {
		checkSwordBoolean = b;
		if (b) {
			chkInt = 0;

		}
		Swordint = i;
	}

	public Boolean isSettoSword() {
		return checkSwordBoolean;
	}

}
