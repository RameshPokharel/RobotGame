package myCustomPackage;

import java.awt.Image;
import java.awt.Rectangle;




public class MyTile {

	private int tileX, tileY, speedX, type;
	public Image tileImage;

	private Robot robot = start.getRobot();
	private Background bg = start.getBg1();

	private Rectangle r;
	public static Boolean ch= false; 

	    public MyTile(int x, int y, int typeInt) {
	        tileX = x * 40;
	        tileY = y * 40;
	        type = typeInt;
	        r= new Rectangle(0,0, 0,0);
	        if (type == 5) {
				tileImage = start.tiledirt;
			} else if (type == 8) {
				tileImage = start.tilegrassTop;
			} else if (type == 4) {
				tileImage = start.tilegrassLeft;

			} else if (type == 6) {
				tileImage = start.tilegrassRight;

			} else if (type == 2) {
				tileImage = start.tilegrassBot;
			} else {
				type = 0;
			}

	    }

	    public void update() {
	    	ch= false;
	    	speedX = bg.getSpeedX() * 5;
			tileX += speedX;
			r.setBounds(tileX, tileY, 40, 40);
			r.setBounds(tileX, tileY, 40, 40);
			
			if (r.intersects(Robot.yellowRed) && type != 0) {
				checkVerticalCollision(Robot.rect, Robot.rect2);
				checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft, Robot.footright);
			}
	    }

	    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
			if (rtop.intersects(r)) {
				robot.setJump(false);
				robot.setSpeedY(0);
				robot.setCentery(tileY + 103);
			}

			if (rbot.intersects(r) && type == 8) {
				robot.setJump(false);
				robot.setSpeedY(0);
				robot.setCentery(tileY - 63);
			}
		}

		public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot) {
			if (type != 5 && type != 2 && type != 0){
				if (rleft.intersects(r)) {
					robot.setCenterx(tileX + 102);
					robot.setSpeedX(0);
					
		
				}else if (leftfoot.intersects(r)) {
					robot.setCenterx(tileX + 85);
					robot.setSpeedX(0);
				}
				
				if (rright.intersects(r)) {
					robot.setCenterx(tileX - 62);
					robot.setSpeedX(0);
					ch=true;
				}
				
				else if (rightfoot.intersects(r)) {
					robot.setCenterx(tileX - 45);
					robot.setSpeedX(0);
					ch= true;
				}
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
	    
	    public void clear()
	    {

	    	 

	    	 robot = start.getRobot();
	    	 bg = start.getBg1(); ch= false; 

	    }

}
