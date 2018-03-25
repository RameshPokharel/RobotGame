package RobotPackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import myCustomPackage.start;

public class RobotAnimation {
	ArrayList<Image> imageArray;
	int currentFrame = 0;
	int check, xPos;
	Boolean move, fireCheck = false;
	Image currentImg;
	int x = 0, y, count;
	private int chkInt;
	

	public RobotAnimation() {
		fireCheck = false;
		move = false;
		chkInt = 0;
		imageArray = new ArrayList<>();
	}

	public void addFrame(Image img) {

		imageArray.add(img);

	}

	public void update() {
		SetImage();

	}

	public void SetImage() {
		if (isReadyToFire() == true) {
			currentImg = imageArray.get(5);
		}
		else
		{ 
			currentImg=imageArray.get(4);
		}
		if (getMove() || chkInt > 0) {

			x += 1;
			if (x < 25) {
				{
					count += 1;
					if (count > 10) {
						xPos += 5;
						count = 2;
					}
				}
				currentImg = imageArray.get(0);
				System.out.println("first loop x :" + x);

			} else if (x >= 50 && x < 100) {
				x += 1;

				count += 1;
				if (count > 25) {
					xPos += 5;
					count = 2;
				}

				currentImg = imageArray.get(1);
				System.out.println("sec loop x :" + x);
			} else if (x >= 100 && x < 150) {
				x += 1;

				count += 1;
				if (count > 15) {
					xPos += 5;
					count = 3;
				}
				currentImg = imageArray.get(2);
				System.out.println("th loop x :" + x);

			} else if (x >= 150 && x < 250) {

				System.out.println("at last");
				x += 1;

				count += 1;
				if (count > 15) {
					xPos += 4;
					count = 3;
				}
				currentImg = imageArray.get(3);
				System.out.println("fo loop x :" + x);

			} else if (x >= 250 && x < 280) {

				System.out.println("at last");
				x += 1;

				count += 1;
				if (count > 10) {
					xPos += 1;
					count = 3;
				}
				currentImg = imageArray.get(4);
				System.out.println("fo loop x :" + x);

				chkInt = 0;
				setX(0);
			}

		}
		

	}

	public Image getImage() {
		return currentImg;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setMove(boolean b, int checkInt) {
		this.move = b;
		this.chkInt = checkInt;
		if(b==true)
			fireCheck=false;
	}

	public boolean getMove() {
		return move;
	}

	public int chkInt() {
		return chkInt;
	}

	public int getX() {
		return xPos;
	}

	public void setReadyToFire(boolean fire) {
		this.fireCheck = fire;

	}

	public Boolean isReadyToFire() {
		return fireCheck;

	}

}
