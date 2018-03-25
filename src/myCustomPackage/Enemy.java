package myCustomPackage;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy implements EnemyInterface{

	private int maxHealth, currentHealth, power, speedX, centerX, centerY;
	private Background bg = start.getBg1();
	Rectangle r;
	public int health = myStaticClass.enemyHealth;
	private int movementSpeed;
	private Robot robot = start.getRobot();

	Boolean isPerfect = false,isOutOfCover=false;
	int count = 6000;
	int in = 0;
	public ArrayList<enemyCheck> a;

	int time;
	public boolean setCrash=false;
	int initialX,initialY;

	public Enemy(int x, int y, int time) {
		initialX=x;
		initialY=y;
		centerX = x;
		centerY = y;
		this.time = time;
		r = new Rectangle(0, 0, 0, 0);
		a = new ArrayList<>();
		in = 0;
	}

	// Behavioral Methods
	public void update() {
		attack();
		if(health>0){
		try {
			follow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		centerX += speedX * 2;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		
		r.setBounds(centerX+5 - myStaticClass.heliBoyWidth/2, centerY +5-myStaticClass.heliBoyHeight/2, myStaticClass.heliBoyWidth, myStaticClass.heliBoyHeight-5);
		if(centerX+20<0|| centerX>myStaticClass.screenWidth+20)
		{
			isOutOfCover=true;
		}
		else
		{
			isOutOfCover=false;
		}
		addCheck();
		}
		else
		{
			setCrash=true;
			centerX=-100;
			r.setBounds(centerX +5- myStaticClass.heliBoyWidth/2, centerY +5-myStaticClass.heliBoyHeight/2, myStaticClass.heliBoyWidth, myStaticClass.heliBoyHeight-5);

		}
	}

	public Boolean isOutOfCoverage()
	{
		return isOutOfCover;
		
	}
	private void addCheck() {
		c();
	}

	public void c() {
		if(!isOutOfCoverage())
		{
		if (a.size() % 2== 0)
			in += 1500;
		else
			in +=100;
		if (in >= count) {
			in = in % count;
			if (manageArray())
				a.add(new enemyCheck(centerX - 20, centerY));
		}
		}
	}

	public Boolean manageArray() {
		 if(robot.fullRobot.getY()-10<=centerY &&
		 robot.fullRobot.getY()+robot.fullRobot.getHeight()+1>centerY)

			isPerfect = true;
		else
			isPerfect = false;
		return isPerfect;
	}

	public void follow() {

		if (centerX < -95 || centerX > 810) {
			movementSpeed = 0;
		}

		else if (Math.abs(robot.getCenterx() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (robot.getCenterx() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}

	public void die() {

	}

	public void attack() {
     if(robot.yellowRed.intersects(r)){
		if(robot.checkSwordBoolean)
		{
			health-=1;
			System.out.println("health: "+health);
		}
}
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return health;
	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

	public ArrayList<enemyCheck> myCheck() {

		return a;
	}

	public void clear(int x, int y) {
		centerX = x;
		centerY = y;
		a.clear();
		health=myStaticClass.enemyHealth;
		setCrash=false;
	}
	
}
