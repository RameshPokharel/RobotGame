package myCustomPackage;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import superEnemy.BulletManage;

public class MyTile implements EnemyInterface {
	public ArrayList<enemyCheck> a;
	private int  currentHealth;
	Boolean isPerfect = false,isOutOfCover=false;
	public boolean setCrash=false;
	int initialX,initialY;
	private ArrayList<Projectile> projectiles ;

	public int tileX, tileY, speedX, type;
	public static int t;
	public Image tileImage;

	ManageEnemy me=start.me;
	private Robot robot = start.getRobot();
	private Background bg = start.getBg1();

	public static Rectangle r;
	int count=0;
	// public static Boolean ch= false;

	public MyTile(int x, int y, int typeInt) {
		currentHealth=maxHealth;
		tileX = x * 40;
		tileY = y * 40;
		type = typeInt;
		t=typeInt;
		a= new ArrayList<>();
		r = new Rectangle(0, 0, 0, 0);
		if(type==3)
		{
			me.em.add(new HeliBoy(tileX, tileY, 200));
			type=0;
			/*start.hca.addFrame(start.heliboy, 150);
			start.hca.addFrame(start.heliboy2, 150);
			start.hca.addFrame(start.heliboy3, 150);
			start.hca.addFrame(start.heliboy4, 150);
*/
		}
		else if (type == 5) {
			tileImage = start.bombWar;
		} else if (type == 8) {
			tileImage = start.tilegrassTop;
		} else if (type == 4) {
			tileImage = start.tree;

		} else if (type == 6) {
			tileImage = start.tilegrassRight;

		} else if (type == 2) {
			tileImage = start.tilegrassBot;
		} else if (type == 1) {
			tileImage = start.coin;
		} else {
			type = 0;
		}

	}

	public void update() {
		// ch= false;
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		if(type==5 && manageArray())
		{
			r.setBounds(tileX-4, tileY+5, myStaticClass.tileWidth, myStaticClass.tileHeight-5);

			count+=1;
			if(count>50)
			{
				count=0;
				a.add(new enemyCheck(tileX, tileY));
				
			}
			attack();
			if(getCurrentHealth()<0)
			{
				type=0;
			}

			
		}
		r.setBounds(tileX, tileY, myStaticClass.tileWidth, myStaticClass.tileHeight);
		if (r.intersects(Robot.yellowRed) && type != 0) {
			checkVerticalCollision(Robot.rectTop, Robot.rectBot);
			checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft, Robot.footright);
		}
	}

	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if(type!=1)
		{
		if (rtop.intersects(r)) {
			robot.setJump(false);
			robot.setSpeedY(0);
			robot.setCentery(tileY + myStaticClass.tileHeight + myStaticClass.robotCenterYValue);
		}

		if (rbot.intersects(r) && type == 8) {
			robot.setJump(false);
			robot.setSpeedY(0);
			robot.setCentery(tileY - myStaticClass.robotCenterYValue);
		}}
	}

	public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot) {

		if (type != 2 && type != 0 && type != 1 &&type != 4) {
			if (rleft.intersects(r)) {
				robot.setCenterx(tileX + 40 + myStaticClass.robotCenterXValue);
				robot.setSpeedX(0);

			} else if (leftfoot.intersects(r)) {
				robot.setCenterx(tileX + 40 + myStaticClass.robotCenterXValue);
				robot.setSpeedX(0);
			}

			if (rright.intersects(r)) {
				robot.setCenterx(tileX - myStaticClass.robotCenterXValue);
				robot.setSpeedX(0);

			}

			else if (rightfoot.intersects(r)) {
				robot.setCenterx(tileX - myStaticClass.robotCenterXValue);
				robot.setSpeedX(0);

			}
		}
		if(type==1)
		{
			// print GoldcOin at Robot Class And increase it 
			robot.GoldCoin+=1;
		setTileX(-200);
		}
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void clear() {

		robot = start.getRobot();
		bg = start.getBg1(); // ch= false;

	}

	@Override
	public Boolean isOutOfCoverage() {
		return isOutOfCover;
	}

	@Override
	public Boolean manageArray() {

		 if(
				 robot.fullRobot.getX()+700>tileX && tileX>robot.fullRobot.getX())

					isPerfect = true;
				else
					isPerfect = false;
				return isPerfect;	}

	@Override
	public void attack() {
	     if(robot.fullRobot.intersects(r)){
			if(robot.checkSwordBoolean)
			{
				currentHealth-=1;
			}
			
	}
	     projectiles=robot.getProjectiles();
			for(Projectile p:projectiles)	
			if(type==5){{
				if(p.getRect().intersects(r))
				{	currentHealth-=10;
				p.setVisible(false);;
				}
			}}
		}

	@Override
	public int getMaxHealth() {
			return 50;
	}

	@Override
	public int getCurrentHealth() {
		return currentHealth;
	}

	
}
